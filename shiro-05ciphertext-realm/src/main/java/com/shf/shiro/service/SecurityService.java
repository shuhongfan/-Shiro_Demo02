package com.shf.shiro.service;

import java.util.Map;

public interface SecurityService {
    Map<String,String> findPasswordByLoginName(String loginName);
}
