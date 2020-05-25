<template>
  <div class="post">
    <div class="main">
      <h1>{{Post.Title}}</h1>
      <!-- 预览各项数据 -->
      <div class="preview">
        <div class="identify">
          来自板块：{{Post.PostIdentity}}
        </div>
        
        <div class="browse">
          <i class="el-icon-view"></i>
          {{Post.BrowseNum}}
        </div>
       
        <div class="comment">
           <i class="el-icon-chat-round"></i>
          {{Post.CommentNum}}
        </div>
        
        <div class="likes">
          <i class="el-icon-thumb"></i>
          {{Post.LikesNum}}
        </div>
      </div>
      <!-- 文章发表时间 -->
      <div class="time">
        文章发表：{{Post.Time}} | 最后编辑：{{Post.EditTime}}
      </div>
      <!-- 帖子内容 -->
      <div class="content">
        {{Post.Content}}
      </div>
      <!-- 分割线 -->
      <div class="div"></div>
      <!-- 点赞按钮 -->
      <div class="likes-end">
        <img @click="isLikes = !isLikes" v-if="!isLikes" src="../assets/Icon/Post/likes.png" alt="">
        <img @click="isLikes = !isLikes" v-else src="../assets/Icon/Post/likes_cur.png" alt="">
        {{Post.LikesNum}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Post",
  data() {
    return {
      isLikes:false,
      Post: {
        PostID: 123,
        UserID: 233,
        PostIdentity : 1,
        Content: "帖子内容",
        UserName: "用户名",
        AccessoryPath: "附件路径",
        ImagePath:
          "http://forum.loheagn.com/assets/avatars/MC8w4ARonPhlzlbb.png",
        Title: "写大作业好开心啊",
        LikesNum: 0,
        BrowseNum: 1,
        CommentNum: 2,
        Time:'05-24',
        EditTime:'05-24'
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
<style>
.post .main{
  display: flex;
  line-height: 30px;
  flex-direction: column;
  align-items: center;
  width: 700px;
  border: 1px solid slategray;
  border-radius: 5px;
  margin: 5px;
  padding: 20px;
  background-color: #fff;
}

.post .main .preview{
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 500px;
}

.post .main .likes-end{
  display: flex;
  align-items: center;
}

.post .main .likes-end img{
  width: 40px;
  height:40px
}
.el-main{
  background-color: #f0f1f5 !important;

}
</style>
