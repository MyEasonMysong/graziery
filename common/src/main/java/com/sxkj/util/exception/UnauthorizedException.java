package com.sxkj.util.exception;

/**
 * com.sxkj.util.exception.UnauthorizedException
 *
 * @author zwd
 * @Description UnauthorizedException
 * @Date Create in 2018-07-12 0012 15:40
 * @Modified
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
