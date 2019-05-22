package com.vng.chat.config;

import java.util.*;

public class SecurityConfig {

    private static final Map<String, List<String>> mapConfig = new HashMap<String,List<String>>();

    static {
        init();
    }

    private static void init(){
        List<String> urlPatterns;
        //add url pattern can see with role user
        urlPatterns = new ArrayList<String>();
        urlPatterns.add("/chat");
        urlPatterns.add("/user");
        mapConfig.put("ROLE_USER",urlPatterns);
        //add url pattern can see with role admin
        urlPatterns.clear();
        urlPatterns.add("/admin");
        mapConfig.put("ROLE_ADMIN",urlPatterns);
    }

    public Set<String> getAllAppRoles(){
        return mapConfig.keySet();
    }

    public List<String> getUrlPatternsForRole(String role){
        return mapConfig.get(role);
    }

}
