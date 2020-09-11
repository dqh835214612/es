package com.deng.es.controller;

import com.deng.es.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/parse/{keywords}")
    public Boolean parse (@PathVariable("keywords") String keywords) throws Exception {
        return contentService.parseContent(keywords);
    }

    @RequestMapping(value = "/page/{keyword}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    public List<Map<String,Object>> searchPage (@PathVariable("keyword")String keyword,
                                                @PathVariable("pageNo")int pageNo,
                                                @PathVariable("pageSize")int pageSize) throws IOException {
        List<Map<String, Object>> list = contentService.searchPage(keyword, pageNo, pageSize);
        return list;

    }

}
