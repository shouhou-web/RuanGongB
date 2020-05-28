<template>
  <div class="post">
    <!-- 左主界面 -->
    <div class="main">
      <!-- 文章主体 -->
      <div class="article">
        <h1>{{ post.title }}</h1>
        <!-- 预览各项数据 -->
        <div class="preview">
          <div class="identify">来自板块：{{ post.type }}</div>

          <div class="preview-browse">
            <i class="el-icon-view"></i>
            {{ post.browseNum }}
          </div>

          <div class="preview-comment">
            <i class="el-icon-chat-round"></i>
            {{ post.commentNum }}
          </div>

          <div class="preview-likes">
            <i class="el-icon-thumb"></i>
            {{ post.likesNum }}
          </div>
        </div>
        <!-- 文章发表时间 -->
        <div class="time">
          文章发表：{{ post.createTime }} | 最后编辑：{{ post.editTime }}
        </div>
        <!-- 帖子内容 -->
        <div class="content" v-html="post.content">
          {{ post.content }}
        </div>
        <!-- 分割线 -->
        <div class="div"></div>
        <!-- 点赞按钮 -->
        <div class="likes-end">
          <img
            @click="isLikes = !isLikes"
            v-if="!isLikes"
            src="../assets/Icon/Post/likes.png"
            alt=""
          />
          <img
            @click="isLikes = !isLikes"
            v-else
            src="../assets/Icon/Post/likes_cur.png"
            alt="图片无法加载QAQ"
          />
          {{ post.likesNum }}
        </div>
      </div>
      <!-- 评论框 -->
      <div class="new-comment">
        <div class="title">
          看帖是喜欢，评论才是真爱：
        </div>
        <textarea
          class="textarea"
          type="textarea"
          placeholder="请开始你的表演"
          v-model="newcomment"
          maxlength="1000"
          show-word-limit
        >
        </textarea>
        <el-button class="comment-submit" type="primary">评论</el-button>
      </div>
      <!-- 评论区 -->
      <ul class="comment">
        <li class="comment-item" v-for="(item, index) in comment" :key="index">
          <img class="img" :src="item.imagePath" alt="图片无法加载QAQ" />
          <div class="main">
            <div class="main-top">
              <div class="main-top-title">
                <div class="name">
                  {{ item.userName }}
                </div>
                <div class="level">Lv.{{ item.level }}</div>
              </div>
              <div class="main-top-manage">管理</div>
            </div>
            <div class="content">
              {{ item.content }}
            </div>
            <div class="bottom">
              <div class="date">
                {{ item.date }}
              </div>
              <div class="likes">
                <img
                  class="likes-img"
                  v-if="!item.isLikes"
                  src="../assets/Icon/Post/likes.png"
                  alt="图片无法加载QAQ"
                />
                <img
                  class="likes-img"
                  v-else
                  src="../assets/Icon/Post/likes_cur.png"
                  alt="图片无法加载QAQ"
                />
                {{ post.likesNum }}
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <!-- 右侧展示栏 -->
    <div class="aside">
      <!-- 作者 -->
      <div class="author">
        <img :src="post.imagePath" alt="图片无法加载QAQ" />
        <div class="author-content">
          <div class="author-name">
            ♤Ansel♤
          </div>
          <div class="author-level">
            Lv.9
          </div>
        </div>
      </div>
      <!-- 所属板块 -->
      <div class="section-head">
        所属板块
      </div>
      <div class="div"></div>
      <div class="section-body">
        <img src="../assets/Icon/Post/section.png" alt="图片无法加载QAQ" />
        <div class="content">
          {{ post.type }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { downPost } from "../network/post";
export default {
  name: "Post",
  data() {
    return {
      type: [
        "新手上路",
        "讨论区",
        "课程推荐",
        "校园周边",
        "资源共享",
        "刷题板块"
      ],
      isLikes: false,
      newcomment: "",
      post: {
        postID: 123,
        userID: 233,
        postIdentity: 1,
        content: `<div>帖子内容</div>`,
        userName: "用户名",
        accessoryPath: "附件路径",
        imagePath:
          "http://forum.loheagn.com/assets/avatars/MC8w4ARonPhlzlbb.png",
        title: "写大作业好开心啊",
        likesNum: 0,
        browseNum: 1,
        commentNum: 2,
        createTime: "05-24",
        editTime: "05-24"
      },
      User: {},
      comment: [
        {
          commentID: 1,
          userID: 233,
          postID: 123,
          level: 9,
          date: "05-21",
          likesNum: 25,
          content:
            "这个大作业是今天写完还是明天写完，我永远也不知道，也许写不完了",
          userName: "用户名",
          imagePath:
            "https://img-static.mihoyo.com/communityweb/upload/b847b9027dc47246d1e2b11b172764b4.png",
          userLevel: 2
        },
        {
          commentID: 1,
          userID: 233,
          postID: 123,
          level: 9,
          date: "05-21",
          likesNum: 25,
          content: "评论内容",
          userName: "用户名",
          imagePath:
            "https://img-static.mihoyo.com/communityweb/upload/b847b9027dc47246d1e2b11b172764b4.png",
          userLevel: 2
        }
      ]
    };
  },
  methods: {},
  computed: {
    postID() {
      this.$route.query;
      return this.$route.qu.PostID;
    }
  },
  created() {
    //发送请求
    console.log(this.$route.query.postID);
    let postID = this.$route.query.postID;
    downPost(postID).then(res => {
      //下载帖子数据
      this.post = post;
    });
  }
};
</script>
<style>
.post {
  display: flex;
  margin-top: 30px;
  height: auto;
}
/* 主文章 */
.post .main .article {
  display: flex;
  line-height: 30px;
  flex-direction: column;
  align-items: center;
  width: 700px;
  border-radius: 5px;
  margin: 5px;
  padding: 20px;
  background-color: #fff;
}

.post .main .article .preview {
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 500px;
}

.post .main .article .content {
  margin: 20px 0;
  padding: 20px;
}

.post .main .article .div {
  height: 2px;
  background-color: lightgrey;
  width: 80%;
}

.post .main .article .likes-end {
  display: flex;
  align-items: center;
  margin: 20px;
  padding: 20px;
}

.post .main .article .likes-end img {
  width: 40px;
  height: 40px;
  margin-right: 20px;
}

/* 侧边栏 */
.post .aside {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
  padding: 5px;
}

/* 侧边栏-作者 */
.post .aside .author {
  display: flex;
  justify-content: space-around;
  align-items: center;
  border-radius: 5px;
  background-color: #fff;
  width: 280px;
  height: 160px;
  padding: 20px;
}

.post .aside .author .author-content {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  line-height: 30px;
}

/* 侧边栏-所属模块 */
.post .aside .section-head {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fff;
  width: 280px;
  height: 80px;
  margin-top: 30px;
}

.post .aside .div {
  height: 1px;
  background-color: lightgrey;
  width: 100%;
}

.post .aside .section-body {
  background-color: #fff;
  display: flex;
  align-items: center;
  width: 280px;
  height: 90px;
  padding: 20px;
}

.post .aside .section-body .content {
  margin-left: 20px;
}

.post .aside .author img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
}

