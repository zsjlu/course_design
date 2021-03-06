package cn.edu.ujs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.*;
import java.util.Date;

/**
 * Created by DELL on 2017/12/29.
 */
@Entity
public class Inobservance {

    @Id
    @GeneratedValue
    private Integer id;

    /**读者编号*/
    private String userId;

    /**座位编号*/
    private String seatId;

    /**预约时间*/
    private Date reserveTime;

    /**违规类别*/
    private Integer inobservanceTypeId;

    /**创建时间*/
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Integer getInobservanceTypeId() {
        return inobservanceTypeId;
    }

    public void setInobservanceTypeId(Integer inobservanceTypeId) {
        this.inobservanceTypeId = inobservanceTypeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
