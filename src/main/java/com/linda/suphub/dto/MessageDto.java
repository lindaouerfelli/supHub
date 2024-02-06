package com.linda.suphub.dto;

import com.linda.suphub.models.Message;
import com.linda.suphub.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@Builder
public class MessageDto {

    private Integer id;
    private String content;
    private LocalDateTime sentAt;

    private Integer senderId;
    private Integer receiverId;

    public static MessageDto fromEntity(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .content(message.getContent())
                .sentAt(message.getSentAt())
                .senderId(message.getSender().getId())
                .receiverId(message.getReceiver().getId())
                .build();
    }

    public static Message toEntity(MessageDto messageDto) {
        return Message.builder()
                .id(messageDto.getId())
                .content(messageDto.getContent())
                .sentAt(messageDto.getSentAt())
                .sender(User.builder().id(messageDto.getSenderId()).build())
                .receiver(User.builder().id(messageDto.getReceiverId()).build())
                .build();
    }

}
