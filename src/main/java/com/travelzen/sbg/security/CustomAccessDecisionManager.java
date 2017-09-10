package com.travelzen.sbg.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author andrew
 * @createtime 2017-09-08
 * @ide Intellij Idea
 **/
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判定是否拥有访问权限的决策方法
     * @param authentication 在CustomUserDetailsService中添加到GrantedAuthority对象的权限信息集合
     * @param object 包含客户端发起的HttpServletRequest请求信息。object.getHttpRequest()
     * @param configAttributes InvocationSecurityMetadataSource的gerAttributes方法返回的结果。
     *                         该方法用于判定用户请求的url是否在权限的集合中，若在权限表中则返回给decide用于判定用户权限。如果不在权限表中则放行
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes))
            return;
        ConfigAttribute configAttribute;
        String needRole;
        Iterator<ConfigAttribute> iter = configAttributes.iterator();
        while (iter.hasNext()) {
            configAttribute = iter.next();
            needRole = configAttribute.getAttribute();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (needRole.trim().equals(authority.getAuthority())) {
                    return ;
                }
            }
        }
        throw new AccessDeniedException("access denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
