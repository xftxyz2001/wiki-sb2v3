package com.java.wiki.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.wiki.entity.Content;
import com.java.wiki.entity.Doc;
import com.java.wiki.entity.DocExample;
import com.java.wiki.mapper.ContentMapper;
import com.java.wiki.mapper.DocMapper;
import com.java.wiki.mapper.DocMapperCust;
import com.java.wiki.req.DocQueryReq;
import com.java.wiki.req.DocSaveReq;
import com.java.wiki.resp.DocQueryResp;
import com.java.wiki.resp.PageResp;
import com.java.wiki.util.CopyUtil;
import com.java.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {

    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private DocMapper docMapper;

    @Resource
    private DocMapperCust docMapperCust;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;


    public List<DocQueryResp> all(Long ebookId) {

        DocExample docExample = new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
        docExample.setOrderByClause("sort asc");

        List<Doc> docList = docMapper.selectByExample(docExample);

        return CopyUtil.copyList(docList, DocQueryResp.class);

    }

    public PageResp<DocQueryResp> list(DocQueryReq req) {

        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        PageHelper.startPage(req.getNum(), req.getSize());
        List<Doc> docList = docMapper.selectByExample(docExample);
        PageInfo<Doc> pageInfo = new PageInfo<>(docList);

        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<DocQueryResp> pageResp = new PageResp<>();

        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;

    }

    //保存
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        Content content = CopyUtil.copy(req, Content.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            //新增文档信息
            doc.setId(snowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            docMapper.insert(doc);
            content.setId(doc.getId());
            contentMapper.insert(content);
        } else {
            //保存文档信息
            docMapper.updateByPrimaryKey(doc);
            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            //原本没有内容则变成新增内容
            if (count == 0) {
                contentMapper.insert(content);
            }
        }
    }

    public void delete(Long id) {

        docMapper.deleteByPrimaryKey(id);
    }

    public void delete(List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        //where id in()
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }

    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        //文档阅读数+1
        docMapperCust.increaseViewCount(id);
        if (content != null) {
            return content.getContent();
        }
        return null;
    }


}
