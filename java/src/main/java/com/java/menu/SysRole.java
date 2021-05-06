package com.java.menu;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色表(SysRole)实体类
 *
 * @author xiao.li
 * @version 1.0
 * @createDate 2021-02-20 15:43:13
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = -60713114734145221L;
    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 创建人
     */
    private String auther;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 角色描述
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
