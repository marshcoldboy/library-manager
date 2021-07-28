<template>
  <div>
    <el-container>
      <el-header class="header el-icon-s-management">详细书籍信息</el-header>
      <el-container>
        <el-aside width="400px">
          <el-card class="el-card">
            <el-image
              style="width: 200px; height: 295px"
              :src="book.cover"
              fit="scale-down"></el-image>
          </el-card>
          <el-row>
            <el-button type="info" v-if="isUser" plain icon="el-icon-notebook-2" class="el-borrow-button" @click="borrow(book)">借阅</el-button>
          </el-row>
        </el-aside>
        <el-main width="800px">
          <el-row :gutter="20" style="padding-top: 10px">
            <el-col :span="12" offset="2"><div class="title">{{book.title}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12" offset="2"><div class="author">作者：  {{book.author}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12" offset="2"><div class="press">出版社：  {{book.press}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12" offset="2"><div class="price">价格：  {{book.price}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="12" offset="2"><div class="ISBN">ISBN： {{book.isbn}}</div></el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="16" offset="2">
              <div class="abstract bg-purple">
                <el-container>
                  <el-aside width="40px" style="padding-left: 0;padding-top: 0">
                    <p class="abstract">简介: </p>
                  </el-aside>
                  <el-main class="abstract" style="margin-top: 0;padding-top: 15px">
                    {{book.abs}}
                  </el-main>
                </el-container>
              </div>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'BookInfo',
    data () {
      return {
        book: {
          bid: '',
          cover: '',
          title: '',
          author: '',
          isbn: '',
          press: '',
          price: '',
          abs: ''
        },
        isUser: false
      }
    },
    created: function () {
      this.getParams()
      this.getUserStatus()
    },
    watch: {
      // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
      '$route': 'getParams'
    },
    methods: {
      getParams: function () {
        // 取到路由带过来的参数
        var routerParams = this.$route.query.book
        // 将数据放在当前组件的数据内
        this.book = routerParams
      },
      borrow (book) { // 借阅图书
        // var _this = this
        this.$axios.post('/BookBorrow', {
          title: this.book.title,
          username: this.$store.state.username
        }).then(successResponse => {
          if (successResponse.data.code === 200) {
            alert('借阅成功')
          } else {
            alert('该书籍当前状态不可借阅')
          }
        })
      },
      getUserStatus () {
        if (this.$store.state.username) {
          this.$axios.post('/isAdmin', {
            username: this.$store.state.username
          }).then(successResponse => {
            if (successResponse.data.code === 200) {
              this.isUser = false
            } else {
              this.isUser = true
            }
          })
        } else {
          this.isUser = false
        }
      }
    }
  }
</script>

<style scoped>
  body > .el-container {
    margin-bottom: 40px;
  }
  .el-header, .el-footer {
    color: #333;
    text-align: left;
    line-height: 40px;
    margin-left: 100px;
    margin-top: 40px;
    max-height: 30px;
  }
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .el-card{
    height: 330px;
    width: 250px;
    margin-left: 120px;
    margin-top: 50px;
  }
  .el-borrow-button{
    margin-top: 20px;
    margin-left: 85px;
    height: 50px;
    width: 120px;
    font-size: 18px;
    text-align: center;
  }
  .title {
    font-size: 35px;
    text-align: left;
    line-height: 70px;
    font-weight: bold;
    margin-top: 10px;
  }

  .author {
    font-size: 15px;
    text-align: left;
    line-height: 40px;
  }
  .press {
    font-size: 15px;
    text-align: left;
    line-height: 40px;
  }
  .ISBN {
    font-size: 15px;
    text-align: left;
    line-height: 40px;
  }
  .price {
    font-size: 15px;
    text-align: left;
    line-height: 40px;
  }
  .abstract {
    font-size: 15px;
    display: block;
    text-align: left;
    line-height: 40px;
  }
</style>
