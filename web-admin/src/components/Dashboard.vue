<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { statistics } from '~/api/api-log';
import { MyAxiosResponse } from '~/utils/types';

const data = ref({
  totalCount: 0,
  hostCount: 0,
  siteCount: 0,
  hostList: [{host: ''}],
  siteList: [{site: ''}]
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
          <el-statistic text-center title="Api 总数" :value="data.totalCount" />
        </el-col>
        <el-col :span="8">
          <el-statistic text-center title="Host 总数" :value="data.hostCount" />
        </el-col>
        <el-col :span="8">
          <el-statistic text-center title="Site 总数" :value="data.siteCount" />
        </el-col>
      </el-row>
    </el-card>
    <el-row mt-20px :gutter="0">
      <el-col :span="12">
        <el-card v-for="item in data.hostList" :key="item.host">
          {{ item.host }}
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card v-for="item in data.siteList" :key="item.site">
          {{ item.site }}
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style>

</style>