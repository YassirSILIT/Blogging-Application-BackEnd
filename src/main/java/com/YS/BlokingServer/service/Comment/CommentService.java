package com.YS.BlokingServer.service.Comment;

import com.YS.BlokingServer.entities.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(Long postId, String postedBy, String content);
    List<Comment> getCommentsByPostId(Long postId);
}
