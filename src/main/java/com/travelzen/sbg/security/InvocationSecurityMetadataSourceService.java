package com.travelzen.sbg.security;

import com.travelzen.sbg.domain.Permission;
import com.travelzen.sbg.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author andrew
 * @createtime 2017-09-08
 * @ide Intellij Idea
 **/
@Component
public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionMapper permissionMapper;

    private Map<String, Collection<ConfigAttribute>> map = null;

    private void loadResourceDefinition(){
        map = new HashMap<>();
        Collection<ConfigAttribute> collections;
        ConfigAttribute configAttribute;
        List<Permission> permissions = permissionMapper.listAll();
        for (Permission permission : permissions) {
            collections = new ArrayList<>();
            configAttribute = new SecurityConfig(permission.getName());
            collections.add(configAttribute);
            map.put(permission.getUrl(), collections);
        }
    }

    /**
     * 如果制定的url用户有访问权限则将权限数据打包到Collection<ConfigAttribute>中传给
     * CustomAccessDecisionManager中调用decide方法校验用户权限。
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) loadResourceDefinition();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
