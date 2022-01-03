import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import AdminPanel from '../views/AdminPanel';
import AdminLogin from '../views/AdminLogin';
import PostMain from '../views/PostMain';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/admin',
    name: 'Admin',
    component: AdminPanel
  },
  {
    path: '/login',
    name: 'Login',
    component: AdminLogin
  },
  {
    path: '/post/:id',
    name: 'PostMain',
    component: PostMain
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
