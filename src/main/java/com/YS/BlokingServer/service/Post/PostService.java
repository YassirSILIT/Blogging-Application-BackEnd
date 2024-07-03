package com.YS.BlokingServer.service.Post;

import com.YS.BlokingServer.entities.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    List<Post> listOfPosts();
    Post getPostById(Long id);
    void likePost(Long postId);
    List<Post> searchByName(String name);
}
