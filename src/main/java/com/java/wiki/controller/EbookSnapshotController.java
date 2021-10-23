package com.java.wiki.controller;

import com.java.wiki.resp.CommonResp;
import com.java.wiki.resp.StatisticResp;
import com.java.wiki.service.EbookSnapshotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

    @Resource
    private EbookSnapshotService service;


    @GetMapping("/get-statistic")
    public CommonResp<List<StatisticResp>> getStatistic() {
        List<StatisticResp> statisticResp = service.getStatistic();
        CommonResp<List<StatisticResp>> commonResp = new CommonResp<>();
        commonResp.setContent(statisticResp);
        return commonResp;
    }

    @GetMapping("/get-30-statistic")
    public CommonResp<List<StatisticResp>> get30Statistic() {
        List<StatisticResp> statisticResp = service.get30Statistic();
        CommonResp<List<StatisticResp>> commonResp = new CommonResp<>();
        commonResp.setContent(statisticResp);
        return commonResp;
    }
}
