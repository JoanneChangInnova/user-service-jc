package com.joanne.demo.controller

import com.joanne.demo.service.LoginService
import com.joanne.demo.vo.LoginInfo
import com.joanne.demo.vo.ResponseVo
import com.joanne.demo.vo.UserInfoVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class LoginController(@Autowired val loginService: LoginService) {

    @PostMapping("/login")
    fun login(@RequestBody loginInfo: LoginInfo): ResponseVo<Any> {
        return loginService.verifyUser(loginInfo)
    }
}