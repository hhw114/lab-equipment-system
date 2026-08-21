package com.hhw.handler;

import com.hhw.domain.result.Result;
import com.hhw.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 统一处理所有异常，返回标准Result格式
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1. 处理自定义业务异常
     * 这是最常用的异常处理，业务代码中主动抛出
     */
    @ExceptionHandler(BizException.class)
    public Result handleBizException(BizException e) {
        // 业务异常是预期的，用warn级别，不打印堆栈
        log.warn("业务异常: code={}, message={}", e.getCode(), e.getMessage());
        return Result.fail(e.getMessage());
    }




    /**
     * 2. 处理空指针异常（兜底之一）
     * 这类异常是系统Bug，需要打印完整堆栈
     */
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return Result.fail("系统内部错误，请稍后重试");
    }

    /**
     * 3. 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("非法参数: {}", e.getMessage());
        return Result.fail("参数错误: " + e.getMessage());
    }

    /**
     * 4. 处理运行时异常（兜底）
     * 所有未被上面捕获的运行时异常都在这里处理
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("运行时异常", e);
        return Result.fail("系统繁忙，请稍后重试");
    }

    /**
     * 5. 处理所有其他异常（最终兜底）
     * 包括受检异常等
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常", e);
        return Result.fail("系统繁忙，请稍后重试");
    }
}