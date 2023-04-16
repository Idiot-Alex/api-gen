<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical"
    :collapse="isCollapse"
    @open="handleOpen"
    @close="handleClose"
  >
    <el-menu-item v-for="menu in menus" :key="menu.path" :index="menu.path" @click="toPath(menu.path)">
      <el-icon><component :is="menu.icon"></component></el-icon>
      <template #title>{{ menu.title }}</template>
    </el-menu-item>
    <el-menu-item index="2">
      <el-icon><icon-menu /></el-icon>
      <template #title>数据面板</template>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router';

const menus = reactive([
  { path: '/guide', title: '使用说明', icon: 'Location' },
  { path: '/dashboard', title: '数据面板', icon: 'Menu' },
  { path: '/api-list', title: 'Api 列表', icon: 'Setting' }
])

const isCollapse = ref(true)
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const router = useRouter()
const toPath = (path: any) => {
  router.push({path})
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
