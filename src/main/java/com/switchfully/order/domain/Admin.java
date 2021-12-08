package com.switchfully.order.domain;

import com.switchfully.order.domain.exceptions.InvalidAdminInformationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class Admin{

    private final static String username = "admin";
    private final static String password = "admin";



    public static boolean isAuthorized(String header){
        if(header != null){
            String decodedUsernameAndPassword = new String(Base64.getDecoder().decode(header.substring("Basic ".length())));
            String headerUsername = decodedUsernameAndPassword.substring(0, decodedUsernameAndPassword.indexOf(":"));
            String headerPassword = decodedUsernameAndPassword.substring(decodedUsernameAndPassword.indexOf(":")+1);

            if(headerUsername.equals("admin") && headerPassword.equals("admin")){
                return true;
            }
        }
        throw new InvalidAdminInformationException("You don't have access to this feature");
    }
}
