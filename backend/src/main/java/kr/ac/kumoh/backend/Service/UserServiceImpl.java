package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.LoginStatus;
import kr.ac.kumoh.backend.domain.User;
import kr.ac.kumoh.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public LoginStatus login(String id, String pw) {
        LoginStatus result;
        User loginUser = userRepository.findByIdAndPw(id, pw);

        if (isNull(loginUser))
            result = LoginStatus.Fail;
        else
            result = LoginStatus.Success;

        return result;
    }
}
