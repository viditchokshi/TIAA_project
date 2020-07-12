package com.SpringSignUp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringSignUp.Model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
 
 User findByEmail(String email);
}
