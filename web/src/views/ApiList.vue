<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { list } from '@/api/api-log'

const formData = reactive({
  url: '',
})

const tableParams = reactive({
  pageNo: 1, 
  pageSize: 20,
  url: formData.url
})
const tableData = ref([])
const tabledDataTotal = ref(0)

const loadData = () => {
  list(tableParams).then(res => {
    tableData.value = res.records
    tabledDataTotal.value = res.total
  })
}

const handleSizeChange = (val: number) => {
  tableParams.pageSize = val
  loadData()
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  tableParams.pageNo = val
  loadData()
  console.log(`current page: ${val}`)
}

onMounted(() => {
  console.log('mounted')
  loadData()
})

const onSubmit = () => {
  console.log(formData)
  loadData()
}
</script>

<template>
  <div class="wrapper">
    <el-form :inline="true" :model="formData" class="form-inline">
      <el-form-item label="url">
        <el-input v-model="formData.url" placeholder="url" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Query</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" height="300px">
      <el-table-column fixed prop="url" label="url" width="150" show-overflow-tooltip />
      <el-table-column prop="method" label="method" width="120" />
      <el-table-column prop="resourceType" label="resourceType" width="120" />
      <el-table-column prop="status" label="status" width="120" />
      <el-table-column prop="text" label="text" width="600" show-overflow-tooltip />
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