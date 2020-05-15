import Vue from 'vue'
import VueRouter from 'vue-router'

// 首页相关组件
const Home = () => import('../views/Home/Home.vue');
const Discuss = () => import('../views/Home/Discuss.vue');
const Course = () => import('../views/Home/Course.vue');
const School = () => import('../views/Home/School.vue');
const Resource = () => import('../views/Home/Resource.vue');
const Exercise = () => import('../views/Home/Exercise.vue');

const EditPost = () => import('../views/EditPost.vue')
const ProfileMessage = () => import('../views/ProfileMessage.vue')

const About = () => import('../views/About.vue');
const Register = () => import('../views/Register.vue');
const Profile = () => import('../views/Profile.vue');
//1.安装插件
Vue.use(VueRouter)

//创建router
const routes = [
  {
    path: '/',
    // redirect重定向
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    meta: {
      title: '首页'
    },
    children: [

    ]
  },
  {
    path: '/discuss',
    name: 'Discuss',
    component: Discuss,
  },
  {
    path: '/course',
    name: 'Course',
    component: Course
  },
  {
    path: '/school',
    name: 'School',
    component: School,
  },
  {
    path: '/resource',
    name: 'Resource',
    component: Resource
  },
  {
    path: '/exercise',
    name: 'Exercise',
    component: Exercise
  },
  {
    path: '/editPost',
    name: 'EditPost',
    component: EditPost
  },
  {
    path: '/profileMessage',
    name: 'ProfileMessage',
    component: ProfileMessage
  },
  {
    path: '/about',
    name: 'About',
    meta: {
      title: '关于'
    },
    component: About
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/profile/:UserID',
    name: 'Profile',
    component: Profile
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  // ${//to and from are Route Object,next() must be called to resolve the hook}
  // 这里是修改名字的全局守护路由，暂不应用
  // document.title = to.matched[0].meta.title;
  next();

})

export default router
