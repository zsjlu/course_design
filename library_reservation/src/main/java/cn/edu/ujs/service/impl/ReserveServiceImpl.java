package cn.edu.ujs.service.impl;

import cn.edu.ujs.entity.Reserve;
import cn.edu.ujs.repository.ReserveRepository;
import cn.edu.ujs.service.ReserveService;
import cn.edu.ujs.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2017/12/26.
 */
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveRepository reserveRepository;

    @Override
    public Reserve insert(Reserve reserve) {
        return reserveRepository.save(reserve);
    }

    @Override
    public List<Reserve> findByUserId(String userId) {
        return reserveRepository.findByUserId(userId);
    }

    @Override
    public Reserve findByUserIdAndReserveTime(String userId, Date reserveTime) {
        return reserveRepository.findByUserIdAndReserveTime(userId,reserveTime);
    }

    @Override
    public Reserve findByReserveTimeAndSeatId(Date reserveTime, String seatId) {
        return reserveRepository.findByReserveTimeAndSeatId(reserveTime,seatId);
    }

    @Override
    public Reserve findByReserveTimeStartingWith(Date reserveTime) {
        return reserveRepository.findByReserveTimeStartingWith(reserveTime);
    }

    @Override
    public Reserve findByReserveTimeGreaterThan(Date reserveTime) {
        return reserveRepository.findByReserveTimeGreaterThan(reserveTime);
    }

    @Override
    public List<Reserve> findByReserveTimeBetween(Date startTime, Date endTime) {
        return reserveRepository.findByReserveTimeBetween(startTime,endTime);
    }

    // TODO: 2017/12/30 需要将此返回类型改为List
    @Override
    public Reserve findByUserIdAndReserveTimeBetween(String userId, Date startTime, Date endTime) {
        return reserveRepository.findByUserIdAndReserveTimeBetween(userId, startTime, endTime);
    }

    @Override
    public List<Reserve> findBySeatIdAndReserveTimeBetween(String seatId, Date startTime, Date endTime) {
        return reserveRepository.findBySeatIdAndReserveTimeBetween(seatId, startTime, endTime);
    }

    @Override
    public List<Reserve> findByReserveTime(Date date) {
        return reserveRepository.findByReserveTime(date);
    }


}
