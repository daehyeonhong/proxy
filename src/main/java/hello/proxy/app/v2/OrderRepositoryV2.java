package hello.proxy.app.v2;

public class OrderRepositoryV2 {
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
