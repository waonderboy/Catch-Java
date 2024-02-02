/**
 * 객체지향 4대 개념 - 상속, 다형성
 * 상속 - Naver, Kakao, Payco 등 다양한 간편결제 시스템들이 아래 메서드를 구현하여 사용될 수 있다.
 * 다형성 - Payment를 상속하기만해도 Naver, Kakao, Payco 와 같이 다양한 결제 시스템을 구현할 수 있다.
 */
public interface Payment {

    boolean support(String paymentName);
    void pay(String requester, double payAmount);
    void getPayHistory();
}
