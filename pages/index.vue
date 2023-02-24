<script setup lang="ts">
const route = useRoute()
const runPlaywright = async () => {
  // const browser = await chromium.launch({
  //   args: ["--remote-debugging-port=9222"],
  //   headless: false,
  // });
}
</script>

<template>
  <div>
    <h1>Nuxt Routing set up successfully!</h1>
    <p>Current route: {{ route.path }}</p>
    <a href="https://nuxt.com/docs/getting-started/routing" target="_blank">Learn more about Nuxt Routing</a>
    <button @click="runPlaywright">clicke me</button>

  </div>
</template>
