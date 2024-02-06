package com.linda.suphub.services.impl;

import com.linda.suphub.dto.PostDto;
import com.linda.suphub.dto.UserDto;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.PostCategory;
import com.linda.suphub.models.PostStatus;
import com.linda.suphub.models.User;
import com.linda.suphub.repositories.PostRepository;
import com.linda.suphub.repositories.UserRepository;
import com.linda.suphub.services.PostService;
import com.linda.suphub.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final ObjectsValidator<PostDto> validator;
    @Override
    public Integer save(PostDto dto)
    {

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
