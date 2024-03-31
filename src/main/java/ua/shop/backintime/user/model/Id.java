package ua.shop.backintime.user.model;

public class Id {
    private final Long id;
    public Id(long id) {
        if (id > 0){
            this.id = id;
        } else {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
    }
}
