package com.joanne.demo.vo

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseVo<T>(
    var code: Int=444,
    var msg: String="failed",
    var data: T? //return a UserInfo if successfully login/add/update User, return UpdateUserInfo if fails
)
