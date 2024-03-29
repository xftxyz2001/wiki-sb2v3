package com.java.wiki.controller;

import com.java.wiki.req.EbookQueryReq;
import com.java.wiki.req.EbookSaveReq;
import com.java.wiki.resp.CommonResp;
import com.java.wiki.resp.EbookQueryResp;
import com.java.wiki.resp.PageResp;
import com.java.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp<PageResp<EbookQueryResp>> list(@Valid EbookQueryReq req) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> pageResp = ebookService.list(req);
        resp.setContent(pageResp);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp<?> save(@Valid @RequestBody EbookSaveReq req) {
        CommonResp<?> resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<?> delete(@PathVariable Long id) {
        CommonResp<?> resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }


}
