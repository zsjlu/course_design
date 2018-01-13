package cn.edu.ujs.service.impl;

import cn.edu.ujs.entity.ReadingRoom;

import java.util.List;

/**
 * Created by DELL on 2018/1/6.
 */
public interface ReadingRoomService {

    /**查询所有阅览室*/
    public List<ReadingRoom> findAll();

    /**根据阅楼层编号查询*/
    public List<ReadingRoom> findByFloorId(String floorId);
}
