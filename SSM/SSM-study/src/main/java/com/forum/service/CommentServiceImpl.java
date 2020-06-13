package com.forum.service;

import com.forum.mapper.CommentMapper;
import com.forum.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
/*
 * CommentService接口实现类
 */

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper mapper;

    public void setMapper(CommentMapper mapper) {
        this.mapper = mapper;
    }

    public Comment getCommentByCommentID(int CommentID){
        return mapper.getCommentByCommentID(CommentID);
    }

    public List<Comment> getCommentByPostID(int PostID) {
        return mapper.getCommentByPostID(PostID);
    }

    public List<Comment> getCommentByUserID(int UserID) {
        return mapper.getCommentByUserID(UserID);
    }

    public int addComment(Comment comment) {
        return mapper.addComment(comment);
    }

    public int deleteComment(int CommentID) {
        return mapper.deleteComment(CommentID);
    }

    public int getMaxCommentID(){
        int maxID;
        try{
            maxID=mapper.getMaxCommentID();
            return maxID;
        }
        catch(Exception e){
            return 10000;
        }
    }
}
