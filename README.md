# Java 8 Study 

## Section 1. 함수형 인터페이스와 람다
### (1) 함수형 인터페이스와 람다 표현식
- 함수형 인터페이스 (Functional Interface)
  - 추상 메소드를 딱 하나만 가지고 있는 인터페이스
  - SAM(Single Abstract Method) 인터페이스
  - **@FuncationInterface** 애노테이션을 가지고 있는 인터페이스

- 람다 표현식 (Lambda Expressions)
  - 함수형 인터페이스의 인스턴스를 만드는 방법으로 쓰일 수 있다.
  - 코드를 줄일 수 있다.
  - 메소드 매개변수, 리턴 타입, 변수로 만들어 사용할 수도 있다.

- 자바에서 함수형 프로그래밍
  - 함수를 First class object로 사용할 수 있다.
  - 순수 함수 (Pure function)
    - 사이드 이팩트가 없다. (함수 밖에 있는 값을 변경하지 않는다.)
    - 상태가 없다. (함수 밖에 있는 값을 사용하지 않는다.)
  - 고차 함수 (Higher-Order Function)
    - 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
  - 불변성

### (2) 자바에서 제공하는 함수형 인터페이스
- java.lang.funcation 패키지
- Function<T, R> : T타입을 받아서 R타입을 리턴하는 함수 인터페이스
- BiFunction<T, U, R> : 두개의 값(T, U)를 받아서 R타입을 리턴하는 함수 인터페이스
- Consumer<T> : T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
- Supplier<T> : T 타입의 값을 제공하는 함수 인터페이스
- Predicate<T> : T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
- UnaryOperator<T> : Function<T, R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스
- BinaryOperator<T> : BiFunction<T, U, R>의 특수한 형태로, 동일한 타입의 입력 값 두개를 받아 리턴하는 함수 인터페이스

### (3) 람다 표현식
- 람다 : (인자 리스트) -> {바디}
- 인자 리스트
  - 인자가 없을 때 : ()
  - 인자가 한개일 때 : (one) 또는 one
  - 인자가 여러개 일 때 : (one, two)
  - 인자의 타입은 생략 가능, 컴파일러가 추론(infer)하지만 명시할 수도 있다. (Integer one, Integer two)
- 바디
  - 화살표 오른쪽에 함수 본문을 정의한다.
  - 여러 줄인 경우에 {}를 사용해서 묶는다.
  - 한 줄인 경우에 생략 가능, return도 생략 가능.
- 변수 캡처 (Variable Capture)
  - 로컬변수캡처
    - final이거나 effective final 인 경우에만 참조할 수 있다.
    - 그렇지 않을 경우 concurrency(동시성) 문제가 생길 수 있어서 컴파일러가 방지한다.
  - effective final
    - 이것도 역시 자바 8부터 지원하는 기능으로 “사실상" final인 변수.
    - final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수있다.
  - 익명 클래스 구현체와 달리 ‘쉐도윙’하지 않는다.
    - 익명 클래스, 로컬 클래스는 새로 스콥을 만들지만, 람다는 람다를 감싸고 있는 스콥과 같다.

### (4) 메소드 레퍼런스
- 람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.
- 메소드 참조하는 방법
  - 스태틱 메소드 참조	         =>  타입::스태틱 메소드
  - 특정 객체의 인스턴스 메소드 참조	 =>  객체 레퍼런스::인스턴스 메소드
  - 임의 객체의 인스턴스 메소드 참조	 =>  타입::인스턴스 메소드
  - 생성자 참조                 =>  타입::new


## Section 2. 인터페이스의 변화
### (1) 인터페이스 기본 메소드와 스태틱 메소드
- 기본 메소드 (Default Methods)
  - 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
  - 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
  - **기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.**
    - **컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.**
    - **반드시 문서화 할 것. (@implSpec 자바독 태그 사용)**
  - Object가 제공하는 기능 (equals, hasCode)는 기본 메소드로 제공할 수 없다.
    - 구현체가 재정의해야 한다.
  - 본인이 수정할 수 있는 인터페이스에만 기본 메소드를 제공할 수 있다.
  - 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
  - 인터페이스 구현체가 재정의 할 수도 있다.
