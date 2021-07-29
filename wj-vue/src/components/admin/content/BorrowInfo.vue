<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>当前借阅信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-card style="width: 95%;margin: 40px 2% 18px;">
      <div style="margin-top: 40px">
        <side-menu class="fixed" id="side-menu"></side-menu>
        <p style="margin-right: 960px;margin-top: -30px">
          <span><i class="el-icon-s-fold"></i></span>
          <span style="font-size: 20px">当前借阅信息</span>
        </p>
        <el-table
          :data="bookborrow"
          style="width: 100%;margin-top: 10px"
          max-height="500">
          <el-table-column
            prop="username"
            label="用户"
            width="200">
          </el-table-column>
          <el-table-column
            prop="title"
            label="书名"
            width="250">
          </el-table-column>
          <el-table-column
            prop="startdate"
            label="借阅日期"
            width="250">
          </el-table-column>
          <el-table-column
            prop="enddate"
            label="预定归还日期"
            width="250">
          </el-table-column>
          <el-table-column
            prop="renew"
            label="是否续借"
            width="150">
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import EditForm from './EditForm'
export default {
  name: 'BorrowInfo',
  components: {EditForm},
  data () {
    return {
      bookborrow: []
    }
  },
  mounted () {
    this.loadBookBorrow()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    loadBookBorrow () { // 加载当前借阅信息
      var _this = this
      this.$axios.get('/admin/borrow_information').then(resp => {
        if (resp && resp.data.code === 200) {
          _this.bookborrow = resp.data.result
        }
      })
    }
  }
}
</script>

<style scoped>
.fixed {
  position: fixed;
  bottom: 100px;
  top: 150px;/*开始处于距离顶部300px的位置，之后随着滚动条滚动top值改变，然后在top==100时停止*/
  left: 60px;
  box-sizing: border-box;
  z-index: 2;
}
</style>
