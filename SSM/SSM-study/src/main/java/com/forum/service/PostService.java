/*
 * Service层接口
 * 关联PostMapper
 */

package com.forum.service;

import com.forum.pojo.Post;

import java.util.List;

public interface PostService {

    List<Post> getPostByPostIdentity(int PostIdentity);

    List<Post> getPostByUserID(int UserID);

    Post getPostByPostID(int PostID);

    int addPost(Post post);

    int updatePost(Post post);

    int updateStickState(int PostID, int StickState);

    int deletePost(int PostID);

    List<Post> getPostByLikes(int UserID);

    List<Post> getPostByBrowses(int UserID);

    List<Post> getPostByKeyword(String keyword);

    int getMaxPostID();

    int[] getListLikesByPostID(int PostID);

    int getBrowseNumByPostID(int PostID);

    int likePost(int PostID,int UserID);

    int cancelLikePost(int PostID,int UserID);

    int browsePost(int PostID,int UserID);
}
