import request from '@/utils/request'

// 查询员工管理列表
export function listEmploy(query) {
  return request({
    url: '/employee/employ/list',
    method: 'get',
    params: query
  })
}

// 查询员工管理详细
export function getEmploy(employeeId) {
  return request({
    url: '/employee/employ/' + employeeId,
    method: 'get'
  })
}

// 新增员工管理
export function addEmploy(data) {
  return request({
    url: '/employee/employ',
    method: 'post',
    data: data
  })
}

// 修改员工管理
export function updateEmploy(data) {
  return request({
    url: '/employee/employ',
    method: 'put',
    data: data
  })
}

// 删除员工管理
export function delEmploy(employeeId) {
  return request({
    url: '/employee/employ/' + employeeId,
    method: 'delete'
  })
}
