<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { statistics } from '~/api/api-log';
import { MyAxiosResponse } from '~/utils/types';

const data = ref({
  totalCount: 0,
  hostCount: 0,
  siteCount: 0,
  hostList: [],
  siteList: []
})

const loadData = () => {
  statistics().then((res: MyAxiosResponse) => {
    if (res.code === 0) {
      data.value = res.data
    }
  })
}

onMounted(() => {
  loadData()
})
</script>

<template>
  <div ml-5px m-a>
    <el-card class="box-card" mt-5px>
      <el-row>
        <el-col :span="8">
          <el-statistic title="Api 总数" :value="data.totalCount" />
        </el-col>
        <el-col :span="8">
          <el-statistic title="Host 总数" :value="data.hostCount" />
        </el-col>
        <el-col :span="8">
          <el-statistic title="Site 总数" :value="data.siteCount" />
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style>

</style>