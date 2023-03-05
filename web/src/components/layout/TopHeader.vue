<script lang="ts" setup>
import { useDark, useToggle } from '@vueuse/core'

const isDark = useDark()
// const toggleDark = useToggle(isDark)
</script>

<template>
  <div>
    <el-switch
      v-model="isDark"
      class="mt-2"
      inline-prompt
      style="--el-switch-on-color: var(--el-color-black); --el-switch-off-color: var(--el-color-primary)"
      active-icon="Moon"
      inactive-icon="Sunny"
    />
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="25" height="25" />
  </div>
</template>

<style scoped>
</style>