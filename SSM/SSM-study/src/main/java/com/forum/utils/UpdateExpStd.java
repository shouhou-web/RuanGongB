/*
* 提升经验枚举类
* 根据每种行为确定提升经验的数值
*/

package com.forum.utils;

public enum UpdateExpStd {
    pushPost(10),
    pushComment(3),
    CommentByOthers(4),
    likePost(1),
    likedByOthers(2),
    stick(30),
    banned(-10)
    ;

    int num;
    UpdateExpStd(int num){
        this.num=num;
    }
    public int getValue(){
        return this.num;
    }
}
