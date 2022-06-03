package hello.proxy.pureproxy.proxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CacheProxy implements Subject {
    private final Subject target;
    private String cacheValue;

    @Override
    public String operation() {
        log.info("Proxy Call");
        if (this.cacheValue == null) this.cacheValue = this.target.operation();
        return this.cacheValue;
    }
}
