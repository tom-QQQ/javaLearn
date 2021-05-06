package com.java.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

import static com.java.menu.RedisPrefixConstants.*;

/**
 * 使用前提, 保存用户和菜单的表结构为一个roleId角色id字段,一个menuId菜单id字段
 * 获取的菜单结构为树形, 从根节点到子节点, 核心变量为菜单id, 父节点菜单parentId, 孩子节点children
 * 该类主要是根据前端返回的菜单节点id, 不一定是叶子节点, 也可以是非叶子节点的id, 向上获取所有父节点id
 * 这样在查询用户角色时, 可以较为容易的构建出菜单树返回给前端
 * 前端请求完整菜单树会进行完整缓存
 */
public class MenuDeal {

    // 从缓存中读取数据
    private Object getValueFromRedis(String key) {

        if (key.equals("sss")) {
            return null;
        }
        return "缓存中的结果";
    }

    // 保存缓存
    private void saveToRedis(String key, Object value) {

    }

    /**
     * 保存角色权限
     * @param sysRole 系统角色
     * @param roleDto 信息
     */
    private void saveRoleMenuRefTree(SysRole sysRole, RoleDto roleDto) {

        // 这里是有多种菜单数据, 保存到一起
        Set<Long> pcMenuIds = getMenuId(roleDto.getPcMenuIds(), PC_MENU, PC_TREE_MENU);
        Set<Long> pdaMenuIds = getMenuId(roleDto.getPdaMenuIds(), PDA_MENU, PDA_TREE_MENU);
        Set<Long> pcLineSideMenuIds = getMenuId(roleDto.getPcLineSideMenuIds(), PC_LINE_SIDE_MENU, PC_LINE_SIDE_TREE_MENU);
        Set<Long> pdaLineSideMenuIds = getMenuId(roleDto.getPdaLineSideMenuIds(), PDA_LINE_SIDE_MENU, PDA_LINE_SIDE_TREE_MENU);

        Set<Long> all = new HashSet<>();
        all.addAll(pdaMenuIds);
        all.addAll(pcMenuIds);
        all.addAll(pcLineSideMenuIds);
        all.addAll(pdaLineSideMenuIds);
        saveRoleMenuRef(sysRole, all);
    }

    /**
     * 根据角色和菜单id构建和保存数据
     */
    private void saveRoleMenuRef(SysRole sysRole, Set<Long> menus) {

        if (CollectionUtils.isEmpty(menus)){
            return;
        }

        List<SysRoleMenu> sysRoleMenus = new LinkedList<>();

        for (Long id : menus) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(id);
            sysRoleMenu.setRoleId(sysRole.getId());
            sysRoleMenus.add(sysRoleMenu);
        }

