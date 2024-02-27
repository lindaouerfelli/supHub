package com.linda.suphub.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends AbstractEntity{

    private String itemName;
    private String ownerFullName;

    private String itemDescription;

    //@Column(columnDefinition = "bytea")
    //private byte[] image;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Enumerated(EnumType.STRING)
    private PostCategory itemCategory;

    private LocalDate postDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Reservation reservation;


}
