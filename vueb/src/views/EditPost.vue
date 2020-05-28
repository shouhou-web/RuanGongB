<template>
  <div class="editpost">
    <!-- <editor v-model="text" upload-url="/upload/image" fileName="file" @input="input" /> -->
    <neweditor></neweditor>
  </div>
</template>
<script>
import neweditor from "../components/common/editor";

export default {
  name: "EditPost",
  data() {
    return {
      text: ""
    };
  },
  components: {
    neweditor
  },
  methods:{
    input(e){
      console.log(e)
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