        // 保存用户菜单数据到数据库
//        sysRoleMenuDao.insertRoleMenuIds(sysRoleMenus);
    }


    /**
     * 菜单信息
     * @param menuIds 菜单
     * @param menuKey 分层菜单缓存id
     * @param treeMenuKey 树形菜单缓存id
     * @return 结果
     */
    private Set<Long> getMenuId(List<Long> menuIds, String menuKey, String treeMenuKey) {

        if (CollectionUtils.isEmpty(menuIds)) {
            return new HashSet<>();
        }

        // 从缓存读取树形菜单
        Object menu = getValueFromRedis(treeMenuKey);
        if (menu == null) {
            // 打印错误日志
//            log.error("未在缓存中获取到树形菜单信息, 需要先获取菜单信息, 才能更新或创建角色");
            throw new RuntimeException("请先获取菜单信息");
        }

        String treeMenu = menu.toString();

        return getMenuIdWithRedis(menuIds, treeMenu, menuKey);
    }

    /**
     * 菜单一般为树形的叶子节点, 但传过来的menuIds可能是叶子节点, 也可能是非叶子节点
     * 根据权限树和点id获取节点到根节点经过的所有id, 包括根节点id
     * @param menuIds 节点id
     * @param treeMenus 权限树
     * @return 从所有节点出发, 向上到根节点, 向下到叶子节点经过的所有节点, 包括根节点和叶子节点
     */
    private Set<Long> getMenuIdWithRedis(List<Long> menuIds, String treeMenus, String menuKey) {

        Object menu = getValueFromRedis(menuKey);
        String json;

        if (menu == null) {

            List<RedisSysMenu> pcMenus = JSONObject.parseArray(treeMenus, RedisSysMenu.class);
            LinkedList<Map<String, String>> list =  new LinkedList<>();
            recursionClassify(pcMenus, 0, list);

            json = JSONObject.toJSONString(list);
            saveToRedis(menuKey, json);

        } else {
            json = menu.toString();
        }

        List<JSONObject> menuArray = JSONArray.parseArray(json, JSONObject.class);

        Set<Long> result = getMenuId(menuIds, menuArray);

        return result;
    }

    /**
     * 菜单数据核心类
     */

    static class RedisSysMenu implements Serializable {

        // 菜单id
        private Long id;
        // 父级id
        private Long parentId;
        private List<RedisSysMenu> childrenMenu;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public List<RedisSysMenu> getChildrenMenu() {
            return childrenMenu;
        }

        public void setChildrenMenu(List<RedisSysMenu> childrenMenu) {
            this.childrenMenu = childrenMenu;
        }
    }

    /**
     * 将树状菜单按照菜单分层
     * @param list 树状菜单
     * @param layer 层数
     * @param result 分层结果
     */
    private static void recursionClassify(List<RedisSysMenu> list, int layer, List<Map<String, String>> result) {

        for (RedisSysMenu menu : list) {

            RedisSysMenu copyMenu = new RedisSysMenu();
            BeanUtils.copyProperties(menu, copyMenu);
            // 删除子菜单, 减少数据臃肿
            copyMenu.setChildrenMenu(null);

            addMenu(result, layer, copyMenu);

            if (CollectionUtils.isEmpty(menu.getChildrenMenu())) {
                continue;
            }
            recursionClassify(menu.getChildrenMenu(), layer+1, result);
        }
    }

    /**
     * 向指定层添加数据
     * @param result 最终结果
     * @param layer 层数, 对应最终结果的索引
     * @param sysMenu 需要添加的数据
     */
    private static void addMenu(List<Map<String, String>> result, int layer,  RedisSysMenu sysMenu) {

        // 补充缺少的Map
        int lessMap = result.size() - layer - 1;
        if (lessMap < 0) {
            for (int i = 0; i < -lessMap; i++) {
                result.add(new HashMap<>());
            }
        }

        // 为了处理String解析为List<Map>时数字类型数据会被默认解析为Integer问题, 这里将类型改为String
        result.get(layer).put(String.valueOf(sysMenu.getId()), String.valueOf(sysMenu.getParentId()));
    }

    /**
     * 根据子节点id, 查询返回叶子节点id从叶子节点出发到根节点的所有节点id
     * @param menuIds 叶子节点id
     * @param list 分层数据
     * @return 叶子节点和对应的所有父节点id
     */
    private static Set<Long> getMenuId(List<Long> menuIds, List<JSONObject> list) {

        Set<Long> result = new HashSet<>();
        for (Long menuId : menuIds) {

            String loopId = String.valueOf(menuId);
            for (int index = list.size() - 1; index > -1; index--) {

                JSONObject menuMap = list.get(index);
                String parentId = menuMap.getString(String.valueOf(loopId));
                if (parentId == null) {
                    continue;
                }
                result.add(Long.parseLong(loopId));
                // 如果当前层不是最后一层并且当前的loopId是menuId, 即传入的是非叶子节点id, 则根据非叶子节点id补充对应的子节点id
                if (index != list.size() - 1 && loopId.equals(String.valueOf(menuId))) {
                    // 为了提高查询效率, 这里使用map
                    Map<String, String> parentIds = new HashMap<>(16);
                    parentIds.put(loopId, "");
                    addChildId(list, index, parentIds, result);
                }
                loopId = parentId;
            }
        }

        return result;
    }

    /**
     * 根据父节点id查询关联的所有字节点id
     * @param list 分层菜单
     * @param layers 父节点集合所在菜单层
     * @param parentsIds 父节点集合, 为了提高判断效率用了map
     * @param result 查询结果
     */
    private static void addChildId(List<JSONObject> list, Integer layers, Map<String, String> parentsIds, Set<Long> result) {

        // 去下一层找parentId为parentsIds中元素的数据
        layers++;
        if (layers > list.size()-1) {
            return;
        }

        JSONObject parentLayers = list.get(layers);
        // 使用新的Map存储下一层的父节点id, 尽量减少需要比较值的数量
        Map<String, String> newParentsIds = new HashMap<>(16);

        for (Map.Entry<String, Object> entry : parentLayers.entrySet()) {

            if (parentsIds.containsKey((String) entry.getValue())) {

                result.add(Long.parseLong(entry.getKey()));
                newParentsIds.put(entry.getKey(), "");
            }
        }

        addChildId(list, layers, newParentsIds, result);
    }

    public static void main(String[] args) {

        // 树形菜单缓存示例
        String menu = "[{\"updateTime\":1613797998000,\"sort\":0,\"type\":1,\"parentId\":-1," +
                "\"createTime\":1613797996000,\"name\":\"原材料WMS系统\",\"id\":0,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610504673000,\"sort\":1,\"type\":1,\"parentId\":0," +
                "\"createTime\":1610504673000,\"name\":\"入库\",\"href\":\"in-store\",\"id\":1,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610504673000,\"sort\":1,\"type\":1,\"parentId\":1," +
                "\"createTime\":1610504673000,\"name\":\"入库作业\",\"href\":\"inbound\",\"id\":6,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610504673000,\"sort\":1,\"type\":1,\"parentId\":6," +
                "\"createTime\":1610504673000,\"name\":\"采购入库\",\"href\":\"inbound-purchase\",\"id\":9," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610504673000,\"sort\":2,\"type\":1," +
                "\"parentId\":6,\"createTime\":1610504673000,\"name\":\"转库入库\",\"href\":\"inbound-move\",\"id\":10," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610504673000,\"sort\":3,\"type\":1," +
                "\"parentId\":6,\"createTime\":1610504673000,\"name\":\"半成品生产入库\"," +
                "\"href\":\"inbound-semi-production\",\"id\":11,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610504673000,\"sort\":4,\"type\":1,\"parentId\":6,\"createTime\":1610504673000," +
                "\"name\":\"生产退料\",\"href\":\"inbound-production-return\",\"id\":12,\"category\":1," +
                "\"childrenMenu\":[]},{\"updateTime\":1610504673000,\"sort\":5,\"type\":1,\"parentId\":6," +
                "\"createTime\":1610504673000,\"name\":\"经销退库\",\"href\":\"inbound-sale-return\",\"id\":13," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610504673000,\"sort\":6,\"type\":1," +
                "\"parentId\":6,\"createTime\":1610504673000,\"name\":\"附件销售退库\"," +
                "\"href\":\"inbound-attach-sale-return\",\"id\":14,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610504673000,\"sort\":7,\"type\":1,\"parentId\":6,\"createTime\":1610504673000," +
                "\"name\":\"附件转储入库\",\"href\":\"inbound-attach-move\",\"id\":15,\"category\":1," +
                "\"childrenMenu\":[]}]},{\"updateTime\":1610504673000,\"sort\":2,\"type\":1,\"parentId\":1," +
                "\"createTime\":1610504673000,\"name\":\"入库检验\",\"href\":\"inspect\",\"id\":7,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610504673000,\"sort\":2,\"type\":1,\"parentId\":7," +
                "\"createTime\":1610504673000,\"name\":\"质检结果查询\",\"href\":\"inspect-result-search\",\"id\":17," +
                "\"category\":1,\"childrenMenu\":[]}]},{\"updateTime\":1610504673000,\"sort\":3,\"type\":1," +
                "\"parentId\":1,\"createTime\":1610504673000,\"name\":\"补打条码\",\"href\":\"print-bar\",\"id\":8," +
                "\"category\":1,\"childrenMenu\":[{\"updateTime\":1610504673000,\"sort\":1,\"type\":1,\"parentId\":8," +
                "\"createTime\":1610504673000,\"name\":\"补打LPN条码\",\"href\":\"print-lpn\",\"id\":18,\"category\":1," +
                "\"childrenMenu\":[]}]}]},{\"sort\":1,\"type\":1,\"parentId\":0,\"name\":\"模拟器\"," +
                "\"href\":\"simulate-manage\",\"id\":117,\"category\":1,\"childrenMenu\":[{\"sort\":1,\"type\":1," +
                "\"parentId\":117,\"name\":\"虹信模拟器\",\"href\":\"r3-simulator\",\"id\":118,\"category\":1," +
                "\"childrenMenu\":[{\"sort\":1,\"type\":1,\"parentId\":118,\"name\":\"R3/MES新增入库单\"," +
                "\"href\":\"r3-inbound\",\"id\":119,\"category\":1,\"childrenMenu\":[]},{\"sort\":1,\"type\":1," +
                "\"parentId\":118,\"name\":\"R3新增出库单\",\"href\":\"r3-outbound\",\"id\":120,\"category\":1," +
                "\"childrenMenu\":[]}]}]},{\"updateTime\":1610504673000,\"sort\":2,\"type\":1,\"parentId\":0," +
                "\"createTime\":1610504673000,\"name\":\"出库\",\"href\":\"out-store\",\"id\":2,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610940165000,\"sort\":1,\"type\":1,\"parentId\":2," +
                "\"createTime\":1610940165000,\"name\":\"出库作业\",\"href\":\"outbound\",\"id\":33,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610940165000,\"sort\":1,\"type\":1,\"parentId\":33," +
                "\"createTime\":1610940165000,\"name\":\"生产领料\",\"href\":\"outbound-production-pick\",\"id\":34," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":2,\"type\":1," +
                "\"parentId\":33,\"createTime\":1610940165000,\"name\":\"转库出库\"," +
                "\"href\":\"outbound-transfer-inventory\",\"id\":35,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610940165000,\"sort\":3,\"type\":1,\"parentId\":33,\"createTime\":1610940165000," +
                "\"name\":\"销售出库\",\"href\":\"outbound-sale\",\"id\":36,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610940165000,\"sort\":4,\"type\":1,\"parentId\":33,\"createTime\":1610940165000," +
                "\"name\":\"采购退货\",\"href\":\"outbound-purchase-return\",\"id\":37,\"category\":1," +
                "\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":5,\"type\":1,\"parentId\":33," +
                "\"createTime\":1610940165000,\"name\":\"附件转储\",\"href\":\"outbound-attach-save\",\"id\":38," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":6,\"type\":1," +
                "\"parentId\":33,\"createTime\":1610940165000,\"name\":\"非生产性领料\"," +
                "\"href\":\"outbound-not-production-pick\",\"id\":39,\"category\":1,\"childrenMenu\":[]}]}]}," +
                "{\"updateTime\":1610504673000,\"sort\":3,\"type\":1,\"parentId\":0,\"createTime\":1610504673000," +
                "\"name\":\"库存管理\",\"href\":\"inventory-manager\",\"id\":3,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610940165000,\"sort\":1,\"type\":1,\"parentId\":3," +
                "\"createTime\":1610940165000,\"name\":\"库存调整\",\"href\":\"inventory-adjust\",\"id\":51," +
                "\"category\":1,\"childrenMenu\":[{\"updateTime\":1610940165000,\"sort\":1,\"type\":1," +
                "\"parentId\":51,\"createTime\":1610940165000,\"name\":\"库存数量调整\"," +
                "\"href\":\"quantity-inventory-adjust-menu\",\"id\":53,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610940165000,\"sort\":2,\"type\":1,\"parentId\":51,\"createTime\":1610940165000," +
                "\"name\":\"库存数量调整查询\",\"href\":\"quantity-inventory-adjust-search\",\"id\":54,\"category\":1," +
                "\"childrenMenu\":[]},{\"sort\":3,\"type\":1,\"parentId\":51,\"name\":\"R/3批次调整\"," +
                "\"href\":\"r3-inventory-adjust-menu\",\"id\":113,\"category\":1,\"childrenMenu\":[]},{\"sort\":4," +
                "\"type\":1,\"parentId\":51,\"name\":\"R/3批次调整查询\",\"href\":\"r3-inventory-adjust-search\"," +
                "\"id\":114,\"category\":1,\"childrenMenu\":[]},{\"sort\":5,\"type\":1,\"parentId\":51," +
                "\"name\":\"生产批次调整\",\"href\":\"product-inventory-adjust-menu\",\"id\":115,\"category\":1," +
                "\"childrenMenu\":[]},{\"sort\":6,\"type\":1,\"parentId\":51,\"name\":\"生产批次调整查询\"," +
                "\"href\":\"product-inventory-adjust-search\",\"id\":116,\"category\":1,\"childrenMenu\":[]}]}," +
                "{\"updateTime\":1610940165000,\"sort\":2,\"type\":1,\"parentId\":3,\"createTime\":1610940165000," +
                "\"name\":\"库存冻结/解冻\",\"href\":\"inventory-frozen-unfrozen\",\"id\":52,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610940165000,\"sort\":1,\"type\":1,\"parentId\":52," +
                "\"createTime\":1610940165000,\"name\":\"库存冻结\",\"href\":\"inventory-frozen\",\"id\":55," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":2,\"type\":1," +
                "\"parentId\":52,\"createTime\":1610940165000,\"name\":\"库存解冻\",\"href\":\"inventory-unfrozen\"," +
                "\"id\":56,\"category\":1,\"childrenMenu\":[]}]}]},{\"updateTime\":1610504673000,\"sort\":4," +
                "\"type\":1,\"parentId\":0,\"createTime\":1610504673000,\"name\":\"统计报表\"," +
                "\"href\":\"report-manager\",\"id\":4,\"category\":1,\"childrenMenu\":[{\"updateTime\":1610940165000," +
                "\"sort\":1,\"type\":1,\"parentId\":4,\"createTime\":1610940165000,\"name\":\"报表查询\"," +
                "\"href\":\"report-search\",\"id\":57,\"category\":1,\"childrenMenu\":[{\"updateTime\":1610940165000," +
                "\"sort\":1,\"type\":1,\"parentId\":57,\"createTime\":1610940165000,\"name\":\"对账差异报表\"," +
                "\"href\":\"report-balance-diff\",\"id\":58,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610940165000,\"sort\":2,\"type\":1,\"parentId\":57,\"createTime\":1610940165000," +
                "\"name\":\"库存量报表\",\"href\":\"report-inventory\",\"id\":59,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610940165000,\"sort\":3,\"type\":1,\"parentId\":57,\"createTime\":1610940165000," +
                "\"name\":\"采购库存量表\",\"href\":\"report-inventory-purchase\",\"id\":60,\"category\":1," +
                "\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":4,\"type\":1,\"parentId\":57," +
                "\"createTime\":1610940165000,\"name\":\"原材料库龄表\",\"href\":\"report-raw-age\",\"id\":61," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":5,\"type\":1," +
                "\"parentId\":57,\"createTime\":1610940165000,\"name\":\"超期物料报表\"," +
                "\"href\":\"report-material-overdue\",\"id\":62,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610940165000,\"sort\":6,\"type\":1,\"parentId\":57,\"createTime\":1610940165000," +
                "\"name\":\"超期物料滚动消耗表\",\"href\":\"report-material-loss\",\"id\":63,\"category\":1," +
                "\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":7,\"type\":1,\"parentId\":57," +
                "\"createTime\":1610940165000,\"name\":\"出入库明细报表\",\"href\":\"report-in-out-bound-detail\",\"id\":64," +
                "\"category\":1,\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":8,\"type\":1," +
                "\"parentId\":57,\"createTime\":1610940165000,\"name\":\"库存检查记录表\"," +
                "\"href\":\"report-inventory-check\",\"id\":65,\"category\":1,\"childrenMenu\":[]}," +
                "{\"updateTime\":1610940165000,\"sort\":9,\"type\":1,\"parentId\":57,\"createTime\":1610940165000," +
                "\"name\":\"R/3上传失败清单\",\"href\":\"report-upload-list\",\"id\":66,\"category\":1," +
                "\"childrenMenu\":[]},{\"updateTime\":1610940165000,\"sort\":10,\"type\":1,\"parentId\":57," +
                "\"createTime\":1610940165000,\"name\":\"已分配未出库SO表\",\"href\":\"report-so\",\"id\":67,\"category\":1," +
                "\"childrenMenu\":[]}]}]},{\"updateTime\":1610504673000,\"sort\":5,\"type\":1,\"parentId\":0," +
                "\"createTime\":1610504673000,\"name\":\"基础配置\",\"href\":\"basic-manager\",\"id\":5,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610940165000,\"sort\":1,\"type\":1,\"parentId\":5," +
                "\"createTime\":1610940165000,\"name\":\"基础配置\",\"href\":\"configuration\",\"id\":70,\"category\":1," +
                "\"childrenMenu\":[{\"updateTime\":1610940165000,\"sort\":1,\"type\":1,\"parentId\":70," +
                "\"createTime\":1610940165000,\"name\":\"区域/货位维护\",\"href\":\"location-manager\",\"id\":71," +
                "\"category\":1,\"childrenMenu\":[]}]}]}]}]";

        List<Long> menuIds = new LinkedList<>();
        menuIds.add(14L);
        menuIds.add(120L);

        List<RedisSysMenu> pcMenus = JSONObject.parseArray(menu, RedisSysMenu.class);
        LinkedList<Map<String, String>> list =  new LinkedList<>();
        recursionClassify(pcMenus, 0, list);
        String json = JSONObject.toJSONString(list);
        List<JSONObject> a =  JSONArray.parseArray(json, JSONObject.class);
        Set<Long> result = getMenuId(menuIds, a);
        System.out.println("");
    }
}
