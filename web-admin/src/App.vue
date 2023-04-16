<template>
  <el-config-provider namespace="ep">
    <BaseHeader />
    <div style="display: flex">
      <BaseSide />
      <div class="main-container">
        <Suspense :delay="1" :timeout="3000" :jitter="0.2" v-on:start="onLoadStart" v-on:end="onLoadEnd">
          <template #default>
           <RouterView />
          </template>
          <template #fallback>
            <el-skeleton :rows="5" animated />
          </template>
        </Suspense>
      </div>
    </div>
  </el-config-provider>
</template>

<script setup lang="ts">
const onLoadStart = () => {
  console.log('loading...');
}
const onLoadEnd = () => {
  console.log('loaded');
}
</script>

<style>
#app {
  text-align: center;
  color: var(--ep-text-color-primary);
}

.element-plus-logo {
  width: 50%;
}
</style>
