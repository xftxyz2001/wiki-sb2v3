package com.java.wiki.controller;

import com.java.wiki.req.DocQueryReq;
import com.java.wiki.req.DocSaveReq;
import com.java.wiki.resp.CommonResp;
import com.java.wiki.resp.DocQueryResp;
import com.java.wiki.resp.PageResp;
import com.java.wiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;


    @GetMapping("/all/{ebookId}")
    public CommonResp<List<DocQueryResp>> all(@PathVariable Long ebookId) {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all(ebookId);
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    public CommonResp<PageResp<DocQueryResp>> list(@Valid DocQueryReq req) {
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();
        PageResp<DocQueryResp> pageResp = docService.list(req);
        resp.setContent(pageResp);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp<?> save(@Valid @RequestBody DocSaveReq req) {
        CommonResp<?> resp = new CommonResp<>();
        docService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{idsStr}")
    public CommonResp<?> delete(@PathVariable String idsStr) {
        CommonResp<?> resp = new CommonResp<>();
        List<String> list = Arrays.asList(idsStr.split(","));
        docService.delete(list);
        return resp;
    }

    @GetMapping("/find-content/{id}")
    public CommonResp<String> findContent(@PathVariable Long id) {
        CommonResp<String> resp = new CommonResp<>();
        String content = docService.findContent(id);
        resp.setContent(content);
        return resp;
    }

    @GetMapping("/vote/{id}")
    public CommonResp<String> vote(@PathVariable Long id) {
        CommonResp<String> resp = new CommonResp<>();
        docService.vote(id);
        return resp;
    }


}
