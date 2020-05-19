<template>
  <div class="discuss">
    <ul>
      <li class="item" v-for="(item, index) in Post" :key="index">
        <img class="left" :src="item.ImagePath" alt="" />
        <div class="middle">
          <div class="title">
            {{ item.Title }}
          </div>
          <br />
          <div class="name">
            {{ item.UserName }}
          </div>
        </div>
        <div class="right">
          <div>
            {{ item.LikesNum }}
          </div>
          <div>
            <i class="el-icon-edit"></i>
            {{ item.CommentNum }}
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
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
            "http://forum.loheagn.com/assets/avatars/MC8w4ARonPhlzlbb.png",
          Title: "写大作业好开心啊",
          LikesNum: 0,
          BrowseNum: 1,
          CommentNum: 2
        }
      ]
    };
  },
  methods: {}
};
</script>
<style>
.item {
  display: flow-root;
  margin: 15px 0px;
}

.middle {
  position: relative;
  left: 20px;
  top: 20px;
  height: 60px;
}

.title {
  float: left;
  font-size: 15px;
  font-weight: bold;
}

.name {
  float: left;
  font-size: 10px;
  margin-top:20px;
}

.left {
  border-radius: 50%;
  width: 70px;
  height: 70px;
  position: relative;
  left: 9px;
  top: 10px;
}
</style>
