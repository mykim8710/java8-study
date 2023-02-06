package completablefuture;

public class JavaThreadCreateMain {
    public static void main(String[] args) {
        // 쓰레드의 구현 1 : 순서 보장 X
        MyThread myThread = new MyThread();
        myThread.start();   // 쓰레드 생성

        System.out.println("Hello Main: " +Thread.currentThread().getName());    // 메인 Thread : 순서 보장 X

        // 쓰레드의 구현 2 : 순서 보장 X
        Thread thread = new Thread(() -> {
            System.out.println("Thread new Runnable: " +Thread.currentThread().getName());
        });
        thread.start();
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread extends Thread: " +Thread.currentThread().getName());
        }
    }
}