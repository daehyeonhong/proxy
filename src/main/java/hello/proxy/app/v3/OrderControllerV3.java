package hello.proxy.app.v3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;

    @GetMapping(value = "/v3/request")
    public String request(final String itemId) {
        this.orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping(value = "/v3/no-log")
    public String noLog() {
        return "ok";
    }
}
