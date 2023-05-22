<script setup lang="ts">

</script>

<template>
  <div m-10px>
    <el-button type="primary" @click="onSubmit">新建 Api 文档</el-button>
    <el-divider mt-2 mb-2/>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="4" :xl="4"  v-for="o in 10" :key="o">
        <el-card mb-5px class="box-card">
          <template #header>
            <span>文档标题</span>
          </template>
          <p class="text item">一共 29 个 Api</p>
          <p class="text item">refer：www.baidu.com</p>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6">
        <el-menu
          class="el-menu-vertical-demo">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><location /></el-icon>
              <span>Navigator One</span>
            </template>
            <el-menu-item-group title="Group One">
              <el-menu-item index="1-1">item one</el-menu-item>
              <el-menu-item index="1-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
              <el-menu-item index="1-3">item three</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
              <template #title>item four</template>
              <el-menu-item index="1-4-1">item one</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-menu-item index="2">
            <el-icon><icon-menu /></el-icon>
            <span>Navigator Two</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <el-icon><document /></el-icon>
            <span>Navigator Three</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><setting /></el-icon>
            <span>Navigator Four</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="18">
        <p>sfasdf</p>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>

</style>