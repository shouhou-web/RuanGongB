<template>
  <div class="searchpost">
    <div class="searchhead">
      <input class="searchhead-input" v-model="keyword" type="text" />
      <div class="searchhead-text">
        <button @click="search">搜索</button>
      </div>
    </div>
    <div v-if="searchPost.length > 0" class="discuss">
      <!-- 帖子列表 -->
      <div v-for="(item, index) in searchPost" :key="index">
        <div
          class="discuss-post"
          v-if="canRead(item.postLevel)"
          @click="toPost(item.postID)"
        >
          <div class="discuss-post-header">
            <img class="left" :src="item.imagePath" alt="" />
            <div class="name">
              {{ item.userName }}
            </div>
            <img
              class="level"
              :src="getLevel(item.userLevel)"
              alt="图片无法加载QAQ"
            />
            <div class="time">
              {{ item.createTime }}
            </div>
          </div>
          <!-- 中部 -->
          <div class="discuss-post-middle">
            <div class="title navigation">
              {{ item.title }}
            </div>
            <div class="preview" v-html="item.content"></div>
          </div>
          <div class="discuss-post-bottom">
            <div class="discuss-likes discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/preview.png" alt="" />
              <div class="num">{{ item.browseNum }}</div>
            </div>
            <div class="discuss-comment discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/likes.png" alt="" />
              <div class="num">{{ item.likesNum }}</div>
            </div>
            <div class="discuss-likes discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/comment.png" alt="" />
              <div class="num">{{ item.commentNum }}</div>
            </div>
          </div>
        </div>
        <div class="discuss-post" v-else @click="warn(item.postLevel)">
          <div class="discuss-post-header">
            <img class="left" :src="item.imagePath" alt="" />
            <div class="name">
              {{ item.userName }}
            </div>
            <img
              class="level"
              :src="getLevel(item.userLevel)"
              alt="图片无法加载QAQ"
            />
            <div class="time">
              {{ item.createTime }}
            </div>
            <div class="postLevel">
              <div class="postLevel-content">
                等级Lv.{{ item.postLevel }}以上可浏览
              </div>
            </div>
          </div>
          <!-- 中部 -->
          <div class="discuss-post-middle">
            <div class="title">
              {{ item.title }}
            </div>
            <div class="preview" v-html="item.content"></div>
          </div>
          <div class="discuss-post-bottom">
            <div class="discuss-likes discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/preview.png" alt="" />
              <div class="num">{{ item.browseNum }}</div>
            </div>
            <div class="discuss-comment discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/likes.png" alt="" />
              <div class="num">{{ item.likesNum }}</div>
            </div>
            <div class="discuss-likes discuss-post-bottom-item">
              <img src="../../assets/Icon/Post/comment.png" alt="" />
              <div class="num">{{ item.commentNum }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else class="search-no-data">
      <div class="content">
        <img src="../../assets/img/no-data.png" alt="" />
        <p>搜索不到您要的结果，换个关键词试试呗~</p>
      </div>
    </div>
  </div>
</template>

<script>
import { searchPost } from "../../network/post";
export default {
  name: "SearchPost",
  data() {
    return {
      keyword: "",
      searchPost: []
    };
  },
  created() {
    this.keyword = this.$store.state.key;
    this.searchPost = this.$store.state.searchPost;
  },
  methods: {
    warn(postLevel) {
      this.$message({
        type: "warning",
        message: "该帖子需要用户权限等级Lv." + postLevel + "以上才可浏览~"
      });
    },
    canRead(postLevel) {
      return this.$store.state.user.userLevel >= postLevel;
    },
    search() {
      let keyword = this.keyword;
      this.$store.commit("setSearchKey", keyword);
      if (keyword == "") {
        this.$message({
          type: "warning",
          message: "搜索关键字不能为空哦~"
        });
        return;
      }
      searchPost(keyword)
        .then(res => {
          this.searchPost = res;
          if (res.length == 0) {
            this.$message({
              type: "warning",
              message: "搜索不到您要的结果，换个关键词试试呗~"
            });
          } else {
            this.$store.commit("setSearchPost", res);
          }
        })
        .catch(err => {
          this.$message.error("搜索信息失败了~请检查您的网络");
        });
    },
    toPost(PostID) {
      if (!this.$store.state.token) {
        this.$message({
          message: "登录后才能浏览帖子哦~",
          type: "warning"
        });
        return;
      }
      this.$router.push({
        path: "/post",
        query: { postID: PostID }
      });
    },
    getLevel(level) {
      return "https://img-static.mihoyo.com/level/level" + level + ".png";
    }
  }
};
</script>
<style>
.search-no-data {
  margin-top: 20px;
  background-color: #fff;
  border-radius: 4px;
  padding: 150px 0;
  width: 790px;
  height: 530px;
}

.search-no-data .content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  align-items: center;
}

.search-no-data .content img {
  width: 200px;
}

.search-no-data .content p {
  margin-top: 40px;
  color: #999;
}

.searchhead {
  display: flex;
  height: 100%;
}

.searchhead-text {
  width: 120px;
  font-size: 16px;
}

.searchhead-text button {
  background-color: #ffe14c;
  color: #663c00;
  font-weight: 600;
  height: 50px;
  width: 120px;
  display: inline-flex;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  cursor: pointer;
  outline: none;
  border: none;
  border-radius: 0;
}

.searchhead-input {
  width: 670px;
  height: 50px;
  outline: none;
  font-size: inherit;
  display: inline-block;
  border: none;
  padding: 0 30px;
}

.searchpost {
  margin: 20px 20px 20px 0;
  line-height: 50px;
  width: 790px;
}
</style>
