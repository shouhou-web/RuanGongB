// 封装request模块
import { request } from "./request";

// 获取信息，用于管理
export function getUser(userID) {
    return request({
        url: "/getUser",
        params: {
            userID
        },
        method: 'post'
    })
}

//上传禁言消息
export function upManage(userID, adminID, userState, duration, content) {
    return request({
        url: "/upManage",
        params: {
            userID,
            adminID,
            userState,
            duration,
            content
        },
        method: 'post'
    })
}

//获取禁言列表
export function getManagedUser() {
    return request({
        url: "/getManagedUser",
        params: {},
        method: 'post'
    })
}