- 스태틱 메소드(Static Methods)
  - 해당 타입 관련 헬터 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 제공할 수 있다.

### (2) 자바 8 API의 기본 메소드와 스태틱 메소드
- Iterable의 기본 메소드
  - forEach()
  - spliterator()
- Collection의 기본 메소드
  - stream() / parallelStream()
  - removeIf(Predicate)
  - spliterator()
- Comparator의 기본 메소드 및 스태틱 메소드
  - reversed()
  - thenComparing()
  - static reverseOrder() / naturalOrder()
  - static nullsFirst() / nullsLast()
  - static comparing()


## Section 3. Stream
### (1) Stream
- Stream
  - **sequence of elements** supporting sequential and parallel aggregate operations
  - 어떤 연속된 데이터를 처리하는 operation의 모음
  - 데이터를 담고 있는 저장소(컬렉션)가 아니다.
  - `Funtional in nature` : 스트림이 처리하는 **데이터 소스를 변경하지 않는다.**
  - 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
  - 무제한일 수도 있다.(Short Circuit 메소드를 사용해서 제한할 수 있다.)
  - 중개 오퍼레이션은 근본적으로 lazy 하다.
  - 손쉽게 병렬처리 할 수 있다. `.parellelStream()`
- 스트림 파이프라인
  - 0 또는 **다수의 중개 오퍼레이션 (intermediate operation)과 한개의 종료 오퍼레이션(terminal operation)으로 구성**한다.
  - 스트림의 데이터 소스는 오직 터미널 오퍼레이션을 실행할 때에만 처리한다.
- 중개 오퍼레이션
  - **Stream을 리턴 한다.**
  - Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다.
      - 대부분은 Stateless지만 distinct나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.
  - filter, map, limit, skip, sorted, ...
- 종료 오퍼레이션
  - **Stream을 리턴하지 않는다. : 다른 타입을 리턴**
  - collect, allMatch, count, forEach, min, max, ...

### (2) Java Stream API
- 걸러내기 : Filter(Predicate)
- 변경하기 : Map(Function) or FlatMap(Function)
- 생성하기 : generate(Supplier) or Iterate(T seed, UnaryOperator)
- 제한하기 : limit(long) or skip(long)
- 스트림에 있는 데이터가 특정 조건을 만족하는지 확인 : anyMatch(), allMatch(), nonMatch()
- 개수 세기 : count()
- 스트림을 데이터 하나로 뭉치기 : reduce(identity, BiFunction), collect(), sum(), max()


## Section 4. Optional
### (1) Optional
- Optional
  - **오직 값 한개**가 들어있을 수도 없을 수도 있는 컨네이너
- 주의사항
  - **리턴 값으로만 쓰기를 권장**한다.
    - 메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자
  - Optional을 리턴하는 메소드에서 **null을 리턴하지 말자.**
  - 프리미티브 타입용 Optional을 따로 있다.
    - OptionalInt, OptionalLong,...
  - Collection, Map, Stream Array, Optional은 Optional로 감싸지 말 것.

### (2) Java Optional API
- Optional 만들기
  - Optional.of()
  - Optional.ofNullable()
  - Optional.empty()
- Optional에 값이 있는지 없는지 확인하기
  - isPresent()
  - isEmpty() (Java 11부터 제공)
- Optional에 있는 값 가져오기
  - get()
  - 만약에 비어있는 Optional에서 무언가를 꺼낸다면? : `NoSuchElementException` 발생
- Optional에 값이 **있는 경우에 그 값을 가지고 ~~를 하라.**
  - ifPresent(Consumer)
- Optional에 값이 있으면 가져오고 **없는 경우에 ~~를 리턴하라.**
  - orElse(T), 있던 없든 무조건 실행
- Optional에 값이 있으면 가져오고 **없는 경우에 ~~를 하라.**
  - orElseGet(Supplier)ㅡ 없을 때만 실행
- Optional에 값이 있으면 가져오고 **없는 경우 에러를 던져라.**
  - orElseThrow()
- Optional에 들어있는 값 걸러내기
  - Optional filter(Predicate)
- Optional에 들어있는 값 변환하기
  - Optional map(Function)
  - Optional flatMap(Function)
    

