<script lang="ts" setup>
import { toggleDark } from '~/composables'
import { ArrowRight } from '@element-plus/icons-vue'
import { useRoute } from 'vue-router'
import { computed, onMounted, ref, watch } from 'vue'
import { menus } from '~/utils/menu' 

const route = useRoute()
const currentPath:any = ref<string>()

onMounted(() => {
  currentPath.value = route.path
})

watch(route, () => {
  currentPath.value = route.path
})

const menuName = computed(() => {
  const menu = menus.find((menu) => menu.path === currentPath.value)
  return menu ? menu.title : ''
})

</script>

<template>
  <div flex flex-nowrap class="header-wrap">
    <el-menu :ellipsis="false" default-active="1" class="header-menu" mode="horizontal">
      <el-menu-item flex-grow index="1">Api Gen</el-menu-item>
      <el-menu-item h="full" @click="toggleDark()">
        <button class="border-none w-full bg-transparent cursor-pointer" style="height: var(--ep-menu-item-height);">
          <i inline-flex i="dark:ep-moon ep-sunny" />
        </button>
      </el-menu-item>
    </el-menu>
    <el-divider direction="vertical" h-58px />
    <el-breadcrumb class="header-breadcrumb" :separator-icon="ArrowRight">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item v-if="menuName" :to="{ path: currentPath }">
        {{ menuName }}
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<style scoped>
.header-wrap {
  width: 100%;
  border-bottom: solid 1px var(--ep-menu-border-color);
}
.header-menu {
  width: 240px;
  border-bottom: none;
}
.header-breadcrumb {
  line-height: var(--ep-menu-item-height);
}
</style>