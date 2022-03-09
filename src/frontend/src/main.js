import {createApp} from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from 'vue-router'
import BootstrapVue3 from 'bootstrap-vue-3'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueSweetalert2 from "vue-sweetalert2";
import VuexStore from "@/store/store";

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import 'sweetalert2/dist/sweetalert2.min.css';

const app = createApp(App).use(VueSweetalert2).use(VuexStore);

axios.defaults.baseURL = 'api/'

// axios.interceptors.request.use((request) => {
//     request.headers.common.Authorization = `Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpdmFuIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY0Njc4NzI2NiwiZXhwIjoxNjQ2NzkwODY2fQ.eNdlGNppJZcYQ40Qfm57LD-YV-aw3bj_SYFLXvAYVPw`
//     return request
// })

axios.interceptors.response.use((response) => {
    return response
}, (error) => {
    const { status } = error.response || {}

    if (status >= 500) {
        app.config.globalProperties.$swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Internal server error',
            reverseButtons: true,
            confirmButtonText: 'ok',
            cancelButtonText: 'cancel'
        })
    }

    if (status === 403 && app.config.globalProperties.$store.getters['auth/check']) {
        app.config.globalProperties.$swal.fire({
            icon: 'warning',
            title: 'Unauthorized action',
            text: 'Please log in...',
            reverseButtons: true,
            confirmButtonText: 'ok',
            cancelButtonText: 'cancel'
        }).then(() => {
            app.config.globalProperties.$store.commit('auth/LOGOUT')
            app.config.globalProperties.$router.push({name: 'home'})
        })
    }

    //return Promise.reject(error)
    return false
})

const page = path => () => import(`@/pages/${path}.vue`).then(m => m.default || m)

const routes = [
    { path: '/', name: 'home', component: page('Home') },
    { path: '/register', name: 'register', component: page('Register') },
    { path: '/home', name: 'desks', component: page('Desks') }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

app.use(router).use(BootstrapVue3).use(VueAxios, axios).mount('#app')
