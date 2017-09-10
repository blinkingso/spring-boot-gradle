package com.travelzen.sbg.classload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * freemaker spring.tld 加载路径
 */
public class ClassPathTldsLoader {

    /**
     * 指定路径
     */
    private static final String SECURITY_TLD = "classpath:/META-INF/security.tld";

    final private List<String> classPathTlds;

    public ClassPathTldsLoader(String... classPathTlds) {
        super();
        if (classPathTlds == null || classPathTlds.length == 0) {
            this.classPathTlds = Arrays.asList(SECURITY_TLD);
        } else {
            this.classPathTlds = Arrays.asList(classPathTlds);
        }
    }

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void loadClassPathTlds() {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
    }
}
