/**
 * 객체지향 4대 원리 - 캡슐화
 * 캡슐화 - requester, payAmount 변수에 직접적인 접근을 제한하고 필요한 부분만 외부로 사용할 수 있도록 한다
 */
public class PayInfo {
    /**
     * final 사용이유 - 결제 정보는 한번 결제되고 변경되면 안되기 때문에 final로 제한한다.
     */
    private final String requester;
    private final double payAmount;

    private PayInfo(String requester, double payAmount) {
        this.requester = requester;
        this.payAmount = payAmount;
    }

    /**
     * 디자인 패턴, 정적 팩터리 메서드 패턴으로 작성 -> 정적 팩터리 메서드  검색
     * 단순 생성자로 쓰는 것 보다 객체 생성의 의도를 코드에서 명확히 보여줄 수 있다. requester, payAmount와 같은 파라미터를 꼭 받게 할 수 있다.
     */
    public static PayInfo of(String requester, double payAmount) {
        return new PayInfo(requester, payAmount);
    }

    /**
     * 캡슐화의 일부
     */
    public String getRequester() {
        return requester;
    }

    /**
     * 캡슐화의 일부
     */
    public double getPayAmount() {
        return payAmount;
    }

    public String toString() {
        return "요청자 : [ " + requester + " ] / " + "결제금액 : [ " + payAmount + " ]";
    }
}
