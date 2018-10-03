package com.topcheer.tools.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
    //判定是否拥有权限的决策方法
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,InsufficientAuthenticationException{

    }

    @Override
    public boolean supports(ConfigAttribute attribute){
        return  true;
    }

    @Override
    public boolean supports(Class<?> classz) {
         return true;
    }
}
