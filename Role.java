package com.ism.enums;

public enum Role {
    Boutiquier,Admin,Client;

    public static Role getValue(String role) {
        for (Role r : Role.values()) {
            if (r.name().compareToIgnoreCase(role) == 0) {
                return r;
            }
        }
        return null;
    }
}