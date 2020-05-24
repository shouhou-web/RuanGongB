<template>
  <div class="discuss">
    <ul
      class="item"
      @click="toPost(item.PostID)"
      v-for="(item, index) in Post"
      :key="index"
    >
      <img class="left" :src="item.ImagePath" alt="" />
      <li class="middle">
        <div class="title">
          {{ item.Title }}
        </div>
        <div class="name">
          {{ item.UserName }}
        </div>
      </li>
      <li class="likes">
        <div>点赞</div>
        <div>{{ item.LikesNum }}</div>
      </li>

      <li class="comment">
        <div>评论</div>
        <div>{{ item.CommentNum }}</div>
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
  align-items: center;
  margin-right: 10px 0px;
  padding: 20px;
}

.discuss .item .left {
  height: 60px;
  width: 60px;
  border-radius: 50%;
  margin-right: 1em;
}

.discuss .item .middle {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-around;
  line-height: 30px;
}

.discuss .item .title {
  font-weight: 600;
  font-size: 20px;
}

.discuss .item .name {
  font-weight: 200;
  font-size: 10px;
}

.discuss .item .likes {
  display: flex;
  line-height: 30px;
}

.discuss .item .comment {
  display: flex;
  line-height: 30px;
}
</style>
