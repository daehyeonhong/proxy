package hello.proxy.pureproxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DecoratorPatternClient {
    private final Component component;

    public void execute() {
        final String result = this.component.operation();
        log.info("result={}", result);
    }
}
