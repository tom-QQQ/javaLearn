package com.java.menu;


import lombok.Data;

import java.util.List;

@Data
public class RoleDto {

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
     * 角色描述
     */
    private String remark;

    /**
     * 公用工厂菜单
     */
    private List<Long> factoryMenuIds;

    /**
     * 角色对应pc菜单
     */
    private List<Long> pcMenuIds;

    /**
     * 角色对应pc线边库菜单
     */
    private List<Long> pcLineSideMenuIds;

    /**
     * 角色对应pda菜单
     */
    private List<Long> pdaMenuIds;

    /**
     * 角色对应pda线边库菜单
     */
    private List<Long> pdaLineSideMenuIds;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getFactoryMenuIds() {
        return factoryMenuIds;
    }

    public void setFactoryMenuIds(List<Long> factoryMenuIds) {
        this.factoryMenuIds = factoryMenuIds;
    }

    public List<Long> getPcMenuIds() {
        return pcMenuIds;
    }

    public void setPcMenuIds(List<Long> pcMenuIds) {
        this.pcMenuIds = pcMenuIds;
    }

    public List<Long> getPcLineSideMenuIds() {
        return pcLineSideMenuIds;
    }

    public void setPcLineSideMenuIds(List<Long> pcLineSideMenuIds) {
        this.pcLineSideMenuIds = pcLineSideMenuIds;
    }

    public List<Long> getPdaMenuIds() {
        return pdaMenuIds;
    }

    public void setPdaMenuIds(List<Long> pdaMenuIds) {
        this.pdaMenuIds = pdaMenuIds;
    }

    public List<Long> getPdaLineSideMenuIds() {
        return pdaLineSideMenuIds;
    }

    public void setPdaLineSideMenuIds(List<Long> pdaLineSideMenuIds) {
        this.pdaLineSideMenuIds = pdaLineSideMenuIds;
    }
}
