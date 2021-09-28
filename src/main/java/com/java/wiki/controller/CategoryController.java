package com.java.wiki.controller;

import com.java.wiki.req.CategoryQueryReq;
import com.java.wiki.req.CategorySaveReq;
import com.java.wiki.resp.CommonResp;
import com.java.wiki.resp.CategoryQueryResp;
import com.java.wiki.resp.PageResp;
import com.java.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("/list")
    public CommonResp<PageResp<CategoryQueryResp>> list(@Valid CategoryQueryReq req) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> pageResp = categoryService.list(req);
        resp.setContent(pageResp);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp<?> save(@Valid @RequestBody CategorySaveReq req) {
        CommonResp<?> resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<?> delete(@PathVariable Long id) {
        CommonResp<?> resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }


}
