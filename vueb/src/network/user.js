// 封装request模块
import { request } from "./request";

export function login(username, password) {
    return request({
        url: "/login",
        params: {
            username,
            password
        },
        method: 'post'
    })
}

export function register(username, password, password2) {
    return request({
        url: "/register",
        params: {
            username,
            password,
            password2
        },
        method: 'post'
    })
}