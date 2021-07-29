<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>归还确认</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-card style="width: 95%;margin: 40px 2% 18px;">
      <div style="margin-top: 40px">
        <side-menu class="fixed" id="side-menu"></side-menu>
        <p style="margin-right: 1000px;margin-top: -30px">
          <span><i class="el-icon-s-fold"></i></span>
          <span style="font-size: 20px">归还确认</span>
        </p>
        <el-table
          :data="bookReturn"
          style="width: 100%;margin-top: 10px"
          max-height="500">
          <el-table-column
            prop="bookborrow.username"
            label="用户"
            width="150">
          </el-table-column>
          <el-table-column
            prop="bookborrow.title"
            label="书名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="bookborrow.startdate"
            label="借阅日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="bookborrow.enddate"
            label="规定归还日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="returndate"
            label="实际归还日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="fine"
            label="罚款金额"
            width="150">
          </el-table-column>
          <el-table-column
            label="操作"
            width="120">
            <template slot-scope="scope">
              <span><el-button
                @click="acceptBookReturn(scope.row)"
                type="text"
                size="small">
                确认
              </el-button></span>
              <span><el-button
                @click="refuseBookReturn(scope.row)"
                type="text"
                size="small">
                拒绝
              </el-button></span>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
  import EditForm from './EditForm'
  export default {
    name: 'BorrowCheck',
    components: {EditForm},
    data () {
      return {
        bookReturn: []
      }
    },
    mounted () {
      this.loadBookReturn()
    },
    computed: {
      tableHeight () {
        return window.innerHeight - 320
      }
    },
    methods: {
      loadBookReturn () { // 加载需要确认的归还请求
        var _this = this
        this.$axios.get('/admin/book_return_information').then(resp => {
          if (resp && resp.data.code === 200) {
            _this.bookReturn = resp.data.result
          }
        })
      },
      acceptBookReturn (item) { // 确认归还
        this.$axios.post('/admin/book_return/consent', {
          borrowid: item.bookborrow.borrowid
        }).then(successResponse => {
          if (successResponse.data.code === 200) {
            alert('归还成功')
            this.loadBookReturn()
          } else {
            alert('归还失败')
            this.loadBookReturn()
          }
        })
      },
      refuseBookReturn (item) { // 拒绝归还
        this.$axios.post('/admin/book_return/deny', {
          borrowid: item.bookborrow.borrowid
        }).then(successResponse => {
          if (successResponse.data.code === 200) {
            alert('拒绝归还')
            this.loadBookReturn()
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
