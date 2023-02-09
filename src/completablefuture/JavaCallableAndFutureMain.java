package completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class JavaCallableAndFutureMain {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "java";
        };

        Callable<String> spring = () -> {
            Thread.sleep(4000L);
            return "spring";
        };

        /*Future<String> helloFuture = executorService.submit(hello);
        System.out.println("isDone : " +helloFuture.isDone());
        System.out.println("Started!");

        //helloFuture.cancel(true);   // 현재 작업중인 쓰레드 취소
        helloFuture.get();   // 블록킹콜

        System.out.println("isDone : " +helloFuture.isDone());
        System.out.println("End!");
        executorService.shutdown();*/


        // 여러 작업동시 실행 : 제일 오래걸리는 작업만큼시간이 걸린다.
        /*List<Future<String>> futureList = executorService.invokeAll(Arrays.asList(hello, java, spring));
        for (Future<String> future : futureList) {
            System.out.println(future.get());
        }

        executorService.shutdown();*/


        // 여러 작업동시 실행 : 여러 작업 중에 하나라도 먼저 응답이 오면 끝내기
        String result = executorService.invokeAny(Arrays.asList(hello, java, spring));
        System.out.println(result);

        executorService.shutdown();
    }
}
