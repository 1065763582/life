import request from '@/utils/request'

export function getCatalog(data) {
  return request({
    url: 'http://192.168.31.223:8080/api/catalog',
    method: 'get',
    data
  })
}

export function getDistrict(data) {
  return request({
    url: 'http://192.168.31.223:8080/api/district',
    method: 'get',
    data
  })
}

export function getTag(data) {
  return request({
    url: 'http://192.168.31.223:8080/api/tag',
    method: 'get',
    data
  })
}

export function saveBill(data) {
  return request({
    url: 'http://192.168.31.223:8080/api/bill',
    method: 'post',
    data
  })
}

export function getBills(params) {
  return request({
    url: 'http://192.168.31.223:8080/api/bill',
    method: 'get',
    params
  })
}

export function getBill(id) {
  return request({
    url: 'http://192.168.31.223:8080/api/bill/' + id,
    method: 'get'
  })
}

export function getHouseChartLine(params) {
  return request({
    url: 'http://localhost:8080/api/bill/houseChart/line',
    method: 'get',
    params
  })
}

export function getHouseChartBar(params) {
  return request({
    url: 'http://localhost:8080/api/bill/houseChart/bar',
    method: 'get',
    params
  })
}

export function getHouseChartPie(params) {
  return request({
    url: 'http://localhost:8080/api/bill/houseChart/pie',
    method: 'get',
    params
  })
}
