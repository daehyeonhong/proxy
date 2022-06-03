package hello.proxy.pureproxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessageDecorator implements Component {
    private final Component component;

    @Override
    public String operation() {
        log.info("MessageDecorator execute");
        final String before = this.component.operation();
        final String after = "***" + before + "***";
        log.info("MessageDecorator before:{}, after:{}", before, after);
        return after;
    }
}
