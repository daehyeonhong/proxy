package hello.proxy.jdkdynamic.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeInvocationHandler implements InvocationHandler {
    private final Object target;

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        log.info("TimeProxy Execute");
        final long startTime = System.currentTimeMillis();
        final Object result = method.invoke(this.target, args);
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("TimeProxy End resultTime={}", resultTime);
        return result;
    }
}
