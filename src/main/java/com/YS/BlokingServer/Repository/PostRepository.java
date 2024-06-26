package com.YS.BlokingServer.Repository;

import com.YS.BlokingServer.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findAllByNameContaining(String name);
}
