package com.agree.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageTopicListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        // 消息内容
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println(text);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

