package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.ResponseStatus;
import kr.ac.kumoh.backend.dto.RegisterDTO;
import kr.ac.kumoh.backend.dto.UserBookDetailsDTO;
import kr.ac.kumoh.backend.dto.UserInfoDTO;

import java.util.List;

public interface UserService {

    ResponseStatus login(String id, String pw);

    ResponseStatus register(RegisterDTO registerDTO);

    ResponseStatus checkIfIdDuplicated(String id);

    UserInfoDTO getUserInfo(String userId);

    List<UserBookDetailsDTO> getUserMovieReservations(String userId);
}
