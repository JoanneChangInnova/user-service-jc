package com.joanne.demo.vo

data class UserInfoVo(
    val id : Long?,
    val name: String?,
    val account: String?,
    val role_id: Long?
    )
{
    val password:String?=null
}
