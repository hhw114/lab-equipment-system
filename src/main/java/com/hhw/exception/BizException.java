package com.hhw.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常类
 * 用于抛出业务层面的预期异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BizException extends RuntimeException {

    /**
     * 错误码（可选，如果不使用错误码可以只传message）
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 构造函数 - 只传消息（最常用）
     */
    public BizException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * 构造函数 - 传错误码和消息
     */
    public BizException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数 - 传消息和原始异常（用于异常链）
     */
    public BizException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    /**
     * 构造函数 - 传错误码、消息和原始异常
     */
    public BizException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}