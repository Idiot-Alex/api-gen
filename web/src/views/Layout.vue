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
        <el-header>
          <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="25" height="25" />
        </el-header>
        <el-main>
          <nav>
            <RouterLink to="/home">Home</RouterLink>
            <RouterLink to="/about">About</RouterLink>
          </nav>
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
</style>