<template>
  <div>
    <el-menu
      :default-active="currentPath"
      router
      mode="horizontal"
      background-color="snow"
      text-color="#222"
      active-text-color="red"
      style="min-width: 1300px">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>
      <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">图书管理系统</span>
      <el-input
        placeholder="快速搜索..."
        prefix-icon="el-icon-search"
        size="medium"
        style="width: 300px;position:absolute;margin-top: 12px;right: 18%"
        v-model="keywords">
      </el-input>
      <div v-if="showLogin">
        <el-button id="login" style="position: absolute;right: 12px;margin-top: 10px" type="primary" icon="el-icon-user-solid" @click="register">登录</el-button>
      </div>
      <div v-if="showUser">
        <span style="position: absolute;right: 12px;padding-top: 20px" >{{ this.$store.state.username }}</span>
      </div>
    </el-menu>
  </div>
</template>

<script>

  // import store from '@/store'

  export default {
    name: 'NavMenu',
    data () {
      // if (store.state.username) {
      //   return {
      //     navList: [
      //       {name: '/index', navItem: '首页'},
      //       {name: '/library', navItem: '图书馆'},
      //       {name: '/jotter', navItem: '个人中心'}
      //     ],
      //     keywords: ''
      //   }
      // } else {
        return {
          navList: [
            {name: '/index', navItem: '首页'},
            {name: '/library', navItem: '图书馆'},
            {name: '/userCenter', navItem: '个人中心'},
            {name: '/login', navItem: '管理中心'}
          ],
          keywords: '',
          showLogin: true,
          showUser: false
        }
      // }
    },
    mounted () {
      this.checkLoginStatus()
    },
    computed: {
      hoverBackground () {
        return '#ffd04b'
      },
      currentPath () {
        var x = this.$route.path.indexOf('/', 1)
        if (x !== -1) {
          return this.$route.path.substring(0, x)
        } else {
          return this.$route.path
        }
      }
    },
    methods: {
      register () {
        this.$router.push('./Login')
      },
      checkLoginStatus () {
        if (this.$store.state.username) {
          this.showLogin = false
          this.showUser = true
        }
      }
    }
  }
</script>

<style scoped>
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }

</style>
