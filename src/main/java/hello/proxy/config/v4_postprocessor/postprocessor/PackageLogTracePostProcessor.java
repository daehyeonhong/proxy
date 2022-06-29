package hello.proxy.config.v4_postprocessor.postprocessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
@RequiredArgsConstructor
public class PackageLogTracePostProcessor implements BeanPostProcessor {
    private final String basePackage;
    private final Advisor advisor;

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        log.info("param beanName={} bean={}", beanName, bean.getClass());
        //Proxy 적용 대상 여부 체크
        //Proxy 적용 대상이 아니면 원본을 그대로 진행
        final String packageName = bean.getClass().getPackageName();
        if (!packageName.startsWith(basePackage))
            return bean;
        //Proxy 대상이면 Proxy 생성하여 반환
        final ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
        final Object proxy = proxyFactory.getProxy();
        log.info("create proxy:target={} proxy={}", bean.getClass(), proxy.getClass());
        return proxy;
    }
}
