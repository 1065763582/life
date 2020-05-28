<template>
  <div class="app-container">
    <div id="myChartPip" class="chart" style="height: 600px;width:100%;"></div>
  </div>
</template>

<script>
  import {
    getWealthChartPie
  } from '@/api/wealth.js'
  import echarts from 'echarts'
  import moment from 'moment'

  export default {
    data() {
      return {

      }
    },
    mounted() {
      getWealthChartPie().then(response => {
        this.chart = echarts.init(document.getElementById('myChartPip'));
        this.chart.setOption(response.data)
      })
    }
  }
</script>
