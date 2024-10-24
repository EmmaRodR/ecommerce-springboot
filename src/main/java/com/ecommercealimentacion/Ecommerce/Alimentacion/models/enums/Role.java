package com.ecommercealimentacion.Ecommerce.Alimentacion.models.enums;

import java.util.List;

public enum Role {

    CUSTOMER(List.of(Permission.READ_ALL_PRODUCTS,Permission.READ_ALL_CATEGORIES,Permission.USE_CART)),


    ADMIN(List.of(Permission.READ_ALL_PRODUCTS,Permission.MODIFY_ALL_PRODUCT,Permission.READ_ALL_CATEGORIES,Permission.MODIFY_ALL_CATEGORIES,Permission.USE_CART));


    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
