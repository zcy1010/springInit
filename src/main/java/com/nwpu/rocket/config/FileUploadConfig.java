package com.nwpu.rocket.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 类描述：
 *
 * @ClassName FileUploadConfig
 * @Author cyq
 * @Date 2021/4/29 13:34
 * @Version 1.0
 */
@Configuration
public class FileUploadConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小 KB,MB 大写
        factory.setMaxFileSize(DataSize.parse("800MB"));
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.parse("80000MB"));
        return factory.createMultipartConfig();
    }
}