package com.agree.service.impl;

import com.agree.mapper.LogMapper;
import com.agree.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogMapper logMapper;

    @Override
    public void log(String message) {
        logMapper.log(message);
    }
}
