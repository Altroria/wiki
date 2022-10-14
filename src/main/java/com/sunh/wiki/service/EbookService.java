package com.sunh.wiki.service;

import com.sunh.wiki.domain.Ebook;
import com.sunh.wiki.domain.EbookExample;
import com.sunh.wiki.mapper.EbookMapper;
import com.sunh.wiki.req.EbookReq;
import com.sunh.wiki.resp.EbookResp;
import com.sunh.wiki.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }

        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
//        List<EbookResp> respList = new ArrayList<>();
//        for (EbookResp ebook : respList) {
//            EbookResp ebookResp = new EbookResp();
////            ebookResp.setId(ebook.getId());
//            BeanUtils.copyProperties(ebook, ebookResp);
//            respList.add(ebookResp);
//        }
        List<EbookResp> list = CopyUtil.copyList(ebooksList, EbookResp.class);
        return list;
    }
}
