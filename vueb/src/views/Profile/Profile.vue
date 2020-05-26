<template>
  <div>
    <h1>Profile Page</h1>
    <router-link to="/profile/viewed">1</router-link> |
    <router-link to="/profile/liked">2</router-link> |
    <router-link to="/profile/comment">3</router-link> |
    <router-link to="/profile/mypost">4</router-link> |
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Profile",
  computed: {
  },
  components: {},
  created() {
  }
};
</script>
