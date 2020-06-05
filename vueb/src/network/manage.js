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
export function upManage(userID, userState, duration, content) {
    return request({
        url: "/upManage",
        params: {
            userID, 
            userState, 
            duration, 
            content
        },
        method: 'post'
    })
}