package com.example.loan.interceptor;

import com.example.loan.service.RedisService;
import com.example.loan.utils.JwtUtils;
import com.example.loan.utils.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String name=null;
        String token=null;
        HttpSession session=request.getSession();
        name=(String)session.getAttribute("name");
        if(redisService.hasKey(name+":token")){
            token=(String) redisService.get(name+":token");
        }
        if("GET".equals(request.getMethod())){
            return true;
        }
        if(token==null|| !JwtUtils.checkToken(token)) {
            response.setContentType("application/json;charset=utf-8");
            Map<String, Object> result = new HashMap<>();
            result.put("code", 401);
            result.put("msg", "登录过期，请重新登录");
            result.put("data", null);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
            return false;
        }
        return true;
    }
}
