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
      return this.$route.query.id;
    }
  },
  components: {},
  created() {
    console.log(this.$route.query.id);
  }
};
</script>