## Section 5. Date & Time
- 시간표시 : 기계용
  - Instant.now(): 현재 UTC (GMT)를 리턴
  - ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault()); : timeZone 변경
- 시간표시 : 인류용
  - LocalDateTime.now(): 현재 시스템 Zone에 해당하는(로컬) 일시를 리턴한다.
  - LocalDateTime.of(int, Month, int, int, int, int): 로컬의 특정 일시를 리턴한다.
  - ZonedDateTime.of(int, Month, int, int, int, int, ZoneId): 특정 Zone의 특정 일시를 리턴한다.
- 기간을 표현
  - Period / Duration . between()
- 파싱 또는 포매팅
  - DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy"); : 포매터
  - LocalDate date = LocalDateTime.parse(String, DateTimeFormatter); : 파싱
  - LocalDate now = LocalDate.now().format(formatter); : 포매팅


## Section 6. CompletableFuture
### (1) 자바 Concurrent 프로그래밍
- Concurrent 소프트웨어
  - **동시에 여러작업**을 할 수 있는 소프트웨어
  - 예) 웹브라우저로 유튜브를 보면서 키보드로 문서에 타이핑을 할 수 있다.
  - 예) 녹화를 하면서 인텔리J로 코딩을 하고 워드에 적어둔 문서를 보거나 수정 할수 있다.
- 자바에서 지원하는 Concurrent 프로그래밍
  - 멀티 프로세싱(ProcessBuilder)
  - 멀티 쓰레드
- 자바 멀티쓰레드 프로그래밍
  - Thread / Runnable

### (2) Executors
- 고수준 (High-Level) Concurrency 프로그래밍
  - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리.
  - 그런 기능을 Executors에게 위임.
- Executors가 하는 일
  - 쓰레드 만들기 : 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.
  - 쓰레드 관리 : 쓰레드 생명 주기를 관리한다.
  - 작업처리 및 실행 : 쓰레드로 실행 할 작업을 제공 할 수있는 API를 제공한다.
- 주요 인터페이스
  - Executor
    - execute(Runnable)
  - ExecutorService
    - Executor 상속 받은 인터페이스로, Callable도 **실행**할 수 있으며, Executor를 **종료** 시키거나, **여러 Callable을 동시에 실행**하는 등의 기능을 제공한다.
  - ScheduledExecutorService
    - ExecutorService를 상속 받은 인터페이스로 **특정 시간 이후에 또는 주기적으로 작업**을 실행할 수 있다.

### (3) Callable과 Future
- Callable
  - Runnable과 유사하지만 작업의 결과를 받을 수 있다.
- Future
  - 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수 있다.
  - 결과를 가져오기 : get()
  - 작업 상태 확인하기 : isDone()
  - 작업 취소하기 : cancel()
  - 여러 작업 동시에 실행하기 : invokeAll()
  - 여러 작업 중에 하나라도 먼저 응답이 오면 끝내기 : invokeAny()

### (4) CompletableFuture
- CompletableFuture (Implements Future, CompletionStage)
  - 자바에서 비동기(Asynchronous) 프로그래밍을 가능하게하는 인터페이스
  - Future를 사용해서도 어느정도 가능했지만 하기 힘들 일들이 많음
- Future로는 하기 어렵던 작업들
  - Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
  - 블로킹 코드(`get()`)를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
  - 여러 Future를 조합할 수 없다.
    - 예) Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기
  - 예외 처리용 API를 제공하지 않는다.
- 비동기로 작업 실행하기
  - 리턴값이 없는 경우 : runAsync()
  - 리턴값이 있는 경우 : supplyAsync()
  - 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다.
    - 기본은 `ForkJoinPool.commonPool()`
- 콜백 제공하기
  - thenApply(Function): 리턴 값을 받아서 다른 값으로 바꾸는 콜백
  - thenAccept(Consumer): 리턴 값을 또 다른 작업을 처리하는 콜백(리턴없이)
  - thenRun(Runnable): 리턴 값 받지 다른 작업을 처리하는 콜백
  - 콜백 자체를 또 다른 쓰레드에서 실행 할 수 있다.
- 조합하기
  - thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
  - thenCombine(): 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행
  - allOf() : 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
  - anyOf() : 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행
- 예외처리
  - exceptionally(Function)
  - handle(BiFunction)

## Section 7. etc


