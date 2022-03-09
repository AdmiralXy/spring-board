<template>
  <div class="d-flex min-vh-100 min-vw-100 align-items-center justify-content-center">
    <BContainer>
      <BRow class="justify-content-md-center">
        <BCol :cols="12" :lg="5">
          <b-form class="form border shadow rounded-3 pl-lg-5 p-sm-4 p-3" @submit="register">
            <p class="fs-4 text-center">Register</p>
            <b-form-group
                id="input-group-1"
                label="Username:"
                label-for="input-1"
                description="We'll never share your username with anyone else."
            >
              <b-form-input
                  id="input-1"
                  v-model="form.username"
                  placeholder="Enter username"
                  required
              ></b-form-input>
            </b-form-group>
            <b-form-group id="input-group-2" label="Password:" label-for="input-2">
              <b-form-input
                  id="input-2"
                  v-model="form.password"
                  placeholder="Enter password"
                  required
              ></b-form-input>
            </b-form-group>
            <b-form-group id="input-group-2" label="Password confirmation:" label-for="input-2">
              <b-form-input
                  id="input-2"
                  v-model="form.password_confirmation"
                  placeholder="Enter password again"
                  required
              ></b-form-input>
            </b-form-group>
            <p class="form__error small text-danger" :hidden="!form.error">This username is already taken!</p>
            <Button :loading="loading">Register</Button>
            <span class="px-3">or</span>
            <router-link :to="{ name: 'home' }">
              <b-button>Login</b-button>
            </router-link>
          </b-form>
        </BCol>
      </BRow>
    </BContainer>
  </div>
</template>

<script>
import {reactive} from 'vue'
import {mapGetters, mapActions} from 'vuex'
import Button from "@/components/Button";

export default {
  name: 'HomePage',
  data: function () {
    return {
      form: reactive({
        username: '',
        password: '',
        password_confirmation: '',
        error: false
      })
    }
  },
  computed: {
    ...mapGetters({
      loading: 'preloader/loading',
      check: 'auth/check'
    }),
  },
  methods: {
    ...mapActions({
      setLoadingStatus: 'preloader/setLoadingStatus',
      saveToken: 'auth/saveToken'
    }),
    async register() {
      this.setLoadingStatus(true)
      this.axios.post("auth/register", this.form).then(() => {
        this.axios.post("auth/login", this.form).then((response) => {
          this.saveToken(response.data.token)
          this.form.error = false
          this.$router.push({name: 'desks'})
        }).catch(() => this.form.error = true).finally(() => {
          this.setLoadingStatus(false)
        })
      }).catch(() => this.form.error = true).finally(() => {
        this.setLoadingStatus(false)
      })
    }
  },
  mounted() {
    if (this.check)
      this.$router.push({name: 'desks'})
  },
  components: {
    Button
  }
}
</script>

<style scoped>
.form {
  background: #fff;
  border: 1px solid #888888;
}
</style>

<style>
.form__error {
  border: 1px solid #fb000017;
  padding: 8px;
  background: #fb000017;
}
</style>
