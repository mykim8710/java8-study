package completablefuture;

public class JavaThreadMethodJoinMain {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(() -> {
            System.out.println("Thread new Runnable: " +Thread.currentThread().getName());

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalArgumentException(e);
            }

        });
        myThread.start();


        System.out.println("Hello Main Thread: " +Thread.currentThread().getName());
        myThread.join();    // myThread가 끝날때까지 기다린다.

        System.out.println(myThread +" is finished");
    }
}