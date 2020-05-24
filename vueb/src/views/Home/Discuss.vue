<template>
  <div class="discuss">
    <ul>
      <li
        class="item"
        @click="toPost(item.PostID)"
        v-for="(item, index) in Post"
        :key="index"
      >
        <img class="left" :src="item.ImagePath" alt="" />
        <div class="middle">
          <div class="title">
            {{ item.Title }}
          </div>
          <div class="name">
            {{ item.UserName }}
          </div>
        </div>
        <div class="likes">
          <div>点赞</div>
          <div>{{item.LikesNum}}</div>
        </div>

        <div class="comment">
          <div>评论</div>
          <div>{{item.CommentNum}}</div>
        </div>
      </li>
    </ul>
    <router-view></router-view>
  </div>
</template>

<script>
import { request } from "../../network/request";
export default {
  name: "Discuss",
  data() {
    return {
      Post: [
        {
          PostID: 123,
          UserID: 233,
          UserName: "用户名",
          ImagePath:
            "http://forum.loheagn.com/assets/avatars/MC8w4ARonPhlzlbb.png",
          Title: "写大作业好开心啊",
          LikesNum: 0,
          BrowseNum: 1,
          CommentNum: 2
        },
        {
          PostID: 123,
          UserID: 233,
          UserName: "用户名",
          ImagePath:
            "https://upload.jianshu.io/users/upload_avatars/13511312/c1e31c1b-337a-47cb-8785-aee48c16a85e.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240",
          Title: "vue动态绑定class的几种方式",
          LikesNum: 0,
          BrowseNum: 1,
          CommentNum: 2
        }
      ]
    };
  },
  methods: {
    toPost(PostID) {
      this.$router.push({ path: "/post/" + PostID });
    }
  },
  mounted() {
    request({
      url: "/home/multidata"
    })
      .then(res => {
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });
  }
};
</script>
<style>
.discuss .item {
  display: flex;
}

.discuss .item .left {
  height: 50px;
  width: 50px;
  border-radius: 50%;
  flex-grow: 0.01;
}

.discuss .item .middle {
  height: 60px;
  display: flex;
  flex-direction: column;
  justify-content:space-around;
  align-items: flex-start;
  width: auto;
  flex-grow: 0.79;
}

.discuss .item .title {
  height: 25px;
  width: auto;
}

.discuss .item .name {
  height: 25px;
  width: auto;
}

.discuss .item .likes {
  display: flex;
  flex-grow: 0.1;
}

.discuss .item .comment {
  display: flex;
  flex-grow: 0.1;
}
</style>
