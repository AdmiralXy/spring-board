<template>
  <a-spin :spinning="loadingStatus">
    <a-icon slot="indicator" type="loading" style="font-size: 32px" spin />
    <a-layout id="layout">
      <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
        <div class="logo">Spring Board</div>
        <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">
          <a-menu-item key="1" @click="$router.push({ name: 'login' }).catch(() => {})" v-if="!authenticated">
            <a-icon type="user"/>
            <span>Login</span>
          </a-menu-item>
          <a-menu-item key="2" @click="$router.push({ name: 'desks' }).catch(() => {})" v-if="authenticated">
            <a-icon type="unordered-list"/>
            <span>My desks</span>
          </a-menu-item>
          <a-menu-item key="3" @click="openGithub">
            <a-icon type="github"/>
            <span>Github</span>
          </a-menu-item>
          <a-menu-item key="9" @click="exit" v-if="authenticated">
            <a-icon type="logout"/>
            <span>Logout</span>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout>
        <a-layout-header style="background: #fff; padding: 0">
          <a-icon
            class="trigger"
            :type="collapsed ? 'menu-unfold' : 'menu-fold'"
            @click="() => (collapsed = !collapsed)"
          />
        </a-layout-header>
        <a-layout-content
          :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
        >
          <div class="container" style="height: 100%">
            <Nuxt/>
          </div>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-spin>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  data() {
    return {
      collapsed: true
    }
  },
  computed: {
    ...mapGetters({
      authenticated: 'auth/token',
      loadingStatus: 'preloader/loadingStatus'
    })
  },
  methods: {
    openGithub() {
      window.open(
        'https://github.com/AdmiralXy/spring-board',
        '_blank' // <- This is what makes it open in a new window.
      )
    },
    ...mapActions({
      logout: 'auth/logout'
    }),
    exit() {
      this.logout()
      this.$router.push("/")
    }
  }
}
</script>

<style>
*, html, body {
  font-family: Roboto, sans-serif;
}

#layout {
  height: 100vh;
}

#layout .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#layout .trigger:hover {
  color: #1890ff;
}

#layout .logo {
  color: #fff;
  font-size: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}

.ant-layout-sider-collapsed .ant-layout-sider-children .logo {
  font-size: 6px !important;
}

.ant-layout-content {
  min-height: unset !important;
}

.form__error {
  margin-bottom: 15px;
}

.ant-alert-message {
  font-size: 11px;
}

.form__link {
  padding: 0 15px 0 1px;
}

.ant-spin-nested-loading > div > .ant-spin {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 4;
  display: block;
  width: 100%;
  height: 100%;
  max-height: 100%;
}

.ant-btn-success {
  color: #fff;
  background-color: #05a95c;
  border-color: #039650;
  text-shadow: 0 -1px 0 rgb(0 0 0 / 12%);
  box-shadow: 0 2px 0 rgb(0 0 0 / 5%);
}

@media screen and (max-width: 455px) {
  .ant-layout-sider-collapsed {
    display: none !important;
  }

  .ant-layout-content {
    padding: 14px !important;
  }

  .ant-form label {
    font-size: 12px !important;
  }

  .login-form-forgot {
    font-size: 12px !important;
  }

  .ant-layout-sider.ant-layout-sider-dark + .ant-layout .ant-layout-content {
    display: none;
  }

  .ant-layout-sider.ant-layout-sider-dark.ant-layout-sider-collapsed + .ant-layout .ant-layout-content {
    display: block;
  }
}

</style>
