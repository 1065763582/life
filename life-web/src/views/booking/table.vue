<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select filterable placeholder="类别" clearable v-model="catalogId" @change="catalogChange">
        <el-option v-for="item in catalogOptions" :key="item.catalogId" :label="item.catalogName" :value="item.catalogId">
        </el-option>
      </el-select>
      <el-select filterable placeholder="标签" clearable v-model="tagId" @change="tagChange">
        <el-option v-for="item in tagOptions" :key="item.tagId" :label="item.tagName" :value="item.tagId">
        </el-option>
      </el-select>
      <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"
        value-format="yyyy-MM-dd" @change="dataRangeChange">
      </el-date-picker>
    </div>
    <el-table :data="tableData" style="width: 100%" @row-dblclick="editbill" height="590">
      <el-table-column label="序号" type="index" show-overflow-tooltip width="50">
      </el-table-column>
      <el-table-column prop="cdateFormat" label="日期">
      </el-table-column>
      <el-table-column prop="money" label="金额">
        <template slot-scope="scope">
          <span>{{ scope.row.money | moneyFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="catalogName" label="类别">
      </el-table-column>
      <el-table-column prop="tags" label="标签">
        <template slot-scope="scope">
          <el-tag v-for="tag in scope.row.tags" :type="tag.tagType" size="mini" v-if="tag.tagName !==undefined && tag.tagName !== null"
            style="margin-left: 5px;">{{tag.tagName}}</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {
    getBills,
    getCatalog,
    getTag
  } from '@/api/booking.js'
  import moment from 'moment'

  export default {
    data() {
      return {
        catalogId: '',
        tagId: '',
        dateRange: [moment().subtract(1, 'months').format('YYYY-MM-DD'), moment().add(1, 'days').format('YYYY-MM-DD')],
        tableData: [],
        catalogOptions: [],
        districtOptions: [],
        tagOptions: []
      }
    },
    methods: {
      editbill(row, column, event) {
        this.$router.push({
          name: 'Bill Record',
          query: {
            id: row.billId
          }
        })
      },
      handleSizeChange(val) {
        this.pageSize = val
        this.getBillTableData()
      },
      handleCurrentChange(val) {
        this.pageNum = val
        this.getBillTableData()
      },
      catalogChange(data) {
        this.getBillTableData()
      },
      tagChange(data) {
        this.getBillTableData()
      },
      dataRangeChange(data) {
        this.getBillTableData()
      },
      getBillTableData() {
        let billInfo = {
          catalogId: this.catalogId,
          tagId: this.tagId,
          startDate: this.dateRange[0],
          endDate: this.dateRange[1]
        }
        getBills(billInfo).then(response => {
          this.tableData = response.data
        })
      }
    },
    mounted() {
      this.getBillTableData()
      getCatalog().then(response => {
        this.catalogOptions = response.data
      })
      getTag().then(response => {
        this.tagOptions = response.data
      })
    },
    filters: {
      moneyFilter(value) {
        return value.toFixed(2)
      }
    }
  }
</script>

<style>
  .filter-container {
    padding-bottom: 10px
  }
</style>
