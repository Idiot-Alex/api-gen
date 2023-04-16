<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical"
    :collapse="isCollapse"
    @open="handleOpen"
    @close="handleClose"
  >
    <el-menu-item index="1">
      <el-icon><location /></el-icon>
      <template #title>使用说明</template>
    </el-menu-item>
    <el-menu-item index="2">
      <el-icon><icon-menu /></el-icon>
      <template #title>数据面板</template>
    </el-menu-item>
    <el-menu-item index="3">
      <el-icon><setting /></el-icon>
      <template #title>Api 列表</template>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import {
  Location,
  Document,
  Menu as IconMenu,
  Setting,
} from '@element-plus/icons-vue'

const isCollapse = ref(true)
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>

<style scoped>
.el-menu-vertical {
  height: calc(100vh - 60px);
}
.ep-menu-item {
  width: 63px;
}
</style>
