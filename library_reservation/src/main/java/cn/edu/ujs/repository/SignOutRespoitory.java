package cn.edu.ujs.repository;

import cn.edu.ujs.entity.SignOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by DELL on 2017/12/28.
 */
@Component
public interface SignOutRespoitory extends JpaRepository<SignOut,Integer> {


}
