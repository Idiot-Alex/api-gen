<script setup lang="ts">
import { useRouter } from 'vue-router'

const router = useRouter()
function back() {
  router.back()
}
</script>

<template>
  <ElRow>
    <ElResult w-400px m-a title="错误提示" sub-title="发生了一点小问题...">
      <template #icon>
        <el-icon h-100px w-100px><CircleClose /></el-icon>
      </template>
      <template #extra>
        <ElButton type="primary" @click="back">返回</ElButton>
      </template>
    </ElResult>
  </ElRow>
</template>

<style scoped>
i.ep-icon {
  color: #f19595;
}
.ep-icon svg {
  width: 100%;
  height: 100%;
}
</style>