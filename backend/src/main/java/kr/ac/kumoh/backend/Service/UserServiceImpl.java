package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.domain.User;
import kr.ac.kumoh.backend.dto.RegisterDTO;
import kr.ac.kumoh.backend.dto.UserInfoDTO;
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

    @Override
    public StatusOfUser login(String userId, String userPw) {

        User loginUser = userRepository.findByUserIdAndUserPw(userId, userPw);

        StatusOfUser result;
        if (isNull(loginUser))
            result = StatusOfUser.Fail;
        else
            result = StatusOfUser.Success;

        return result;
    }

    @Override
    public UserInfoDTO getUserInfo(String userId) {

        User findUser = userRepository.findByUserId(userId);
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .age(findUser.getAge())
                .gender(findUser.getGender())
                .build();

        return userInfoDTO;
    }

    @Override
    public StatusOfUser register(RegisterDTO registerDTO) {

        StatusOfUser result;
        try {
            User user = User.builder()
                    .userId(registerDTO.getId())
                    .userPw(registerDTO.getPw())
                    .age(registerDTO.getAge())
                    .gender(registerDTO.getGender())
                    .build();

            userRepository.save(user);
            result = StatusOfUser.Success;
        } catch (Exception e) {
            result = StatusOfUser.Fail;
        }

        return result;
    }

    @Override
    public StatusOfUser checkIfIdDuplicated(String userId) {

        User duplicatedIdUser = userRepository.findByUserId(userId);

        StatusOfUser result;
        if (isNull(duplicatedIdUser))
            result = StatusOfUser.Success;
        else
            result = StatusOfUser.Duplicated;

        return result;
    }
}
