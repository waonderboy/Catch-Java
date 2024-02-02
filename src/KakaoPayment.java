import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class KakaoPayment implements Payment{
    /**
     * [static 사용이유]
     * static을 사용하면 클래스 변수가 된다.
     * INSTANCE, NAME, PAYMENTHISTORY 는 객체가 생성될때 마다 생성되지 않고 실행시 클래스가 로딩될때 한번만 메모리에 올리간다. -> 자바 메모리 구조 확인
     * ConcurrentHashMap : 여러요청이 동시에 진입할때 값이 덮어써지는 문제가 발생할 수 있는데 이를 막아준다 -> 동시성 문제
     */
    private static final KakaoPayment INSTANCE = new KakaoPayment();
    private static final String NAME = "KAKAO";
    private static ConcurrentHashMap<String, PayInfo> PAYMENTHISTORY = new ConcurrentHashMap<>();

    /**
     * 디자인 패턴, 싱글턴으로 작성 -> 싱글턴 검색
     * 사용자 요청이 많이 들어오면 동일한 기능을 하는 객체가 여러개 생성되어 메모리가 부족해질 수 있음 -> 자바 웹 프레임워크 스프링 컨테이너가 해당 개념을 사용
     */
    public static KakaoPayment getInstance() {
        return KakaoPayment.INSTANCE;
    }

    /**
     * 결제가능 여부 체크에 대한 '책임'은 결제 클래스에 있다! -> 객체지향 5대원칙 SOLID 중 (SRP) Single Responsibility Principal
     */
    @Override
    public boolean support(String name) {
        return NAME.equals(name);
    }

    /**
     * 결제 후 결제 내역에 넣는다
     */
    @Override
    public void pay(String requester, double payAmount) {
        System.out.println("카카오페이 ["  + payAmount + "원 ] 결제가 완료되었습니다.");
        PAYMENTHISTORY.put(getRequesterUniqueKey(requester), PayInfo.of(requester, payAmount));
    }

    @Override
    public void getPayHistory() {
        System.out.println("Show Mobile Pay");
    }

    /**
     * 결제내역을 구분하기 위함, 유니크한 값을 결제내역의 key 로 사용
     * - 이름_6자리랜덤값
     * ex) 차은우_afF2D1
     */
    private static String getRequesterUniqueKey(String requester) {
        return requester + "_" + UUID.randomUUID().toString().substring(0, 6);
    }
}
