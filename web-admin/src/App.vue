<template>
  <el-config-provider namespace="ep">
    <BaseHeader />
    <div style="display: flex">
      <BaseSide />
      <div class="main-container">
        <el-skeleton v-if="showSkeleton" :rows="5" animated w-70vw mt-4 m-a/>
        <RouterView v-else />
      </div>
    </div>
  </el-config-provider>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'

const showSkeleton = ref(true)
onMounted(() => {
  setTimeout(() => {
    showSkeleton.value = false
  }, 1000)
})
</script>
<style>
#app {
  text-align: center;
  color: var(--ep-text-color-primary);
}

.element-plus-logo {
  width: 50%;
}

.main-container {
  width: calc(100vw - 70px);
  height: calc(100vh - 62px);
  overflow: auto;
}
</style>
