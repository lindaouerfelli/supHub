package com.linda.suphub.dto;

import com.linda.suphub.models.Post;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PostDto {

    private Integer id;
    private String itemName;
    private String itemDescription;
    private PostCategory itemCategory;
    private PostStatus status;
    private String ownerFullName;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postDate;
    //private MultipartFile image;
    //private byte[] image;

    private Integer userId;

    public static PostDto fromEntity(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .itemName(post.getItemName())
                .itemDescription(post.getItemDescription())
                .itemCategory(post.getItemCategory())
                .status(post.getStatus())
                .postDate(post.getPostDate())
                .ownerFullName(post.getOwnerFullName())
                .userId(post.getUser().getId())
                .build();
    }

    public static Post toEntity(PostDto post) {
        return Post.builder()
                .id(post.getId())
                .itemName(post.getItemName())
                .itemDescription(post.getItemDescription())
                .itemCategory(post.getItemCategory())
                .status(post.getStatus())
                .postDate(LocalDate.now())
                .ownerFullName(post.getOwnerFullName())
                //.image(post.getImage())
                .user(User.builder().id(post.getUserId()).build()).build();
    }





}
