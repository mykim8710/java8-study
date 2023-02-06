package completablefuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JavaExecutorsMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

//        executorService.execute(() -> {
//            System.out.println("Thread execute() : " +Thread.currentThread().getName());
//        });

        executorService.submit(getRunnable("Hello"));

        // executorService.shutdownNow(); // Thread 종료 : 바로종료
        executorService.shutdown(); // Thread 종료 : 현재진행중인 작업을 마치고 종료


        // Thread Pool : 2개 executorService 생성
        // Thread pool 크기는 2개지만 5개의 쓰레드가 실행된느 이유?
        // => 내부에 Queue 가 있고 queue에서 대기
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(getRunnable("mykim"));
        executorService2.submit(getRunnable("jwyang"));
        executorService2.submit(getRunnable("khjang"));
        executorService2.submit(getRunnable("jskim"));
        executorService2.submit(getRunnable("jwchung"));

        executorService2.shutdown();


        // ScheduledExecutorService 생성
        ScheduledExecutorService executorService3 = Executors.newSingleThreadScheduledExecutor();
        executorService3.schedule(getRunnable("Schedule~"), 3, TimeUnit.SECONDS);
        executorService3.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> {
            System.out.println(message +" Thread: " + Thread.currentThread().getName());
        };
    }

}
