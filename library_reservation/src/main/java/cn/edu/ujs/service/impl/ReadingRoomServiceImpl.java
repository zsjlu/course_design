package cn.edu.ujs.service.impl;

import cn.edu.ujs.entity.ReadingRoom;
import cn.edu.ujs.repository.ReadingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DELL on 2018/1/6.
 */
@Service
public class ReadingRoomServiceImpl implements ReadingRoomService {

    @Autowired
    private ReadingRoomRepository readingRoomRepository;

    @Override
    public List<ReadingRoom> findAll() {
        return readingRoomRepository.findAll();
    }

    @Override
    public List<ReadingRoom> findByFloorId(String floorId) {
        return readingRoomRepository.findByFloorId(floorId);
    }
}
