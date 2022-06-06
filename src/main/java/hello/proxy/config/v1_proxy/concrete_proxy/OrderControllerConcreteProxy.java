package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {
    private final OrderControllerV2 target;
    private final LogTrace logTrace;

    public OrderControllerConcreteProxy(final OrderControllerV2 target, final LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

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
