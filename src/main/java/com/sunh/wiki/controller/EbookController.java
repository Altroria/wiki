package com.sunh.wiki.controller;

import com.sunh.wiki.req.EbookReq;
import com.sunh.wiki.resp.CommonResp;
import com.sunh.wiki.resp.EbookResp;
import com.sunh.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req){
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list =   ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
