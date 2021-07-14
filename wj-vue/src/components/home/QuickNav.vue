<template>
    <div class="quick-nav">
      <a href="https://gitee.com/shao-yuanjie/library-manager/blob/master/readme.md" target="_blank">
        <img src="../../assets/img/icon/icon5.png" alt="" class="readme">
      </a>
      <img src="../../assets/img/icon/niuniu.jpg" alt="" class="logo">
    </div>
</template>

<script>
  export default {
    name: 'QuickNav'
  }
</script>

<style scoped>
  .quick-nav {
    width: 303px;
    height: 318px;
    background: transparent;
  }

  .readme {
    width: 250px;
    height: 250px;
    position: absolute;
    margin-top: 0px;
    margin-left: 51.5%;
    top: 300px;
    left: 230px;
  }
  .logo {
    width: 220px;
    position: absolute;
    margin-left: 52%;
    margin-top: 40px;
    top: 50px;
    left: 210px;
  }

</style>
