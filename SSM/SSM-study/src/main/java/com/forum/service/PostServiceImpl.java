/*
 * PostService接口实现类
 */

package com.forum.service;

import com.forum.mapper.PostMapper;
import com.forum.pojo.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper mapper;

    public void setMapper(PostMapper mapper) {
        this.mapper = mapper;
    }

    public List<Post> getPostByPostIdentity(int PostIdentity) {
        return mapper.getPostByPostIdentity(PostIdentity);
    }

    public List<Post> getPostByUserID(int UserID) {
        return mapper.getPostByUserID(UserID);
    }

    public Post getPostByPostID(int PostID) {
        return mapper.getPostByPostID(PostID);
    }

    public int addPost(Post post) {
        return mapper.addPost(post);
    }

    public int updatePost(Post post) {
        return mapper.updatePost(post);
    }

    public int updateStickState(int PostID, int StickState) {
        return mapper.updateStickState(PostID,StickState);
    }

    public int deletePost(int PostID) {
        return mapper.deletePost(PostID);
    }

    public List<Post> getPostByLikes(int UserID) {
        return mapper.getPostByLikes(UserID);
    }

    public List<Post> getPostByBrowses(int UserID) {
        return mapper.getPostByBrowses(UserID);
    }

    public List<Post> getPostByKeyword(String keyword){
        keyword="%"+keyword+"%";
        return mapper.getPostByKeyword(keyword);
    }

    public int getMaxPostID() {
        int maxID;
        try{
            maxID=mapper.getMaxPostID();
            return maxID;
        }
        catch(Exception e){
            return 10000;
        }
    }

    public int[] getListLikesByPostID(int PostID) {
        return mapper.getListLikesByPostID(PostID);
    }

    public int getBrowseNumByPostID(int PostID) {
        return mapper.getBrowseNumByPostID(PostID);
    }

    public int likePost(int PostID, int UserID) {
        return mapper.likePost(PostID,UserID);
    }

    public int cancelLikePost(int PostID,int UserID){
        return mapper.cancelLikePost(PostID,UserID);
    }

    public int browsePost(int PostID, int UserID) {
        return mapper.browsePost(PostID,UserID);
    }

}
