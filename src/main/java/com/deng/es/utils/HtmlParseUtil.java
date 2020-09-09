package com.deng.es.utils;

import com.deng.es.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        List<Content> contents = new HtmlParseUtil().htmlParse("java");
        contents.forEach(System.out::println);

    }

    public List<Content> htmlParse (String key) throws Exception {
        String url = "https://search.jd.com/Search?keyword="+key;
        //解析网页
        Document document = Jsoup.parse(new URL(url), 40000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        List<Content> contents = new ArrayList();
        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            contents.add(content);
        }
        return contents;
    }
}
