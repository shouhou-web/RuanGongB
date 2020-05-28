<template>
  <div class="editpost">
    <editor v-model="text" upload-url="/upload/image" fileName="file" @input="input" />
  </div>
</template>
<script>
import editor from "../components/common/v-editor";

export default {
  name: "EditPost",
  data() {
    return {
      text: ""
    };
  },
  components: {
    editor
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
