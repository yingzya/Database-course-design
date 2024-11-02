-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('店铺管理', '0', '1', 'dianpu', 'shop/dianpu/index', 1, 0, 'C', '0', '0', 'shop:dianpu:list', '#', 'admin', sysdate(), '', null, '店铺管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('店铺管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'shop:dianpu:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('店铺管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'shop:dianpu:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('店铺管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'shop:dianpu:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('店铺管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'shop:dianpu:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('店铺管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'shop:dianpu:export',       '#', 'admin', sysdate(), '', null, '');