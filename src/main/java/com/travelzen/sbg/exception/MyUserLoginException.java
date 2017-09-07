package com.travelzen.sbg.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author andrew
 * @createtime 2017-09-07
 * @ide Intellij Idea
 **/
public class MyUserLoginException extends UsernameNotFoundException {

    public MyUserLoginException(String msg) {
        super(msg);
    }

    public MyUserLoginException(String msg, Throwable t) {
        super(msg, t);
    }
}
