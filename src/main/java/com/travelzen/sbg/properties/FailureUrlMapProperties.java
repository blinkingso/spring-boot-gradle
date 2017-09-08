package com.travelzen.sbg.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 登录校验失败map配置
 *
 * @author andrew
 * @createtime 2017-09-08
 * @ide Intellij Idea
 **/
@ConfigurationProperties(prefix = "failure")
@Component
public class FailureUrlMapProperties {

    private Map<String, String> failureMapping;

    public Map<String, String> getFailureMapping() {
        return failureMapping;
    }

    public void setFailureMapping(Map<String, String> failureMapping) {
        this.failureMapping = failureMapping;
    }
}
