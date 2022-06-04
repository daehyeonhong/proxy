package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {
    private final OrderServiceV1 target;
    private final LogTrace logTrace;

    @Override
    public void orderItem(final String itemId) {
        TraceStatus status = null;
        try {
            status = this.logTrace.begin("OrderService.orderItem()");
            this.target.orderItem(itemId);
            this.logTrace.end(status);
        } catch (final Exception exception) {
            this.logTrace.exception(status, exception);
            throw exception;
        }
    }
}
