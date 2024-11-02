-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工管理', '0', '1', 'employ', 'employee/employ/index', 1, 0, 'C', '0', '0', 'employee:employ:list', '#', 'admin', sysdate(), '', null, '员工管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'employee:employ:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'employee:employ:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'employee:employ:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'employee:employ:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('员工管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'employee:employ:export',       '#', 'admin', sysdate(), '', null, '');