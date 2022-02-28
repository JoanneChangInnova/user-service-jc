package com.joanne.demo.service

import com.joanne.demo.model.Users
import com.joanne.demo.repository.UserRepository
import com.joanne.demo.util.EncryptionUtils
import com.joanne.demo.vo.UserInfoVo
import com.joanne.demo.vo.ResponseVo
import com.joanne.demo.vo.UpdateUserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class UserService(@Autowired val userRepository: UserRepository) {

    fun addUser(userInfoVo: UserInfoVo?): ResponseVo<Any> {
        return try {
            checkField(userInfoVo?.username, userInfoVo?.account, userInfoVo?.password, userInfoVo?.roleId)
            var newUser = Users(
                userInfoVo?.username,
                userInfoVo?.account,
                EncryptionUtils.sha256(userInfoVo?.password),
                userInfoVo?.roleId
            )
            userRepository.save(newUser)
            ResponseVo(200, "Create new user successfully", newUser)
        } catch (e: Exception) {
            println("Failed to create new user, msg: ${e.message}")
            ResponseVo(400, "Failed to create new user", userInfoVo)
        }
    }

    fun checkField(username: String?, account: String?, password: String?, roleId: Long?) {
        val listOfNotNull = listOfNotNull(username, account, password, roleId)
//        val isAnyFieldNull = listOf(username,account,password,roleId).any { it == null }
        if (listOfNotNull.size != 4) {
            println("Not null fields are: " + listOfNotNull.forEach(::println))
            throw Exception("Parameters needed could not be null")
        }
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun updateUser(id: Long, updateUserInfo: UpdateUserInfo?): ResponseVo<Any> {
        val user = userRepository.findById(id)
        var updatedUser: UserInfoVo? = null
        //todo: using ifPresentOrElse()?
        if (user.isPresent) { // two ifPresent !?
            user.ifPresent {
                it.username = updateUserInfo?.username
                userRepository.save(it)
                updatedUser = UserInfoVo(it.id, it.account, it.username, it.password, it.roleId)
            }
            return ResponseVo(200, "Update user successfully", updatedUser)
        } else {
            return ResponseVo(400, "Failed to update user", null)
        }
    }

    fun findAll(): List<Users> {
        return userRepository.findAll()
    }
}