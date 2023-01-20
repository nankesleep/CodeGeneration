<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 项目细节
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="querySearch.id"
          placeholder="表名称/表备注"
          class="handle-input mr10"
        ></el-input>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="dialogFormVisible = true"
          >新增表</el-button
        >
      </div>
      <el-table
        :data="tableData"
        border
        class="table"
        ref="multipleTable"
        header-cell-class-name="table-header"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          prop="id"
          label="表ID"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column prop="tableName" label="表名称"></el-table-column>
        <el-table-column prop="remark" label="表备注"></el-table-column>
        <el-table-column prop="fieldCount" label="字段数"></el-table-column>
        <el-table-column prop="createName" label="创建人"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="updateName" label="更新人"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间"></el-table-column>
        <el-table-column label="操作" width="220" align="center">
          <template #default="scope">
            <el-button
              type="text"
              icon="el-icon-search"
              class="green"
              @click="handleCheck(scope.$index, scope.row)"
              >查看</el-button
            >
            <el-button
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              type="text"
              icon="el-icon-delete"
              class="red"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :current-page="query.pageNum"
          :page-size="query.pageSize"
          :total="pageTotal"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        ></el-pagination>
      </div>
    </div>

    <!-- 编辑对话框 -->
    <el-dialog title="编辑" v-model="editVisible" width="45%">
      <el-form ref="form" :model="form" label-width="70px">
        <el-form-item label="ID">
          <el-input v-model="form.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="表名称">
          <el-input v-model="form.tableName"></el-input>
        </el-form-item>
        <el-form-item label="表备注">
          <el-input v-model="form.remark"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="editVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 新增表对话框 -->
    <el-dialog v-model="dialogFormVisible" title="新增表">
      <el-form :model="fromAdd">
        <el-form-item label="表名:" :label-width="formLabelWidth">
          <el-input v-model="fromAdd.tableName" placeholder="请填写英文表名" />
        </el-form-item>
        <el-form-item label="表备注:" :label-width="formLabelWidth">
          <el-input v-model="fromAdd.remark" placeholder="请填写表备注" />
        </el-form-item>
        <el-form-item label="默认字段:" :label-width="formLabelWidth">
          <el-checkbox
            v-model="checkAll"
            :indeterminate="isIndeterminate"
            @change="handleCheckAllChange"
            >Check all</el-checkbox
          >
          <el-checkbox-group
            v-model="checkedCities"
            @change="handleCheckedCitiesChange"
          >
            <el-checkbox v-for="city in cities" :key="city" :label="city">{{
              city
            }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="addNewTable">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {
  projectDetail_initdata,
  projectDetail_searchTableByID,
  projectDetail_addNewTable,
  projectDetail_updateTable,
  projectDetail_delTable,
} from "../network/index";
export default {
  name: "basetable",
  data() {
    return {
      query: {
        id: 0,
        pageNum: 1,
        pageSize: 10,
      },
      querySearch: {
        id: "",
      },
      dialogFormVisible: false,
      tableData: [],
      multipleSelection: [],
      delList: [],
      editVisible: false,
      pageTotal: 1,
      form: {},
      fromAdd: {
        pid:"",
        tableName: "",
        remark: "",
      },
      idx: -1,
      id: -1,

      checkAll: false,
      isIndeterminate: true,
      checkedCities: ["id", "create_time"],
      cities: ["id", "create_time", "maya", "time"],
    };
  },
  created() {
    this.setQuery();
    this.getData();
  },
  methods: {
    // 构建query
    setQuery() {
      let href = window.location.href;
      let projectID = href.split("=")[1];
      this.fromAdd.pid = projectID;
      this.query.id = projectID;
    },
    // 获取数据
    getData() {
      projectDetail_initdata(this.query).then((res) => {
        this.tableData = res.data.rows;
        this.pageTotal = parseInt(res.data.total);
      });
    },
    handleSizeChange(val) {
      this.query.pageSize = val;
      this.getData(); // 重新请求数据
    },
    handleCurrentChange(val) {
      this.query.pageNum = val;
      this.getData();
    },
    // 执行查询
    handleSearch() {
      projectDetail_searchTableByID(this.querySearch).then((res) => {
        this.pageTotal = [res.data].length;
        this.tableData = [res.data];

        if (this.pageTotal >= 1) {
          this.$message.success(`查询成功 共搜索到${this.pageTotal}条记录`);
        } else {
          this.$message.success(`未查询到数据`);
        }
      });
    },

    // 新增表
    addNewTable() {
      this.fromAdd["fieldDefaut"] = this.checkedCities;
      projectDetail_addNewTable(this.fromAdd).then((res) => {
        if (res.code === 200) {
          this.$message.success(`新增数据表:"${this.fromAdd.tableName}"成功`);
        }
      });
      this.dialogFormVisible = false;
    },

    // 查看表
    handleCheck(index, row) {
      let tableID = row.id;
      this.$router.push({
        path: "/tableCheck",
        query: {
          tableID: tableID,
        },
      });
    },

    // 执行删除
    handleDelete(index, row) {
      this.$confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          projectDetail_delTable(row.id).then((res) => {
            if (res.code === 200) {
              setTimeout(() => {
                this.$message.success(`删除字段： ${row.tableName} 成功`);
                this.tableData.splice(index, 1);
              }, 500);
            }
          });
        })
        .catch(() => {});
    },

    // 编辑数据
    handleEdit(index, row) {
      // this.$message.success("仅做演示，未提交到数据库");
      this.idx = index;
      this.form = row;
      this.editVisible = true;
    },
    // 执行编辑
    saveEdit() {
      projectDetail_updateTable(this.form).then((res) => {
        if (res.code === 200) {
          this.$message.success(`修改第 ${this.idx + 1} 行成功`);
          this.editVisible = false;
          this.$set(this.tableData, this.idx, this.form);
        }
      });
    },

    // 分页查询
    handlePageChange(val) {
      // console.log("分页导航：", val)
      this.query.pageIndex = val;

      if (this.pageTotal > 100) {
        this.getData();
      } else if (this.pageTotal > 1 && this.pageTotal < 100) {
        console.log("hello world");
      }
    },
  },
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}

.table {
  width: 100%;
  font-size: 14px;
}

.red {
  color: #ff0000;
}

.green {
  color: #00ff66;
}

.mr10 {
  margin-right: 10px;
}

.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}
</style>
