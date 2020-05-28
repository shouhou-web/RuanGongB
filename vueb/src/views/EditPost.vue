<template>
  <div class="editpost">
    <input v-model="title" type="text" placeholder="帖子标题" />
    <label for="male">
      <input type="radio" id="male" value="讨论区" v-model="type" />讨论区
    </label>
    <label for="female">
      <input type="radio" id="female" value="课程推荐" v-model="type" />课程推荐
    </label>
    <editor
      v-model="content"
      upload-url="/upload/image"
      fileName="file"
      @input="input"
    />
    <!-- <neweditor></neweditor> -->
    <button @click="submitPost">上传</button>
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
      type: ""
    };
  },
  components: {
    editor
  },
  methods: {
    input(e) {
      console.log(e);
    },
    submitPost() {
      console.log(this.$store.state.user.userID);
      console.log(this.title);
      console.log(this.type);
      console.log(this.content);
      let postIdentity;
      switch (this.type){
        case "新手上路":
          postIdentity = 0;
          break;
        case "讨论区":
          postIdentity = 1;
          break;
        case "课程推荐":
          postIdentity = 2;
          break;
        case "校园周边":
          postIdentity = 3;
          break;
        case "资源共享":
          postIdentity = 4;
          break;
        case "刷题板块":
          postIdentity = 5;
          break;
      }
      uploadPost(this.$store.state.userID, this.title, postIdentity, this.content)
        .then(res => {
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
.ql-editor {
  height: 400px;
}
.el-main {
  background-color: #fff;
}

.editpost {
  height: auto;
}
</style>
