package com.linda.suphub.services;

import com.linda.suphub.dto.AuthenticationRequest;
import com.linda.suphub.dto.AuthenticationResponse;
import com.linda.suphub.dto.UserDto;
import javax.persistence.criteria.CriteriaBuilder;

public interface UserService extends AbstractService<UserDto> {


    //valider un compte utilisateur
    Integer validateAccount(Integer id);

    // un userpeut invalider ou desactiver un compte user suite a des actiosn anormals
    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    UserDto updateUserProfile(Integer userId, UserDto updatedUserDto);

}
