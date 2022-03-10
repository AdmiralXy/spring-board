<template>
  <div class="app-container min-vh-100 min-vw-100">
    <router-view></router-view>
  </div>
  <span class="btn" :hidden="true"></span>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      msg: ''
    }
  },
  mounted() {
    this.$store.dispatch('init/clientInit')
  }
}
</script>

<style>
@import '../node_modules/typeface-roboto/index.css';
*, html, body {
  font-family: Roboto, sans-serif;
}

.app-container {
  background: rgb(195 195 195 / 51%);
}

.btn:focus {
  box-shadow: unset !important;
}

.btn:hover {
  opacity: 0.8;
}
</style>
