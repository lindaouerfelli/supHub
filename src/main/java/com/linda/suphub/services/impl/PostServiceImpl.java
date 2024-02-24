package com.linda.suphub.services.impl;

import com.linda.suphub.dto.PostDto;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;
import com.linda.suphub.repositories.PostRepository;
import com.linda.suphub.services.PostService;
import com.linda.suphub.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final ObjectsValidator<PostDto> validator;


    @Override
    public Integer save(PostDto dto) {
        // Enregistrer le post
        validator.validate(dto);
        Post post = PostDto.toEntity(dto);
        return repository.save(post).getId();
    }

    @Override
    @Transactional
    public List<PostDto> findAll() {
        return repository.findAll()
                .stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public PostDto findById(Integer id) {
        return repository.findById(id)
                .map(PostDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No post was found with the ID: " + id));
    }
    @Override
    public void delete(Integer id)
    {
        repository.deleteById(id);
    }

    @Override
    public void updatePostStatus(Integer postId) {
        repository.updateStatusToReserved(postId);

    }

    @Override
    public List<PostDto> findAllByItemCategory(PostCategory category) {
        return repository.findAllByItemCategory(category)
                .stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public List<PostDto> findAllByStatus(PostStatus status)
    {
        return repository.findAllByStatus(status)
                .stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public List<PostDto> findAllByUser(User user) {
        return repository.findAllByUser(user)
                .stream()
                .map(PostDto::fromEntity)
                .collect(Collectors.toList());
    }

}
