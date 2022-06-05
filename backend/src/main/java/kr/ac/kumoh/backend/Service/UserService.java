package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.StatusOfUser;
import kr.ac.kumoh.backend.dto.RegisterDTO;

public interface UserService {

    StatusOfUser login(String id, String pw);

    StatusOfUser register(RegisterDTO registerDTO);

    StatusOfUser checkIfIdDuplicated(String id);
}
