import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false
Vue.use(ElementUI)
//挂载router
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')


//封装request模块
// import {request} from "./network/request";

// request({
//   url:'/home/multidata'
// }).then(res =>{
//   console.log(res);
// }).catch(err =>{
//   console.log(err)
// })
