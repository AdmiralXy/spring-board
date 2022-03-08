import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const page = path => () => import(`@/pages/${path}.vue`).then(m => m.default || m)

const routes = [
    { path: '/', component: page('Home') },
    { path: '/hello', component: page('Home') },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

createApp(App).use(router).use(ElementPlus).mount('#app')
