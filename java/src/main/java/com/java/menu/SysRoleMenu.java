package com.java.menu;

import java.io.Serializable;

/**
 * 角色-菜单关系表(SysRoleMenu)实体类
 */
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 553019169520156529L;
    /**
     * 角色编号
     */
    private Long roleId;
    /**
     * 菜单编号
     */
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}
