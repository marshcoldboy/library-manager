<template>
  <el-row :gutter="40" class="panel-group">
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-people">
          <i class="el-icon-user-solid" style="font-size: 50px"></i>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            用户
          </div>
          <count-to :start-val="0" :end-val="userNumber" :duration="2600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-book">
          <i class="el-icon-collection" style="font-size: 50px"></i>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            书籍
          </div>
          <count-to :start-val="0" :end-val="bookNumber" :duration="3200" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel" @click="pushToCheck()">
        <div class="card-panel-icon-wrapper icon-message">
          <i class="el-icon-message" style="font-size: 50px"></i>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            待确认信息
          </div>
          <count-to :start-val="0" :end-val="messageNumber" :duration="3000" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="6" class="card-panel-col">
      <div class="card-panel">
        <div class="card-panel-icon-wrapper icon-borrow">
          <i class="el-icon-document-copy" style="font-size: 50px"></i>
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            借阅人次
          </div>
          <count-to :start-val="0" :end-val="borrowNumber" :duration="3600" class="card-panel-num" />
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import CountTo from 'vue-count-to'

export default {
  components: {
    CountTo
  },
  data () {
    return {
      userNumber: '',
      bookNumber: '',
      messageNumber: '',
      borrowNumber: ''
    }
  },
  mounted () {
    this.load()
  },
  methods: {
    pushToCheck () {
      this.$router.push('./content/borrowcheck')
    },
    handleSetLineChartData (type) {
      this.$emit('handleSetLineChartData', type)
    },
    load () {
      this.loadUserNumber()
      this.loadBookNumber()
      this.loadMessageNumber()
      this.loadBorrowNumber()
    },
    loadUserNumber () {
      this.$axios.get('/admin/user').then(resp => {
        if (resp && resp.data.code === 200) {
          this.userNumber = resp.data.result.length
        }
      })
    },
    loadBookNumber () {
      this.$axios.get('/books').then(resp => {
        if (resp && resp.data.code === 200) {
          this.bookNumber = resp.data.result.length
        }
      })
    },
    loadMessageNumber () {
      this.$axios.get('/admin/book_return_information').then(resp => {
        if (resp && resp.data.code === 200) {
          this.messageNumber = resp.data.result.length
        }
      })
    },
    loadBorrowNumber () {
      this.$axios.get('/admin/borrow_history').then(resp => {
        if (resp && resp.data.code === 200) {
          this.borrowNumber = resp.data.result.length
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-book {
        background: #f4516c;
      }

      .icon-borrow {
        background: #b6a2de
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-borrow {
      color: #b6a2de
    }

    .icon-book {
      color: #f4516c;
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
