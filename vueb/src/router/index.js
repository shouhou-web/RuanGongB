import Vue from 'vue'
import VueRouter from 'vue-router'

// 首页相关组件
const Home = () => import('../views/Home/Home.vue');
const Discuss = () => import('../views/Home/Discuss.vue');
const Course = () => import('../views/Home/Course.vue');
const School = () => import('../views/Home/School.vue');
const Resource = () => import('../views/Home/Resource.vue');
const Exercise = () => import('../views/Home/Exercise.vue');

//消息相关组件
const ProfileMessage = () => import('../views/Message/ProfileMessage.vue')
const ViewCom = () => import('../views/Message/ViewCom.vue')
const ViewLike = () => import('../views/Message/ViewLike.vue')
const ViewSystem = () => import('../views/Message/ViewSystem.vue')

//个人空间相关组件
const Profile = () => import('../views/Profile/Profile.vue');
const Viewd = () => import('../views/Profile/Viewed.vue')
const Liked = () => import('../views/Profile/Liked.vue')
const MyPost = () => import('../views/Profile/MyPost.vue')
const comment = () => import('../views/Profile/Comment.vue')
const EditInfo = () => import('../views/Profile/EditInfo.vue')
const ProfileLevel = () => import('../views/Profile/ProfileLevel.vue')

// 帖子相关组件
const EditPost = () => import('../views/Post/EditPost.vue')
const Post = () => import('../views/Post/Post.vue')
const SearchPost = () => import('../views/Post/SearchPost.vue')

//管理员相关组件
const ManageProfile = () => import('../views/Manage/ManageProfile.vue') 
const ViewProfile = () => import('../views/Manage/ViewProfile.vue')
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
      {
        path: '/',
        // redirect重定向
        redirect: '/home/discuss'
      },
      {
        path: '/home/discuss',
        name: 'Discuss',
        component: Discuss,
      },
      {
        path: '/home/course',
        name: 'Course',
        component: Course
      },
      {
        path: '/home/school',
        name: 'School',
        component: School,
      },
      {
        path: '/home/resource',
        name: 'Resource',
        component: Resource
      },
      {
        path: '/home/exercise',
        name: 'Exercise',
        component: Exercise
      },
      {
        path: '/home/searchPost',
        name: 'SearchPost',
        component: SearchPost
      },
    ]
  },
  {
    path: '/post',
    name: 'Post',
    component: Post
  },
  {
    path: '/editPost',
    name: 'EditPost',
    component: EditPost
  },
  {
    path: '/editPost',
    name: 'EditPost',
    component: EditPost
  },
  {
    path: '/manageProfile',
    name: 'ManageProfile',
    component: ManageProfile
  },
  {
    path: '/profileMessage',
    name: 'ProfileMessage',
    component: ProfileMessage,
    children: [
      {
        path: '/',
        // redirect重定向
        redirect: '/profileMessage/viewcom'
      },
      {
        path: '/profileMessage/viewcom',
        name: 'ViewCom',
        component: ViewCom
      },
      {
        path: '/profileMessage/viewlike',
        name: 'ViewLike',
        component: ViewLike
      },
      {
        path: '/profileMessage/viewsystem',
        name: 'ViewSyste,',
        component: ViewSystem,
      }

    ]
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    children: [
      {
        path: '/',
        redirect: '/profile/myPost'
      },
      {
        path: '/profile/myPost',
        name: 'MyPost',
        component: MyPost
      },
      {
        path: '/profile/viewed',
        name: 'Viewd',
        component: Viewd
      },
      {
        path: '/profile/comment',
        name: 'Comment',
        component: comment
      },
      {
        path: '/profile/liked',
        name: 'Liked',
        component: Liked
      },
      {
        path: '/profile/editInfo',
        name: 'EditInfo',
        component: EditInfo
      },
      {
        path: '/profile/profileLevel',
        name: 'ProfileLevel',
        component: ProfileLevel
      },
      {
        path: '/profile/viewProfile',
        name: 'ViewProfile',
        component: ViewProfile
      },
    ]
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
