package kr.ac.kumoh.backend.controller;

import kr.ac.kumoh.backend.Service.UserService;
import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.OneParamDTO;
import kr.ac.kumoh.backend.dto.RegisterDTO;
import kr.ac.kumoh.backend.dto.UserDTO;
import kr.ac.kumoh.backend.dto.UserInfoDTO;
import kr.ac.kumoh.backend.repository.BookDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookDetailsRepository repository;

    @GetMapping("/test")
    public void test() {
        repository.getUserMovieSchedule("id1");
    }

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

    @PostMapping("/register")
    public StatusOfUser register(@RequestBody RegisterDTO registerDTO) {

        return userService.register(registerDTO);
    }

    @GetMapping("/duplicated/{userId}")
    public StatusOfUser checkDuplicatedId(@PathVariable String userId) {

        return userService.checkIfIdDuplicated(userId);
    }
}
