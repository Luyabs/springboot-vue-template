<template>
  <div>
    <!-- 搜索框 与 新增 -->
    <div id="headBar" style="margin-top: 20px; margin-left: 20px;">
      <el-row :gutter="20">
        <el-col :span="4"> <el-input placeholder="类别" size="medium" prefix-icon="el-icon-search" v-model="searchType"> </el-input> </el-col>
        <el-col :span="4"> <el-input placeholder="书名" size="medium" prefix-icon="el-icon-search" v-model="searchName"> </el-input> </el-col> 
        <el-col :span="4"> <el-input placeholder="简介" size="medium" prefix-icon="el-icon-search" v-model="searchDescription"> </el-input> </el-col> 
        <el-button size="medium" @click="fetchData()"> 查询 </el-button>
        <el-button type="success" size="medium" @click="add()"> 新增 </el-button>
        <el-button size="primary" @click="helloWorld()"> Hello! </el-button>
      </el-row>

    </div>

    <!-- 表格 -->
    <div id="table" style="margin-top: 10px;">
      <el-table
        :data="tableData"
        style="width: 100%">
        <!-- <el-table-column type="index" width="30" align="center"> </el-table-column> -->
        <el-table-column prop="id" label="编号" width="60" align="center"> </el-table-column>
        <el-table-column prop="type" label="类别" width="180"> </el-table-column>
        <el-table-column prop="name" label="书名" width="180"> </el-table-column>
        <el-table-column prop="description" label="简介"> </el-table-column>
        <el-table-column label="操作" width="250">
          <template slot-scope="scope">
            <el-button type="primary" size="medium" @click="edit(scope.row.id)"> 修改 </el-button>
            <el-button type="warning" size="medium" @click="remove(scope.row.id)"> 删除 </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分页组件 -->
    <div id="pagination" style="margin-top: 15px; margin-left: 20px;">
      <el-pagination
        :page-sizes="[5, 10, 20, 30]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        background
      ></el-pagination>
    </div>
  
    <!-- 增加框 -->
    <div id="addDialog">
      <el-dialog title="新增书本" :visible.sync="addDialogVisible" width="30%">
        <el-form label-position="right" label-width="80px">
          <el-form-item label="类别"> <el-input v-model="formData.type"> </el-input> </el-form-item>
          <el-form-item label="书名"> <el-input v-model="formData.name"> </el-input> </el-form-item>
          <el-form-item label="简介"> <el-input v-model="formData.description"> </el-input> </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addConfirm()">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  
    <!-- 编辑框 -->
    <div id="editDialog">
      <el-dialog title="编辑书本" :visible.sync="editDialogVisible" width="30%">
        <el-form label-position="right" label-width="80px">
          <el-form-item label="类别"> <el-input v-model="formData.type"> </el-input> </el-form-item>
          <el-form-item label="书名"> <el-input v-model="formData.name"> </el-input> </el-form-item>
          <el-form-item label="简介"> <el-input v-model="formData.description"> </el-input> </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="editConfirm()">确 定</el-button>
        </span>
      </el-dialog>
    </div>

  </div>
</template>

<script>
import { getPage, getById, addBook, editBook, removeBook, sayHello } from '@/api/book'

export default {
  data() {
    return {
      searchType: '',
      searchName: '',
      searchDescription: '',

      tableData: [],
      total: 0,
      currentPage: 1,
      pageSize: 5,

      addDialogVisible: false, // 增添表单可见度
      editDialogVisible: false, // 修改表单可见度

      formData: {} // 表单数据
      
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    // helloworld
    helloWorld() {
      sayHello().then(response => {
        this.$message.success(response.data.hello + this.searchName + ' ' + this.searchName + ' ' + this.searchDescription)
      })
    },

    // 改变每页大小
    handleSizeChange(val) {
      this.pageSize = val
      this.fetchData()
    },

    // 改变当前页号
    handleCurrentChange(val) {
      this.currentPage = val
      this.fetchData()
    },
    
    // 获取全部数据
    fetchData() {
      const params = {
        currentPage: this.currentPage,
        pageSize: this.pageSize,
        type: this.searchType,
        name: this.searchName,
        description: this.searchDescription
      }
      getPage(params).then(response => {
        if (response.success === true) {
          this.tableData = response.data.page.records
          this.total = response.data.page.total
          this.$message.success(response.message)
        }
        else
          this.$message.error(response.message)
      })
    },

    // 打开增加框
    add() {
      this.formData = {}  // 清楚表单
      this.addDialogVisible = true
    },

    // 确认增加
    addConfirm() {
      addBook(this.formData).then(response => {
        if (response.success === true)
          this.$message.success(response.message)
        else
          this.$message.error(response.message)
      }).finally(
        this.fetchData(),
        this.addDialogVisible = false
      )
    },

    // 打开修改框
    edit(id) {
        this.editDialogVisible = true
        // 获取该id对应的具体信息
        getById(id).then(response => {
          if (response.success === true) {
            this.formData = response.data.book
          }
          else {
            this.$message.error(response.message)
            this.editDialogVisible = false
          }
        })
    },

    // 确认修改
    editConfirm() {
      editBook(this.formData).then(response => {
        if (response.success === true)
          this.$message.success(response.message)
        else
          this.$message.error(response.message)
      }).finally(
        this.fetchData(),
        this.editDialogVisible = false
      )
    },

    // 删除
    remove(id) {
      this.$confirm('此操作将永久删除该文件(id: ' + id + '), 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        removeBook(id).then(response => {
          if (response.success === true)
            this.$message.success(response.message)
          else
            this.$message.error(response.message)
          this.fetchData()
        })
      }).catch(() => {
        this.$message.info('已取消删除');          
      });
    },

  }
}
</script>
