package com.joanne.demo.repository

import com.joanne.demo.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<Users, Long> {
    fun findByAccount(account: String):Users?
}