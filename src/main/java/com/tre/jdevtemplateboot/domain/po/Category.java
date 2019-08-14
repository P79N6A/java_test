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
 * @since 2018-10-12
 */
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CId")
    private Long CId;

    @TableField("CName")
    private String CName;

    public Long getCId() {
        return CId;
    }

    public void setCId(Long CId) {
        this.CId = CId;
    }
    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    @Override
    public String toString() {
        return "Category{" +
        "CId=" + CId +
        ", CName=" + CName +
        "}";
    }
}
