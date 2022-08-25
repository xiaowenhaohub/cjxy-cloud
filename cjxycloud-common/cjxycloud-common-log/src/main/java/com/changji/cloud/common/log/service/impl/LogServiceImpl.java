package com.changji.cloud.common.log.service.impl;

import com.changji.cloud.common.log.model.OperationLog;
import com.changji.cloud.common.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：小问号.
 * @ Date       ：Created in 15:00 2022/8/25
 * @ Modified By：
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Override
    public void printLog(OperationLog operationLog) {
      log.info("操作日志:{}",operationLog.toString());
    }
}
