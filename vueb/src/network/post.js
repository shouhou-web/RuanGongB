// 封装request模块
import { request } from "./request";
import qs from 'qs'

//下载文件
export function downfile(fileName) {
    return request({
        url: "/downfile",
        responseType: 'blob',
        params: {
            fileName
        },
        method: 'post',

    })
}

// 上传帖子
export function uploadPost(userID, postID, title, postLevel, postIdentity, content, fileData) {
    return request({
        url: "/uploadPost",
        params: {
            userID, postID, title, postLevel, postIdentity, content, fileData
        },
        method: 'post',
        headers: {
            'Content-Type': 'multipart/form-data;charse=UTF-8'
        }
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
export function likePost(userID, postID, likeState) {
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
export function pushComment(userID, postID, content) {
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

//置顶帖子
//stickstate 1：置顶该帖子，0：取消置顶
export function stickPost(postID, adminID, stickstate) {
    return request({
        url: "/stickPost",
        params: {
            postID, adminID, stickstate
        },
        method: 'post'
    })
}

//删除帖子
export function deletePost(postID, userID) {
    return request({
        url: "/deletePost",
        params: {
            postID, userID
        },
        method: 'post'
    })
}

//搜索帖子
export function searchPost(keyword) {
    return request({
        url: "/searchPost",
        params: {
            keyword
        },
        method: 'post'
    })
}