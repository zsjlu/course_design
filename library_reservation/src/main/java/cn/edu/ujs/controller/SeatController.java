package cn.edu.ujs.controller;

import cn.edu.ujs.entity.Seat;
import cn.edu.ujs.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by DELL on 2017/12/25.
 */
@RestController
public class SeatController {


    @Autowired
    SeatService seatService;

    @RequestMapping(value = "/save/{readingRoomId}/{seatId}")
    public void save(@PathVariable String readingRoomId, @PathVariable String seatId) {
        Seat seat = new Seat();
        seat.setReadingRoomId(readingRoomId);
        seat.setSeatId(seatId);
        seatService.save(seat);
    }

    @GetMapping(value = "/updateSeatStatus")
    public void updateSeatStatus(@RequestParam("seatId") String seatId, @RequestParam("seatStatus") Integer seatStatus) {
        seatService.updateSeatStatus(seatId,seatStatus);
    }

}
