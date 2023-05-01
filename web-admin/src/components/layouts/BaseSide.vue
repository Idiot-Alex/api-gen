<template>
  <el-menu
    :default-active="menuIndex"
    class="el-menu-vertical"
    :collapse="isCollapse"
  >
    <el-menu-item v-for="menu in menus" :key="menu.path" :index="menu.path" @click="toPath(menu.path)">
      <el-icon><component :is="menu.icon" /></el-icon>
      <template #title>{{ menu.title }}</template>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { computed } from '@vue/reactivity'
import { ElLoading } from 'element-plus'
import { reactive, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useMenuStore } from '~/stores/menu'

const route = useRoute()
const menuStore = useMenuStore()

const menuIndex = computed(() => {
  const index = route.path
  if (index !== menuStore.menuIndex) {
    menuStore.changeMenuIndex(index)
  }
  return menuStore.menuIndex
})

const menus = reactive([
  { path: '/guide', title: '使用说明', icon: 'Location' },
  { path: '/dashboard', title: '数据面板', icon: 'Menu' },
  { path: '/api-log-list', title: 'Api 列表', icon: 'Document' },
  { path: '/sys-config-list', title: '系统配置', icon: 'Setting' },
  { path: '/hello', title: 'Hello', icon: 'User' }
])

const isCollapse = ref(true)

const router = useRouter()
const toPath = (path: any) => {
  const loading = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  setTimeout(() => {
    loading.close()
  }, 1000)
  menuStore.changeMenuIndex(path)
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
