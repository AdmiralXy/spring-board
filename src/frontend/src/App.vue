<template>
  <router-view></router-view>
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
    fetch("api/message", {
      headers: new Headers({
        'Authorization': 'Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpdmFuIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY0Njc3MTQxNywiZXhwIjoxNjQ2Nzc1MDE3fQ.cKejjwizXk1Ittwqqs4rnWcT3DIFXfrEBcCDnkJGRck'
      }),
    }).then((response) => response.text()).then((data) => this.msg = data)
  }
}
</script>

<style>
@import '../node_modules/typeface-roboto/index.css';
*, html, body {
  font-family: Roboto, sans-serif;
}
</style>
