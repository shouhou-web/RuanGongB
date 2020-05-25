<template>
  <div class="container">
    <div class="header">Login</div>
    <div class="form-warpper">
      <input
        type="text"
        v-model="username"
        placeholder="username"
        class="input-item"
      />
      <input
        type="password"
        v-model="password"
        placeholder="password"
        class="input-item"
      />
      {{ username + password }}
      <button @click="submit" class="btn">登录</button>
    </div>
  </div>
</template>

<script>
import { request } from "../network/request";
export default {
  name: "Register",
  computed: {},
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    submit() {
      console.log(this.username)
      console.log(this.password)
      request({
        url: "/login",
        params: {
          username:this.username,
          password:this.password
        },
        method:'post'
      })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style></style>
