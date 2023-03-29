<script setup>
import { ref, reactive, onMounted, computed } from 'vue'
import { calcHeight } from '@/utils/util'
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
const listHeight = ref('300px')
const tempData = ref({})

onMounted(() => {
  listHeight.value = calcHeight(window.innerHeight, [175]) + 'px'
  loadData()
})

const showData = (id) => {
  tempData.value = tableData.value.filter(item => item.id === id)[0]
  console.log(tempData.value)
}

const headers = computed(() => {
  if (Object.keys(tempData.value).length > 0) {
    return JSON.parse(tempData.value.requestHeaders)
  }
  return {}
})

const loading = ref(false)
const noMore = computed(() => tableData.value.length >= tabledDataTotal.value)
const disabled = computed(() => loading.value || noMore.value)
const loadData = () => {
    loading.value = true
    setTimeout(() => {
      list(tableParams).then(res => {
        console.log(res.records)
        if (res.records) {
          res.records.forEach(element => tableData.value.push(element))
          tableParams.pageNo++
        }
        tabledDataTotal.value = res.total
        loading.value = false
      })
    }, 200)
}

</script>
<template>
  <el-row :gutter="10" :style="{height: listHeight}">
    <el-col :xs="16" :sm="12" :md="8" :lg="6" :xl="2" v-loading="loading" element-loading-text="loading...">
      <div v-infinite-scroll="loadData"
        :infinite-scroll-disabled="disabled"
        infinite-scroll-distance="20"
        infinite-scroll-delay="500"
        :style="{height: listHeight}"
        overflow-scroll p-0 m-0>
        <el-card shadow="hover" v-for="item in tableData" :key="item.id" mb-2 mr-2 cursor-pointer @click="showData(item.id)">
          <el-tooltip
            effect="dark"
            :content="item.url"
            placement="top-start">
            <span block overflow-hidden whitespace-nowrap text-ellipsis>{{ item.url }}</span>
          </el-tooltip>
        </el-card>
        <el-divider v-if="noMore">
          <span>没有更多数据了...</span>
        </el-divider>
      </div>
    </el-col>
    <el-col :xs="8" :sm="12" :md="16" :lg="18" :xl="22" b-l-gray b-l-1 b-l-solid>
      <div :style="{height: listHeight}" ml-2 overflow-y-scroll>
        <el-descriptions
          title="接口详细信息"
          direction="vertical"
          :column="2" border>
          <template #extra>
            <el-button type="primary">Operation</el-button>
          </template>
          <el-descriptions-item label="method" width="100">
            <el-tag v-show="tempData.method" size="small">{{ tempData.method }}</el-tag>
            </el-descriptions-item>
          <el-descriptions-item label="url" label-align="left" align="left" min-width="200">
            <span break-all overflow-y-scroll max-h-100px block>{{ tempData.url }}</span>
          </el-descriptions-item>
          <el-descriptions-item v-if="tempData.text" label="text" label-align="left" align="left" min-width="200">
            <span break-all overflow-y-scroll max-h-100px block>{{ tempData.text }}</span>
          </el-descriptions-item>
        </el-descriptions>
        <el-descriptions
          title="headers"
          :column="2" mt-4 border>
          <el-descriptions-item v-for="(v, k) in headers" :key="k" :label="k" width="200">
            <span break-all overflow-y-scroll max-h-100px block>{{ v }}</span>
          </el-descriptions-item>
        </el-descriptions>
      </div>
    </el-col>
  </el-row>
</template>
<style scoped>
.infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 0 10px 10px 0;
  color: var(--el-color-primary);
  cursor: pointer;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
</style>