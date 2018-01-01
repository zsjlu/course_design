package cn.edu.ujs.repository;

import cn.edu.ujs.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DELL on 2017/12/25.
 */
@Component
public interface SeatRepository extends JpaRepository<Seat, String> {

    List<Seat> findBySeatStatus(Integer seatStatus);
    List<Seat> findByReadingRoomId(String readingRoomId);
    List<Seat> findBySeatStatusNot(Integer seatStatus);
}
