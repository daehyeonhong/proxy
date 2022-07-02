package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {
    private final LogTrace logTrace;

    @Around(value = "execution(* hello.proxy.app..*(..))")
    public Object execute(final ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            final String message = joinPoint.getSignature().toShortString();
            status = this.logTrace.begin(message);

            final Object result = joinPoint.proceed();

            this.logTrace.end(status);
            return result;
        } catch (final Exception exception) {
            this.logTrace.exception(status, exception);
            throw exception;
        }
    }
}
