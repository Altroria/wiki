package com.sunh.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sunh.wiki.domain.Ebook;
import com.sunh.wiki.domain.EbookExample;
import com.sunh.wiki.mapper.EbookMapper;
import com.sunh.wiki.req.EbookQueryReq;
import com.sunh.wiki.req.EbookSaveReq;
import com.sunh.wiki.resp.EbookQueryResp;
import com.sunh.wiki.resp.PageResp;
import com.sunh.wiki.util.CopyUtil;
import com.sunh.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    private  static  final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;
//    查询
    public PageResp<EbookQueryResp> list(EbookQueryReq req){

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);
        LOG.info("总行数 {}",pageInfo.getTotal());
        LOG.info("总页数 {}",pageInfo.getPages());


//        List<EbookResp> respList = new ArrayList<>();
//        for (EbookResp ebook : respList) {
//            EbookResp ebookResp = new EbookResp();
////            ebookResp.setId(ebook.getId());
//            BeanUtils.copyProperties(ebook, ebookResp);
//            respList.add(ebookResp);
//        }
        List<EbookQueryResp> list = CopyUtil.copyList(ebooksList, EbookQueryResp.class);
//        return list;

        PageResp<EbookQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

//    保存
    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else {
            //保存
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }
}
