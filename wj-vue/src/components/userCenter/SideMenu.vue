<template>
  <el-menu
    default-active="1"
    style="width: 170px">
    <a href="#current-borrow">
      <el-menu-item index="1">
        <i class="el-icon-reading"></i>
        <span slot="title">当前借阅</span>
      </el-menu-item>
    </a>
    <a href="#borrow-history">
      <el-menu-item index="2">
        <i class="el-icon-s-fold"></i>
        <span slot="title">借阅历史</span>
      </el-menu-item>
    </a>
    <a href="#fine">
      <el-menu-item index="3">
        <i class="el-icon-warning-outline"></i>
        <span slot="title">超期罚款</span>
      </el-menu-item>
    </a>
    <a href="#user-information">
      <el-menu-item index="4">
        <i class="el-icon-user"></i>
        <span slot="title">个人信息</span>
      </el-menu-item>
    </a>
  </el-menu>
</template>

<script>
export default {

}
</script>

<style>
a {
  text-decoration: none;
  color: inherit;
}
</style>
