package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.*;
import kr.ac.kumoh.backend.dto.RegisterDTO;
import kr.ac.kumoh.backend.dto.UserBookDetailsDTO;
import kr.ac.kumoh.backend.dto.UserInfoDTO;
import kr.ac.kumoh.backend.repository.BookDetailsRepository;
import kr.ac.kumoh.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookDetailsRepository bookDetailsRepository;

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
        List<BookDetails> userMovieSchedules = bookDetailsRepository.getUserMovieSchedule(findUser.getUserId());

        List<UserBookDetailsDTO> userBookDetailsDTOS = new ArrayList<>();
        if (!isNull(userMovieSchedules)) {
            for (BookDetails userMovieSchedule : userMovieSchedules) {

                UserBookDetailsDTO userBookDetailsDTO = new UserBookDetailsDTO();

                MovieSchedule movie = userMovieSchedule.getMovieSchedule();
                Book book = userMovieSchedule.getBook();
                List<Seat> seats = book.getSeats();

                userBookDetailsDTO.setMovieName(movie.getMovie().getTitle());
                userBookDetailsDTO.setFloor(movie.getTheater().getFloor());
                userBookDetailsDTO.setScreen(movie.getTheater().getScreen());
                userBookDetailsDTO.setScreenDate(movie.getScreenDate());
                userBookDetailsDTO.setScreenTime(movie.getScreenTime());
                userBookDetailsDTO.setPrice(book.getPrice());
                userBookDetailsDTO.setNumOfPeople(book.getNumOfPeople());

                seats.forEach(seat -> {
                    String seatRow = seat.getSeatRow();
                    int seatColumn = seat.getSeatColumn();

                    userBookDetailsDTO.getRows().add(seatRow);
                    userBookDetailsDTO.getColumns().add(seatColumn);
                });

                userBookDetailsDTOS.add(userBookDetailsDTO);
            }
        } else {
            log.info("User가 예매한 내역이 없습니다!");
        }

        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .age(findUser.getAge())
                .gender(findUser.getGender())
                .userBookDetailsDTOS(userBookDetailsDTOS)
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
