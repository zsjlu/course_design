package cn.edu.ujs.service.impl;

import cn.edu.ujs.entity.SignOut;
import cn.edu.ujs.repository.SignOutRespoitory;
import cn.edu.ujs.service.SignOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DELL on 2017/12/28.
 */
@Service
public class SignOutServiceImpl implements SignOutService {

    @Autowired
    private SignOutRespoitory signOutRespoitory;

    @Override
    public SignOut update(SignOut signOut) {
        return signOutRespoitory.save(signOut);
    }
}
