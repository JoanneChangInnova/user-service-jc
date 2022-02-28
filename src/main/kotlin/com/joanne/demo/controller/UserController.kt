package com.joanne.demo.controller

import com.joanne.demo.model.Users
import com.joanne.demo.service.UserService
import com.joanne.demo.vo.UserInfoVo
import com.joanne.demo.vo.ResponseVo
import com.joanne.demo.vo.UpdateUserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@CrossOrigin
class UserController(@Autowired val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<Users> = userService.findAll()

    @PostMapping
    fun addUser(@RequestBody userInfoVo: UserInfoVo): ResponseVo<Any> {
        return userService.addUser(userInfoVo)
    }

    @PatchMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updateUserInfo: UpdateUserInfo?): ResponseVo<Any> {
        return userService.updateUser(id, updateUserInfo)
    }

    @DeleteMapping("/{id}")
    fun removeUser(@PathVariable id: Long) = userService.deleteUser(id)

}