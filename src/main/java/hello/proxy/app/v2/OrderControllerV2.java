package hello.proxy.app.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping
@ResponseBody
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;

    @GetMapping(value = "/v2/request")
    public String request(final String itemId) {
        this.orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping(value = "/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
