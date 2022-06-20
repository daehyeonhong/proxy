package hello.proxy.cglib.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {
    public final Object target;

    @Override
    public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy Execute");
        final long startTime = System.currentTimeMillis();
        final Object result = methodProxy.invoke(target, args);
        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;
        log.info("TimeProxy End resultTime={}", resultTime);
        return result;
    }
}
