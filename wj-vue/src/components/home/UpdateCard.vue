<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span style="font-weight: bold;font-size: 20px">最近更新</span>
    </div>
    <div class="block">
      <el-timeline>
        <el-timeline-item timestamp="2021/7/21" placement="top">
          <el-card>
            <h4>实现第一部分基本功能</h4>
            <p>42 提交于 2021/7/21 15:50</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </el-card>
</template>

<script>
  export default {
    name: 'UpdateCard'
  }
</script>

<style scoped>
  .box-card {
    margin-top: 5px;
    width: 988px;
    height: 300px;
    text-align: left;
  }
</style>
