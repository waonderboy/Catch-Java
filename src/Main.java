import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Payment> paymentSystemList  = new ArrayList();

    public static void main(String[] args) {
        /**
         * 결제시스템 초기화 작업, 간편 결제 가능한 클래스 객체를 생성하여 결제가능 리스트에 넣는다
         */
        initPaymentSystem();
        Scanner scanner = new Scanner(System.in);

        /**
         * 결제 요청 받는 부분
         */
        System.out.println("결제 요청자 이름을 입력해주세요 !");
        String requester = scanner.next();
        System.out.println("결제 금액을 입력해주세요 !");
        double payAmount = scanner.nextDouble();
        System.out.println("간편 결제 시스템명을 알려주세요 !");
        String paymentName = scanner.next();

        /**
         * 결제 요청자가 원하는 간편 결제 시스템을 가져옴
         * 결제 후 결제 이력 출력
         */
        Payment paymentSystem = getPaymentSystem(paymentName);
        paymentProcess(paymentSystem, requester, payAmount);
        paymentSystem.getPayHistory();
    }

    /**
     * 서버가 뜰 때 필요 서비스에서 지원하는 간편결제 시스템을 생성 후 메모리에 올려놓는다.
     * 디자인패턴 싱글턴 패턴으로 생성되기 때문에 '단 하나의 객체'만 생성된다.
     * 사용자 요청이 들어오면 '단 하나의 객체'만 사용하여 결제하게된다.
     * 아래처럼 개발시 결제시스템이 추가될때 아래 부분 코드만 수정하면된다.
     */
    private static void initPaymentSystem() {
        // NAVER, KAKAO, PAYCO
        // 추가될때 마다 해당 코드만 수정
        paymentSystemList.add(KakaoPayment.getInstance());
        paymentSystemList.add(NaverPayment.getInstance());
        paymentSystemList.add(PaycoPayment.getInstance());
    }

    /**
     * 객체지향 4대원리 추상화
     * 추상화 - Payment가 어떻게 구현된지 몰라도 결제를 진행할 수 있다. 해당 메서드 입장에서는 알 필요도 없다
     *
     * DI (Dependency Injection) 개념 적용 -> 의존성 주입
     * paymentProcess라는 메서드는 Payment를 상속한 객체 (NaverPay, KakaoPay, PaycoPay) 어느 것이든 들어와도 상관이 없다.
     * paymentProcess는 특정한 구현체에 의존하는 것이 아닌, 주입 받는 파라미터(인자)에게 의존한다. 때문에 의존성 주입이라 한다.
     */
    private static void paymentProcess(Payment payment, String requester, double payAmount) {
        if (payment == null) return;
        payment.pay(requester, payAmount);
    }

    /**
     * 요청 결제를 찾아 결제를 진행한다.
     */
    private static Payment getPaymentSystem(String paymentName) {
        return paymentSystemList.stream()
                .filter(Payment -> Payment.support(paymentName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원 결제가 없음"));
    }
}