<template>
  <div id="nav">
    <Home />
  </div>
  <router-view />
</template>

<script>
import Home from "@/views/Home.vue";

export default {
  components: {
    Home,
  },
};
</script>

<style>
body {
  display: flex;
  justify-content: center;
  height: "100%";
  width: "100%";
}
#app {
  display: flex;
  justify-content: center;
  flex-direction: column;

  font-family: sans-serif, Avenir, Helvetica, Arial;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
