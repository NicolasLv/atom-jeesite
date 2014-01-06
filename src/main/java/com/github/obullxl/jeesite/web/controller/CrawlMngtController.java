/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.obullxl.jeesite.dal.DBSize;
import com.github.obullxl.jeesite.dal.dto.CrawlDTO;
import com.github.obullxl.jeesite.dal.dto.TopicDTO;
import com.github.obullxl.jeesite.dal.valve.TopicValve;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.enums.TopicMediaEnum;
import com.github.obullxl.jeesite.web.enums.TopicReplyEnum;
import com.github.obullxl.jeesite.web.enums.TopicStateEnum;
import com.github.obullxl.lang.MapExt;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.lang.enums.EnumBase;
import com.github.obullxl.lang.enums.ValveBoolEnum;
import com.github.obullxl.lang.utils.TextUtils;
import com.github.obullxl.lang.web.crawl.CrawlData;
import com.github.obullxl.lang.web.crawl.WebCrawler;

/**
 * 爬虫控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: CrawlMngtController.java, V1.0.1 2013年12月10日 下午4:51:51 $
 */
@Controller
@RequestMapping("/admin")
public class CrawlMngtController extends AbstractController {

    /**
     * 新增爬虫
     */
    @RequestMapping(value = "/crawl/create.html", method = RequestMethod.GET)
    public String crawlCreate() {
        return this.toAdminView(VOPT_CRAWL_CREATE, "crawl-create");
    }

    @ResponseBody
    @RequestMapping(value = "/crawl/create.html", method = RequestMethod.POST)
    public BizResponse crawlCreate(String name, String content) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 脚本检查
            EnumBase check = this.checkGroovy(content);
            if (check != null) {
                this.buildResponse(response, check);
                return response;
            }

            // 新增记录
            CrawlDTO crawl = new CrawlDTO();
            crawl.setName(name);
            crawl.setContent(content);

            long id = this.crawlDAO.insert(crawl);
            response.getBizData().put(BizResponse.BIZ_ID_KEY, Long.toString(id));

            // 保持Groovy文件
            this.saveGroovy(id, content);
        } catch (Exception e) {
            logger.error("爬虫新增异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 修改爬虫
     */
    @RequestMapping(value = "/crawl/update-{id}.html", method = RequestMethod.GET)
    public String crawlUpdate(@PathVariable long id) {
        this.setWebData("crawlId", id);
        return this.toAdminView(VOPT_CRAWL_MANAGE, "crawl-update");
    }

    @ResponseBody
    @RequestMapping(value = "/crawl/update-{id}.html", method = RequestMethod.POST)
    public BizResponse crawlUpdate(@PathVariable long id, String name, String content) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 脚本检查
            EnumBase check = this.checkGroovy(content);
            if (check != null) {
                this.buildResponse(response, check);
                return response;
            }

            // 查询
            CrawlDTO crawl = this.crawlDAO.find(id);
            crawl.setName(name);
            crawl.setContent(content);

            this.crawlDAO.update(crawl);

            // 保持Groovy文件
            this.saveGroovy(id, content);
        } catch (Exception e) {
            logger.error("爬虫修改异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    /**
     * 数据抓取
     */
    @RequestMapping(value = "/crawl/input.html", method = RequestMethod.GET)
    public String crawlInput() {
        // 爬虫信息
        Map<Long, String> crawls = new LinkedHashMap<Long, String>();
        List<CrawlDTO> dtos = this.crawlDAO.findNames();
        for (CrawlDTO dto : dtos) {
            crawls.put(dto.getId(), dto.getName());
        }
        this.setWebData("crawls", crawls);

        return this.toAdminView(VOPT_CRAWL_INPUT, "crawl-input");
    }

    @ResponseBody
    @RequestMapping(value = "/crawl/input.html", method = RequestMethod.POST)
    public BizResponse crawlInput(long crawlId, String links, String text) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        GroovyClassLoader gloader = new GroovyClassLoader(WebCrawler.class.getClassLoader());
        try {
            // 查询
            CrawlDTO crawl = this.crawlDAO.find(crawlId);
            if (logger.isInfoEnabled()) {
                logger.info("准备执行爬虫-{}.{}.", crawl.getId(), crawl.getName());
            }

            // 加载
            Class<?> clz = gloader.parseClass(crawl.getContent());
            WebCrawler crawler = (WebCrawler) clz.newInstance();

            // 链接
            Map<String, String> args = MapExt.parse(text, "|", "=");
            String[] urls = StringUtils.split(links, "|");
            for (String url : urls) {
                List<CrawlData> datas = crawler.crawl(url, new HashMap<String, String>());
                for (CrawlData data : datas) {
                    TopicDTO topic = this.newInitTopic();

                    TopicValve valve = topic.findValve();
                    valve.sotState(TopicStateEnum.findDefault(MapUtils.getString(args, "topic.state")));
                    valve.sotTop(ValveBoolEnum.findDefault(MapUtils.getString(args, "topic.top")));
                    valve.sotLink(ValveBoolEnum.findDefault(MapUtils.getString(args, "topic.link")));
                    valve.sotMedia(TopicMediaEnum.findDefault(MapUtils.getString(args, "topic.media")));
                    valve.sotReply(TopicReplyEnum.findDefault(MapUtils.getString(args, "topic.reply")));

                    topic.setFlag(valve.getValve());
                    topic.setCatg(StringUtils.trimToEmpty(MapUtils.getString(args, "topic.catg")));
                    topic.setLinkUrl(data.getUrl());
                    topic.setMediaUrl(StringUtils.trimToEmpty(MapUtils.getString(args, "topic.media_url")));
                    topic.setTitle(data.getTitle());

                    if (StringUtils.isBlank(data.getSummary())) {
                        data.setSummary(TextUtils.truncate(data.getContent(), DBSize.Topic.SUMMARY_MAX));
                    } else {
                        data.setSummary(TextUtils.truncate(data.getSummary(), DBSize.Topic.SUMMARY_MAX));
                    }
                    topic.setSummary(data.getSummary());

                    topic.setContent(data.getContent());

                    if (logger.isInfoEnabled()) {
                        logger.info("解析URL-{}-{}.", url, data.getTitle());
                    }

                    // 新增主题
                    this.topicDAO.insert(topic);
                }
            }
        } catch (Exception e) {
            logger.error("爬虫执行异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        } finally {
            this.close(gloader);
        }

        // JSON返回
        return response;
    }

    /**
     * 爬虫管理
     */
    @RequestMapping(value = "/crawl/manage.html")
    public String crawlManage() {
        return this.toAdminView(VOPT_CRAWL_MANAGE, "crawl-manage");
    }

    /**
     * 检查爬虫Groovy脚本
     */
    private EnumBase checkGroovy(String content) {
        // 脚本检查
        GroovyClassLoader gloader = new GroovyClassLoader(WebCrawler.class.getClassLoader());
        try {
            Class<?> clz = gloader.parseClass(content);
            WebCrawler object = (WebCrawler) clz.newInstance();
            Assert.notNull(object);
            return null;
        } catch (Exception e) {
            logger.error("Groovy脚本错误", e);
            return BizResponseEnum.GROOVY_ERROR;
        } finally {
            this.close(gloader);
        }
    }

    /**
     * 保持Groovy文件
     */
    private void saveGroovy(long id, String content) {
        try {
            String path = this.findServletContext().getRealPath("/WEB-INF/groovy/crawler/" + id + ".groovy");
            FileUtils.writeStringToFile(new File(path), content);
        } catch (Exception e) {
            logger.error("保存Groovy异常-[{}].", id, e);
        }
    }

}
