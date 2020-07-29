package com.yadu.securedapp.secured.spring.boot.app.repository;

import com.yadu.securedapp.secured.spring.boot.app.models.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredential, Long> {
}
