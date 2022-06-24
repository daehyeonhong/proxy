package hello.proxy.advisor;

import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

class MultiAdvisorTests {
    @Test
    @DisplayName("여러 프록시")
    void multiAdvisorTest1() {
        //Proxy1 생성
        final ServiceInterface target = new ServiceImpl();
        final ProxyFactory proxyFactory1 = new ProxyFactory(target);
        final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save");
        final PointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor1());
        proxyFactory1.addAdvisor(advisor1);
        final ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        final ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        final PointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor2());
        proxyFactory2.addAdvisor(advisor2);
        final ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();
        proxy2.save();
    }

    @Test
    @DisplayName("하나의 프록시 여러 어드바이져")
    void multiAdvisorTest2() {
        final PointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor1());
        final PointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advisor2());

        //Proxy1 생성
        final ServiceInterface target = new ServiceImpl();
        final ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);

        final ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
    }

    @Slf4j
    static class Advisor1 implements MethodInterceptor {
        @Override
        public Object invoke(final MethodInvocation invocation) throws Throwable {
            log.info("Call advisor1");
            return invocation.proceed();
        }
    }

    @Slf4j
    static class Advisor2 implements MethodInterceptor {
        @Override
        public Object invoke(final MethodInvocation invocation) throws Throwable {
            log.info("Call advisor2");
            return invocation.proceed();
        }
    }
}
