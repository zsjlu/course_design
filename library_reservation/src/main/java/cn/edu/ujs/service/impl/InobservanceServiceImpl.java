package cn.edu.ujs.service.impl;

import cn.edu.ujs.entity.Inobservance;
import cn.edu.ujs.repository.InobservanceRepository;
import cn.edu.ujs.service.InobservanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DELL on 2017/12/29.
 */
@Service
public class InobservanceServiceImpl implements InobservanceService {

    @Autowired
    private InobservanceRepository inobservanceRepository;

    @Override
    public Inobservance findByUserId(String userId) {
        return inobservanceRepository.findByUserId(userId);
    }

    @Override
    public Inobservance save(Inobservance inobservance) {
        return inobservanceRepository.save(inobservance);
    }
}
