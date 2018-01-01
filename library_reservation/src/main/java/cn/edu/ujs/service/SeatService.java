package cn.edu.ujs.service;

import cn.edu.ujs.entity.Seat;

import java.util.List;

/**
 * Created by DELL on 2017/12/25.
 */
public interface SeatService {

    /**添加(更新）一个座位*/
    Seat save(Seat seat);

    /**修改座位状态信息*/
    void updateSeatStatus(String SeatId, Integer seatStatus);

    /**根据座位号查询*/
    public Seat findOne(String seatId);

    /**根据座位状态查询*/
    public List<Seat> findBySeatStatus(Integer seatStatus);

    /**查询没有被释放的座位*/
    public List<Seat> findBySeatStatusNot(Integer seatStatus);


}
