package com.YS.BlokingServer.service.Comment;

import com.YS.BlokingServer.Repository.CommentRepository;
import com.YS.BlokingServer.Repository.PostRepository;
import com.YS.BlokingServer.entities.Comment;
import com.YS.BlokingServer.entities.Post;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment addComment(Long postId, String postedBy, String content) {
        Optional<Post> postedById = postRepository.findById(postId);
        if (postedById.isPresent()) {
            Comment comment = new Comment();

            comment.setPost(postedById.get());
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());
            comment.setContent(content);

            return commentRepository.save(comment);
        }else {
             throw new EntityNotFoundException("Post Not Found");
        }

    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
