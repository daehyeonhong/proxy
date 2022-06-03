package hello.proxy.app.v1;

import hello.proxy.app.util.Utils;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {
    @Override
    public void save(final String itemId) {
        if (itemId.equals("ex"))
            throw new IllegalArgumentException("오류 발생!");
        Utils.sleep(1000);
    }
}
