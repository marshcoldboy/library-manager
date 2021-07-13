<template>
  <div id="bg">
  <div>
      <div class="home">
        <div id="header-div" style="height: 430px">
          <div style="height: 50px"></div>
          <carousel></carousel>
          <quick-nav style="float: right;margin-top: -220px;margin-right: 480px"></quick-nav>
        </div>
        <update-card id="update-card"></update-card>
        <about id="about"></about>
      </div>
  </div>
  </div>
</template>

<script>
  import Carousel from './Carousel'
  import QuickNav from './QuickNav'
  import About from '@/components/common/About'
  import UpdateCard from './UpdateCard'
  export default {
    name: 'AppIndex',
    components: {Carousel, QuickNav, About, UpdateCard}
  }
</script>

<style scoped>
  #bg{
    background:url("../../assets/img/bg/bgh2.jpeg");
    width:100%;
    height:100%;
    background-attachment:fixed;
  background-size:100% 100%;}
  .home {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 0px;
  }

  #header-div {
    padding-bottom: 20px;
    padding-left: 5px;
    background-color: white;
  }
</style>
