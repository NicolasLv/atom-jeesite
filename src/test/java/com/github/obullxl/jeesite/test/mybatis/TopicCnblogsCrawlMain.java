/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.test.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.obullxl.jeesite.dal.dao.TopicDAO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.lang.web.crawl.CrawlData;
import com.github.obullxl.lang.web.crawl.support.CnblogsWebCrawler;

/**
 * cnblogs爬虫
 * 
 * @author obullxl@gmail.com
 * @version $Id: TopicCnblogsCrawlMain.java, V1.0.1 2013年12月6日 下午12:41:12 $
 */
public class TopicCnblogsCrawlMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/*.xml");

        TopicDAO topicDAO = context.getBean(TopicDAO.class);

        List<String> urls = fetchLinks();
        for (String url : urls) {
            TopicDTO topic = new TopicDTO();

            topic.setState("T");
            topic.setCatg(3001);
            topic.setTflag("T");
            topic.setRflag("F");
            topic.setRfrom("");
            topic.setMflag("F");
            topic.setMpath("");
            topic.setMcount(0);
            topic.setTreply("T");
            topic.setVisit(0);
            topic.setReply(0);

            CnblogsWebCrawler crawler = new CnblogsWebCrawler();
            List<CrawlData> data = crawler.crawl(url, new HashMap<String, String>());

            topic.setTitle(data.get(0).getTitle());
            topic.setSummary(data.get(0).getSummary());
            topic.setContent(data.get(0).getContent());

            String id = topicDAO.insert(topic);
            System.out.println(url + "-插入-" + "-ID: " + id);
        }
    }

    private static List<String> fetchLinks() throws Exception {
        List<String> links = new ArrayList<String>();

        List<String> sites = Arrays.asList("http://www.cnblogs.com/obullxl/default.html?page=1", "http://www.cnblogs.com/obullxl/default.html?page=2");
        for (String site : sites) {
            Document document = Jsoup.connect(site).get();
            Element body = document.body();

            Elements posts = body.getElementsByClass("postTitle");
            for (Element post : posts) {
                Element a = post.getElementsByTag("a").get(0);
                String link = a.attr("href");
                links.add(link);
            }
        }

        return links;
    }

}
