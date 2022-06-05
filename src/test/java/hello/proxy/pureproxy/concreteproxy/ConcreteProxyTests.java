package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import org.junit.jupiter.api.Test;

class ConcreteProxyTests {
    @Test
    void noProxy() {
        final ConcreteLogic logic = new ConcreteLogic();
        final ConcreteClient client = new ConcreteClient(logic);
        client.execute();
    }
}
