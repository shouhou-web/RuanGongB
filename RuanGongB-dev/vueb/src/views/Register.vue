<template>
  <div>
    <h1>For Register</h1>
    <div class="form-warpper">
        <input
          type="text"
          name="username"
          placeholder="username"
          class="input-item"
        />
        <input
          type="password"
          name="password"
          placeholder="password"
          class="input-item"
        />
        <input
          type="password"
          name="password"
          placeholder="password again"
          class="input-item"
        />
        <div class="btn">注册</div>
      </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  computed: {},
  methods: {
    submit() {
      request({
        url: "/register",
        params: {
          type: "sell",
          page: 5
        }
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
