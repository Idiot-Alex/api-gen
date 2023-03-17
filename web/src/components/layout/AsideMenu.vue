<script lang="ts" setup>
import { ref } from 'vue'

const collapseState = ref(false)

</script>

<template>
  <el-menu
    default-active="1"
    class="el-menu-vertical-demo"
    :collapse="collapseState"
  >
    <el-menu-item index="1">
      <el-icon><HomeFilled /></el-icon>
      <template #title>使用说明</template>
    </el-menu-item>
    <el-menu-item index="2">
      <el-icon><document /></el-icon>
      <template #title>API 列表</template>
    </el-menu-item>
  </el-menu>
</template>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 100vh;
}
</style>
