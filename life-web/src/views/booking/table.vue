<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select filterable placeholder="类别" v-model="catalogId">
        <el-option v-for="item in catalogOptions" :key="item.catalogId" :label="item.catalogName" :value="item.catalogId">
        </el-option>
      </el-select>
      <el-select filterable placeholder="标签" v-model="tagId">
        <el-option v-for="item in tagOptions" :key="item.tagId" :label="item.tagName" :value="item.tagId">
        </el-option>
      </el-select>
      <el-date-picker v-model="datetimerange" type="datetimerange" range-separator="至" start-placeholder="开始日期"
        end-placeholder="结束日期" value-format="yyyy-MM-dd HH:mm:ss">
      </el-date-picker>
    </div>
    <el-table :data="tableData" style="width: 100%" @row-dblclick="editbill">
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
          <el-tag v-for="tag in scope.row.tags" :type="tag.tagType" size="mini" v-if="tag.tagName !==undefined && tag.tagName !== null" style="margin-left: 5px;">{{tag.tagName}}</el-tag>
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
        datetimerange:[],
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
      }
    },
    mounted() {
      getBills().then(response => {
        this.tableData = response.data
      })
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
