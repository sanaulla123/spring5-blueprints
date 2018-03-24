package com.packt.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packt.blog.model.BlogUser;

public interface BlogUserRepository extends JpaRepository<BlogUser, String> {

}
