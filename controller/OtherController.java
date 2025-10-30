package com.example.loan.controller;

import com.example.loan.service.UserInformationService;
import com.example.loan.utils.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/other")
public class OtherController {

    @Autowired
    private UserInformationService userInformationService;

    @GetMapping("/score")
    public ResponseResult<Map<String,Object>> getScore(HttpServletRequest request){
        HttpSession session=request.getSession();
        String name=(String)session.getAttribute("name");
        int score=userInformationService.getScore(name);
        Map<String,Object>map=new HashMap<>();
        map.put("name",name);
        map.put("creditScore",score);
        return ResponseResult.success(200,"success",map);
    }
}
