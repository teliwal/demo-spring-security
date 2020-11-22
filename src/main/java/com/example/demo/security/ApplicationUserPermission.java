package com.example.demo.security;

public enum ApplicationUserPermission {

    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_HIRE("employee:hire"),
    EMPLOYEE_FIRE("employee:fire");

    private String permission;


    ApplicationUserPermission(String label) {
        this.permission = label;
    }

    public String getPermission() {
        return permission;
    }
}
