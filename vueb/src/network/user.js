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

export function getMyPost(userID) {
    return request({
        url: "/getMyPost",
        params: {
            userID
        },
        method: 'post'
    })
}

export function getLikedPost(userID) {
    return request({
        url: "/getLikedPost",
        params: {
            userID
        },
        method: 'post'
    })
}

export function getComment(userID) {
    return request({
        url: "/getComment",
        params: {
            userID
        },
        method: 'post'
    })
}

export function getBrowsePost(userID) {
    return request({
        url: "/getBrowsePost",
        params: {
            userID
        },
        method: 'post'
    })
}

// 修改个人信息
export function editUser(userID, imagePath, userName, userWord, sex) {
    return request({
        url: "/editUser",
        params: {
            userID, 
            imagePath,
            userName, 
            userWord, 
            sex
        },
        method: 'post'
    })
}
