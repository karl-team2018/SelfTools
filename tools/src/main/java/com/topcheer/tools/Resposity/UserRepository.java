package com.topcheer.tools.Resposity;

import com.topcheer.tools.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String name);//错误经历，findByName
}
