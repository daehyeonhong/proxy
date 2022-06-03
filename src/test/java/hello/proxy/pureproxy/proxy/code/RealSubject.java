package hello.proxy.pureproxy.proxy.code;

import hello.proxy.app.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
    @Override
    public String operation() {
        log.info("실제 객체 호출");
        Utils.sleep(1001);
        return "data";
    }
}
