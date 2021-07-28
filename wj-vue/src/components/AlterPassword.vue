<template>
  <body id="paper">
  <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">修改密码</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.email"
                auto-complete="off" placeholder="E-Mail"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.verificationCode"
                auto-complete="off" placeholder="验证码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.password"
                auto-complete="off" placeholder="新密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="loginForm.repeat"
                auto-complete="off" placeholder="重复密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="back">取消</el-button>
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="alterPassword">修改密码</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
export default{
  data () {
    return {
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        email: [{required: true, message: '请输入邮箱', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}], // 输入新密码
        repeat: [{required: true, message: '请重复一遍密码', trigger: 'blur'}], // 重复一遍新密码
        verificationCode: [{required: true, message: '请输入验证码', trigger: 'blur'}] // 验证码
      },
      checked: true,
      loginForm: { // 修改密码的表单内容
        username: '',
        password: '',
        repeat: '',
        phone: '',
        email: '',
        verificationCode: ''
      },
      loading: false
    }
  },
  methods: {
    back () { // 取消更改密码回到登录界面
      this.$router.push('./Login')
    }
  }
}
</script>
<style>
#paper {
  background:url("../assets/img/bg/loginbg.jpg") no-repeat;
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: -5px 0px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
