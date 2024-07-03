package com.YS.BlokingServer.service.Post;

import com.YS.BlokingServer.Repository.PostRepository;
import com.YS.BlokingServer.entities.Post;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    // declared a repository and injection
    private PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // implement the save methode
    @Override
    public Post savePost(Post post) {
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setDate(new Date());
        return postRepository.save(post);
    }

    @Override
    public List<Post> listOfPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> postById = postRepository.findById(id);
        if (postById.isPresent()) {
            Post post = postById.get();
            post.setViewCount(post.getViewCount() + 1);
            return postRepository.save(post);
        }else {
            throw new EntityNotFoundException("Post not found");
        }
    }
    @Override
    public void likePost(Long postId){
        Optional<Post> postById = postRepository.findById(postId);
        if (postById.isPresent()) {
            Post post = postById.get();
            post.setLikeCount(post.getLikeCount() + 1);
             postRepository.save(post);
        }else {
            throw new EntityNotFoundException("Post not found with id: ");
        }
    }

    @Override
    public List<Post> searchByName(String name) {
        return postRepository.findAllByNameContaining(name);
    }
}
