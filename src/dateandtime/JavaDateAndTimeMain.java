package dateandtime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class JavaDateAndTimeMain {
    public static void main(String[] args) {

        // 지금 이 순간을 기계시간 : UTC(GMT)
        Instant now = Instant.now();
        System.out.println("instant = " + now);

        // 내 로컬 기준으로 변환
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        System.out.println("zonedDateTime = " + zonedDateTime);


        // 인류용 일시
        LocalDateTime nowHuman = LocalDateTime.now();   // 로컬기준 Asia/Seoul
        System.out.println("nowHuman = " + nowHuman);
        LocalDateTime mykimBirthday = LocalDateTime.of(1987, Month.OCTOBER, 17,0,0,0);
        System.out.println("mykimBirthday = " + mykimBirthday);

        // 기간을 표현 - 휴먼
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2023,Month.OCTOBER, 17);

        System.out.println("today = " + today);
        System.out.println("thisYearBirthday = " + thisYearBirthday);

        Period period = Period.between(today, thisYearBirthday); // 날짜로 비교
        System.out.println("period.getDays() = " + period.getDays());
        System.out.println("period.get(ChronoUnit.DAYS) = " + period.get(ChronoUnit.DAYS));

        // 기간을 표현 - 기계
        Instant nowPlus10 = now.plus(10, ChronoUnit.SECONDS);
        Duration duration = Duration.between(now, nowPlus10);
        System.out.println("duration = " + duration.getSeconds());
        

        // 파싱 또는 포매팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        LocalDate date = LocalDate.parse("07/15/1982", formatter);
        System.out.println(date);
        System.out.println(today.format(formatter));
    }
}
