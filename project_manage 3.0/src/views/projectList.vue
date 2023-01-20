<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 项目列表
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-select
          v-model="querySearch.status"
          placeholder="类型"
          class="handle-select mr10"
        >
          <el-option key="1" label="进行中" value="0"></el-option>
          <el-option key="2" label="已完成" value="1"></el-option>
          <el-option key="3" label="全选" value="all"></el-option>
        </el-select>
        <el-input
          v-model="querySearch.id"
          placeholder="请输入项目ID"
          class="handle-input mr10"
        ></el-input>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch"
          >搜索</el-button
        >
        <el-button type="primary" @click="dialogFormVisible = true"
          >新建项目</el-button
        >
      </div>

      <!-- 项目列表展示区域 -->
      <div class="projectListContainer">
        <div v-for="(item, index) in projectList" :key="item.id">
          <el-card
            shadow="hover"
            class="Project elcard"
            style="width: 400px; ,height: 403px"
          >
            <div class="projectContainer">
              <div class="projectTitle">
                <div class="titleLeft">
                  <el-button type="success" circle size="large">{{
                    item.id
                  }}</el-button>
                </div>
                <div class="titleCenter">
                  <div class="centerUp">
                    {{ item.projectName }}
                  </div>
                  <div class="centerDown">
                    {{ item.description }}
                  </div>
                </div>
                <div class="titleRight">
                  {{ item.status === "0" ? "进行中" : "已完成" }}
                </div>
              </div>
              <div class="projectInfo">
                <div class="infoLeft">
                  <div>管理员：{{ item.manageName }}</div>
                  <div>表前缀：{{ item.tablePrefix }}</div>
                  <div>创建人：{{ item.createName }}</div>
                  <div>更新人：{{ item.updateName }}</div>
                </div>
                <div class="infoRigth">
                  <div>数据库类型：{{ item.type }}</div>
                  <div>项目根路径：{{ item.location }}</div>
                  <div>创建时间：{{ item.createTime }}</div>
                  <div>更新时间：{{ item.updateTime }}</div>
                </div>
              </div>
              <div class="projectEdit">
                <el-button type="primary" @click="checkTheProject(item.id)"
                  >查看</el-button
                >
                <el-button type="primary" @click="editTheProject(item)"
                  >编辑</el-button
                >
                <el-button type="primary" @click="checkTheMember(item.id)"
                  >成员</el-button
                >
                <el-button type="primary" @click="deleteTheProject(item, index)"
                  >删除</el-button
                >
              </div>
            </div>
          </el-card>
        </div>

        <el-dialog v-model="dialogFormVisible" title="创建项目">
          <el-form :model="form">
            <el-form-item label="数据库类型:" :label-width="formLabelWidth">
              <el-select
                v-model="form.databaseType"
                placeholder="Please select a SpatialQueryMode"
              >
                <el-option label="Mysql" value="Mysql" />
                <el-option label="Oracle" value="Oracle" />
              </el-select>
            </el-form-item>
            <el-form-item label="项目名称:" :label-width="formLabelWidth">
              <el-input
                v-model="form.projectName"
                placeholder="请填写项目名称"
              />
            </el-form-item>
            <el-form-item label="项目根路径:" :label-width="formLabelWidth">
              <el-input
                v-model="form.location"
                placeholder="请填项目根路径，如:com.wjtsc.code"
              />
            </el-form-item>
            <el-form-item label="项目描述:" :label-width="formLabelWidth">
              <el-input
                v-model="form.description"
                resize="horizontal"
                placeholder="请填写项目描述"
              />
            </el-form-item>
            <el-form-item label="表前缀:" :label-width="formLabelWidth">
              <el-input
                v-model="form.tablePrefix"
                resize="horizontal"
                placeholder="请填写表前缀"
              />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="addNewProject">确定</el-button>
            </span>
          </template>
        </el-dialog>

        <el-dialog
          v-model="delFormVisible"
          :title="'删除项目-' + delFrom.delProjectName"
        >
          <el-form :model="delFrom">
            <el-form-item
              label="此删除操作不可逆，请确认后操作!如需删除，请再次输入登录密码"
              :label-width="formLabelWidth"
            >
              <el-input
                v-model="loginPasswordForComfimDel"
                placeholder="请输入密码"
              />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="delFormVisible = false">取消</el-button>
              <el-button type="primary" @click="deleteTheProjectByID"
                >确定</el-button
              >
            </span>
          </template>
        </el-dialog>

        <el-dialog v-model="menberVisible" title="项目成员" width="78%">
          <el-form>
            <el-form-item label="" :label-width="formLabelWidth">
              <div class="handle-box">
                <el-input
                  v-model="querySearch.id"
                  placeholder="成员名字"
                  class="handle-input mr10"
                  @input="setInputNull"
                ></el-input>
                <el-button
                  type="primary"
                  icon="el-icon-search"
                  @click="searchMenber"
                  >搜索</el-button
                >
                <el-button
                  type="primary"
                  icon="el-icon-search"
                  @click="addNewMenberVisible = true"
                  v-if="isAdmin"
                  >添加成员</el-button
                >
              </div>
              <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
              >
                <el-table-column
                  prop="id"
                  label="成员ID"
                  width="80"
                  align="center"
                ></el-table-column>
                <el-table-column prop="isAdmin" label="管理员">
                  <template #default="scope">
                    {{ scope.row.isAdmin == 0 ? "是" : "否" }}
                  </template>
                </el-table-column>
                <el-table-column prop="power" label="权限">
                  <template #default="scope">
                    {{ scope.row.power == 0 ? "可编辑" : "仅查看" }}
                  </template>
                </el-table-column>
                <el-table-column prop="userName" label="账号"></el-table-column>
                <el-table-column prop="nickName" label="昵称"></el-table-column>
                <el-table-column prop="email" label="邮箱"></el-table-column>
                <el-table-column
                  prop="phoneNumber"
                  label="电话号码"
                ></el-table-column>
                <el-table-column
                  v-if="isAdmin"
                  label="操作"
                  width="200"
                  align="center"
                >
                  <template #default="scope">
                    <el-button
                      type="text"
                      icon="el-icon-edit"
                      @click="handleEditMenber(scope.$index, scope.row)"
                      >修改权限</el-button
                    >
                    <el-button
                      type="text"
                      icon="el-icon-delete"
                      class="red"
                      @click="handleDeleteMenber(scope.$index, scope.row)"
                      >删除</el-button
                    >
                  </template>
                </el-table-column>
              </el-table>
            </el-form-item>
          </el-form>
        </el-dialog>
      </div>

      <!-- 分页查询-项目总数-页码 -->
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="query.pageNum"
          :page-size="query.pageSize"
          layout="total, prev, pager, next, jumper"
          :total="pageTotal"
        >
        </el-pagination>
      </div>
    </div>

    <!-- 项目编辑对话框 -->
    <el-dialog title="编辑项目" v-model="editVisible" width="50%">
      <el-form ref="form" :model="fromEdit" label-width="120px">
        <div class="editPlane">
          <div class="editPlaneLeft">
            <el-form-item label="项目名称">
              <el-input v-model="fromEdit.projectName"></el-input>
            </el-form-item>
            <el-form-item label="数据库类型:" :label-width="formLabelWidth">
              <el-select v-model="fromEdit.type" placeholder="请选择数据库类型">
                <el-option label="Mysql" value="0" />
                <el-option label="Oracle" value="1" />
              </el-select>
            </el-form-item>
            <el-form-item label="表前缀">
              <el-input v-model="fromEdit.tablePrefix"></el-input>
            </el-form-item>
            <el-form-item label="创建人">
              <el-input v-model="fromEdit.createName"></el-input>
            </el-form-item>
            <el-form-item label="更新人">
              <el-input v-model="fromEdit.updateName"></el-input>
            </el-form-item>
            <el-form-item label="项目描述">
              <el-input v-model="fromEdit.description"></el-input>
            </el-form-item>
          </div>
          <div class="editPlaneRigth">
            <el-form-item label="项目根路径">
              <el-input v-model="fromEdit.location"></el-input>
            </el-form-item>
            <el-form-item label="项目状态:" :label-width="formLabelWidth">
              <el-select
                v-model="fromEdit.status"
                placeholder="Please select a SpatialQueryMode"
              >
                <el-option label="进行中" value="0" />
                <el-option label="已完成" value="1" />
              </el-select>
            </el-form-item>
            <el-form-item label="项目管理员">
              <el-input v-model="fromEdit.manageName"></el-input>
            </el-form-item>
            <el-form-item label="创建时间">
              <el-input v-model="fromEdit.createTime"></el-input>
            </el-form-item>
            <el-form-item label="更新时间">
              <el-input v-model="fromEdit.updateTime"></el-input>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="editVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 编辑成员权限对话框 -->
    <el-dialog title="修改权限" v-model="editMenberVisible" width="50%">
      <el-form ref="form" :model="fromEditMenber" label-width="120px">
        <div class="editPlane">
          <div class="editPlaneLeft">
            <el-form-item label="权限:" :label-width="formLabelWidth">
              <el-select v-model="fromEditMenber.power" placeholder="选择权限">
                <el-option label="可查看" value="0" />
                <el-option label="可编辑" value="1" />
              </el-select>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="editMenberVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEditMenber">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 添加成员对话框 -->
    <el-dialog title="添加成员" v-model="addNewMenberVisible" width="50%">
      <el-form ref="form" :model="fromAddMenber" label-width="120px">
        <div class="editPlane">
          <div class="editPlaneLeft">
            <el-form-item label="成员ID">
              <el-input v-model="fromAddMenber.id"></el-input>
            </el-form-item>
            <el-form-item label="成员名称">
              <el-input v-model="fromAddMenber.userName"></el-input>
            </el-form-item>
            <el-form-item label="昵称">
              <el-input v-model="fromAddMenber.nickName"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="fromAddMenber.email"></el-input>
            </el-form-item>
            <el-form-item label="电话号码">
              <el-input v-model="fromAddMenber.phoneNumber"></el-input>
            </el-form-item>
            <el-form-item label="管理员:" :label-width="formLabelWidth">
              <el-select
                v-model="fromAddMenber.isAdmin"
                placeholder="是否为管理员n"
              >
                <el-option label="是" value="0" />
                <el-option label="否" value="1" />
              </el-select>
            </el-form-item>
            <el-form-item label="权限:" :label-width="formLabelWidth">
              <el-select v-model="fromAddMenber.power" placeholder="选择权限">
                <el-option label="可编辑" value="0" />
                <el-option label="仅查看" value="1" />
              </el-select>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="addNewMenberVisible = false">取 消</el-button>
          <el-button type="primary" @click="addNewMenber">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {
  projectlist_searchByID,
  projectlist_searchByStatus,
  projectlist_initData,
  projectlist_editProject,
  projectlist_delProjectByID,
  projectlist_verifyPassword,
  projectlist_addProject,
  projectlist_checkTheMenber,
  menber_addMenber,
  member_deleteMenber,
  member_editMenberPower,
} from "../network/index";
export default {
  name: "basetable",
  data() {
    return {
      query: {
        pageNum: 1,
        pageSize: 10,
      },
      querySearch: {
        id: "",
        status: "all",
      },
      tableData: [],
      // multipleSelection: [],
      // delList: [],
      editVisible: false,
      pageTotal: 0,
      // form: {},
      idx: -1,
      id: -1,
      projectList: [],
      dialogFormVisible: false,
      form: {
        type: "Mysql",
        projectName: "",
        location: "",
        description: "",
        tablePrefix: "",
      },
      fromEdit: {
        projectName: "",
        type: "0", // type number
        tablePrefix: "",
        createName: "",
        updateName: "",
        description: "",
        location: "",
        status: "0",
        manageName: "",
        createTime: "",
        updateTime: "",
      },
      delFormVisible: false,
      delFrom: {
        projectID: "",
        delProjectName: "",
      },
      loginPasswordForComfimDel: "",
      delIndex: -1,
      menberVisible: false,
      projectIDNow: "",
      editMenberVisible: false,
      fromEditMenber: {},
      addNewMenberVisible: false,
      fromAddMenber: {},
      isAdmin: false,
    };
  },
  //     this.getData();
  //   },
  mounted() {
    this.getData();
  },
  methods: {
    // 获取数据
    getData() {
      projectlist_initData(this.query).then((res) => {
        if (res.code === 200) {
          this.projectList = res.data.rows;
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

    // 新建项目
    addNewProject() {
      this.dialogFormVisible = false;
      let newProjectName = this.form.projectName;
      if (this.form.type === "Mysql") {
        this.form.type = "0";
      } else {
        this.form.type = "1";
      }
      projectlist_addProject(this.form).then((res) => {
        if (res.code === 200) {
          this.$message.success(`新增项目"${newProjectName}"成功`);
        }
      });
    },

    // 查看项目
    checkTheProject(projectID) {
      this.$router.push({
        path: "/projectDetail",
        query: {
          projectID: projectID,
        },
      });
    },

    // 编辑项目
    editTheProject(project) {
      this.editVisible = true;
      this.fromEdit = project;
    },
    // 执行编辑
    saveEdit() {
      this.editVisible = false;
      // this.$message.success(`修改第 ${this.idx + 1} 行成功`);
      // this.$set(this.tableData, this.idx, this.form);

      let projectID = this.fromEdit.id;

      projectlist_editProject(this.fromEdit).then((res) => {
        if (res.code === 200) {
          this.$message.success(`修改项目ID为:"${projectID}"成功`);
        }
      });
    },

    // 查看成员
    checkTheMember(projectID) {
      projectlist_checkTheMenber(projectID).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data.users;
          if (res.data.isAdmin == 1) {
            this.isAdmin = false;
          } else {
            this.isAdmin = true;
          }
          this.menberVisible = true;
        }
      });
    },

    // 删除项目
    deleteTheProject(project, index) {
      this.delFrom.delProjectName = project.projectName;
      this.delFrom.projectID = project.id;
      this.delIndex = index;
      this.delFormVisible = true;
    },

    async deleteTheProjectByID() {
      this.delFormVisible = false;
      projectlist_verifyPassword({
        password: this.loginPasswordForComfimDel,
      }).then((res) => {
        if (res.code === 200) {
          projectlist_delProjectByID(this.delFrom.projectID).then((res) => {
            if (res.code === 200) {
              setTimeout(() => {
                this.$message.success(`删除成功`);
                this.projectList.splice(this.delIndex, 1);
              }, 500);
            } else {
              this.$message.error(`校验失败，请检查密码是否输入正确`);
            }
          });
        } else {
          this.$message.error(`校验失败，请检查密码是否输入正确`);
        }
      });
    },

    // 执行查询
    handleSearch() {
      if (this.querySearch.status === "all") {
        if (this.querySearch.id <= 0) {
          this.$message.warning("项目ID必须填写且大于0");
          return null;
        }

        projectlist_searchByID(this.querySearch.id).then((res) => {
          if (res.code === 200) {
            this.projectList = [];
            this.projectList = [res.data];

            // this.pageTotal = this.projectList.length
            this.pageTotal = this.projectList.length;
            // this.query.pageSize = res.length

            if (this.pageTotal >= 1) {
              this.$message.success(`查询成功 共搜索到${this.pageTotal}条记录`);
            } else {
              this.$message.success(`未查询到数据`);
            }
          }
        });
      } else if (this.querySearch.id === "") {
        let queryCombine = {};
        Object.assign(queryCombine, this.query, this.querySearch);
        projectlist_searchByStatus(queryCombine).then((res) => {
          if (res.code === 200) {
            this.projectList = res.data.rows;

            // this.pageTotal = this.projectList.length
            this.pageTotal = parseInt(res.data.total);
            // this.query.pageSize = res.length

            if (this.pageTotal >= 1) {
              this.$message.success(`查询成功 共搜索到${this.pageTotal}条记录`);
            } else {
              this.$message.success(`未查询到数据`);
            }
          }
        });
      } else {
        this.$message.warning(`请设置全选或清除搜索框内容`);
      }
    },

    // 删除成员
    handleDeleteMenber(index, item) {
      this.$confirm("确定要删除吗？", "提示", {
        type: "warning",
      })
        .then(() => {
          let query = {
            userId: item.id,
            projectId: this.projectIDNow,
          };
          member_deleteMenber({ parmas: query }).then((res) => {
            // 这里返回始终为{}，不知道为啥，使用方式和前面的一摸一样的
            console.log("删除成员:", res);
            this.$message.success("删除成功");
            this.tableData.splice(index, 1);
          });
        })
        .catch(() => {});
    },

    // 修改成员权限
    handleEditMenber(index, row) {
      this.idx = index;
      this.form = row;
      this.editMenberVisible = true;
    },
    saveEditMenber() {
      this.editMenberVisible = false;
      member_editMenberPower(this.fromEditMenber).then((res) => {
        console.log("Edit-menberPower:", res);
      });
    },

    // 添加成员:
    addNewMenber() {
      menber_addMenber(this.fromAddMenber).then((res) => {
        if (res.code === 200) {
          this.$message.success(
            `新增成员: ${this.fromAddMenber.userName} 成功`
          );
          this.addNewMenberVisible = false;
        }
      });
    },
  },
};
</script>

<style scoped>
.editPlane {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.projectListContainer {
  display: flex;
  flex-wrap: wrap;
  width: 100vw;

  margin: 20px;
}

.projectListContainer .elcard {
  width: 400px;
  height: 260px;
  margin: 10px;
}

.projectContainer {
  display: flex;

  flex-direction: column;
  /* justify-content: center; */
  align-items: center;

  height: 200px;
}

.projectTitle {
  display: flex;
  flex-direction: row;
  justify-content: space-around;

  height: 25%;

  /* background-color: yellow; */
}

.projectTitle .titleLeft {
  margin-right: 90px;
  /* flex: 1; */
}

.projectTitle .titleCenter {
  padding-right: 30px;
}

.projectTitle .titleRight {
  /* flex: 1; */
}

.projectInfo {
  display: flex;
  height: 60%;
  width: 100%;

  flex-direction: row;
  justify-content: space-between;
}

.projectEdit {
  display: flex;
  height: 15%;
  width: 100%;
  flex-direction: row;
  justify-content: space-between;
}

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
