package com.YS.BlokingServer.controller;


import com.YS.BlokingServer.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> addComment(@RequestParam Long postId,@RequestParam String postedBy,@RequestBody String content){
        try {
            return ResponseEntity.ok(commentService.addComment(postId,postedBy,content));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Long postId){
        try{
            return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something Went Wrong");
        }
    }
}
