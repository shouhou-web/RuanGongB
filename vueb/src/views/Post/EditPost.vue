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
          <label v-if="isAdmin" class="edit-type-li">
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
      <div class="edit-title">
        <div class="edit-title-t edit-left">
          阅读权限：
        </div>
        <el-select v-model="postLevel" placeholder="请选择帖子的最低阅读等级">
          <el-option
            v-for="item in option"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <div class="edit-content">
        <div class="edit-content-t edit-left">
          内容：
        </div>
        <quill-editor
          class="edit-content-e"
          ref="myTextEditor"
          v-model="content"
          :options="quillOption"
        >
        </quill-editor>
        <!-- <editor
          class="edit-content-e"
          v-model="content"
          upload-url="/upload/image"
          fileName="file"
        /> -->
      </div>
      <!-- <neweditor></neweditor> -->
    </div>
    <div class="edit-footer">
      <button class="edit-upload" @click="submitPost">上传</button>
    </div>
  </div>
</template>
<script>
// import editor from "../components/common/v-editor";
import { uploadPost } from "../../network/post";
import { quillEditor } from "vue-quill-editor";
import quillConfig from "../../components/common/quill-config";
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
export default {
  name: "EditPost",
  data() {
    return {
      quillOption: quillConfig,
      content: "",
      title: "",
      postLevel: 1,
      postID: 0,
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
  created() {
    //发送请求
    console.log(this.$route.query.post);
    let post = this.$route.query.post;
    if (post != null) {
      this.postID = post.postID;
      this.title = post.title;
      this.content = post.content;
      this.postIdentity = post.postIdentity;
    }
  },
  components: {
    quillEditor
  },
  computed: {
    option() {
      let opt = [];
      for (let i = 1; i <= this.$store.state.user.userLevel; i++) {
        let tmp = {
          value: i,
          label: "Lv." + i
        };
        opt.push(tmp);
      }
      return opt;
    },
    isAdmin() {
      return this.$store.state.user.userIdentity == 1;
    },
  },
  methods: {
    submitPost() {
      console.log(this.$store.state.user.userID);
      console.log(this.title);
      console.log(this.postLevel)
      console.log(this.postIdentity);
      console.log(this.content);
      let userID = this.$store.state.user.userID;
      let title = this.title;
      let postLevel = this.postLevel;
      let postIdentity = this.postIdentity;
      let content = this.content;
      let postID = this.postID;
      if (title == "") {
        this.$message({
          message: "帖子标题不能为空~",
          type: "warning"
        });
        return;
      }
      if (!postIdentity) {
        this.$message({
          message: "帖子类型不能为空~",
          type: "warning"
        });
        return;
      }
      if (content == "") {
        this.$message({
          message: "帖子内容不能为空",
          type: "warning"
        });
        return;
      }
      uploadPost(userID, postID, title, postLevel, postIdentity, content)
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
.edit-content,.edit-title,.edit-type{
  margin: 10px 0;
}
.el-input__inner{
  height: 50px;
}

.el-select{
  display: flex;
  align-items: center;
  line-height: 50px;
  position: relative;
}

.hover {
  height: auto;
  width: auto;
  display: flex;
  margin-left: -86px;
}

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

.hover {
    height: auto;
    width: auto;
    display: flex;
    margin-left: -86px;
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
  height: 410px;
  margin: 20px 0 0 0;
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
  margin-top: 30px;
}

.ql-editor {
  height: 400px;
}

.el-main {
  background-color: rgb(240, 241, 245);
}

.editpost-main {
  height: auto;
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: auto;
  padding: 50px 80px 40px;
}

.editpost {
  display: flex;
  flex-direction: column;
  height: auto;
  padding-bottom: 20px;
  margin-top: 30px;
  background-color: #fff;
  border-radius: 4px;
}
</style>
