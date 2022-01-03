import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import '@babel/polyfill'
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@fortawesome/fontawesome-free/css/all.css'
import Editor from '@tinymce/tinymce-vue'

Vue.config.productionTip = false

Vue.component('editor', Editor)

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
