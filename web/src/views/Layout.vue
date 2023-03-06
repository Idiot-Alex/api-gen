<script lang="ts" setup>

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <AsideMenu />
      </el-aside>
      <el-container>
        <el-header class="header">
          <TopHeader />
        </el-header>
        <el-main>
          <Main />
          <el-divider/>
          <HelloWorld msg="You did it!" />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style scoped>
.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
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