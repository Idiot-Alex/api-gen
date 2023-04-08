<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { statistics } from '@/api/api-log'

const statisticsData = ref({})

const loadStatistics = () => {
  statistics().then(res => {
    if (res.code === 0) {
      statisticsData.value = res.data
    }
  })
}

onMounted(() => {
  loadStatistics()
})
</script>

<template>
  <el-row :gutter="12">
    <el-col :span="8">
      <el-card shadow="hover">
        <span>Api 总数：</span>
        <el-tag>{{ statisticsData.totalCount }}</el-tag>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card shadow="hover">
        <span>Api Host 总数：</span>
        <el-tag>{{ statisticsData.hostCount }}</el-tag>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card shadow="hover">
        <span>Api Site 总数：</span>
        <el-tag>{{ statisticsData.siteCount }}</el-tag>
      </el-card>
    </el-col>
  </el-row>
  <HelloWorld msg="You did it!" />
</template>

<style scoped>

</style>