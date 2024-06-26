package com.YS.BlokingServer.Repository;

import com.YS.BlokingServer.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByPostId(Long postId);
}
