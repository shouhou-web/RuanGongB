<template>
  <div class="editpost">
    <div class="editpost-header">
      发布帖子
    </div>
    <div class="editpost-main">
      <div class="edit-title">
        <div class="edit-title-t edit-left">
          标题：
        </div>
        <input
          class="edit-title-i"
          v-model="title"
          type="text"
          placeholder="帖子标题"
        />
      </div>
      <div class="edit-type">
        <div class="edit-type-t edit-left">
          帖子类型：
        </div>
        <div class="edit-type-l">
          <label class="edit-type-li">
            <input
              name="type"
              type="radio"
              id="0"
              value="0"
              v-model="postIdentity"
            />
            新手上路
          </label>
          <label
            class="edit-type-li"
            v-for="(item, index) in type"
            :key="index"
            :for="item.postIdentity"
          >
            <input
              name="type"
              type="radio"
              :id="item.postIdentity"
              :value="item.postIdentity"
              v-model="postIdentity"
            />
            {{ item.label }}
          </label>
        </div>
      </div>
      <div class="edit-content">
        <div class="edit-content-t edit-left">
          内容：
        </div>
        <editor
          class="edit-content-e"
          v-model="content"
          upload-url="/upload/image"
          fileName="file"
        />
      </div>
      <!-- <neweditor></neweditor> -->
    </div>
    <div class="edit-footer">
      <button class="edit-upload" @click="submitPost">上传</button>
    </div>
  </div>
</template>
<script>
import editor from "../components/common/v-editor";
import { uploadPost } from "../network/post";
export default {
  name: "EditPost",
  data() {
    return {
      content: "",
      title: "",
      postIdentity: null,
      type: [
        {
          label: "讨论区",
          postIdentity: 1
        },
        {
          label: "课程推荐",
          postIdentity: 2
        },
        {
          label: "校园周边",
          postIdentity: 3
        },
        {
          label: "资源共享",
          postIdentity: 4
        },
        {
          label: "刷题板块",
          postIdentity: 5
        }
      ],
      post: {}
    };
  },
  components: {
    editor
  },
  created() {
    //发送请求
    console.log(this.$route.query.post);
    let post = this.$route.query.post;
    this.title = post.title;
    this.content = post.content;
    this.postIdentity = post.postIdentity;
  },
  methods: {
    submitPost() {
      console.log(this.$store.state.user.userID);
      console.log(this.title);
      console.log(this.postIdentity);
      console.log(this.content);
      let userID = this.$store.state.user.userID;
      let title = this.title;
      let postIdentity = this.postIdentity;
      let content = this.content;
      // switch (type) {
      //   case "新手上路":
      //     postIdentity = 0;
      //     break;
      //   case "讨论区":
      //     postIdentity = 1;
      //     break;
      //   case "课程推荐":
      //     postIdentity = 2;
      //     break;
      //   case "校园周边":
      //     postIdentity = 3;
      //     break;
      //   case "资源共享":
      //     postIdentity = 4;
      //     break;
      //   case "刷题板块":
      //     postIdentity = 5;
      //     break;
      // }
      uploadPost(userID, title, postIdentity, content)
        .then(res => {
          console.log(res);
          this.$message({
            message: "帖子上传成功",
            type: "success"
          });
          this.$router.push({
            path: "/post",
            query: { postID: res }
          });
        })
        .catch(err => {
          this.$message.error("上传失败了~请检查网络并再次尝试");
          return;
        });
    }
  }
};
</script>
<style>
.editpost-header {
  widows: 1000px;
  height: 50px;
  padding: 0px 30px;
  font-size: 16px;
  font-weight: 600;
  line-height: 50px;
  border-bottom: 1px solid #e9eef3;
  display: flex;
}

.edit-left {
  width: 100px;
  display: inline-flex;
  line-height: 50px;
  flex-shrink: 0;
  outline: none;
}

.edit-title {
  display: flex;
}

.edit-title-i {
  position: relative;
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  z-index: 2;
  border: none;
  color: #333;
  padding: 0 20px;
  background-color: transparent;
  font-size: inherit;
  width: 100px;
  border: 1px solid #ebebeb;
  border-radius: 4px;
}

.edit-type {
  display: flex;
}

.edit-type-l {
  display: flex;
  line-height: 50px;
  align-content: center;
  width: 740px;
  justify-content: space-around;
}

.edit-content {
  display: flex;
}

.edit-content-e {
  width: 740px;
}

.edit-footer {
  display: flex;
  justify-content: center;
  width: 1000px;
  margin: 10px;
}

.edit-upload {
  background-color: #00d5ff;
  color: #fff;
  border-radius: 4px;
  width: 190px;
  height: 42px;
  outline: none;
  border: none;
}

.ql-editor {
  height: 400px;
}

.el-main {
  background-color: #e9eef3;
}

.editpost-main {
  height: auto;
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: 642px;
  padding: 50px 80px 40px;
}

.editpost {
  display: flex;
  flex-direction: column;
  padding-bottom: 70px;
  margin-top: 30px;
  background-color: #fff;
  border-radius: 4px;
}
</style>
