package com.YS.BlokingServer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String content;
    private String postedBy;
    private String image;
    private Date date;
    private int likeCount;
    private int viewCount;
    private List<String> tags;
}
