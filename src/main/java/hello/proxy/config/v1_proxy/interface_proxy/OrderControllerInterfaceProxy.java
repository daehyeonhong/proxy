package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {
    private final OrderControllerV1 target;
    private final LogTrace logTrace;

    @Override
    public String request(final String itemId) {
        TraceStatus status = null;
        try {
            status = this.logTrace.begin("OrderController.request()");
            final String result = this.target.request(itemId);
            this.logTrace.end(status);
            return result;
        } catch (final Exception exception) {
            this.logTrace.exception(status, exception);
            throw exception;
        }
    }

    @Override
    public String noLog() {
        return this.target.noLog();
    }
}
