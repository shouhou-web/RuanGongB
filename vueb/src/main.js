import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import store from './store/index'
import 'element-ui/lib/theme-chalk/index.css';
// import VueQuillEditor from 'vue-quill-editor'
// import 'quill/dist/quill.core.css'
// import 'quill/dist/quill.snow.css'
// import 'quill/dist/quill.bubble.css'
  
// Vue.use(VueQuillEditor)
Vue.config.productionTip = false
Vue.use(ElementUI)
//挂载router
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')