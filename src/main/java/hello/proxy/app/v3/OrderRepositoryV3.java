package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {
    public void save(final String itemId) {
        if (itemId.equals("ex")) throw new IllegalArgumentException("오류 발생!");
        this.sleep(1000);
    }

    private void sleep(final int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
