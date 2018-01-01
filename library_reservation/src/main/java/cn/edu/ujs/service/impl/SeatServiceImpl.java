package cn.edu.ujs.service.impl;

import cn.edu.ujs.entity.Seat;
import cn.edu.ujs.repository.SeatRepository;
import cn.edu.ujs.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2017/12/25.
 */
@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void updateSeatStatus(String seatId, Integer seatStatus) {
        Seat seat = seatRepository.findOne(seatId);
        seat.setSeatStatus(seatStatus);
        seatRepository.save(seat);
    }

    @Override
    public Seat findOne(String seatId) {
        return seatRepository.findOne(seatId);
    }

    @Override
    public List<Seat> findBySeatStatus(Integer code) {
        return seatRepository.findBySeatStatus(code);
    }

    @Override
    public List<Seat> findBySeatStatusNot(Integer seatStatus) {
        return seatRepository.findBySeatStatusNot(seatStatus);
    }
}
