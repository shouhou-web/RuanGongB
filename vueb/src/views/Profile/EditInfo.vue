<template>
  <div class="editinfo">
    <!-- 阴影 -->
    <div
      :class="[editImage ? '' : 'page-mask-show']"
      @click="cancel"
      class="page-mask"
    ></div>
    <!-- 悬浮窗 -->
    <div
      :class="[editImage ? 'page-state' : 'page-state-hide']"
      class="open-page"
    >
      <div class="image">
        <div class="image-title">
          选择头像
        </div>
        <div class="image-middle">
          <div
            v-for="(item, index) in image"
            :key="index"
            @click="clickImage(item)"
            class="image-middle-img"
            :class="[item == currentImagePath ? 'image-middle-img-active' : '']"
          >
            <img :src="item" alt="" />
          </div>
        </div>
        <div class="image-bottom">
          <button @click="cancel" class="cancel">
            取消
          </button>
          <button @click="assure" class="assure">
            确定
          </button>
        </div>
      </div>
    </div>
    <!-- 编辑页面 -->
    <div class="editinfo-title">
      编辑资料
    </div>
    <div class="editinfo-main">
      <!-- 上传头像 -->
      <div class="uploadimg">
        <!-- 头像上传组件 -->
        <img :src="imagePath" alt="" />
        <div @click="editImage = !editImage" class="uploadimg-text">
          修改头像
        </div>
      </div>
      <div class="otherinfo">
        <!-- 昵称 -->
        <div class="o-name">
          <div class="on-title">
            昵称
          </div>
          <div class="on-input">
            <input v-model="userName" type="text" />
          </div>
        </div>
        <!-- 性别 -->
        <div class="o-sex">
          <div class="os-title">
            性别
          </div>
          <div class="os-label">
            <label v-for="(item, index) in sex" :key="index" class="os-label-i">
              <input
                name="sex"
                type="radio"
                :id="item.userSex"
                :value="item.userSex"
                :checked="item.userSex == userSex"
                v-model="userSex"
              />
              {{ item.label }}
            </label>
          </div>
        </div>
        <!-- 个性签名 -->
        <div class="o-word">
          <div class="ow-title">
            个性签名
          </div>
          <div class="ow-content">
            <input v-model="userWord" type="text" />
          </div>
        </div>
        <!-- 提交 -->
        <div class="o-save">
          <button @click="save">保存</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { editUser } from "../../network/user";
export default {
  name: "EditInfo",
  data() {
    return {
      editImage: false,
      imagePath: "",
      currentImagePath: "",
      userName: "",
      userSex: 1,
      userWord: "",
      sex: [
        {
          label: "男",
          userSex: 1
        },
        {
          label: "女",
          userSex: 2
        },
        {
          label: "保密",
          userSex: 0
        }
      ],
      image: [
        "https://img-static.mihoyo.com/avatar/avatar30044.png",
        "https://img-static.mihoyo.com/avatar/avatar30045.png",
        "https://img-static.mihoyo.com/communityweb/upload/d3183774d0d6f7a41d670e7dbc4654e3.png",
        "https://img-static.mihoyo.com/communityweb/upload/820f461e107e17f11d8fa8c5e45d5289.png",
        "https://img-static.mihoyo.com/communityweb/upload/fbdf7f523c1dd6626d2c5f03ee3f6f98.png",
        "https://img-static.mihoyo.com/communityweb/upload/57b90e40dc9dc200b5edd78dee9c2229.png",
        "https://img-static.mihoyo.com/communityweb/upload/1ef39940f7b29521ec1eddd2e7dec95f.png",
        "https://img-static.mihoyo.com/communityweb/upload/f50dd4023f740340b1c54141c7b6c1be.png",
        "https://img-static.mihoyo.com/communityweb/upload/de22b6827f169102f4cf860462d92feb.png",
        "https://img-static.mihoyo.com/communityweb/upload/323cc51093b9f86722bb20482508bf3d.png",
        "https://img-static.mihoyo.com/communityweb/upload/696cf7f6443df04eccf6ebb2e241ee74.png",
        "https://img-static.mihoyo.com/communityweb/upload/b6bbc725a4afd2369827782d537b24d4.png",
        "https://img-static.mihoyo.com/communityweb/upload/cae78e205c56da101fb44b13554a949b.png",
        "https://img-static.mihoyo.com/communityweb/upload/da411f6525512811eba03bfbad9633a6.png",
        "https://img-static.mihoyo.com/communityweb/upload/b847b9027dc47246d1e2b11b172764b4.png",
        "https://img-static.mihoyo.com/communityweb/upload/3462ba26d0721ebc099410fd28a97edd.png"
      ]
    };
  },
  methods: {
    clickImage(item) {
      this.currentImagePath = item;
    },
    cancel() {
      this.editImage = false;
      this.currentImagePath = this.imagePath;
    },
    assure() {
      this.imagePath = this.currentImagePath;
    },
    save() {
      let userID = this.userID;
      let imagePath = this.imagePath;
      let userName = this.userName;
      let userWord = this.userWord;
      let userSex = this.userSex;
      if (userName == "") {
        this.$message({
          type: "warning",
          message: "用户名不能为空~"
        });
        return;
      }
      if (userWord == "") {
        this.$message({
          type: "warning",
          message: "个性签名不能为空~"
        });
        return;
      }
      editUser(userID, imagePath, userName, userWord, userSex)
        .then(res => {
          console.log(res);
          if (res == 0) {
            this.$store.commit(
              "editInfo",
              imagePath,
              userName,
              userWord,
              userSex
            );
            this.$message({
              message: "修改信息成功~",
              type: "success"
            });
          } else if (res == 1)
            this.$message({
              type: "warning",
              message: "用户名已存在~"
            });
          else if (res == 2) this.$message.error("上传信息失败了~请检查网络");
        })
        .catch(err => {
          console.log(err);
          this.$message.error("上传信息失败了~请检查网络");
        });
    }
  },
  created() {
    this.userID = this.$store.state.user.userID;
    this.imagePath = this.$store.state.user.imagePath;
    this.currentImagePath = this.imagePath;
    this.userSex = this.$store.state.user.userSex;
    this.userName = this.$store.state.user.userName;
    this.userWord = this.$store.state.user.userWord;
  }
};
</script>
<style>
.image {
  display: flex;
  flex-direction: column;
  width: 480px;
  position: fixed;
  left: 85%;
  margin-left: -50%;
  top: 20%;
}

