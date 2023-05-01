<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { statistics } from '~/api/api-log'
import { MyAxiosResponse } from '~/utils/types'
import { calcHeight } from '~/utils/util'
import useClipboard from 'vue-clipboard3'
import { ElMessage } from 'element-plus'

const data = ref({
  totalCount: 0,
  hostCount: 0,
  siteCount: 0,
  hostList: [{host: '', count: 0}],
  siteList: [{site: '', count: 0}]
})
const colHeight = ref('300px')
const statisRef = ref()

const loadData = () => {
  statistics().then((res: MyAxiosResponse) => {
    if (res.code === 0) {
      data.value = res.data
    }
  })
}

onMounted(() => {
  colHeight.value = calcHeight(window.innerHeight, [130, statisRef.value.$el.clientHeight]) + 'px'
  loadData()
})

const { toClipboard } = useClipboard()
const copyText = (text: string) => {
  toClipboard(text).then(() => {
    ElMessage.success('复制成功')
  }).catch((err) => {
    console.log(err)
    ElMessage.error('复制失败')
  })
}
</script>

<template>
  <div ml-5px m-a>
    <el-card ref="statisRef" class="box-card" m-20px>
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
    <div m-20px>
      <el-timeline>
        <el-timeline-item timestamp="Host 统计（点击可复制）" placement="top">
          <el-card>
            <el-badge m-10px v-for="item in data.hostList" :key="item.host" :value="item.count" type="primary">
              <span>
                <el-tag @click="copyText(item.host)" cursor-pointer>{{ item.host }}</el-tag>
              </span>
            </el-badge>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="Site 统计（点击可复制）" placement="top">
          <el-card>
            <el-badge m-10px v-for="item in data.siteList" :key="item.site" :value="item.count" type="primary">
              <span>
                <el-tag @click="copyText(item.site)" cursor-pointer>{{ item.site }}</el-tag>
              </span>
            </el-badge>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<style scoped>
</style>