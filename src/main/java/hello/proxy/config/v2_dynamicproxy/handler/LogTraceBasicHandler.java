package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.MessageFormat;

@RequiredArgsConstructor
public class LogTraceBasicHandler implements InvocationHandler {
    private final Object target;
    private final LogTrace logTrace;

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        TraceStatus status = null;
        try {
            final String message = MessageFormat.format("{0}.{1}()", method.getDeclaringClass().getSimpleName(), method.getName());
            status = this.logTrace.begin(message);
            final Object result = method.invoke(target, args);
            this.logTrace.end(status);
            return result;
        } catch (final Exception exception) {
            this.logTrace.exception(status, exception);
            throw exception;
        }
    }
}
