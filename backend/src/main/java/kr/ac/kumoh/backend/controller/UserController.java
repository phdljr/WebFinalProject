package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.UserService;
import kr.ac.kumoh.backend.domain.LoginStatus;
import kr.ac.kumoh.backend.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Success: 로그인 성공
     *    Fail: 아이디 또는 비밀번호를 잘못 입력
     */
    @PostMapping("/login")
    public LoginStatus login(@RequestBody UserDTO userDTO) {

        return userService.login(userDTO.getId(), userDTO.getPw());
    }
}
