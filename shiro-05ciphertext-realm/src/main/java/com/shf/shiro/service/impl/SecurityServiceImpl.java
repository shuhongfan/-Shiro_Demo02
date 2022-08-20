package com.shf.shiro.service.impl;

import com.shf.shiro.service.SecurityService;
import com.shf.shiro.tools.DigestsUtil;

import java.util.Map;


public class SecurityServiceImpl implements SecurityService {
    @Override
    public Map<String,String> findPasswordByLoginName(String loginName) {
        return DigestsUtil.entryptPassword("123");
    }
}
