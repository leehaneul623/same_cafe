package com.mysite.cafe.repository;

import com.mysite.cafe.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
