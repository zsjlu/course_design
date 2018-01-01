package cn.edu.ujs.controller;

import cn.edu.ujs.VO.ResultVO;
import cn.edu.ujs.entity.Reserve;
import cn.edu.ujs.entity.Seat;
import cn.edu.ujs.entity.SignIn;
import cn.edu.ujs.entity.UserInfo;
import cn.edu.ujs.enums.*;
import cn.edu.ujs.service.ReserveService;
import cn.edu.ujs.service.SeatService;
import cn.edu.ujs.service.SignInService;
import cn.edu.ujs.service.UserInfoService;
import cn.edu.ujs.util.ResultVOUtil;
import cn.edu.ujs.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by DELL on 2017/12/26.
 */
@RestController
@RequestMapping(value = "/sign_in")
public class SignInController {

    private static final Logger logger = LoggerFactory.getLogger(SignInController.class);

    @Autowired
    private SignInService signInService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private SeatService seatService;

    @RequestMapping
    public ResultVO signIn(@RequestParam String userId) {

        UserInfo userInfo = userInfoService.findOne(userId);
        ResultVO resultVO = null;
        SignIn signIn = null;
        if(userInfo != null) {
            //判断你当天是否预约
            if (userInfo.getReserveStatus() == ReserveStatusEnum.RESERVED.getCode()) {
                Reserve todayReserve = readTodayReservation(userId);
                //判断你当天是否签到
                if (userInfo.getSignInStatus() == SignInStatusEnum.NOT_SIGN_IN.getCode()) {
                    //更新签到信息
                    signIn = updateSignIn(userId,
                                          todayReserve.getSeatId(),
                                          SignInStatusEnum.FIRST_SIGN_IN.getCode(),
                                          todayReserve.getReserveTime());
                    //更新座位表
                    updateSeat(todayReserve.getSeatId());
                    //更新读者状态信息
                    updateUserInfo(userInfo,SignInStatusEnum.FIRST_SIGN_IN.getCode());
                    //返回视图对象
                    resultVO = ResultVOUtil.success(signIn,
                                                    ResultEnum.SUCCESS_SIGN_IN.getCode(),
                                                    ResultEnum.SUCCESS_SIGN_IN.getMessage());
                }
                // TODO: 2017/12/28 处理签离后又签到的逻辑
                //判断是否签离
                else {
                    if (userInfo.getSignOutStatus() == SignOutStatusEnum.TEMPORARY_SIGN_OUT.getCode()) {
                        //更新签到表信息
                        signIn = updateSignIn(userId,
                                              todayReserve.getSeatId(),
                                              SignInStatusEnum.SIGN_IN_AFTER_SIGN_OUT.getCode(),
                                              todayReserve.getReserveTime());
                        //更新读者状态信息表
                        userInfo.setSignInStatus(SignInStatusEnum.SIGN_IN_AFTER_SIGN_OUT.getCode());
                        userInfo.setSignOutStatus(SignOutStatusEnum.NOT_SIGN_OUT.getCode());
                        userInfoService.save(userInfo);
                        //更新座位表
                        updateSeat(todayReserve.getSeatId());
                        resultVO = ResultVOUtil.success(signIn,
                                                        ResultEnum.SUCCESS_SIGN_IN.getCode(),
                                                        ResultEnum.SUCCESS_SIGN_IN.getMessage());
                    }

                }

            }
            else {
                resultVO = ResultVOUtil.error(ResultEnum.NOT_RESERVED.getCode(),ResultEnum.NOT_RESERVED.getMessage());
            }
        }
        else {
            resultVO = ResultVOUtil.error(ResultEnum.NOT_RESERVED.getCode(),
                                          ResultEnum.NOT_RESERVED.getMessage());
        }
        return resultVO;
    }

    /**
     * 更新签到表信息
     * @param userId
     * @param seatId
     * @return
     */
    public SignIn updateSignIn(String userId, String seatId, Integer signInType, Date reserveTime) {
        SignIn signIn = new SignIn();
        signIn.setUserId(userId);
        signIn.setSeatId(seatId);
        signIn.setSignInTpye(signInType);
        signIn.setReserveTime(reserveTime);
        return signInService.save(signIn);
    }

    /**
     * 更新读者状态信息表
     * @param userInfo
     * @param code
     */
    public void updateUserInfo(UserInfo userInfo, Integer code) {
        userInfo.setSignInStatus(code);
        userInfoService.save(userInfo);
    }

    /**
     * 更新座位状态为被签到
     * @param seatId
     */
    public void updateSeat(String seatId) {
        Seat seat = seatService.findOne(seatId);
        seat.setSeatStatus(SeatStatusEnum.BEING_SIGN_IN.getCode());
        seatService.save(seat);
    }

    /**
     * 读取今天的预约信息
     * @param userId
     * @return
     */
    public Reserve readTodayReservation(String userId) {
        Reserve todayReserve = reserveService.findByUserIdAndReserveTimeBetween(userId,
                TimeUtil.stringToDate(TimeUtil.getDateShort()),
                TimeUtil.addOneDay(TimeUtil.getDateShort()));
        return todayReserve;
    }
}
