package completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class JavaCompletableFuture2Main {
    public static void main(String[] args) throws Exception {

        // 조합하기
        CompletableFuture<String> helloFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " +Thread.currentThread().getName());
            return "Hello ";
        });

        // completableFuture이 마친 후 completableFuture2가 실행되야한다.
        // 두 작업이 서로 이어서 실행하도록 조합 : thenCompose()
        CompletableFuture<String> composeFuture = helloFuture.thenCompose(result -> getCompletableFuture(result));
        System.out.println(composeFuture.get());

        // 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행 : thenCombine()
        CompletableFuture<String> msFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("msFuture Thread : " +Thread.currentThread().getName());
            return "10,000원";
        });

        CompletableFuture<String> appleFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("appleFuture Thread : " +Thread.currentThread().getName());
            return "20,000원";
        });

        CompletableFuture<String> combineFuture = msFuture.thenCombine(appleFuture, (msResult, appleResult) -> {
            return "MS : " + msResult + "  ||  APPLE : " + appleResult;
        });
        System.out.println(combineFuture.get());


        // 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행 : allOf()
        List<CompletableFuture<String>> futures = Arrays.asList(msFuture, appleFuture);
        CompletableFuture[] futuresArr = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArr)
                .thenApply(v -> futures.stream()
                                            .map(f -> f.join())
                                            .collect(Collectors.toList()));

        results.get().forEach(result -> System.out.println("주식가격 : " +result));


        // 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행 : anyOf()
        CompletableFuture.anyOf(futuresArr).thenAccept(System.out::println).get();


        // 예외처리 1
        boolean throwError = true;
        CompletableFuture<String> errorFuture = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException("Throw Exception!!");
            }

            System.out.println("errorFuture Thread : " +Thread.currentThread().getName());
            return "future";
        }).exceptionally(e -> {
            e.printStackTrace();
            return "default value";
        });

        System.out.println(errorFuture.get()); // default value


        // 예외처리 2
        CompletableFuture<String> errorFuture2 = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException("Throw Exception!!");
            }

            System.out.println("errorFuture Thread : " +Thread.currentThread().getName());
            return "future2";
        }).handle((result, ex) -> {
            if(ex !=null) {
                return "ERROR";
            }
            return result;
        });

        System.out.println(errorFuture2.get());     // ERROR
    }

    private static CompletableFuture<String> getCompletableFuture(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message +"World";
        });
    }
}
