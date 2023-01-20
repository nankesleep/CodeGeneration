import {createRouter, createWebHistory} from "vue-router";
import Home from "../views/Home.vue";

const routes = [
    {
        path: '/',
        redirect: '/login'
    }, {
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {
                path: "/Homepage",
                name: "Homepage",
                meta: {
                    title: '系统首页'
                },
                component: () => import (
                "../views/Homepage.vue")
            },
            {
                path: "/projectList",
                name: "projectList",
                meta: {
                    title: '项目列表'
                },
                component: () => import (
                "../views/projectList.vue")
            },{
                path: "/projectDetail",
                name: "projectDetail",
                meta: {
                    title: '项目细节'
                },
                component: () => import (
                "../views/projectDetail.vue")
            },{
                path: "/tableCheck",
                name: "tableCheck",
                meta: {
                    title: '表信息'
                },
                component: () => import (
                "../views/tableCheck.vue")
            },
           
        ]
    }, {
        path: "/login",
        name: "Login",
        meta: {
            title: '登录'
        },
        component: () => import (
        "../views/Login.vue")
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

router.beforeEach((to,from,next)=>{
    if(to.matched.some((route)=>route.meta.Auth)){
        if(localStorage.getItem('token')){
            next()
        }else{
             next({
                path:'/login',
                query:{
                    returnURL:to.path
                }
            })
        }
       
    }else{
         next()
    }
   
})



export default router;