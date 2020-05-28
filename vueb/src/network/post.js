// 封装request模块
import { request } from "./request";
import qs from 'qs'
export function upimg(imgdata) {
    console.log(qs.stringify(imgdata))
    return request({
        url: "/upimg",
        params: {
            imgdata : qs.stringify(imgdata)
        },
        // data: imgdata,
        method: 'post',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
}

export function uploadPost(userID, title, postIdentity, content) {
    return request({
        url: "/uploadPost",
        params: {
            userID, title, postIdentity, content
        },
        method: 'post'
    })
}

export function downPost(postID) {
    return request({
        url: "/downPost",
        params: {
            postID
        },
        method: 'post'
    })
}