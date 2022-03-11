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
  components: {
    Layout
  }
}
</script>
