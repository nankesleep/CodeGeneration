<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 表信息
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="querySearch.id"
          placeholder="字段名称"
          class="handle-input mr10"
        ></el-input>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch"
          >搜索</el-button
        >
        <el-button type="primary" icon="el-icon-search" @click="addNewFiled"
          >新增字段</el-button
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
          label="主键ID"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="fieldName"
          label="字段名称"
          width="120"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="pid"
          label="关联表"
          width="80"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="type"
          label="字段类型"
          width="100"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="length"
          label="长度"
          width="100"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="remark"
          label="备注"
          width="200"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="defaultValue"
          label="默认值"
          width="110"
        ></el-table-column>
        <el-table-column label="键类型" align="center" width="110">
          <template #default="scope">
            {{ keyType[scope.row.keyType] }}
          </template>
        </el-table-column>
        <el-table-column label="主键自增" align="center" width="100">
          <template #default="scope">
            {{ scope.row.isAuto === 0 ? "无" : "是" }}
          </template>
        </el-table-column>
        <el-table-column label="运行为null" align="center" width="100">
          <template #default="scope">
            {{ scope.row.isNull === 0 ? "是" : "否" }}
          </template>
        </el-table-column>
        <!-- <el-table-column prop="fuctionId" label="功能id" width="80"></el-table-column> -->
        <el-table-column label="操作" width="200" align="center">
          <template #default="scope">
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
    <el-dialog title="编辑" v-model="editVisible" width="30%">
      <el-form ref="form" :model="form" label-width="90px">
        <el-form-item label="主键ID">
          <el-input v-model="form.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="字段名称">
          <el-input v-model="form.fieldName"></el-input>
        </el-form-item>
        <el-form-item label="关联表">
          <el-input v-model="form.pid"></el-input>
        </el-form-item>
        <el-form-item label="字段类型">
          <el-input v-model="form.type"></el-input>
        </el-form-item>
        <el-form-item label="长度">
          <el-input v-model="form.length"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark"></el-input>
        </el-form-item>
        <el-form-item label="默认值">
          <el-input v-model="form.defaultValue"></el-input>
        </el-form-item>
        <el-form-item label="键类型">
          <el-input v-model="form.keyType"></el-input>
        </el-form-item>
        <el-form-item label="主键自增">
          <el-input v-model="form.isAuto"></el-input>
        </el-form-item>
        <el-form-item label="运行为null">
          <el-input v-model="form.isNull"></el-input>
        </el-form-item>
        <el-form-item label="功能id">
          <el-input v-model="form.fuction"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="editVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {
  tableAttribute_getInfo,
  tableAttribute_getfiledByID,
  tableAttribute_deletefiledByID,
  tableAttribute_updatefiledByID,
} from "../network/index";
export default {
  name: "basetable",
  data() {
    return {
      query: {
        id: "",
        pageNum: 1,
        pageSize: 10,
      },
      querySearch: {
        id: "",
      },
      tableData: [],
      multipleSelection: [],
      delList: [],
      editVisible: false,
      pageTotal: "",
      form: {
        id: "",
        pid: "",
        fieldName: "",
        type: "",
        length: "",
        remark: "",
        defaultValue: "",
        keyType: "",
        isAuto: "",
        isNull: "",
        fuction: "",
      },
      fromAdd: {},
      idx: -1,
      id: -1,

      checkAll: false,
      isIndeterminate: true,
      checkedCities: ["id", "create_time"],
      cities: ["id", "create_time", "maya", "time"],

      keyType: {
        0: "无",
        1: "主键",
        2: "唯一索引",
        3: "普通索引",
      },
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
      let tableID = href.split("=")[1];
      this.form.pid = tableID;
      this.query.id = tableID;
    },
    // 获取数据
    getData() {
      tableAttribute_getInfo(this.query).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data.rows;
          this.pageTotal = parseInt(res.data.total);
        }
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
      tableAttribute_getfiledByID(this.querySearch).then((res) => {
        if (res.code === 200) {
          this.pageTotal = [res.data].length;
          this.tableData = [res.data];

          if (this.pageTotal >= 1) {
            this.$message.success(`查询成功 共搜索到${this.pageTotal}条记录`);
          } else {
            this.$message.success(`未查询到数据`);
          }
        }
      });
    },

    addNewFiled() {
      this.$message.success("开发中......");
    },

    // 执行删除
    handleDelete(index, row) {
      // this.$message.success("仅做演示，未提交到数据库");
      this.$confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          tableAttribute_deletefiledByID(row.id).then((res) => {
            if (res.code === 200) {
              setTimeout(() => {
                this.$message.success(`删除字段： ${row.fieldName} 成功`);
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
      tableAttribute_updatefiledByID(this.form).then((res) => {
        if (res.code === 200) {
          this.$message.success(`修改第 ${this.idx + 1} 行成功`);
          this.editVisible = false;
          this.$set(this.tableData, this.idx, this.form);
        }
      });
    },

    // 分页查询
    handlePageChange(val) {
      console.log("分页导航：", val);
      this.queryInit.pageIndex = val;

      if (this.pageTotal > 10) {
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
