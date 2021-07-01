package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import com.udacity.jwdnd.c1.review.model.User;
import org.apache.ibatis.annotations.*;
import java.util.*;

@Mapper
public interface ChatMessageMapper {

    @Select("SELECT * FROM MESSAGES WHERE messageid= #{id}")
    ChatMessage findMessage(int id);
    @Select("SELECT * FROM MESSAGES")
    ArrayList<ChatMessage> getMessages();
    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES (#{userName}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(ChatMessage chatMessage);
    @Delete("DELETE FROM MESSAGES WHERE messageid = #{id}")
    void delete(int id);
}
