package hello.proxy;

import hello.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(value = {AppV1Config.class, AppV2Config.class})
//@Import(value = {InterfaceProxyConfig.class})
//@Import(value = {ConcreteProxyConfig.class})
//@Import(value = {DynamicProxyBasicConfig.class})
//@Import(value = {DynamicProxyFilterConfig.class})
//@Import(value = {ProxyFactoryConfigV1.class})
//@Import(value = {ProxyFactoryConfigV2.class})
@Import(value = {BeanPostProcessorConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
