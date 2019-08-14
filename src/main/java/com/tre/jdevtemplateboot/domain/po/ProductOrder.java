package com.tre.jdevtemplateboot.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JDev
 * @since 2018-11-20
 */
public class ProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("orderid")
    private Long orderid;

    @TableId("detailid")
    private Long detailid;

    @TableId("userid")
    private Long userid;

    @TableField("ordername")
    private String ordername;

    @TableField("detailname")
    private String detailname;

    @TableField("username")
    private String username;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }
    public Long getDetailid() {
        return detailid;
    }

    public void setDetailid(Long detailid) {
        this.detailid = detailid;
    }
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }
    public String getDetailname() {
        return detailname;
    }

    public void setDetailname(String detailname) {
        this.detailname = detailname;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
        "orderid=" + orderid +
        ", detailid=" + detailid +
        ", userid=" + userid +
        ", ordername=" + ordername +
        ", detailname=" + detailname +
        ", username=" + username +
        "}";
    }
}
