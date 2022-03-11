<template>
  <div class="form-container">
    <div class="form">
      <a-form-model :model="form"
        id="components-form-demo-normal-login"
        :form="form"
        class="login-form"
        @submit="handleSubmit"
      >
        <p class="form__title">Register</p>
        <a-form-model-item>
          <a-input placeholder="Username" v-model="form.username">
            <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item>
          <a-input type="password" placeholder="Password" v-model="form.password">
            <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-alert class="form__error" v-if="error" type="error" message="This username is already taken" banner />
        <a-form-model-item>
          <a-checkbox v-model="remember">
            Remember me
          </a-checkbox>
          <a-button type="primary" html-type="submit" class="login-form-button" :loading="loadingStatus"
                    :disabled="!form.username || !form.password"
          >
            Sign up
          </a-button>
          or
          <a-button class="form__link" type="link" @click="$router.push({ name: 'login' })">
            login now!
          </a-button>
        </a-form-model-item>
      </a-form-model>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  middleware: 'quest',
  data() {
    return {
      form: {
        username: null,
        password: null
      },
      remember: true,
      error: false
    }
  },
  computed: {
    ...mapGetters({
      loadingStatus: 'preloader/loadingStatus'
    })
  },
  methods: {
    ...mapActions({
      saveToken: 'auth/saveToken',
      setLoadingStatus: 'preloader/setLoadingStatus'
    }),
    handleSubmit(e) {
      this.error = false
      this.setLoadingStatus(true)
      e.preventDefault();
      this.$axios.post('auth/register', this.form).then(() => {
        this.$axios.post('auth/login', this.form).then((response) => {
          this.saveToken({
            token: response.data.token,
            remember: true
          })
          this.$router.push({ name: 'desks' })
        }).catch(() => { this.error = true })
      }).catch(() => { this.error = true }).finally(() => {
        this.setLoadingStatus(false)
      })
    },
  }
}
</script>

<style>
.form-container {
  height: 100%;
}

.form {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-form {
  padding: 0 26px 6px 26px;
  border: 1px solid #88888830;
  border-radius: 6px;
}

.form__title {
  padding-top: 3rem;
  text-align: center;
  font-size: 24px;
  font-weight: 600;
}

#components-form-demo-normal-login .login-form {
  max-width: 300px;
}

#components-form-demo-normal-login .login-form-forgot {
  float: right;
}

#components-form-demo-normal-login .login-form-button {
  width: 100%;
}
</style>
