<template>
  <a-layout id="layout">
    <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
      <div class="logo">Spring Board</div>
      <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">
        <a-menu-item key="1">
          <a-icon type="user" />
          <span>Login</span>
        </a-menu-item>
        <a-menu-item key="2">
          <a-icon type="unordered-list" />
          <span>My desks</span>
        </a-menu-item>
        <a-menu-item key="3" @click="openGithub">
          <a-icon type="github" />
          <span>Github</span>
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
        <Nuxt/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
export default {
  data() {
    return {
      collapsed: true
    }
  },
  methods: {
    openGithub() {
      window.open(
        'https://github.com/AdmiralXy/spring-board',
        '_blank' // <- This is what makes it open in a new window.
      )
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
  font-size: 13px;
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
}

</style>
