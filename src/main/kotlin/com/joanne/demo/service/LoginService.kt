package com.joanne.demo.service

import com.joanne.demo.model.Users
import com.joanne.demo.repository.UserRepository
import com.joanne.demo.util.EncryptionUtils
import com.joanne.demo.vo.LoginInfo
import com.joanne.demo.vo.ResponseVo
import com.joanne.demo.vo.UserInfoVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginService(@Autowired val userRepository: UserRepository) {

    fun verifyUser(loginInfo: LoginInfo): ResponseVo<Any> {
        //check if user exsist, yes : check if password right, yes return 200
        //                      any no: return 403 unauthenticated
        val user = userRepository.findByAccount(loginInfo.account)
        if (user != null) {
            val password = EncryptionUtils.sha256(loginInfo.password)
            if (password.equals(user.password)) {
                val userInfo = UserInfoVo(user.id, user.username, user.account, user.roleId)
                return ResponseVo(200, "authenticated User", userInfo)
            }
        }
        return ResponseVo(403, "unauthenticated", null)
    }

}