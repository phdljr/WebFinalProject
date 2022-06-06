package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Book;
import kr.ac.kumoh.backend.domain.BookDetails;
import kr.ac.kumoh.backend.domain.Seat;
import kr.ac.kumoh.backend.domain.User;
import kr.ac.kumoh.backend.dto.ReserveMovieDTO;
import kr.ac.kumoh.backend.repository.BookDetailsRepository;
import kr.ac.kumoh.backend.repository.BookRepository;
import kr.ac.kumoh.backend.repository.SeatRepository;
import kr.ac.kumoh.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final SeatRepository seatRepository;
    private final BookDetailsRepository bookDetailsRepository;

    @Override
    public void saveUserMovieReservation(ReserveMovieDTO reserveMovieDTO) {

        // Get Values from DTO
        String userId = reserveMovieDTO.getUserId();
        int numOfPeople = reserveMovieDTO.getNumOfPeople();
        int price = reserveMovieDTO.getPrice();
        List<String> rows = reserveMovieDTO.getRows();
        List<Integer> columns = reserveMovieDTO.getColumns();

        // find User by UserId
        User findUser = userRepository.findByUserId(userId);

        // Save Book Info
        Book book = new Book(numOfPeople, price);
        bookRepository.save(book);

        // Save Seat Info
        for (int i = 0; i < numOfPeople; i++) {
            Seat seat = new Seat(rows.get(i), columns.get(i));
            book.addSeat(seat);

            seatRepository.save(seat);
        }

        // Save BookDetails Info
        BookDetails bookDetails = new BookDetails();
        bookDetails.setBook(book);
        bookDetails.setUser(findUser);
        bookDetailsRepository.save(bookDetails);
    }
}
