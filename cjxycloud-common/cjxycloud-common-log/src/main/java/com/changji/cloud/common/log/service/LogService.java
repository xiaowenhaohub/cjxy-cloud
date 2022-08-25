package com.changji.cloud.common.log.service;

import com.changji.cloud.common.log.model.OperationLog;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 14:57 2022/8/25
 * @ Modified By：
 */
public interface LogService {

    /**
     * 打印日志
     */
    void printLog(OperationLog operationLog);
}
