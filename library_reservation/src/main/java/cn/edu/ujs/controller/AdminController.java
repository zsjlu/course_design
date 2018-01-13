package cn.edu.ujs.controller;

import cn.edu.ujs.VO.*;
import cn.edu.ujs.entity.Inobservance;
import cn.edu.ujs.entity.InobservanceType;
import cn.edu.ujs.entity.Reserve;
import cn.edu.ujs.enums.ResultEnum;
import cn.edu.ujs.mapper.*;
import cn.edu.ujs.service.BlacklistService;
import cn.edu.ujs.service.ReserveService;
import cn.edu.ujs.util.AdminUtil;
import cn.edu.ujs.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2018/1/8.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ReserveMapper reserveMapper;

    @Autowired
    private SignInMapper signInMapper;

    @Autowired
    private SignOutMapper signOutMapper;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private InobservanceTypeMapper inobservanceTypeMapper;

    @Autowired
    private InobservanceMapper inobservanceMapper;

    @RequestMapping(value = "/adminLogin")
    public ResultVO login(@RequestParam String userAccount,
                          @RequestParam String password) {

        ResultVO resultVO = null;
        boolean flag = userAccount.equals("admin")&&password.equals("admin");
        if (flag) {
            resultVO = ResultVOUtil.success(flag, ResultEnum.SUCCESS_LOGIN.getCode(),
                    ResultEnum.SUCCESS_LOGIN.getMessage());
            //session.setAttribute(userId,true);
            //request.getSession().setAttribute(userId,userId);
            //stringRedisTemplate.opsForValue().set(userId,userId);
            //httpSession.setAttribute("user",userId);
            //System.out.println(httpSession.getAttribute("user").toString()+"登录成功");
        }
        else {
            resultVO = ResultVOUtil.error(flag,ResultEnum.ERROR_LOGIN.getCode(),ResultEnum.ERROR_LOGIN.getMessage());
        }
        //System.out.println("\nhahahahahah"+request.getSession().getAttribute(userId).toString());
        return resultVO;
    }

    @RequestMapping(value = "/countCollege")
    public List countCollege() {
        return reserveMapper.countByCollege();
    }

    @RequestMapping(value = "/countDegree")
    public List countDegree() {
        return reserveMapper.countByDegree();
    }

    @RequestMapping(value = "/countSeat")
    public List countSeat() {
        return reserveMapper.countBySeat();
    }

    @RequestMapping(value = "/countReadingRoom")
    public List countReadingRoom() {
        return reserveMapper.countByReadingRoom();
    }

    @RequestMapping(value = "/countByWeek")
    public List countByWeek() {
        return reserveMapper.countByWeek();
    }

    @RequestMapping(value = "/countByTime")
    public List countByTime(@RequestParam String reserveTime) {
        return reserveMapper.countByTime(reserveTime);
    }

    @RequestMapping(value = "/reserveRecord")
    public List<ReserveVO> getReserveRecord() {
        List<ReserveVO> reserveVOList = reserveMapper.getReserveRecord();
        return ReserveVO.setTag(reserveVOList);
    }

    @RequestMapping(value = "/deleteOneReserve")
    public Integer deleteOne(@RequestParam Integer id) {
        return reserveMapper.deleteById(id);
    }

    @RequestMapping(value = "/deleteReserve")
    public Integer deleteReserve(@RequestParam String idList) {

        //将前台传过来的数组字符串封装成List
        String str = idList.substring(1,idList.length()-1);
        String[] ids = idList.substring(1,idList.length()-1).split(",");
        List<Integer> integerList = new ArrayList<>();
        for(String id : ids) {
            integerList.add(new Integer(id));
        }
        Integer result = reserveService.deleteByIdIn(integerList);
        return result;
        //return null;
    }

    @RequestMapping(value = "/signInRecord")
    public List<SignInVO> getSignInRecord() {
        List<SignInVO> signInVOList = signInMapper.getSignInRecord();
        return SignInVO.setTag(signInVOList);
    }

    @RequestMapping(value = "/signOutRecord")
    public List<SignOutVO> getSignOutRecord() {
        List<SignOutVO> signOutVOList = signOutMapper.getSignOutRecord();
        return SignOutVO.setTag(signOutVOList);
    }

    @RequestMapping(value = "/blacklist")
    public List<BlacklistVO> getBlacklist() {
        List<BlacklistVO> blacklistVOList = blacklistMapper.getBlacklist();
        return blacklistVOList;
    }

    @RequestMapping(value = "/deleteBlacklist")
    public Integer deleteBlacklist(@RequestParam String idList) {
        Integer result = blacklistService.deleteByIdIn(AdminUtil.stringToList(idList));
        return result;
    }

    @RequestMapping(value = "/inobservanceType")
    public List<InobservanceType> getInobservanceType() {
        return inobservanceTypeMapper.getInobservanceType();
    }

    @RequestMapping(value = "/inobservance")
    public List<InobservanceVO> getInobservance() {
        List<InobservanceVO> inobservanceVOList = inobservanceMapper.getInobservance();
        return inobservanceVOList;
    }


}
