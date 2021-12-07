package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.InvalidAdminInformationException;

import java.util.Base64;

public class Admin{

    private final static String username = "admin";
    private final static String password = "admin";

    public static void isAuthorized(String header){
        if(header != null){
            String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(header.substring("Basic ".length())));
            String headerUsername = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
            String headerPassword = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":"));

            if(!headerUsername.equals(username) || !headerPassword.equals(password)){
                throw new InvalidAdminInformationException("You don't have access to this feature");
            }
        }
    }
}
