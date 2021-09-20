package com.java.wiki.service;


import com.java.wiki.entity.Test;
import com.java.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;


    public List<Test> list() {
        return testMapper.list();
    }


}
