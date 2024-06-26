package com.YS.BlokingServer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String postedBy;
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false )
    private Post post;

}