.image-title {
  padding: 0 30px;
  line-height: 50px;
  min-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
  font-weight: 600;
  background-color: #fff;
  display: flex;
  width: 480px;
}

.image-middle {
  padding: 30px 0 0 30px;
  height: 480px;
  overflow: auto;
  background-color: #fff;
  display: flex;
  flex-wrap: wrap;
  width: 480px;
}

.image-middle-img {
  display: inline-block;
  vertical-align: top;
  width: 90px;
  height: 90px;
  padding: 4px;
  border: 1px solid transparent;
  margin: 0 20px 20px 0;
  border-radius: 100%;
  cursor: pointer;
}

.image-middle-img-active {
  border-color: #4cc3ff;
}

.image-middle-img img {
  width: 100%;
  height: 100%;
  border-radius: 100%;
  border: 1px solid #f0f0f0;
}

.image-bottom {
  width: 480px;
  height: 95px;
  padding: 30px 0;
  background-color: #fff;
}

.cancel {
  width: 106px;
  line-height: 32px;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
  color: #666;
  background-color: #fff;
  margin-right: 20px;
  outline: none;
}

.assure {
  background-color: #4cc3ff;
  border-color: #4cc3ff;
  width: 106px;
  line-height: 32px;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border: 1px solid #fff;
  border-radius: 4px;
  color: #fff;
  outline: none;
}

.o-save {
  margin-top: 30px;
}

.o-save button {
  border: 1px solid #4cc3ff;
  border-radius: 4px;
  color: #00d5ff;
  line-height: 32px;
  width: 106px;
  font-size: 16px;
  background-color: #fff;
  outline: none;
}
.os-label {
  padding: 0 20px;
  width: 250px;
  display: flex;
  justify-content: space-between;
}

.os-label input {
  margin-right: 5px;
}

.ow-content input,
.on-input input {
  width: 300px;
  height: 44px;
  padding: 0px 20px;
  color: #111;
  outline-style: none;
  border: 0px;
}

.ow-content input:focus,
.on-input input:focus {
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075),
    0 0 8px rgba(102, 175, 233, 0.6);
}

.os-title,
.on-title,
.ow-title {
  width: 70px;
  text-align: right;
  color: #999;
  margin-right: 30px;
  display: flex;
  justify-content: flex-end;
}

.o-name,
.o-sex,
.o-word {
  display: flex;
  margin-bottom: 10px;
  line-height: 44px;
  outline: none;
}

.uploadimg {
  margin-bottom: 23px;
}
.uploadimg img {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  margin-bottom: 10px;
}

.uploadimg-text {
  color: #4cc3ff;
  font-size: 14px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  margin-bottom: 23px;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}

.avatar {
  width: 120px;
  height: 120px;
  display: block;
}

.editinfo {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  width: 700px;
}

.editinfo-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.editinfo-main {
  display: flex;
  flex-direction: column;
  height: auto;
  padding: 40px 30px 100px;
}
</style>
