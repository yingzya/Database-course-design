import request from '@/utils/request'

// 查询物流信息列表
export function listWuliu(query) {
  return request({
    url: '/logistics/wuliu/list',
    method: 'get',
    params: query
  })
}

// 查询物流信息详细
export function getWuliu(wuliuId) {
  return request({
    url: '/logistics/wuliu/' + wuliuId,
    method: 'get'
  })
}

// 新增物流信息
export function addWuliu(data) {
  return request({
    url: '/logistics/wuliu',
    method: 'post',
    data: data
  })
}

// 修改物流信息
export function updateWuliu(data) {
  return request({
    url: '/logistics/wuliu',
    method: 'put',
    data: data
  })
}

// 删除物流信息
export function delWuliu(wuliuId) {
  return request({
    url: '/logistics/wuliu/' + wuliuId,
    method: 'delete'
  })
}
