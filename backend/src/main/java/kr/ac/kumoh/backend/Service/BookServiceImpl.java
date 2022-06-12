package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.*;
import kr.ac.kumoh.backend.dto.ReserveMovieDTO;
import kr.ac.kumoh.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static kr.ac.kumoh.backend.domain.ResponseStatus.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final SeatRepository seatRepository;
    private final BookDetailsRepository bookDetailsRepository;
    private final MovieScheduleRepository movieScheduleRepository;

    @Override
    public ResponseStatus saveUserMovieReservation(ReserveMovieDTO reserveMovieDTO) {

        // Get Values from DTO
        String userId = reserveMovieDTO.getUserId();
        //
        String theaterName = reserveMovieDTO.getTheaterName();
        String screenName = reserveMovieDTO.getScreenName();
        String screenTime = reserveMovieDTO.getScreenTime();
        String movieName = reserveMovieDTO.getMovieName();
        //
        int numOfPeople = reserveMovieDTO.getNumOfPeople();
        int price = reserveMovieDTO.getPrice();
        List<String> rows = reserveMovieDTO.getRows();
        List<Integer> columns = reserveMovieDTO.getColumns();

        // find User by UserId
        System.out.println("userId = " + userId);
        User findUser = userRepository.findByUserId(userId);
        if (isNull(findUser))
            return NonExistUser;

        // find MovieSchedule
        MovieSchedule movieSchedule = movieScheduleRepository.getMovieSchedule(movieName, theaterName, screenName, screenTime);
        if (isNull(movieSchedule))
            return NonExistMovie;

        // Save Book Info
        Book book = new Book(numOfPeople, price);
        bookRepository.save(book);

        // Save Seat Info
        for (int i = 0; i < numOfPeople; i++) {
            Seat seat;
            try {
                seat = new Seat(rows.get(i), columns.get(i));
            } catch (NullPointerException e) {
                return CantSaveSeat;
            }

            book.addSeat(seat);
            seatRepository.save(seat);
        }
        movieSchedule.subRemainingSeat(numOfPeople);

        // Save BookDetails Info
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBook(book);
        bookDetails.setUser(findUser);
        bookDetails.setMovieSchedule(movieSchedule);
        bookDetailsRepository.save(bookDetails);
        movieScheduleRepository.save(movieSchedule);
        return Success;
    }
}
