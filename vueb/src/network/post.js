// 封装request模块
import { request } from "./request";

export function upimg(imgdata) {
    return request({
        url: "/upimg",
        params: {
            imgdata
        },
        method: 'post'
    })
}