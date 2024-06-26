package com.YS.BlokingServer.controller;

import com.YS.BlokingServer.entities.Post;
import com.YS.BlokingServer.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public Post savePost(@RequestBody Post post){
        return postService.savePost(post);
    }
    @GetMapping
    public List<Post> listOfPosts(){
        return postService.listOfPosts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
           Post post = postService.getPostById(id);
            return ResponseEntity.ok(post);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId){
        try {
            postService.likePost(postId);
            return ResponseEntity.ok(new String[]{"Post liked successfully."});
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(postService.searchByName(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
   /*@GetMapping("/search")
    public List<Post> searchByName(@RequestParam String name){
        return postService.searchByName(name);
    }*/
}
