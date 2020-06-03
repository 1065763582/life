<template>
  <div class="app-container">
    <el-row>
      <el-date-picker v-model="dateRange" type="daterange" @change="dataRangeChange" value-format="yyyy-MM-dd"
        start-placeholder="开始日期" end-placeholder="结束日期" style="width: 100%;">
      </el-date-picker>
    </el-row>
    <el-row style="margin-top: 2%;">
      <div id="myChartBar" class="chart" style="height: 300px;width:100%"></div>
    </el-row>
  </div>
</template>

<script>
  import {
    getBillWeekChartLine
  } from '@/api/booking.js'
  import echarts from 'echarts'
  import moment from 'moment'

  export default {
    data() {
      return {
        dateRange: [moment().isoWeekday(-83).format('YYYY-MM-DD'), moment().isoWeekday(8).format('YYYY-MM-DD')]
      }
    },
    mounted() {
      this.setCharts(this.dateRange[0], this.dateRange[1]);
    },
    methods: {
      initCharts(option, id) {
        this.chart = echarts.init(document.getElementById(id));
        this.chart.setOption(option)
      },
      dataRangeChange(data) {
        this.dateRange = [data[0].substr(0, 8) + "01", data[1].substr(0, 8) + "01"]
        this.setCharts(this.dateRange[0], this.dateRange[1]);
      },
      setCharts(start, end) {
        getBillWeekChartLine({
          start,
          end
        }).then(response => {
          this.initCharts(response.data, 'myChartBar');
        })
      }
    }
  }
</script>
