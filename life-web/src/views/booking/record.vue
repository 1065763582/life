<template>
  <div class="app-container">
    <el-form ref="form" :model="consume" label-width="120px">
      <el-input v-model="consume.billId" type="text" style="display: none;" />
      <el-form-item label="日期">
        <el-date-picker v-model="consume.cdate" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="Pick a date"
          style="width: 100%;" />
      </el-form-item>
      <el-form-item label="金额">
        <el-input-number v-model="consume.money" :precision="2" :step="0.1" :max="9999999" :min="0" style="width: 100%;text-align:left !important;" />
      </el-form-item>
      <el-form-item label="类别">
        <el-select v-model="consume.catalogId" filterable style="width: 100%;">
          <el-option v-for="item in catalogOptions" :key="item.catalogId" :label="item.catalogName" :value="item.catalogId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="地点">
        <el-select v-model="consume.districtId" filterable style="width: 100%;">
          <el-option v-for="item in districtOptions" :key="item.districtId" :label="item.districtName" :value="item.districtId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Tags">
        <el-select v-model="consume.tagIds" multiple filterable style="width: 100%;">
          <el-option v-for="item in tagOptions" :key="item.tagId" :label="item.tagName" :value="item.tagId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="consume.desc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">OK</el-button>
        <el-button @click="onClear">Clear</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {
    getCatalog,
    saveBill,
    getBill,
    getDistrict,
    getTag
  } from '@/api/booking.js'

  import moment from 'moment'

  export default {
    data() {
      return {
        consume: this.initConsume(),
        catalogOptions: [],
        districtOptions: [],
        tagOptions: []
      }
    },
    mounted() {
      getCatalog().then(response => {
        this.catalogOptions = response.data
      })
      getDistrict().then(response => {
        this.districtOptions = response.data
      })
      getTag().then(response => {
        this.tagOptions = response.data
      })
      if (this.$route.query.id === undefined) {
        this.consume = this.initConsume()
      } else {
        getBill(this.$route.query.id).then(response => {
          this.consume = response.data
        })
      }
    },
    methods: {
      onSubmit() {
        //this.consume.cdate = this.formatDateTime(this.consume.cdate)
        saveBill(this.consume).then(response => {
          if (response.code === 20000) {
            this.onClear()
          }
          this.$message(response.message)
        })
      },
      onClear() {
        this.consume = this.initConsume()
      },
      initConsume() {
        return {
          billId: null,
          cdate: moment().format('YYYY-MM-DD 00:00:00'),
          money: 0,
          desc: '',
          catalog: '',
          districtId: '',
          brandId: '',
          tags: []
        }
      }
    }
  }
</script>

<style scoped>
  input {
    text-align: left !important;
  }
</style>
