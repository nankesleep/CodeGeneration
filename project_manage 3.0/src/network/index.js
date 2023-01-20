import { request } from "./request";

// 登录 - 验证用户名和密码并获取token
export const login = (query) => {
  return request({
    url: "/login",
    method: "post",
    headers: {
      isToken: false,
    },
    data: query,
  });
};

// 退出登录
export const loginOut = (query) => {
  return request({
    url: "/logout",
    method: "post",
    data: query,
  });
};

// 首页 - 获取前五个项目信息
export const homePage_getFiveProjects = (query) => {
  return request({
    url: "/project/getProject",
    method: "get",
    params: query,
  });
};

// 项目列表 - 初始化项目列表
export const projectlist_initData = (query) => {
  return request({
    url: "/project/getProjectList",
    method: "get",
    params: query,
  });
};

// 项目列表 - 通过id搜索项目
export const projectlist_searchByID = (query) => {
  return request({
    url: "/project/getProjectById/" + query,
    method: "get",
    headers: {
      isToken: false,
    },
    params: query,
  });
};

// 项目列表 - 通过状态搜索项目
export const projectlist_searchByStatus = (query) => {
  return request({
    url: "/project/getProjectByStatus",
    method: "get",
    headers: {
      isToken: false,
    },
    params: query,
  });
};

// 项目列表 - 编辑项目
export const projectlist_editProject = (query) => {
  return request({
    url: "/project/updateProject",
    method: "put",
    data: query,
  });
};

// 项目列表 - 验证密码
export const projectlist_verifyPassword = (query) => {
  return request({
    url: "/project/verifyPassword",
    method: "post",
    data: query,
  });
};

// 项目列表 - 根据id删除项目
export const projectlist_delProjectByID = (query) => {
  return request({
    url: "/project/deleteProject/" + query,
    method: "delete",
    params: query,
  });
};

// 项目列表 - 添加项目
export const projectlist_addProject = (query) => {
  return request({
    url: "/project/addProject",
    method: "post",
    data: query,
  });
};

// 项目列表 - 查看成员
export const projectlist_checkTheMenber = (query) => {
  return request({
    url: "/user/getProjectUser/" + query,
    method: "get",
    params: query,
  });
};

// 项目细节(查看项目) - 初始化数据
export const projectDetail_initdata = (query) => {
  return request({
    url: "/table/getTableList",
    method: "get",
    headers: {
      isToken: false,
    },
    params: query,
  });
};

// 查看项目 - 搜索项目表
export const projectDetail_searchTableByID = (query) => {
  return request({
    url: "/table/getTableById/{id}",
    method: "get",
    headers: {
      isToken: false,
    },
    params: query,
  });
};

// 查看项目 - 添加项目表
export const projectDetail_addNewTable = (query) => {
  return request({
    url: "/table/addTable",
    method: "post",
    data: query,
  });
};

// 查看项目 - 更新项目表
export const projectDetail_updateTable = (query) => {
  return request({
    url: "/table/updateTable",
    method: "put",
    data: query,
  });
};

// 查看项目 - 删除项目表
export const projectDetail_delTable = (query) => {
  return request({
    url: "/table/deleteTable/" + query,
    method: "delete",
    headers: {
      isToken: false,
    },
    params: query,
  });
};

// 表信息 - 获取href上指定id表的属性信息
export const tableAttribute_getInfo = (query) => {
  return request({
    url: "/field/getFieldList",
    method: "get",
    params: query,
  });
};

// 表信息 - 根据id查询表指定字段
export const tableAttribute_getfiledByID = (query) => {
  return request({
    url: "/field/getFieldById/" + query,
    method: "get",
    params: query,
  });
};

// 表信息 - 根据id删除表中指定字段
export const tableAttribute_deletefiledByID = (query) => {
  return request({
    url: "/field/deleteField/"+query,
    method: "delete",
    params: query,
  });
};

// 表信息 - 根据id更新表中指定字段属性
export const tableAttribute_updatefiledByID = (query) => {
  return request({
    url: "/field/updateField",
    method: "put",
    data: query,
  });
};

// 成员信息 - 添加成员
export const menber_addMenber = (query) => {
  return request({
    url: "/user/addUser",
    method: "post",
    params: query,
  });
};

// 成员信息 - 删除成员
export const member_deleteMenber = (query) => {
  return request({
    url: "/user/deleteUser/{userId}/{projectId}",
    method: "delete",
    params: query,
  });
};

// 成员信息 - 修改成员权限
export const member_editMenberPower = (query) => {
  return request({
    url: "/user/updateAdmim",
    method: "put",
    data: query,
  });
};
