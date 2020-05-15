<template>
  <div>
    <h1>Profile Page</h1>
    <h2>{{ userID }}</h2>
  </div>
</template>

<script>
export default {
  name: "Profile",
  computed: {
    userID() {
      return this.$route.params.UserID;
    }
  },
  components: {}
};
</script>
