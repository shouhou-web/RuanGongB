<template>
  <div class="manageprofile">
    <div class="mp-header">
      管理用户
    </div>
    <div class="mp-main">
      <div class="mp-name">
        <div class="mp-name-t mp-left">
          用户名：
        </div>
        <div class="mp-name-r">
          {{ user.userName }}
        </div>
      </div>
      <!-- 用户状态 -->
      <div class="mp-state">
        <div class="mp-state-t mp-left">
          用户状态：
        </div>
        <div v-if="user.userState == 0" class="mp-state-r">
          正常
        </div>
        <div v-else class="mp-state-r">
          封禁中
        </div>
      </div>

      <div class="mp-time">
        <div class="mp-time-t mp-left">
          封禁时间：
        </div>
        <el-select
          class="mp-state-r"
          v-model="duration"
          placeholder="请选择封禁时间"
        >
          <el-option
            v-for="item in time"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <!-- 封禁原因 -->
      <div v-if="duration > 0" class="mp-reason">
        <div class="mp-reason-t mp-left">
          封禁原因：
        </div>
        <input
          class="mp-reason-i"
          v-model="content"
          type="text"
          placeholder="封禁原因"
        />
      </div>
    </div>
    <div class="mp-footer">
      <button class="mp-back" @click="back">返回</button>
      <button class="mp-save" @click="submitManage">保存</button>
    </div>
  </div>
</template>

<script>
import {getUser, upManage} from '../../network/manage'
export default {
  name: "ManageProfile",
  data() {
    return {
      duration: 0,
      content: "",
      time: [
        {
          value: 0,
          label: "无"
        },
        {
          value: 1,
          label: "一天"
        },
        {
          value: 3,
          label: "三天"
        },
        {
          value: 7,
          label: "七天"
        },
        {
          value: 100,
          label: "永久"
        }
      ],
      user: {
        userID: 10001,
        userName: "用户名",
        userState: 0 //0正常，1被禁
      }
    };
  },
  computed: {},
  methods: {
    submitManage() {
      console.log(this.user.userID);
      console.log(this.content);
      console.log(this.duration)
      console.log(this.user.userState)
      let userID = this.user.userID;
      let content = this.content;
      let duration = this.duration;
      let userState = this.user.userState
      if (duration == 0) {
        userState = 0;
        content = ""
      }else 
        userState = 1;
      upManage(userID,userState,duration,content).then(res => {
        if (res == 0) {
          this.$message({
            type:'success',
            message:'保存成功~'
          })
          this.$router.go(-1);
        }
        else {
          this.$message.error('保存信息失败，清检查您的网络~')
        }
      }).catch(err => {
        this.$message.error('获取信息失败，清检查您的网络~')
      })
    },
    back() {
      this.$router.go(-1);
    }
  },
  created() {
    console.log(this.$route.query.userID);
    let userID = this.$route.query.userID;
    getUser(userID).then(res => {
      if (res == null) {
        this.$message.error('获取信息失败，清检查您的网络~')
      }else {
        this.user = res;
      }
    }).catch(err => {
      this.$message.error('获取信息失败，清检查您的网络~')
    })
  }
};
</script>
<style>
.mp-reason-i,
.mp-name-r,
.mp-state-r {
  display: flex;
  line-height: 50px;
  align-items: center;
}

.mp-reason-i {
  border: 1px solid #ebebeb;
  padding: 13px;
  font-size: 15px;
  line-height: 10px;
}

.mp-name,
.mp-state,
.mp-time,
.mp-reason {
  display: flex;
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
  height: auto !important;
  width: auto !important;
  display: flex;
  margin-left: -86px;
}

.mp-footer {
  display: flex;
  padding: 0 75px;
}

.mp-back {
  background-color: #ebebeb;
  color: #000;
  border-radius: 4px;
  width: 90px;
  height: 42px;
  outline: none;
  border: none;
  margin-top: 0px;
  margin-right: 20px;
}

.mp-save {
  background-color: #00d5ff;
  color: #fff;
  border-radius: 4px;
  width: 90px;
  height: 42px;
  outline: none;
  border: none;
  margin-top: 0px;
}

.manageprofile {
  display: flex;
  flex-direction: column;
  height: auto;
  padding-bottom: 20px;
  margin-top: 30px;
  background-color: #fff;
  border-radius: 4px;
}

.mp-main {
  height: auto;
  display: flex;
  flex-direction: column;
  width: 1000px;
  height: auto;
  padding: 50px 80px 40px;
}

.mp-header {
  widows: 1000px;
  height: 50px;
  padding: 0px 30px;
  font-size: 16px;
  font-weight: 600;
  line-height: 50px;
  border-bottom: 1px solid #e9eef3;
  display: flex;
}

.mp-left {
  width: 100px;
  display: inline-flex;
  line-height: 50px;
  flex-shrink: 0;
  outline: none;
}
</style>
