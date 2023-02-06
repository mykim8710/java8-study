package completablefuture;

public class JavaThreadMethodSleepMain {
    public static void main(String[] args) {
        Thread myThread = new Thread(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread new Runnable: " +Thread.currentThread().getName());
        });
        myThread.start();


        // myThread는 sleep이므로 메인 Thread가 먼저 작동
        System.out.println("Hello Main Thread: " +Thread.currentThread().getName());
    }
}