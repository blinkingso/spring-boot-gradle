package com.travelzen.sbg.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author andrew
 * @createtime 2017-09-08
 * @ide Intellij Idea
 **/
public class PermissionNotFoundException extends UsernameNotFoundException {
    public PermissionNotFoundException(String msg) {
        super(msg);
    }

    public PermissionNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
