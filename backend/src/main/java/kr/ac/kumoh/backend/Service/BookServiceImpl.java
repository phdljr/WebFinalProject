package kr.ac.kumoh.backend.Service;

import kr.ac.kumoh.backend.domain.Book;
import kr.ac.kumoh.backend.domain.Seat;
import kr.ac.kumoh.backend.dto.ReserveMovieDTO;
import kr.ac.kumoh.backend.repository.BookRepository;
import kr.ac.kumoh.backend.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final SeatRepository seatRepository;

    @Override
    public void saveUserMovieReservation(ReserveMovieDTO reserveMovieDTO) {

        int numOfPeople = reserveMovieDTO.getNumOfPeople();
        int price = reserveMovieDTO.getPrice();
        List<String> rows = reserveMovieDTO.getRows();
        List<Integer> columns = reserveMovieDTO.getColumns();

        Book book = new Book(numOfPeople, price);
        bookRepository.save(book);

        for (int i = 0; i < numOfPeople; i++) {
            Seat seat = new Seat(rows.get(i), columns.get(i));
            book.addSeat(seat);

            seatRepository.save(seat);
        }
    }
}
