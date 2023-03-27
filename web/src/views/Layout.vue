<script lang="ts" setup>

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <AsideMenu />
      </el-aside>
      <el-container>
        <el-header class="header" h-60px>
          <TopHeader />
        </el-header>
        <el-main class="main-wrapper">
          <RouterView />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style scoped>
.main-wrapper {
  height: calc(100vh - var(--el-header-height));
}
nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}
.header {
  line-height: var(--el-header-height);
  border-bottom: 1px solid var(--color-border);
}
</style>