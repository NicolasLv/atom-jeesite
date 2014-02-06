/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.github.obullxl.jeesite.web.controller;

import java.util.List;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.obullxl.jeesite.web.enums.BizResponseEnum;
import com.github.obullxl.jeesite.web.webx.CfgWebX;
import com.github.obullxl.lang.ToString;
import com.github.obullxl.lang.biz.BizResponse;
import com.github.obullxl.model.topic.TopicModel;

/**
 * 数据同步控制器
 * 
 * @author obullxl@gmail.com
 * @version $Id: DataSyncController.java, V1.0.1 2013年12月31日 下午6:57:30 $
 */
@Controller
@RequestMapping("/data")
public class DataSyncController extends AbstractController {

    /**
     * 数据同步
     */
    @ResponseBody
    @RequestMapping(value = "/sync/topic-apply.htm", method = RequestMethod.POST)
    public BizResponse topicApply(String id) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            TopicModel topic = this.topicService.findByID(id);
            if (topic == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            // 同步
            String json = JSON.toJSONString(topic);

            List<String> hosts = CfgWebX.findDataSyncHosts();
            for (String host : hosts) {
                String url = "http://" + host + "/data/sync/topic-execute.htm";
                Response rtn = Jsoup.connect(url).ignoreContentType(true).data("data", json).method(Method.POST).execute();
                
                if (logger.isInfoEnabled()) {
                    logger.info("[数据同步]-执行结果-{}", ToString.toString(rtn));
                }
            }

            response.getBizData().put(BizResponse.BIZ_ID_KEY, topic.getId());
        } catch (Exception e) {
            logger.error("主题数据同步申请异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/sync/topic-execute.htm", method = RequestMethod.POST)
    public BizResponse topicExecute(String data) {
        // 操作结果
        BizResponse response = this.newBizResponse();

        try {
            // 校验
            TopicModel topic = JSON.parseObject(data, TopicModel.class);
            if (topic == null) {
                this.buildResponse(response, BizResponseEnum.OBJECT_NOT_EXIST);
                return response;
            }

            TopicModel exist = this.topicService.findByID(topic.getId());
            if (exist != null) {
                // 更新
                this.topicService.update(topic);
            } else {
                // 插入
                this.topicService.create(topic);
            }

            // 同步
            response.getBizData().put(BizResponse.BIZ_ID_KEY, topic.getId());
        } catch (Exception e) {
            logger.error("主题数据同步执行异常!", e);
            this.buildResponse(response, BizResponseEnum.SYSTEM_ERROR);
        }

        // JSON返回
        return response;
    }

}
