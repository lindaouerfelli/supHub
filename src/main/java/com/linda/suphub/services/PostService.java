package com.linda.suphub.services;

import com.linda.suphub.dto.PostDto;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;

import java.util.List;

public interface PostService extends AbstractService<PostDto> {

    List<PostDto> findAllByItemCategory (PostCategory category);

    List<PostDto> findAllByStatus (PostStatus status);

    List<PostDto> findAllByUser (User user);


}
