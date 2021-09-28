package com.java.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.wiki.entity.Ebook;
import com.java.wiki.entity.EbookExample;
import com.java.wiki.mapper.EbookMapper;
import com.java.wiki.req.EbookQueryReq;
import com.java.wiki.req.EbookSaveReq;
import com.java.wiki.resp.EbookQueryResp;
import com.java.wiki.resp.PageResp;
import com.java.wiki.util.CopyUtil;
import com.java.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;


    public PageResp<EbookQueryResp> list(EbookQueryReq req) {

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!StringUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }

        PageHelper.startPage(req.getNum(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);

        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<EbookQueryResp> pageResp = new PageResp<>();

        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

    }

    //保存
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            //新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);

        } else {
            ebookMapper.updateByPrimaryKey(ebook);
        }


    }


}
