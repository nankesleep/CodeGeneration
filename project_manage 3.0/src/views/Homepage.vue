<template>
    <div>
        <el-row class="rowBox">
            <el-col>
                <!-- 首页项目四个栏目数据 -->
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="6" v-for="item in topData" :key="item">
                        <el-card shadow="hover" :body-style="{ padding: '0px' }">
                            <div :class="item.class">
                                <i :class="item.icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{ item.number }} 个</div>
                                    <div>{{ item.text }}</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>

                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-card shadow="hover">
                            <div class="echartsBar" ref="echartsBar"></div>
                        </el-card>
                    </el-col>
                    <el-col :span="12">
                        <el-card shadow="hover">
                            <div class="echartsLine" ref="echartsLine"></div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>

        <div class="projectListContainer rowBox">
            <el-card shadow="hover" class="addNew elcard" style="width: 450px;,height:403px;">
                <el-button type="primary" @click="createProjectDialogVisible = true">新建项目</el-button>
            </el-card>
            <div v-for="item in projectList" :key="item.id">
                <el-card shadow="hover" class="Project elcard" style="width: 450px;,height:403px;">
                    <div class="projectContainer">
                        <div class="projectTitle">
                            <div class="titleLeft">
                                <el-button type="success" circle size="large">{{ item.id }}</el-button>
                            </div>
                            <div class="titleCenter">
                                <div class="centerUp">
                                    {{ item.projectName }}
                                </div>
                                <div class="centerDown">
                                    {{ item.description }}
                                </div>
                            </div>
                            <div class="titleRight">{{ item.status === "0" ? "进行中" : "已完成" }}</div>
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
                            <el-button type="primary" @click="checkTheProject(item.id)">查看</el-button>
                            <el-button type="primary">编辑</el-button>
                            <el-button type="primary">成员</el-button>
                            <el-button type="primary">删除</el-button>
                        </div>
                    </div>
                </el-card>
            </div>
        </div>
    </div>

    <!-- 创建项目对话框 -->
    <el-dialog v-model="createProjectDialogVisible" title="创建项目">
        <el-form :model="form">
            <el-form-item label="数据库类型:" :label-width="formLabelWidth">
                <el-select v-model="form.databaseType" placeholder="请选择数据库类型">
                    <el-option label="Mysql" value="Mysql" />
                    <el-option label="Oracle" value="Oracle" />
                </el-select>
            </el-form-item>
            <el-form-item label="项目名称:" :label-width="formLabelWidth">
                <el-input v-model="form.projectName" placeholder="请填写项目名称" />
            </el-form-item>
            <el-form-item label="项目根路径:" :label-width="formLabelWidth">
                <el-input v-model="form.location" placeholder="请填项目根路径，如:com.wjtsc.code" />
            </el-form-item>
            <el-form-item label="项目描述:" :label-width="formLabelWidth">
                <el-input v-model="form.description" resize="horizontal" placeholder="请填写项目描述" />
            </el-form-item>
            <el-form-item label="表前缀:" :label-width="formLabelWidth">
                <el-input v-model="form.tablePrefix" resize="horizontal" placeholder="请填写表前缀" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="createProjectDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addNewProject">确定</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script>
