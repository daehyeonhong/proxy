package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DecoratorPatternTest {
    @Test
    void noDecoratorTest() {
        final Component realComponent = new RealComponent();
        final DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1Test() {
        final Component realComponent = new RealComponent();
        final MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        final DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2Test() {
        final Component realComponent = new RealComponent();
        final MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        final TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        final DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
