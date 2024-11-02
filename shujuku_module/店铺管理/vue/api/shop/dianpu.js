import request from '@/utils/request'

// 查询店铺管理列表
export function listDianpu(query) {
  return request({
    url: '/shop/dianpu/list',
    method: 'get',
    params: query
  })
}

// 查询店铺管理详细
export function getDianpu(shopId) {
  return request({
    url: '/shop/dianpu/' + shopId,
    method: 'get'
  })
}

// 新增店铺管理
export function addDianpu(data) {
  return request({
    url: '/shop/dianpu',
    method: 'post',
    data: data
  })
}

// 修改店铺管理
export function updateDianpu(data) {
  return request({
    url: '/shop/dianpu',
    method: 'put',
    data: data
  })
}

// 删除店铺管理
export function delDianpu(shopId) {
  return request({
    url: '/shop/dianpu/' + shopId,
    method: 'delete'
  })
}
