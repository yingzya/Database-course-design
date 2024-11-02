import request from '@/utils/request'

// 查询订单管理列表
export function listOorder(query) {
  return request({
    url: '/order/oorder/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getOorder(orderId) {
  return request({
    url: '/order/oorder/' + orderId,
    method: 'get'
  })
}

// 新增订单管理
export function addOorder(data) {
  return request({
    url: '/order/oorder',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateOorder(data) {
  return request({
    url: '/order/oorder',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delOorder(orderId) {
  return request({
    url: '/order/oorder/' + orderId,
    method: 'delete'
  })
}
