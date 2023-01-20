<template>
    <div>
        <el-card shadow="hover" class="Project elcard" style="width: 400px;,height:403px;">
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
                    <div class="titleRight">{{ item.status === "0" ? "进行中" : "未完成" }}</div>
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
                    <el-button type="primary" @click="editTheProject(item)">编辑</el-button>
                    <el-button type="primary" @click="checkTheMember(item.id)">成员</el-button>
                    <el-button type="primary" @click="deleteTheProject(item, index)">删除</el-button>
                </div>
            </div>
        </el-card>
    </div>
</template>
<!-- 父组件传值，子组件控制父组件data，用vue3的inject方便些，时间不多还有其他事，暂时不改了 -->
<script>
    export default {
    name:"projectCard",
    //子组件通过props接收父组件的传值
    props: ["item"],
    data() {
        return {
        
        }
    }
    }
</script>

<style scoped>

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
    justify-content: space-between;

    height: 25%;

    /* background-color: yellow; */
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

</style>