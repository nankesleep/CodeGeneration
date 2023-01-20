<template>
    <div class="sidebar">
        <el-menu
            class="sidebar-el-menu"
            :default-active="onRoutes"
            :collapse="collapse"
            background-color="#324157"
            text-color="#bfcbd9"
            active-text-color="#20a0ff"
            unique-opened
            router
            @click="handleClick"
        >
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template #title>
                            <i :class="item.icon"></i>
                            <span>{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.subs">
                            <el-submenu
                                v-if="subItem.subs"
                                :index="subItem.index"
                                :key="subItem.index"
                            >
                                <template #title>{{ subItem.title }}</template>
                                <el-menu-item v-for="(threeItem, i) in subItem.subs" :key="i" :index="threeItem.index">{{ threeItem.title }}</el-menu-item>
                            </el-submenu>
                                <el-menu-item v-else :index="subItem.index" :key="subItem.index + 1">{{ subItem.title }}</el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i>
                        <template #title>{{ item.title }}</template>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                items: [
                    {
                        icon: "el-icon-s-management",
                        index: "Homepage",
                        title: "系统首页"
                    },
                   
                   {
                       icon: "el-icon-s-data",
                       index: "1",
                       title: "项目管理",
                       subs: [
                           
                           {
                               index: "projectList",
                               title: "项目列表",
                           },
                           {
                               index: "",
                               title: "生成记录管理"
                           },
                           {
                               index: "",
                               title: "数据恢复"
                           },
                       ]
                   },
                    {
                        icon: "el-icon-view",
                        index: "2",
                        title: "配置管理",
                        subs: [
                            {
                                index: "",
                                title: "字典数据管理"
                            },
                            {
                                index: "",
                                title: "默认字段管理"
                            }
                        ]
                    },
                    // {
                    //     icon: "el-icon-s-management",
                    //     index: "chengdufresh",
                    //     title: "生鲜超市",
                    // },  
                    {
                        icon: "el-icon-mobile-phone",
                        index: "6",
                        title: "运维管理",
                        subs: [
                            
                            {
                                index: "",
                                title: "用户管理",
                            },
                            {
                                index: "",
                                title: "日志管理"
                            }
                        ]
                    },
            
                ],
                sidebar: {}
            };
        },
        mounted() {
            this.sidebar = this.$store.state.sidebar;
        },
        computed: {
            onRoutes() {
                return this.$route.path.replace("/", "");
            },
            collapse() {
                return this.$store.state.collapse
            }
        },
        methods: {
            handleClick(el) {
                console.log(el.target.innerText)
            }
        }
    };
</script>

<style scoped>
    .sidebar {
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom: 0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar {
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse) {
        width: 250px;
    }
    .sidebar > ul {
        height: 100%;
    }
</style>