package com.kuehne_nagel.bitcoin_rate.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingConfig {
    private final Logger log = LoggerFactory.getLogger(LoggingConfig.class);

    @Before("within(com.kuehne_nagel.bitcoin_rate.service.*))")
    public void logBeforeCall(JoinPoint joinPoint) {
        var strBuilder = new StringBuilder();
        strBuilder.append("bitcoin_rate-start-method: ")
                .append(joinPoint.getSignature());

        if (joinPoint.getArgs().length > 0) {
            strBuilder.append(" with arguments: ")
                    .append(Arrays.toString(joinPoint.getArgs()));
        }

        log.info(strBuilder.toString());
    }

    @AfterReturning(value = "within(com.kuehne_nagel.bitcoin_rate.service.*))", returning = "result")
    public void logAfterCall(JoinPoint joinPoint, Object result) {
        var strBuilder = new StringBuilder()
                .append("bitcoin_rate-end-method: ")
                .append(joinPoint.getSignature());

        if (joinPoint.getArgs().length > 0) {
            strBuilder.append(" with arguments: ")
                    .append(Arrays.toString(joinPoint.getArgs()));
        }

        if (result != null) {
            strBuilder.append("\n[bitcoin_rate-method-result: ")
                    .append(result).append("\nbitcoin_rate-end-method-result end]\n");
        }

        log.info(strBuilder.toString());
    }

}
