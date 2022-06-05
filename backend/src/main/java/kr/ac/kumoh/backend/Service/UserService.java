package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.LoginStatus;

public interface UserService {

    LoginStatus login(String id, String pw);
}
