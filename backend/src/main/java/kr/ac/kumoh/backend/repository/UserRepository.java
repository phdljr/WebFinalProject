package kr.ac.kumoh.backend.repository;

import kr.ac.kumoh.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserIdAndUserPw(String userId, String userPw);

    User findByUserId(String userId);
}
