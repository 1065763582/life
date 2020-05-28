import request from '@/utils/request'

export function getWealthChartPie(params) {
  return request({
    url: 'http://localhost:8080/api/wealth/pie',
    method: 'get'
  })
}
