<template>
  <div class="form-container">
    <div class="form">
      <a-form-model :model="form"
        id="components-form-demo-normal-login"
        :form="form"
        class="login-form"
        @submit="handleSubmit"
      >
        <p class="form__title">Login</p>
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
        <a-form-model-item>
          <a-checkbox v-model="remember">
            Remember me
          </a-checkbox>
          <a-button type="primary" html-type="submit" class="login-form-button"
                    :disabled="!form.username || !form.password"
          >
            Log in
          </a-button>
          or
          <a href="">
            register now!
          </a>
        </a-form-model-item>
      </a-form-model>
    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex'

export default {
  middleware: 'quest',
  data() {
    return {
      form: {
        username: null,
        password: null
      },
      remember: true
    }
  },
  methods: {
    ...mapActions({
      saveToken: 'auth/saveToken'
    }),
    handleSubmit(e) {
      e.preventDefault();
      this.$axios.post('auth/login', this.form).then((response) => {
        this.saveToken({
          token: response.data.token,
          remember: this.remember
        })
        this.$router.push({ name: 'desks' })
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
  padding: 0px 26px 6px 26px;
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
