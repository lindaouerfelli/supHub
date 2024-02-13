package com.linda.suphub.services;

import com.linda.suphub.dto.MessageDto;
import com.linda.suphub.dto.PostDto;
import com.linda.suphub.models.Message;

import java.util.List;

public interface MessageService extends AbstractService<MessageDto>{


    List<MessageDto> findAllByUserId(Integer userId);


}
