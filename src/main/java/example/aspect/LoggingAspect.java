package example.aspect;


import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;


@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* example.facade.*.*(..)) || @annotation(Loggable)")
    public void executeLogging(){}

    @SneakyThrows
    @Around(value = "executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        StringBuilder message = new StringBuilder("Method: ");
        message.append(joinPoint.getSignature().getName());
        message.append(" totalTime: ").append(totalTime).append("ms");
        Object[] args = joinPoint.getArgs();
        if(null != args && args.length > 0) {
            message.append(" args[ | ");
            Arrays.asList(args).forEach(arg -> {
                message.append(arg).append(" | ");
            });
            message.append("]");
        }
        if (returnValue instanceof Collection) {
            message.append(", returning: ").append(((Collection) returnValue).size()).append(" instance(s)");
        } else {
            message.append(", returning: ").append(returnValue.toString());
        }
        LOGGER.info(message.toString());
        return returnValue;
    }
}
