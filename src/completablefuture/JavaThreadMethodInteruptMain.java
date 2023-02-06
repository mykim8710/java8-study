package completablefuture;

public class JavaThreadMethodInteruptMain {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(() -> {
            while(true) {
                System.out.println("Thread new Runnable: " +Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("interupt=> exit");
                    return;
                }
            }
        });
        myThread.start();


        System.out.println("Hello Main Thread: " +Thread.currentThread().getName());
        Thread.sleep(3000L);
        myThread.interrupt();
    }
}