package com.shf.shiro.service.impl;

import com.shf.shiro.service.SecurityService;


public class SecurityServiceImpl implements SecurityService {
    @Override
    public String findPasswordByLoginName(String loginName) {
        return "123";
    }
}
