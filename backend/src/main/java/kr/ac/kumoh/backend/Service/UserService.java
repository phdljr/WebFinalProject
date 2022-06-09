package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.RegisterDTO;
import kr.ac.kumoh.backend.dto.UserBookDetailsDTO;
import kr.ac.kumoh.backend.dto.UserInfoDTO;

import java.util.List;

public interface UserService {

    StatusOfUser login(String id, String pw);

    StatusOfUser register(RegisterDTO registerDTO);

    StatusOfUser checkIfIdDuplicated(String id);

    UserInfoDTO getUserInfo(String userId);

    List<UserBookDetailsDTO> getUserMovieReservations(String userId);
}
