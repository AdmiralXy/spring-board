export default {
  // Disable server-side rendering: https://go.nuxtjs.dev/ssr-mode
  ssr: false,

  // Target: https://go.nuxtjs.dev/config-target
  target: 'static',

  // Global page headers: https://go.nuxtjs.dev/config-head
  head: {
    title: 'Spring Board',
    htmlAttrs: {
      lang: 'en'
    },
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '' },
      { name: 'format-detection', content: 'telephone=no' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },

  // Global CSS: https://go.nuxtjs.dev/config-css
  css: [
    'ant-design-vue/dist/antd.css',
    "~/node_modules/bootstrap/dist/css/bootstrap.min.css"
  ],

  // Plugins to run before rendering page: https://go.nuxtjs.dev/config-plugins
  plugins: [
    '@/plugins/antd-ui',
    '~plugins/nuxt-client-init',
    '~/plugins/axios',
    "~/node_modules/bootstrap/dist/js/bootstrap.bundle.min.js"
  ],

  // Auto import components: https://go.nuxtjs.dev/config-components
  components: true,

  // Modules for dev and build (recommended): https://go.nuxtjs.dev/config-modules
  buildModules: [
    '@nuxtjs/google-fonts'
  ],

  // Google fonts
  googleFonts: {
    families: {
      Roboto: true
    }
  },

  // Generate
  generate: {
    routes: [
      '/'
    ]
  },

  // Modules: https://go.nuxtjs.dev/config-modules
  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/router',
    'cookie-universal-nuxt'
  ],

  // Axios global configure
  axios: {
    proxy: true,
    prefix: '/api/'
  },

  // Proxy
  proxy: {
    '/api/': 'http://localhost:8080',
  },

  // Build Configuration: https://go.nuxtjs.dev/config-build
  build: {
  }
}
