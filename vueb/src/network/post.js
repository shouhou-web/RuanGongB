// 封装request模块
import { request } from "./request";
import qs from 'qs'

//上传图片到数据库
export function upimg(imgdata) {
    console.log(qs.stringify(imgdata))
    return request({
        url: "/upimg",
        // params: {
        //     imgdata : qs.stringify(imgdata)
        // },
        params: {
            imgdata
        },
        method: 'post',
        
    })
}

// 上传帖子
export function uploadPost(userID, title, postIdentity, content) {
    return request({
        url: "/uploadPost",
        params: {
            userID, title, postIdentity, content
        },
        method: 'post'
    })
}

// 获取特定种类的帖子
export function getPostList(postIdentity) {
    return request({
        url: "/getPostList",
        params: {
            postIdentity
        },
        method: 'post'
    })
}

// 获取帖子数据
export function downPost(postID) {
    return request({
        url: "/downPost",
        params: {
            postID
        },
        method: 'post'
    })
}

// 点赞帖子
export function likePost(userID,postID,likeState) {
    return request({
        url: "/likePost",
        params: {
            userID,
            postID,
            likeState
        },
        method: 'post'
    })
}

// 评论帖子
export function pushComment(postID,userID,content) {
    return request({
        url: "/pushComment",
        params: {
            userID,
            postID,
            content
        },
        method: 'post'
    })
}