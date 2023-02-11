package completablefuture;

import java.util.concurrent.CompletableFuture;

public class JavaCompletableFutureMain {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("mykim");   // completableFuture의 기본값 설정
        System.out.println("completableFuture.get() = " + completableFuture.get());


        // ● 리턴값이 없는 경우: runAsync()
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            System.out.println("completableFuture2 " +Thread.currentThread().getName());
        });
        System.out.println("completableFuture2.isDone() = " + completableFuture2.isDone());
        completableFuture2.get();
        System.out.println("completableFuture2.isDone() = " + completableFuture2.isDone());


        // ● 리턴값이 있는 경우: supplyAsync()
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture3 " +Thread.currentThread().getName());
            return "completableFuture3";
        });
        System.out.println("completableFuture3.isDone() = " + completableFuture3.isDone());
        System.out.println(completableFuture3.get());
        System.out.println("completableFuture3.isDone() = " + completableFuture3.isDone());


        // 콜백 제공하기
        // ● 리턴값을 받아서 다른 값으로 바꾸는 콜백 : thenApply(Function)
        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture4 " +Thread.currentThread().getName());
            return "completableFuture4";
        }).thenApply((str) -> {
            System.out.println("completableFuture4 " +Thread.currentThread().getName());
            return str.toUpperCase();
        });

        String result = completableFuture4.get();
        System.out.println("result = " + result);


        // ● 리턴값을 또 다른 작업을 처리하는 콜백 (리턴없이) : thenAccept(Consumer)
        CompletableFuture<Void> completableFuture5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture5 " + Thread.currentThread().getName());
            return "completableFuture5";
        }).thenAccept((str) -> {
            System.out.println("completableFuture5 " + Thread.currentThread().getName());
            System.out.println(str.toUpperCase());
        });

        completableFuture5.get();


        // ● 리턴값 받지 다른 작업을 처리하는 콜백 : thenRun(Runnable)
        CompletableFuture<Void> completableFuture6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("completableFuture6 " + Thread.currentThread().getName());
            return "completableFuture6";
        }).thenRun(() -> {
            System.out.println("completableFuture6 " + Thread.currentThread().getName());
        });

        completableFuture6.get();
    }
}
