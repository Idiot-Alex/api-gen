<script setup>
import Menu from './Menu.vue'
import HelloWorld from '../components/HelloWorld.vue'
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <Menu></Menu>
      </el-aside>
      <el-container>
        <el-header>Header</el-header>
        <el-main>
          <HelloWorld msg="You did it!" />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>