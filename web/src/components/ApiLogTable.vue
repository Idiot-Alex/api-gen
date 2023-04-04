<script setup>
import { onMounted, computed, reactive, ref, watch } from 'vue'
import { list, del } from '@/api/api-log'
import { formatBytes, calcHeight, formatDateTime } from '@/utils/util'

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

const loading = ref(false)
const tableData = ref([])
const tabledDataTotal = ref(0)
const formInline = ref()
const pagination = ref()
const tableHeight = ref('300px')
const tempData = ref({})
const drawerVisible = ref(false)

const loadData = () => {
  loading.value = true
  list(tableParams).then(res => {
    if (res.code === 0) {
      tableData.value = res.data
      tabledDataTotal.value = res.total
    }
    loading.value = false
  })
}

const handleSizeChange = (val) => {
  tableParams.pageSize = val
  loadData()
}
const handleCurrentChange = (val) => {
  tableParams.pageNo = val
  loadData()
}

onMounted(() => {
  tableHeight.value = calcHeight(window.innerHeight, [120, formInline.value.$el.clientHeight, pagination.value.$el.clientHeight]) + 'px'
  loadData()
})

const onSubmit = () => {
  loadData()
}

const handleDelete = (id) => {
  del(id).then(res => {
    if (res.code === 0) {
      loadData()
    }
  })
}

const showDetailDrawer = (row) => {
  tempData.value = row
  drawerVisible.value = true
}

const reqHeaders = computed(() => {
  if (Object.keys(tempData.value).length > 0) {
    return JSON.parse(tempData.value.requestHeaders)
  }
  return {}
})
const resHeaders = computed(() => {
  if (Object.keys(tempData.value).length > 0) {
    return JSON.parse(tempData.value.responseHeaders)
  }
  return {}
})

</script>
<template>
  <el-form :inline="true" :model="formData" ref="formInline" class="form-inline">
    <el-form-item label="请求地址">
      <el-input v-model="formData.url" placeholder="请输入请求地址" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">查询</el-button>
    </el-form-item>
  </el-form>
  <el-divider class="m-0!" />
  <el-table v-loading="loading" :data="tableData" :height="tableHeight">
    <el-table-column fixed prop="url" label="请求地址" show-overflow-tooltip />
    <el-table-column prop="method" label="请求方式" width="120">
      <template #default="scope">
      <el-tag>{{ scope.row.method }}</el-tag>
    </template>
    </el-table-column>
    <el-table-column prop="resourceType" label="资源类型" width="120" />
    <el-table-column prop="status" label="状态码" width="120" />
    <el-table-column prop="responseBodySize" label="响应体大小" width="100">
      <template #default="{row}">
        {{ formatBytes(row.responseBodySize) }}
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="150">
      <template #default="{row}">
        <el-button type="primary" size="small" @click="showDetailDrawer(row)">详情</el-button>
        <el-popconfirm title="是否确认删除该记录?" confirm-button-text="是" cancel-button-text="否" 
          confirm-button-type="danger" cancel-button-type="primary" width="200" 
          @confirm="handleDelete(row.idStr)">
          <template #reference>
            <el-button type="danger" size="small">删除</el-button>
            </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    ref="pagination"
    mt-20px
    v-model:current-page="tableParams.pageNo"
    v-model:page-size="tableParams.pageSize"
    :page-sizes="[20, 100, 200]"
    background
    layout="total, sizes, prev, pager, next"
    :total="tabledDataTotal"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange" />
    <!-- 抽屉框 -->
    <div class="api-log-detail-drawer">
      <el-drawer
      v-model="drawerVisible"
      title="接口详情信息"
      direction="ltr"
      size="80%">
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-collapse-item title="基本信息" name="1">
          <p>
            <span font-800>请求地址</span>：<span>{{ tempData.url }}</span>
          </p>
          <p>
            <span font-800>请求方式</span>：<span>{{ tempData.method }}</span>
          </p>
          <p>
            <span font-800>资源类型</span>：<span>{{ tempData.resourceType }}</span>
          </p>
          <p>
            <span font-800>状态码</span>：<span>{{ tempData.status }}</span>
          </p>
          <p>
            <span font-800>请求时间</span>：<span>{{ formatDateTime(tempData.createTime) }}</span>
          </p>
        </el-collapse-item>
        <el-collapse-item title="请求头信息" name="2">
          <p v-for="(v, k) in reqHeaders" :key="k" :label="k">
            <span font-800>{{ k }}</span>：<span>{{ v }}</span>
          </p>
        </el-collapse-item>
        <el-collapse-item title="响应头信息" name="3">
          <p v-for="(v, k) in resHeaders" :key="k" :label="k">
            <span font-800>{{ k }}</span>：<span>{{ v }}</span>
          </p>
        </el-collapse-item>
      </el-collapse>
    </el-drawer>
    </div>
</template>

<style scoped>
.api-log-detail-drawer :deep(.el-drawer__header) {
  margin-bottom: 0;
}
</style>