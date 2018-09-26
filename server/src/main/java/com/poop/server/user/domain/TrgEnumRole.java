package com.poop.server.user.domain;

public enum TrgEnumRole {
    NULL,
    ADMIN,
    USER;

    TrgEnumRole() {
    }

   public static TrgEnumRole toEnumRole(int r){
       return values()[r];
    }
}
