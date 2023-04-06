<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const toPage = (path) => {
  router.push(path);
}

const collapseState = ref(false)

const menus = [
  {
    id: '1',
    name: '首页',
    icon: 'HomeFilled',
    path: '/dashboard'
  },
  {
    id: '2',
    name: 'API 列表',
    icon: 'Document',
    path: '/api-list'
  }
]


</script>

<template>
  <el-menu
    default-active="1"
    class="el-menu-vertical-demo"
    :collapse="collapseState"
  >
    <el-menu-item v-for="menu in menus" :key="menu.id" :index="menu.id" @click="toPage(menu.path)">
      <el-icon>
        <component :is="menu.icon"></component>
      </el-icon>
      <template #title>{{menu.name}}</template>
    </el-menu-item>
  </el-menu>
</template>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 100vh;
}
</style>
