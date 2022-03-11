import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const page = path => () => import(`~/pages/${path}`).then(m => m.default || m)

const routes = [
  {
    path: '/',
    component: page('index'),
    children: [
      { path: '', name: 'login', component: page('login') },
      { path: 'p-register', name: 'register', component: page('register') },
      { path: 'p-desks', name: 'desks', component: page('desks') },
      { path: 'p-desk/:id', name: 'desk', component: page('desk') }
    ]
  }
]

export function createRouter () {
  return new Router({
    routes,
    mode: 'history'
  })
}
