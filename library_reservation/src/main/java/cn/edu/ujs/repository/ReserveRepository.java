package cn.edu.ujs.repository;

import cn.edu.ujs.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2017/12/26.
 */
@Component
public interface ReserveRepository extends JpaRepository<Reserve,Integer>{

    public List<Reserve> findByUserId(String userId);

    public Reserve findByUserIdAndReserveTime(String userId, Date reserveTime);

    public Reserve findByReserveTimeAndSeatId(Date reserveTime, String seatId);

    public Reserve findByReserveTimeStartingWith(Date reserveTime);

    public Reserve findByReserveTimeGreaterThan(Date reserveTime);

    public List<Reserve> findByReserveTimeBetween(Date startTime, Date endTime);

    public Reserve findByUserIdAndReserveTimeBetween(String userId, Date startTime, Date endTime);

    public List<Reserve> findBySeatIdAndReserveTimeBetween(String seatId, Date startTime, Date endTime);

    public List<Reserve> findByReserveTime(Date date);

    public List<Reserve> findBySeatId(String seatId);
}
