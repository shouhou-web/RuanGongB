<template>
  <div class="scholl">
    <el-container>
      <el-aside width="200px">
        <ul class="nav-l">
          <li :class="[onClick.com ? 'blue' : '']" @click="comClick">
            回复我的
          </li>
          <li :class="[onClick.like ? 'blue' : '']" @click="likeClick">
            收到的赞
          </li>
          <li :class="[onClick.system ? 'blue' : '']" @click="systemClick">
            系统消息
          </li>
        </ul>
      </el-aside>
      <el-main><router-view /></el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "ProfileMessage",
  data() {
    return {
      onClick: {
        com: true,
        like: false,
        system: false
      }
    };
  },
  methods: {
    comClick() {
      this.closeAll();
      this.onClick.com = true;
      this.$router.push("/profileMessage/viewcom");
    },
    likeClick() {
      this.closeAll();
      this.onClick.like = true;
      this.$router.push("/profileMessage/viewlike");
    },
    systemClick() {
      this.closeAll();
      this.onClick.system = true;
      this.$router.push("/profileMessage/viewsystem");
    },
    closeAll() {
      for (let key in this.onClick) this.onClick[key] = false;
    }
  }
};
</script>

<style>
.nav-l {
  width: 200px;
  min-height: 100%;
}

.nav-l li:hover {
  color: #409eff;
}

.blue {
  color: #409eff;
}

.nav-l li {
  font-size: 14px;
  padding: 0 20px;
  cursor: pointer;
  transition: border-color 0.3s, background-color 0.3s, color 0.3s;
  box-sizing: border-box;
  height: 56px;
  line-height: 56px;
  position: relative;
  -webkit-box-sizing: border-box;
  white-space: nowrap;
  list-style: none;
}

.el-aside {
  background-color: #fff;
}

.el-main {
  background-color: #fff;
}
</style>
