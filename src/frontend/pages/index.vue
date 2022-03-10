<template>
  <Layout/>
</template>

<script>
import Layout from "~/layout/default"

export default {
  name: 'IndexPage',
  data() {
    return {
      message: null,
    }
  },
  mounted() {
    // this.$axios.post('message').then((response) => {
    //   this.message = response
    // })
  },
  components: {
    Layout
  }
}
</script>
