<template>
  <div>
    <div style="margin-top: 40px">
      <side-menu class="fixed" id="side-menu"></side-menu>
      <div id="current-borrow">
        <el-card class="menu" >
          <i class="el-icon-reading"/>
          当前借阅</el-card>
      </div>
      <div id="borrow-history">
        <el-card class="menu">
          <i class="el-icon-s-fold"/>
          借阅历史</el-card>
      </div>
      <div id="fine">
        <el-card class="menu">
          <i class="el-icon-warning-outline"/>
          超期罚款</el-card>
      </div>
      <div id="user-information">
        <el-dialog
          title="修改用户信息"
          :visible.sync="dialogFormVisible">
          <el-form v-model="user" style="text-align: left" ref="dataForm">
            <el-form-item label="用户名" label-width="120px" prop="username">
              <label>{{user.username}}</label>
            </el-form-item>
            <el-form-item label="真实姓名" label-width="120px" prop="name">
              <el-input v-model="user.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="手机号" label-width="120px" prop="phone">
              <el-input v-model="user.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" label-width="120px" prop="email">
              <el-input v-model="user.email" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="onSubmit(user)">确 定</el-button>
          </div>
        </el-dialog>
        <el-card class="menu">
          <i class="el-icon-user"/>
          个人信息
          <div style="margin-top: 25px;margin-left: 33px">
            <p class="info">用户名： {{user.username}}</p>
            <p class="info">真实姓名： {{user.name}}</p>
            <p class="info">手机号：  {{user.phone}}</p>
            <p class="info">邮箱： {{user.email}}</p>
          </div>
          <el-button style="position: absolute;margin-left: 800px;margin-top: -100px" type="primary" icon="el-icon-setting" @click="alterUser()">修改
          </el-button>
        </el-card>
      </div>
    </div>
  </div>
</template>
<script>
  import SideMenu from '../userCenter/SideMenu'
  export default {
    name: 'UserCenterIndex',
    components: {SideMenu},
    data () {
      return {
        user: {

        },
        dialogFormVisible: false
      }
    },
    mounted () {
      this.loadUser()// 给window添加一个滚动滚动监听事件
      window.addEventListener('scroll', this.handleScroll)
    },
    methods: {
      handleScroll () { // 改变元素的top值
        debugger
        var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
        var offsetTop = document.querySelector('#side-menu').offsetTop

        if (scrollTop <= 10) {
          offsetTop = 150 - Number(scrollTop)
          document.querySelector('#side-menu').style.top = offsetTop + 'px'
        } else {
          document.querySelector('#side-menu').style.top = '150px'
        }
      },
      destroyed () { // 离开该页面需要移除这个监听的事件
        window.removeEventListener('scroll', this.handleScroll)
      },
      loadUser () {
        var _this = this
        this.$axios.post('/user-information', {
          username: this.$store.state.username
        }).then(resp => {
          if (resp && resp.data.code === 200) {
            _this.user = resp.data.result
          }
        })
      },
      onSubmit (user) {
        // 根据视图绑定的角色 id 向后端传送角色信息
        this.$axios.put('/user-information/alterUser', {
          username: user.username,
          name: user.name,
          phone: user.phone,
          email: user.email
        }).then(resp => {
          if (resp && resp.data.code === 200) {
            this.$alert('用户信息修改成功')
            this.dialogFormVisible = false
            // 修改角色后重新请求用户信息，实现视图更新
            this.loadUser()
          } else {
            this.$alert(resp.data.message)
          }
        })
      },
      alterUser () {
        this.dialogFormVisible = true
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
  #current-borrow{
    width: 980px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }
  #borrow-history{
    width: 980px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }
  #fine{
    width: 980px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }
  #user-information{
    width: 980px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }
  .menu{
    text-align: left;
    font-weight: bold;
    font-size: 23px;
    padding-left: 10px;
  }
  .info{
    font-size: 16px;
    font-weight: normal;
    margin-bottom: 6px;
  }
</style>
