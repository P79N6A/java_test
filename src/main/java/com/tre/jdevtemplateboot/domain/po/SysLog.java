package com.tre.jdevtemplateboot.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author JDev
 * @since 2018-10-12
 */
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String operation;

    private String method;

    private String params;

    private String ip;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SysLog{" +
        "id=" + id +
        ", username=" + username +
        ", operation=" + operation +
        ", method=" + method +
        ", params=" + params +
        ", ip=" + ip +
        ", createDate=" + createDate +
        "}";
    }
}
