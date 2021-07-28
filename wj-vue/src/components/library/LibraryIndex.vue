<template>
  <div id="bg">
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <books class="books-area" ref="booksArea"></books>
    </el-main>
  </el-container>
    <div class="kind">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
    </div>
  </div>
</template>

<script>
  import SideMenu from './SideMenu'
  import Books from './Books'
  import PieChart from '../admin/dashboard/admin/components/PieChartBook'

  export default {
    name: 'AppLibrary',
    components: {Books, SideMenu, PieChart},
    methods: {
      listByCategory () { // 按分类显示图书
        var _this = this
        var cid = this.$refs.sideMenu.cid
        var url = 'categories/' + cid + '/books'
        this.$axios.get(url).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.$refs.booksArea.books = resp.data.result
            _this.$refs.booksArea.currentPage = 1
          }
        })
      }
    }
  }
</script>

<style scoped>
  #bg {
    background:url("../../assets/img/bg/bgh2.jpeg");
    width:100%;
    height:100%;
    background-attachment:fixed;
    background-size:100% 100%;
  }
  .books-area {
    width: 990px;
    margin-left: 100px;
  }
  .kind {
    width: 250px;
    top: 100px;
    left: 1230px;
    position: absolute;
  }
</style>
