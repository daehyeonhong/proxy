package hello.proxy.app.v1;

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {
    @Override
    public void save(final String itemId) {
        if (itemId.equals("ex"))
            throw new IllegalArgumentException("오류 발생!");
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
