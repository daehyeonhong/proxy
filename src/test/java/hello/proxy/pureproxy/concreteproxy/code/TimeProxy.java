package hello.proxy.pureproxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TimeProxy extends ConcreteLogic {
    private final ConcreteLogic target;

    @Override
    public String operation() {
        log.info("TimeDecorator Start");
        final long startTime = System.currentTimeMillis();
        final String result = this.target.operation();
        final long endTime = System.currentTimeMillis();
        log.info("TimeDecorator End resultTime:{}", endTime - startTime);
        return result;
    }
}
