package com.linda.suphub.repositories;

import com.linda.suphub.models.Message;
import com.linda.suphub.models.Post;
import com.linda.suphub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllBySender_IdOrReceiver_Id(Integer senderId, Integer receiverId);
}
