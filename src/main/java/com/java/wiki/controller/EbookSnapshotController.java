package com.java.wiki.controller;

import com.java.wiki.resp.CommonResp;
import com.java.wiki.resp.StatisticResp;
import com.java.wiki.service.EbookSnapshotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

    @Resource
    private EbookSnapshotService service;


    public CommonResp getStatistic() {
        List<StatisticResp> statisticResps = service.getStatistic();
        CommonResp<List<StatisticResp>> commonResp = new CommonResp<>();
        commonResp.setContent(statisticResps);
        return commonResp;
    }

}
