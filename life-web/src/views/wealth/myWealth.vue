<template>
  <div class="app-container">
    <el-row style="margin-top: 2%;">
      <div id="myChartPip" class="chart" style="height: 600px;width:100%;"></div>
    </el-row>
    <el-row style="margin-top: 2%;">
    </el-row>
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
