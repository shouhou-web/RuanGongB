/*
 * 关联数据库表Post，Likes，Browses
 * 其中Likes表与Browse表与Post类紧密关联，且操作较少，故合并至该类
 * 数据交互类
 */

package com.forum.mapper;

import com.forum.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    /*
    根据帖子类型，返回该类型所有帖子的所有信息
    返回一个Post列表
     */
    List<Post> getPostByPostIdentity(@Param("PostIdentity") int PostIdentity);

    /*
    根据userID，返回该用户发表的所有帖子的所有信息
    返回一个Post列表
     */
    List<Post> getPostByUserID(@Param("UserID") int UserID);

    /*
    根据帖子ID，返回帖子所有信息
    返回一个Post对象
     */
    Post getPostByPostID(@Param("PostID") int PostID);

    /*
    添加帖子,发布帖子需要
    返回结果为插入条数，结果为0时插入失败
    默认设定StickState值为0，设定Time, EditTime值为GETDATE()，即当前时间
     */
    int addPost(Post post);

    /*
    修改帖子，编辑帖子需要
    返回结果为更新行数，结果为0时更新失败
    只允许修改Title, Content，并将EditTime置为GETDATE()
     */
    int updatePost(Post post);

    /*
    * 更改帖子置顶状态
    * 传入新的置顶状态
    * 返回更新行数
    */
    int updateStickState(@Param("PostID") int PostID, @Param("StickState") int StickState);

    /*
    删除帖子
    返回删除行数，结果为0时删除失败
     */
    int deletePost(@Param("PostID") int PostID);

    /*
    根据用户ID查找我点赞过的帖子，返回帖子所有信息
    返回一个Post列表
     */
    List<Post> getPostByLikes(@Param("UserID") int UserID);

    /*
    根据用户ID查找我浏览过的帖子
    返回一个Post列表
     */
    List<Post> getPostByBrowses(@Param("UserID") int UserID);

    /*
    * 根据关键字查找帖子
    * 返回Post列表
    */
    List<Post> getPostByKeyword(@Param("keyword") String keyword);

    /*
    查找当前最大的帖子ID，用于设置新帖子ID
    返回int最大PostID
     */
    int getMaxPostID();

    /*
    * 查找点赞过该帖子的用户
    * 返回int数组，UserID列表
    */
    int[] getListLikesByPostID(int PostID);

    /*
    * 查找浏览过该帖子的用户
    * 返回int，浏览数
    */
    int getBrowseNumByPostID(int PostID);

    /*
    * 添加一条点赞记录
    * 返回插入条数（应当为1）
    */
    int likePost(@Param("PostID") int PostID,@Param("UserID") int UserID);

    /*
    * 删除点赞记录
    * 返回删除条数（1代表点赞过，0代表未点赞过）*/
    int cancelLikePost(@Param("PostID") int PostID,@Param("UserID") int UserID);

    /*
    * 添加一条浏览记录
    * 返回插入条数（应当为1）
    */
    int browsePost(@Param("PostID") int PostID,@Param("UserID") int UserID);
}
