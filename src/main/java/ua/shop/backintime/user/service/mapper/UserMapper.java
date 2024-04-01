package ua.shop.backintime.user.service.mapper;

import org.springframework.stereotype.Service;
import ua.shop.backintime.user.RoleEntity;
import ua.shop.backintime.user.UserEntity;
import ua.shop.backintime.user.controller.request.DeliveryData;
import ua.shop.backintime.user.controller.request.UpdateUserRequest;
import ua.shop.backintime.user.controller.response.UserResponse;
import ua.shop.backintime.user.service.dto.UpdateUserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public ua.shop.backintime.user.service.dto.UserDto toUserDto(UserEntity user) {
        ua.shop.backintime.user.service.dto.UserDto dto = new ua.shop.backintime.user.service.dto.UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setDeliveryData(new DeliveryData(user.getTelephoneNumber(), user.getCityName(), user.getNPdepartment()));
        dto.setLastUpdatedDate(user.getLastUpdatedDate());
        dto.setCreatedDate(user.getCreatedDate());
        dto.setRoles(user.getRoles().stream()
                .map(RoleEntity::getName).collect(Collectors.toSet()));
        return dto;
    }

    public UpdateUserDto toUpdateUserDto(UpdateUserRequest request) {
        UpdateUserDto dto = new UpdateUserDto();
        dto.setOldPassword(request.getOldPassword());
        dto.setOldEmail(request.getOldEmail());
        dto.setNewPassword(request.getNewPassword());
        dto.setNewEmail(request.getNewEmail());
        return dto;
    }

    public UserResponse toUserResponse(ua.shop.backintime.user.service.dto.UserDto dto) {
        UserResponse response = new UserResponse();
        response.setId(dto.getId());
        response.setFirstName(dto.getFirstName());
        response.setLastName(dto.getLastName());
        response.setEmail(dto.getEmail());
        response.setDeliveryData(dto.getDeliveryData());
        response.setLastUpdatedDate(dto.getLastUpdatedDate());
        response.setCreatedDate(dto.getCreatedDate());
        response.setRoles(dto.getRoles());
        return response;
    }

    public List<UserResponse> toUserResponses(List<ua.shop.backintime.user.service.dto.UserDto> dtos) {
        if (dtos == null){
            return null;
        }
        return dtos.stream().map(this::toUserResponse).toList();
    }

    public List<ua.shop.backintime.user.service.dto.UserDto> toUserDtos(List<UserEntity> entities) {
        if (entities == null){
            return null;
        }
        return entities.stream().map(this::toUserDto).toList();
    }
}