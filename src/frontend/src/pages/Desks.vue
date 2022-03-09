<template>
  <div>
    My desks
  </div>
  <button @click="exit">LOGOUT</button>
</template>

<script>
import {reactive} from 'vue'
import {mapActions} from "vuex";

export default {
  name: 'DesksPage',
  data: function () {
    return {
      form: reactive({
        username: '',
        password: ''
      })
    }
  },
  methods: {
    ...mapActions({
      logout: 'auth/logout'
    }),
    exit() {
      this.logout()
      this.$router.push({name: 'home'})
    }
  }
}
</script>

<style scoped>

</style>
