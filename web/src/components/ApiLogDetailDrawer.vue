<script setup>
import { ref, computed, defineProps } from 'vue'
import { formatDateTime } from '@/utils/util'

const props = defineProps({
  visible: Boolean,
  data: Object
})

const emit = defineEmits(['close'])

console.log(props)
// const data = ref(props.data)
const drawerVisible = ref(props.visible)

const reqHeaders = computed(() => {
  // if (Object.keys(props.data.value).length > 0) {
  //   return JSON.parse(props.data.value.requestHeaders)
  // }
  return {}
})

const resHeaders = computed(() => {
  // if (Object.keys(props.data.value).length > 0) {
  //   return JSON.parse(props.data.value.responseHeaders)
  // }
  return {}
})

const close = () => {
  drawerVisible.value = false
  emit('close', drawerVisible.value)
}

</script>
<template>
  <el-drawer
    v-model="drawerVisible"
    @close="close"
    title="接口详情信息"
    direction="ltr"
    size="80%">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="基本信息" name="1">
        <p>
          <span font-800>请求地址</span>：<span>{{ props.data.url }}</span>
        </p>
        <p>
          <span font-800>请求方式</span>：<span>{{ props.data.method }}</span>
        </p>
        <p>
          <span font-800>资源类型</span>：<span>{{ props.data.resourceType }}</span>
        </p>
        <p>
          <span font-800>状态码</span>：<span>{{ props.data.status }}</span>
        </p>
        <p>
          <span font-800>请求时间</span>：<span>{{ formatDateTime(props.data.createTime) }}</span>
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
</template>
<style scoped>

</style>