// 导入echarts和自定义的axois二次封装请求函数
import * as echarts from 'echarts'
import { homePage_getFiveProjects, projectlist_addProject } from '../network/index'
export default {
    name: "homePage",
    data() {
        return {
            name: localStorage.getItem("ms_username"),
            user_token: localStorage.getItem("user_token"),
            topData: {
                projectTatal: {
                    number: '123',
                    icon: 'el-icon-s-goods grid-con-icon',
                    class: 'grid-content grid-con-1',
                    text: "项目总数"
                },
                tableTatal: {
                    number: '123',
                    icon: 'el-icon-message-solid grid-con-icon',
                    class: 'grid-content grid-con-2',
                    text: "表总数"
                },
                newTableWeekly: {
                    number: '123',
                    icon: 'el-icon-shopping-cart-full grid-con-icon',
                    class: 'grid-content grid-con-3',
                    text: "本周新增表数"
                },
                createCount: {
                    number: '123',
                    icon: 'el-icon-shopping-bag-2 grid-con-icon',
                    class: 'grid-content grid-con-4',
                    text: "生成总次数"
                },
            },
            projectList: [],
            createProjectDialogVisible: false,
            form: {},
            projectTop5Data: {
                xAxisData: [],
                seriesData: []
            }

        };
    },
    created() {

        homePage_getFiveProjects("初始化组件数据").then(res => {
            if (res.code === 200) {
                this.projectList = res.data
                this.projectList.forEach(project => {
                    this.projectTop5Data.xAxisData.push(project.projectName)
                    this.projectTop5Data.seriesData.push(project.tableNumber)
                })

                // 获取到数据后，初始化echarts图表
                this.initProjectTop5EchartsBar()

            }
        })

    },

    methods: {
        // 生成柱状图
        initProjectTop5EchartsBar() {
            let myChart_bar = echarts.init(this.$refs.echartsBar);
            let option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: this.projectTop5Data.xAxisData,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '表数',
                        type: 'bar',
                        barWidth: '60%',
                        data: this.projectTop5Data.seriesData
                    }
                ]
            };

            option && myChart_bar.setOption(option);
        },

        // 生成折线图
        initEchartsLine() {
            let myChart_point = echarts.init(this.$refs.echartsLine);
            let option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [150, 230, 224, 218, 135, 147, 260],
                        type: 'line'
                    }
                ]
            };

            option && myChart_point.setOption(option);

        },

        // 新建项目
        addNewProject() {
            this.createProjectDialogVisible = false;

            let newProjectName = this.form.projectName;

            projectlist_addProject(this.form).then(res => {

                if (res.code === 200) {
                    this.$message.success(`新增项目"${newProjectName}"成功`);
                }
            })
        },

        // 查看项目
        checkTheProject(projectID) {
            this.$router.push({
                path: "/projectDetail",

                //query是个配置项 - location.href带上项目id
                query: { 
                    projectID: projectID
                }
            })
        },


    },
    mounted() {
        this.initEchartsLine()
    }
};
</script>

<style scoped>
.rowBox {
    width: 92vw;
}

.echartsLine {
    width: 100%;
    height: 16.9rem;

    /* background-color: yellow; */
}

.echartsLineBox {
    display: flex;
    justify-content: space-between;
}

.echartsLineInput {
    width: 300px;
}

.echartsBar {
    width: 100%;
    height: 21.5rem;

}

.echartsLine {
    width: 100%;
    height: 21.5rem;

}

.addNew {
    display: flex;
    justify-content: center;
    align-items: center;

    background-color: rgb(255, 255, 255);

    border: none;
}

.projectListContainer {
    display: flex;
    flex-wrap: wrap;
    width: 100vw;

    margin: 20px;
}

.projectListContainer .elcard {
    /* width: 400px;
    height:260px; */
    margin: 10px;
}

.projectContainer {
    display: flex;

    flex-direction: column;
    /* justify-content: center; */
    align-items: center;

    height: 200px
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


.el-row {
    margin-bottom: 20px;
}

.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
}

.grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}

.grid-con-icon {
    font-size: 50px;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    color: #fff;
}

.grid-con-1 .grid-con-icon {
    background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
    background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
    background: rgb(205, 226, 17);
}

.grid-con-3 .grid-num {
    color: rgb(242, 94, 67);
}

.grid-con-4 .grid-con-icon {
    background: rgb(34, 15, 204);
}

.grid-con-4 .grid-num {
    color: rgb(32, 39, 150);
}

.user-info {
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 2px solid #ccc;
    margin-bottom: 20px;
}

.user-avator {
    width: 120px;
    height: 120px;
    border-radius: 50%;
}

.user-info-cont {
    /* padding-left: 40px; */
    flex: 1;
    font-size: 14px;
    color: #999;
}

.user-info-cont div:first-child {
    margin-left: 30px;
    font-size: 30px;
    color: #222;
}

.user-info-cont div:last-child {
    font-size: 20px;
    color: #222;
    margin-left: 30px;
}

.user-info-list {
    font-size: 14px;
    color: #999;
    line-height: 25px;
}

.user-info-list span {
    margin-left: 50px;
}

.mgb20 {
    margin-bottom: 20px;
}

.todo-item {
    font-size: 14px;
}

.todo-item-del {
    text-decoration: line-through;
    color: #999;
}

.schart {
    width: 100%;
    height: 300px;
}
</style>
