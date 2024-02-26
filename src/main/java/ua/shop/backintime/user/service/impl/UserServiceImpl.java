package ua.shop.backintime.user.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.shop.backintime.config.jwt.UserDetailsImpl;
import ua.shop.backintime.user.RoleEntity;
import ua.shop.backintime.user.UserEntity;
import ua.shop.backintime.user.UserRole;
import ua.shop.backintime.user.repository.RoleRepository;
import ua.shop.backintime.user.repository.UserRepository;
import ua.shop.backintime.user.service.UserService;
import ua.shop.backintime.user.service.dto.UpdateUserDto;
import ua.shop.backintime.user.service.dto.UserDto;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserIncorrectPasswordException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;
import ua.shop.backintime.user.service.mapper.UserMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Primary
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder encoder;
    @Autowired private UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        user.setLastLoginDateTime(LocalDateTime.now());
        return UserDetailsImpl.build(user);
    }

    @Override
    @Transactional
    public void registerUser(UserDto userDto, String password) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistException(userDto);
        }

        UserEntity user = new UserEntity(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                encoder.encode(password)
        );

        Set<RoleEntity> roleEntities = roleRepository.findByNames(Collections.singleton(UserRole.USER));
        user.setRoles(roleEntities);
        user.setLastUpdatedDate(LocalDate.now());
        user.setCreatedDate(LocalDate.now());
        userRepository.save(user);
    }
    @Override
    @Transactional
    public UserDto updateUser(Long userId, UpdateUserDto updateUserDto)
            throws UserNotFoundException, UserIncorrectPasswordException, UserAlreadyExistException {
        UserEntity user = findUserByEmail(updateUserDto.getOldEmail());
        if (userRepository.existsByEmail(updateUserDto.getNewEmail())) {
            throw new UserAlreadyExistException(updateUserDto);
        }
        if (encoder.matches(updateUserDto.getOldPassword(), user.getPassword())) {
            user.setEmail(updateUserDto.getNewEmail());
            user.setPassword(encoder.encode(updateUserDto.getNewPassword()));
            user.setLastUpdatedDate(LocalDate.now());
            user.setActiveToken(null);
            return userMapper.toUserDto(userRepository.save(user));
        } else {
            throw new UserIncorrectPasswordException(updateUserDto.getOldEmail());
        }
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toUserDtos(userRepository.findAll());
    }

    @Override
    public UserDto findByEmail(String email) {
        return userMapper.toUserDto(findUserByEmail(email));
    }
    @Override
    public void setLoggout(String email){
        UserEntity userEntity = findUserByEmail(email);
        userEntity.setActiveToken(null);
        userRepository.save(userEntity);
    }

    @Override
    public void login(String email, String token) {
        UserEntity userEntity = findUserByEmail(email);
        userEntity.setActiveToken(token);
        userRepository.save(userEntity);
    }

    @Override
    public boolean canLogin(String email, String token) {
        try {
            UserEntity user = findUserByEmail(email);

            if (!user.getLastLoginDateTime().plusDays(1).isAfter(LocalDateTime.now())) {
                user.setActiveToken(null);
            }

            return token.equals(user.getActiveToken());
        } catch (Exception e){
            return false;
        }
    }

    private UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}