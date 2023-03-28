<script setup lang="ts">
import { onMounted, reactive, ref, watch } from 'vue'
import { list } from '@/api/api-log'
import { formatBytes, calcHeight } from '@/utils/util'


</script>

<template>
  <div class="wrapper">
    <el-tabs type="border-card">
      <el-tab-pane label="表格">
        <ApiLogTable />
      </el-tab-pane>
      <el-tab-pane label="列表">
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<style scoped>
.wrapper {
  height: 100%;
}
</style>