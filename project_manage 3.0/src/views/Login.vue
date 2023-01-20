<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">用户登录</div>
            <el-form :model="param" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="username">
                        <template #prepend>
                            <el-button icon="el-icon-user"></el-button>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="param.password"
                        @keyup.enter="submitForm()">
                        <template #prepend>
                            <el-button icon="el-icon-lock"></el-button>
                        </template>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>

import { login } from '../network/index'
import { setToken } from '@/utils/auth'

export default {
    data() {
        return {
            param: {
                username: "",
                password: ""
            }
        };
    },
    created() {
        this.$store.commit("clearTags");
    },
    methods: {
        submitForm() {
            // // 仅做免登陆用
            // this.$router.push("/Homepage");

            let query = {
                userName: this.param.username,
                password: this.param.password
            }

            login(query).then(res => {

                if (res.code === 200 && res.data) {
                    this.$message.success("登录成功");
                    localStorage.setItem("ms_username", this.param.username);
                    setToken(res.data.token);
                    this.$router.push("/Homepage");
                    this.$store.commit('initIdentity', '系统管理员')
                } else {
                    this.$message.error("账号或密码错误");
                    return false;
                }
            })
        }
    }
};
</script>

<style scoped>

.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../assets/img/login-bg.jpg);
    background-size: 100%;
    z-index: 2;

}

.ms-title {

    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: rgb(72, 129, 61);
    border-bottom: 1px solid #ddd;

}

.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}

.ms-content {
    padding: 40px 30px;
}

.login-btn {
    text-align: center;
}

.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}

.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #fff;
}
</style>
