package hello.proxy.config.v3_proxyfactory.advice;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import java.lang.reflect.Method;
import java.text.MessageFormat;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {
    private final LogTrace logTrace;

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            final Method method = invocation.getMethod();
            final String message = MessageFormat.format("{0}.{1}()", method.getDeclaringClass().getSimpleName(), method.getName());
            status = this.logTrace.begin(message);
            final Object result = invocation.proceed();
            this.logTrace.end(status);
            return result;
        } catch (final Exception exception) {
            this.logTrace.exception(status, exception);
            throw exception;
        }
    }
}
