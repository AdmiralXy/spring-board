<template>
  <BContainer>
    <BRow>
      <BCol :cols="12">
        <nav class="navbar">
          <div class="container-fluid">
            <a class="navbar-brand text-black" href="/">Spring Board</a>
            <b-button @click="exit">Logout</b-button>
          </div>
        </nav>
      </BCol>
    </BRow>
  </BContainer>
  <BContainer>
    <BRow>
      <BCol v-for="item in [1,2,3,4,5,6,7,8,9,10]" :key="item" :cols="3">
        <b-card border-variant="primary"
                :header="'Desk #' + item"
                header-bg-variant="primary"
                header-text-variant="light"
                align="center" class="my-3">
          <b-button>Open</b-button>
        </b-card>
      </BCol>
      <BCol :cols="3">
        <b-card border-variant="success"
                header="Create new"
                header-bg-variant="success"
                header-text-variant="light"
                align="center" class="my-3">
          <b-button>+</b-button>
        </b-card>
      </BCol>
    </BRow>
  </BContainer>
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
