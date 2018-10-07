package com.agree.listener;

import com.agree.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageQueueListener implements MessageListener {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void onMessage(Message message) {
        //
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            logMapper.log(text);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

