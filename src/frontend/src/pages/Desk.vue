<template>
  <BContainer>
    <BRow>
      <BCol :cols="12">
        <button @click="exit">LOGOUT</button>
      </BCol>
    </BRow>
  </BContainer>
  <BContainer>
    <BRow>
      <BCol v-for="item in [1,2,3,4,5,6,7,8,9,10]" :key="item" :cols="3">
        <b-card bg-variant="info" text-variant="light" header="Info" class="my-3">
          {{ item }} Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.
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
