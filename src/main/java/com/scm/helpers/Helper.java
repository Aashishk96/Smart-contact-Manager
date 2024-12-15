package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // if we logged in through email & password then how to retrieve email
        if(authentication instanceof OAuth2AuthenticationToken){

           var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
           var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

           var oauth2User = (OAuth2User)authentication.getPrincipal();
           String username ="";

           if(clientId.equalsIgnoreCase("google")){
            // sign in with google account
                System.out.println("Getting email from google account...");
                username = oauth2User.getAttribute("email").toString();
           }
           else if(clientId.equalsIgnoreCase("github")){
            // sign in with linkedin account
                System.out.println("Getting email from github account...");
                username = oauth2User.getAttribute("email") !=null ? 
                    oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString()+ "@gmail.com";
           }

        // sign in with github account

        // sign in with facebook account
           return username;
        }
        else{
            System.out.println("Getting data from local database...");
            return authentication.getName();
        }
       
    }
}