/* 评论输入 */
.post .main .new-comment {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  width: 700px;
  height: 245px;
  padding: 30px 50px;
  background-color: #fff;
  margin: 20px 5px;
  border-radius: 5px;
}

.post .main .new-comment .title {
  display: flex;
  justify-content: flex-start;
  line-height: 30px;
  font-size: 14px;
  color: #ccc;
}

.post .main .new-comment .textarea {
  border: 0;
  border: 1px solid #f0f1f5;
  border-radius: 5px;
  background-color: #fff;
  width: 600px;
  height: 100px;
  padding: 10px;
  resize: none;
  outline: none;
}

.comment-submit {
  widows: 86px;
  height: 34px;
  margin: 20px 10px 5px 500px;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 评论样式 */
.post .main .comment {
  margin-bottom: 30px;
}

.post .main .comment .comment-item {
  background-color: #fff;
  width: 700px;
  height: 168px;
  padding: 24px 50px;
  line-height: 22px;
  font-size: 14px;
  display: flex;
  justify-content: flex-start;
  margin: 0px 5px;
  border-radius: 5px;
}

.post .main .comment .img {
  width: 44px;
  height: 44px;
  border-radius: 50%;
}

.post .main .comment .main {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  line-height: 30px;
  padding: 0 10px;
}

.post .main .comment .main .main-top {
  display: flex;
  justify-content: flex-start;
  width: 500px;
  font-size: 14px;
  line-height: 22px;
}

.post .main .comment .main .main-top-title {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 44px;
  height: 44px;
}

.post .main .comment .main .main-top-manage {
  margin-left: 400px;
  color: #fff;
  font-size: 14px;
  width: 60px;
}

.post .main .comment .main .main-top-manage:hover {
  color: #ccc;
}

.post .main .comment .main .bottom {
  display: flex;
  flex-direction: row;
  margin-top: 5px;
}

.post .main .comment .main .bottom .date {
  color: #ccc;
  font-size: 14px;
}

.post .main .comment .main .bottom .likes {
  margin-left: 400px;
  display: flex;
  width: 30px;
  justify-content: space-between;
  align-items: center;
}

.post .main .comment .main .bottom .likes-img {
  width: 15.33px;
  height: 15.33px;
}

.el-main {
  background-color: #f0f1f5 !important;
  height: auto;
}
</style>
