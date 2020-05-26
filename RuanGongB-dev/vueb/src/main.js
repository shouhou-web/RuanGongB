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


// 封装request模块
// import {request} from "./network/request";

// request({
//   url:'/home/multidata',
//   params:{
//     type:'sell',
//     page:5
//   }
// }).then(res =>{
//   console.log(res);
// }).catch(err =>{
//   console.log(err)
// })

//并发请求
// const callback1 = axios({
//   url: '/home/multidata',
// })
// const callback2 = axios({
//   url: '/home/data',
//   params:{
//     type:'sell',
//     page:5
//   }
// })
// axios.all([callback1,callback2])
// .then(axios.spread((res1, res2)=>{
//   console.log(res1);
//   console.log(res2)
// }))