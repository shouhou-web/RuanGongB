<template>
  <div class="profilelevel">
    <div class="profilelevel-title">
      我的等级
    </div>
    <div class="profilelevel-main">
      <div class="pl-my">
        <img :src="getMarkLevel" alt="" />
      </div>
      <div class="pl-title">
        等级提升规则
      </div>
      <table class="pl-table">
        <thead>
          <tr>
            <td class="head">等级</td>
            <td class="head">升级所需经验值</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in level" :key="index">
            <td>
              <span class="icon">
                <img :src="item.img" alt="">
              </span>
            </td>
            <td>
              <span class="text">
                {{item.level}}
              </span>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileLevel",
  data() {
    return {
      level:[]
    };
  },
  methods: {},
  computed: {
    getMarkLevel() {
      let userLevel = this.$store.state.user.userLevel;
      return (
        "https://img-static.mihoyo.com/levelMark/levelMark" + userLevel + ".png"
      );
    },
    getExperience() {
      return this.$store.state.user.experience;
    }
  },
  created() {
    let level = [];
    let experience = [0,30,110,290,592,1168,2190,2190,2190,2920,3650,3650,3942,4818,5840,8030]
    for (let i=1;i<=16;i++){
      let item = {
        level : experience[i-1],
        img : "https://img-static.mihoyo.com/level/level" + i + ".png"
      }
      level.push(item)
    }
    console.log(level)
    this.level = level;
  }
};
</script>
<style>
.pl-table .icon {
  display: inline-block;
    width: 36px;
    height: 16px;
    text-align: left;
}

.pl-table .text {
  color: #fa0;
}

.pl-table .icon img {
  height: 16px;
}

.pl-table tbody {
  color: #666;
  font-size: 14px;
}

.pl-table thead {
  background-color: #eff6ff;
  color: #333;
  font-size: 14px;
  font-weight: 600;
}

.pl-table thead tr {
  height: 50px;
}

.pl-table td {
  border: 1px solid #e4eaf2;
  text-align: center;
  line-height: 1.15;
  height: 50px;
}

.pl-table thead .head {
  color: #333;
    font-size: 14px;
    font-weight: 600;
}

.pl-table {
  width: 100%;
  margin-top: 20px;
  border: 1px solid #e4eaf2;
  border-collapse: collapse;
  table-layout: fixed;
}
.pl-title {
  display: flex;
  height: 51px;
  width: 100%;
  line-height: 50px;
  font-size: 16px;
}

.pl-my {
  display: flex;
  justify-content: center;
}

.pl-aside {
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: center;
}
.profilelevel {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  width: 700px;
}

.profilelevel-title {
  display: flex;
  height: 51px;
  width: 100%;
  padding: 0 30px;
  line-height: 50px;
  border-bottom: 1px solid #ebebeb;
  font-size: 16px;
}

.profilelevel-main {
  display: flex;
  flex-direction: column;
  height: auto;
  padding: 40px 30px 100px;
}
</style>
