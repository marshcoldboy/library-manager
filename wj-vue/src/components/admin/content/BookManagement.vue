<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>图书管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <edit-form @onSubmit="loadBooks()" ref="edit"></edit-form>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        ref="checkBoxTable"
        :data="books"
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item>
                <span>{{ props.row.abs }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="title"
          label="书名"
          fit>
        </el-table-column>
        <el-table-column
          prop="isbn"
          label="ISBN"
          fit>
        </el-table-column>
        <el-table-column
          prop="category.name"
          label="分类"
          width="100">
        </el-table-column>
        <el-table-column
          prop="author"
          label="作者"
          fit>
        </el-table-column>
        <el-table-column
          prop="press"
          label="出版社"
          fit>
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          fit>
        </el-table-column>
        <el-table-column
          prop="amount"
          label="数量"
          fit>
        </el-table-column>
        <!--<el-table-column-->
          <!--prop="abs"-->
          <!--label="摘要"-->
          <!--show-overflow-tooltip-->
          <!--fit>-->
        <!--</el-table-column>-->
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editBook(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click="deleteBook(scope.row)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button @click="cancelSelection()">取消选择</el-button>
        <el-button @click="deleteSelectedBooks()">批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import EditForm from './EditForm'
  export default {
    name: 'BookManagement',
    components: {EditForm},
    data () {
      return {
        books: [],
        multipleSelection: []
      }
    },
    mounted () {
      this.loadBooks()
    },
    computed: {
      tableHeight () {
        return window.innerHeight - 320
      }
    },
    methods: {
      deleteBook (item) { // 删除图书按钮
        this.$confirm('此操作将永久删除该书籍, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .post('/admin/content/books/delete', {bid: item.bid}).then(resp => {
              if (resp && resp.data.code === 200) {
                this.loadBooks()
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      editBook (item) { // 添加图书按钮
        this.$refs.edit.dialogFormVisible = true
        this.$refs.edit.form = {
          bid: item.bid,
          cover: item.cover,
          title: item.title,
          author: item.author,
          isbn: item.isbn,
          price: item.price,
          press: item.press,
          abs: item.abs,
          amount: item.amount,
          category: {
            cid: item.category.cid.toString(),
            name: item.category.name
          }
        }
        // this.$refs.edit.category = {
        //   id: item.category.id.toString()
        // }
      },
      loadBooks () {
        var _this = this
        this.$axios.get('/books').then(resp => {
          if (resp && resp.data.code === 200) {
            _this.books = resp.data.result
          }
        })
      },
      deleteSelectedBooks () { // 多选删除图书
        var _this = this
        this.$confirm('此操作将永久删除所选书籍, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .post('/admin/content/books/deleteSelectedBooks', {bids: _this.multipleSelection}).then(resp => {
              if (resp && resp.data.code === 200) {
                this.loadBooks()
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      cancelSelection (rows) { // 取消删除
          this.$refs.checkBoxTable.clearSelection()
      },
      handleSelectionChange (val) {
        this.multipleSelection = []
        val.forEach(item => {
          this.multipleSelection.push(item.bid)
        })
      }
    }
  }
</script>

<style scoped>
</style>
