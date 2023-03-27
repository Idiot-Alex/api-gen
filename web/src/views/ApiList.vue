<script setup lang="ts">
import { onMounted, reactive, ref, watch } from 'vue'
import { list } from '@/api/api-log'
import { formatBytes, calcHeight } from '@/utils/util'

const formData = reactive({
  url: '',
})

const tableParams = reactive({
  pageNo: 1, 
  pageSize: 20,
  url: formData.url
})

watch(formData, (newVal) => {
  tableParams.url = newVal.url
})

const tableData = ref([])
const tabledDataTotal = ref(0)
const formInline = ref()
const pagination = ref()
const tableHeight = ref('300px')

const loadData = () => {
  list(tableParams).then(res => {
    tableData.value = res.records
    tabledDataTotal.value = res.total
  })
}

const handleSizeChange = (val) => {
  tableParams.pageSize = val
  loadData()
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val) => {
  tableParams.pageNo = val
  loadData()
  console.log(`current page: ${val}`)
}

onMounted(() => {
  tableHeight.value = calcHeight(window.innerHeight, [120, formInline.value.$el.clientHeight, pagination.value.$el.clientHeight]) + 'px'
  loadData()
})

const onSubmit = () => {
  loadData()
}

</script>

<template>
  <div class="wrapper">
    <el-form :inline="true" :model="formData" ref="formInline" class="form-inline">
      <el-form-item label="url">
        <el-input v-model="formData.url" placeholder="url" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Query</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" :height="tableHeight">
      <el-table-column fixed prop="url" label="url" width="150" show-overflow-tooltip />
      <el-table-column prop="method" label="method" width="120" />
      <el-table-column prop="resourceType" label="resourceType" width="120" />
      <el-table-column prop="status" label="status" width="120" />
      <el-table-column prop="responseBodySize" label="responseBodySize" width="100">
        <template #default="{row}">
          {{ formatBytes(row.responseBodySize) }}
        </template>
      </el-table-column>
      <!-- <el-table-column fixed="right" label="Operations" width="120">
        <template #default>
          <el-button link type="primary" size="small" @click="handleClick"
            >Detail</el-button
          >
          <el-button link type="primary" size="small">Edit</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    <el-pagination
      ref="pagination"
      class="pagination"
      v-model:current-page="tableParams.pageNo"
      v-model:page-size="tableParams.pageSize"
      :page-sizes="[20, 100, 200]"
      background
      layout="total, sizes, prev, pager, next"
      :total="tabledDataTotal"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange" />
  </div>
</template>

<style scoped>
.wrapper {
  height: 100%;
}
.pagination {
  margin-top: 20px;
}
</style>