package com.hhw.handler;

import com.hhw.domain.result.Result;
import com.hhw.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
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
     */
    @ExceptionHandler(BizException.class)
    public Result handleBizException(BizException e) {
        log.warn("业务异常: code={}, message={}", e.getCode(), e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 2. 处理数据库完整性约束异常（重点）
     * 包括：字段不能为空、唯一键冲突、外键约束等
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("数据库完整性约束异常: {}", e.getMessage());

        // 获取根本原因（最底层的 SQLException）
        Throwable rootCause = getRootCause(e);
        String message = rootCause != null ? rootCause.getMessage() : e.getMessage();

        // 根据不同的错误类型，返回友好的提示
        String friendlyMessage = buildFriendlyMessage(message);

        return Result.fail(friendlyMessage);
    }

    /**
     * 3. 处理 SQL 异常（兜底）
     */
    @ExceptionHandler(SQLException.class)
    public Result handleSQLException(SQLException e) {
        log.warn("SQL异常: {}", e.getMessage());
        return Result.fail("数据库操作失败：" + buildFriendlyMessage(e.getMessage()));
    }

    /**
     * 4. 处理参数类型转换异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String paramName = e.getName();
        Object value = e.getValue();
        String requiredType = e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "未知";

        String message;
        if ("id".equals(paramName)) {
            message = String.format("参数 [%s] 格式错误，请传入数字类型（如 1、2、3），实际传入：%s", paramName, value);
        } else {
            message = String.format("参数 [%s] 格式错误，期望类型为 %s，实际传入：%s", paramName, requiredType, value);
        }

        log.warn("参数类型转换异常: {}", message);
        return Result.fail(message);
    }

    /**
     * 5. 处理参数缺失异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String message = String.format("缺少必要参数 [%s]，类型为 %s", e.getParameterName(), e.getParameterType());
        log.warn("参数缺失: {}", message);
        return Result.fail(message);
    }

    /**
     * 6. 处理 @Valid 参数校验失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = bindingResult.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        log.warn("参数校验失败: {}", message);
        return Result.fail("参数校验失败: " + message);
    }

    /**
     * 7. 处理请求体解析异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn("请求体解析异常: {}", e.getMessage());
        return Result.fail("请求体格式错误，请检查 JSON 格式是否正确");
    }

    /**
     * 8. 处理 404 路径不存在异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.warn("请求路径不存在: {} {}", e.getHttpMethod(), e.getRequestURL());
        return Result.fail("请求路径不存在，请检查 URL 是否正确");
    }

    /**
     * 9. 处理空指针异常（兜底之一）
     */
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException e) {
        log.error("空指针异常", e);
        return Result.fail("系统内部错误，请稍后重试");
    }

    /**
     * 10. 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("非法参数: {}", e.getMessage());
        return Result.fail("参数错误: " + e.getMessage());
    }

    /**
     * 11. 处理运行时异常（兜底）
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("运行时异常", e);
        return Result.fail("系统繁忙，请稍后重试");
    }

    /**
     * 12. 处理所有其他异常（最终兜底）
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常", e);
        return Result.fail("系统繁忙，请稍后重试");
    }

    // ==================== 私有辅助方法 ====================

    /**
     * 获取异常的根本原因（最底层）
     */
    private Throwable getRootCause(Throwable e) {
        Throwable cause = e;
        while (cause.getCause() != null && cause.getCause() != cause) {
            cause = cause.getCause();
        }
        return cause;
    }

    /**
     * 根据数据库错误信息，构建用户友好的提示
     */
    private String buildFriendlyMessage(String dbMessage) {
        if (dbMessage == null) {
            return "数据库操作失败，请检查数据完整性";
        }

        // 字段不能为空
        if (dbMessage.contains("doesn't have a default value")) {
            // 提取字段名，例如 "Field 'name' doesn't have a default value" -> "name"
            String fieldName = extractFieldName(dbMessage);
            return String.format("缺少必填字段 [%s]，请检查请求数据是否完整", fieldName);
        }

        // 唯一键冲突
        if (dbMessage.contains("Duplicate entry") || dbMessage.contains("UNIQUE")) {
            return "数据已存在，请勿重复提交";
        }

        // 外键约束
        if (dbMessage.contains("foreign key") || dbMessage.contains("FOREIGN KEY")) {
            return "数据关联冲突，请检查引用的数据是否存在";
        }

        // 字段长度超限
        if (dbMessage.contains("Data too long") || dbMessage.contains("length")) {
            return "输入的数据长度超出限制，请检查字段长度";
        }

        // 数据类型错误
        if (dbMessage.contains("Incorrect") && dbMessage.contains("value")) {
            return "输入的数据格式不正确，请检查数据类型";
        }

        // 默认
        return "数据库操作失败：" + dbMessage;
    }

    /**
     * 从错误信息中提取字段名
     * 例如: "Field 'name' doesn't have a default value" -> "name"
     */
    private String extractFieldName(String message) {
        // 匹配 'xxx' 或 `xxx` 包裹的内容
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("['`]([^'`]+)['`]");
        java.util.regex.Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "未知字段";
    }
}