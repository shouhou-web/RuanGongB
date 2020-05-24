<template>
  <div class="post">
    <h1>{{ postID }}</h1>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Post",
  data() {
    return {
      Post: {
        PostID: 123,
        UserID: 233,
        Title: "帖子标题",
        Content: "帖子内容",
        UserName: "用户名",
        AccessoryPath: "附件路径",
        ImagePath:
          "http://forum.loheagn.com/assets/avatars/MC8w4ARonPhlzlbb.png",
        Title: "写大作业好开心啊",
        LikesNum: 0,
        BrowseNum: 1,
        CommentNum: 2
      },
      Comment:[
        {
          CommentID:1,
          UserID:233,
          PostID:123,
          Content:'评论内容',
          UserName:'用户名',
          ImagePath:'https://bbs.mihoyo.com/bh3/accountCenter/postList?id=4018242',
          UserLevel:2
        }
      ]
    };
  },
  methods: {},
  computed: {
    postID() {
      return this.$route.params.PostID;
    }
  },
  created() {
    //发送请求
    console.log(this.$route.query.id);
  }
};
</script>
