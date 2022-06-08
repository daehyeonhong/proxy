package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Proxy;

@Slf4j
class JdkDynamicProxyTest {
    @Test
    void dynamicA() {
        final AImpl target = new AImpl();
        final TimeInvocationHandler handler = new TimeInvocationHandler(target);
        final AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        final BImpl target = new BImpl();
        final TimeInvocationHandler handler = new TimeInvocationHandler(target);
        final BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        proxy.call();
        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }
}
