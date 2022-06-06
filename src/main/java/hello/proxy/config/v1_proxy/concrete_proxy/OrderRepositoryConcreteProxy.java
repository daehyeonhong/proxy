package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {
    private final OrderRepositoryV2 target;
    private final LogTrace logTrace;

    @Override
    public void save(final String itemId) {
        TraceStatus status = null;
        try {
            status = this.logTrace.begin("OrderRepository.save()");
            this.target.save(itemId);
            this.logTrace.end(status);
        } catch (final Exception exception) {
            this.logTrace.exception(status, exception);
            throw exception;
        }
    }
}
