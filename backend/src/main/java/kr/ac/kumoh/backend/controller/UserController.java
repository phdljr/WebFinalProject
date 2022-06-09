package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.UserService;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.*;
import kr.ac.kumoh.backend.repository.BookDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Success: 로그인 성공
     * Fail: 아이디 또는 비밀번호를 잘못 입력
     */
    @PostMapping("/login")
    public StatusOfUser login(@RequestBody UserDTO userDTO) {

        return userService.login(userDTO.getId(), userDTO.getPw());
    }

    @PostMapping("/loginSuccess")
    public UserInfoDTO loginSuccess(@RequestBody OneParamDTO param) {

        UserInfoDTO userInfo = userService.getUserInfo(param.getParam());
        return userInfo;
    }

    @PostMapping("/reservations")
    public List<UserBookDetailsDTO> userBookDetailsDTOS(@RequestBody OneParamDTO oneParamDTO) {

        return userService.getUserMovieReservations(oneParamDTO.getParam());
    }

    @PostMapping("/register")
    public StatusOfUser register(@RequestBody RegisterDTO registerDTO) {

        return userService.register(registerDTO);
    }

    @GetMapping("/duplicated/{userId}")
    public StatusOfUser checkDuplicatedId(@PathVariable String userId) {

        return userService.checkIfIdDuplicated(userId);
    }
}
