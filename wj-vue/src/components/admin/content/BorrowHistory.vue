<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>借阅历史</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-card style="width: 95%;margin: 40px 2% 18px;">
      <div id="borrow-history">
        <p style="margin-right: 1000px">
          <span><i class="el-icon-s-fold"></i></span>
          <span style="font-size: 20px">借阅历史</span>
        </p>
        <div>
          <span style="margin-left: 500px">
            <el-input v-model="username" placeholder="按用户名查询" style="width: 150px"></el-input>
          </span>
          <span style="margin-left: 10px">
            <el-date-picker
              v-model="date"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
              :picker-options="pickerOptions">
              </el-date-picker>
              <el-button @click="loadBorrowHistoryAccordingDateAndUsername()">查询</el-button>
          </span>
        </div>
          <el-table
            :data="borrowHistory"
            style="width: 100%;margin-top: 20px"
            max-height="500">
            <el-table-column
              prop="username"
              label="用户"
              width="200">
            </el-table-column>
            <el-table-column
              prop="title"
              label="书名"
              width="200">
            </el-table-column>
            <el-table-column
              prop="startdate"
              label="借阅日期"
              width="200">
            </el-table-column>
            <el-table-column
              prop="enddate"
              label="规定归还日期"
              width="200">
            </el-table-column>
            <el-table-column
              prop="returndate"
              label="实际归还日期"
              width="200">
            </el-table-column>
            <el-table-column
              prop="status"
              label="状态"
              width="100">
            </el-table-column>
          </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import EditForm from './EditForm'
export default {
  name: 'BorrowHistory',
  components: {EditForm},
  data () {
    return {
      borrowHistory: [],
      date: '',
      username: '',
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick (picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      }
    }
  },
  mounted () {
    // this.loadBorrowHistory()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    loadBorrowHistory () {
      var _this = this
      this.$axios.get('/borrow_history_all').then(resp => {
        if (resp && resp.data.code === 200) {
          _this.borrowHistory = resp.data.result
        }
      })
    },
    loadBorrowHistoryAccordingDate () {
      var _this = this
      this.$axios.post('/borrow_history_all_accordingDate', {
        date: this.date
      }).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.borrowHistory = resp.data.result
        }
      })
    },
    loadBorrowHistoryAccordingUsername () {
      var _this = this
      this.$axios.post('/borrow_history', {
        username: this.username
      }).then(resp => {
        if (resp && resp.data.code === 200) {
          _this.borrowHistory = resp.data.result
        }
      })
    },
    loadBorrowHistoryAccordingDateAndUsername () {
      var _this = this
      if (this.user !== '' && this.date === '') {
        this.loadBorrowHistoryAccordingUsername()
      } else if (this.user === '' && this.date !== '') {
        this.loadBorrowHistoryAccordingDate()
      } else {
        this.$axios.post('/borrow_history_all_accordingDateAndUsername', {
          userName: this.username,
          date: this.date
        }).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.borrowHistory = resp.data.result
          }
        })
      }
    }
  }
}
</script>

<style scoped>
</style>
