## 🔥 목표 
  팀원들과 모각소 목표 설정 및 스프링 기초 공부
## 👩‍💻 활동
  스프링 핵심 원리(기본편) sec 1 ~ 3 완강 및 내용 정리
  
  ### 공부내용
 
<details>
<summary>sec1 객체 지향 설계와 스프링</summary>
 
### **스프링**

- **자바** 언어 기반의 프레임워크
- 좋은 **객체 지향** 애플리케이션 개발이 가능하도록 도움 (다형성을 극대화해서 이용하게 해줌)

### **좋은 객체 지향 프로그래밍이란**

- **객체 지향 프로그래밍** : 컴퓨터 프로그램을 객체들의 모임으로 봄 → 각각의 객체가 협력함
- **유연 + 변경 용이**
    
    = 컴포넌트를 쉽고 유연하게 변경 가능하면서 개발 가능
    
    = **다형성(polymorphism)**
    
    - **역할/구현**을 분리
        - 클라이언트는 대상(인터페이스)의 역할만 알면 됨
        - 클라이언트는 대상의 내부 구조 몰라도 됨 + 내부 구조 변경되어도 영향 X
        - 클라이언트는 대상 자체를 변경해도 영향 X
        - 확장 가능한 설계
        - 인터페이스를 안정적으로 잘 설계하는 것이 중요
    - 객체의 **협력**이라는 관계
        - 혼자 있는 객체는 없음
        - 클라이언트 - 요청, 서버 - 응답
        - 수많은 객체 클라이언트 ↔ 객체 서버가 서로 협력 관계
    
    ➡️ 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경
    
- **좋은 객체 지향 설계의 5가지 원칙 (SOLID)**
    - **SRP : 단일 책임 원칙**(single responsibility principle)
        - 한 클래스는 하나의 책임만 가짐
        - 변경 시 파급효과⬇️
    - **OCP : 개방-폐쇄 원칙**(open/closed principle)
        - 확장에는 open, 변경에는 close
    - **LSP : 리스코프 치환 원칙**(liskov substitution principle)
        - 객체는 프로그램의 정확성을 깨뜨리지 않고, 하위 타입의 인스턴스로 변경 가능
    - **ISP : 인터페이스 분리 원칙**(interface segregation principle)
        - 특정 클라이언트를 위한 인터페이스를 여러개로 둠
        - 인터페이스 명확, 대체가능성⬆️
    - **DIP : 의존관계 역전 원칙**(dependency inversion principle)
        - 추상화에 의존해야지, 구체화에 의존하면 안됨
            
            = 구현 클래스에 의존하지 말고, 인터페이스에 의존해야됨
            
            = 역할에 의존하게 해야됨 
            
    💡 다형성만으로 OCP, DIP 지킬 수 없음 → 뭔가 더 필요함

### **객체 지향 프로그래밍 & 스프링**

- 스프링은 다음 기술로 다형성 + OCP, DIP 지원
    - DI(Dependency Injection) : 의존관계, 의존성 주입
    - DI 컨테이너 제공
- 클라이언트 코드의 변경 없이 확장 가능
- 쉽게 부품 교체하듯 개발 가능
</details>

<details>
<summary>sec2 스프링 핵심 원리 이해1 - 예제 만들기</summary>
 
### 개발환경 세팅

- Java 11 설치
- IDE: IntelliJ 설치
- **스프링 부트 스타터 이용하여 스프링 프로젝트 생성**: [https://start.spring.io/](https://start.spring.io/)
    
    <aside>
    💡 프로젝트 환경설정을 편리하게 하려고 스프링 부트를 사용한 것 !!
    
    지금은 스프링 없는 순수한 자바로만 개발을 진행한다는 점을 꼭 기억하자! 
    
    스프링 관련은 한참 뒤에 등장한다.
    
    </aside>
  
    ![2-1](https://user-images.githubusercontent.com/96513157/213848473-fd468268-6aa7-4f12-bcb7-b33012bb046f.png)
    
    ![2-2](https://user-images.githubusercontent.com/96513157/213848471-c489ff0a-3421-4a4a-89ce-757535e3fbeb.png)
    
    intellij 이용하여 프로젝트 열기
    
    - 오류 발생→ 해결 : [https://velog.io/@ogu1208/Error-프로젝트-세팅-오류](https://velog.io/@ogu1208/Error-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%84%B8%ED%8C%85-%EC%98%A4%EB%A5%98)
    - 오류 발생 (jdk 버전) → 해결 : [https://velog.io/@persestitan/Spring-Cause-error-invalid-source-release-17-해결방법-IntelliJ](https://velog.io/@persestitan/Spring-Cause-error-invalid-source-release-17-%ED%95%B4%EA%B2%B0%EB%B0%A9%EB%B2%95-IntelliJ)
    
- **동작 확인**
    
    : 기본 메인 클래스 실행( CoreApplication.main() )
    
    ![2-3](https://user-images.githubusercontent.com/96513157/213848469-ba3e1d5d-17e5-4560-9043-a8edcc68e8f9.png)
    
    - **IntelliJ Gradle 대신에 자바 직접 실행**
        - 최근 IntelliJ 버전은 Gradle을 통해서 실행 하는 것이 기본 설정.
        - 이렇게 하면 실행속도가 느림 → 다음과 같이 변경하면 자바로 바로 실행해서 실행속도⬆️
            
            Preferences>Gradle 검색
            - Build and run using: Gradle IntelliJ IDEA
            - Run tests using: Gradle IntelliJ IDEA
            

### **요구사항 명세서**

1. 회원
    - 회원을 가입하고 조회할 수 있다.
    - 회원은 일반과 VIP 두 가지 등급이 있다.
    - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
2. 주문과 할인 정책
    - 회원은 상품을 주문할 수 있다.
    - 회원 등급에 따라 할인 정책을 적용할 수 있다.
    - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수
    있다.)
    - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)

### **회원 도메인 설계 및 개발**

![회원 클래스 다이어그램](https://user-images.githubusercontent.com/96513157/213848467-eeb75476-bba4-45ad-83cf-c4f3006a6130.png)

**회원 도메인 클래스 다이어그램**

1. **MemberService**
    
    ```java
    public interface MemberService  {
        void join(Member member);
        Member findMember(Long memberId);
    }
    ```
    
    ```java
    public class MemberServiceImpl implements MemberService{
        private final MemberRepository memberRepository = = new MemoryMemberRepository();
      
        @Override
        public void join(Member member) {
            memberRepository.save(member);
        }
    
        @Override
        public Member findMember(Long memberId) {
            return memberRepository.findById(memberId);
        }
    }
    ```
    
2. **MemberRepository**
    
    ```java
    public interface MemberRepository {
        void save(Member member);
    
        Member findById(Long memberId);
    }
    ```
    
    ```java
    public class MemoryMemberRepository implements MemberRepository{
        private static Map<Long, Member> store = new HashMap<>();
    
        @Override
        public void save(Member member) {
            store.put(member.getId(), member);
        }
    
        @Override
        public Member findById(Long memberId) {
            return store.get(memberId);
        }
    }
    ```
    

### **주문 도메인 설계 및 개발**

![2-5](https://user-images.githubusercontent.com/96513157/213848464-0f8b574d-ce9b-48c8-8958-1fb72fb09f5d.png)

**주문 클래스 다이어그램**

1. **order**
    
    ```java
    public interface OrderService  {
        Order createOrder(Long memberId, String itemName, int itemPrice);
    }
    ```
    
    ```java
    public class  OrderServiceImpl implements OrderService{
        private final MemberRepository memberRepository = new MemoryMemberRepository();
        private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    		
    		@Override
        public Order createOrder(Long memberId, String itemName, int itemPrice) {
            Member member = memberRepository.findById(memberId);
            int discountPrice = discountPolicy.discount(member,itemPrice);
            return new Order(memberId, itemName, itemPrice, discountPrice);
        }
    		
    		//Getter, Setter
    }
    ```
    
2. **discount**
</details>


<details>
<summary>sec3 스프링 핵심 원리 이해2 - 객체 지향 원리 적용</summary>
 
### 새로운 할인정책 개발

```java
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price*discountPercent/100;
        } else {
            return 0;
        }
    }
}
```

- **할인정책 변경**
    
    ![3-1](https://user-images.githubusercontent.com/96513157/213848770-3e5bbe1e-2850-4d0d-9a50-02894cc32b29.png)
    
    할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 수정해야 함
    
    - **문제점**
        - 우리는 역할과 구현을 충실하게 분리함. OK
        - 다형성도 활용하고, 인터페이스와 구현 객체를 분리함. OK
        - OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수함
            
            ➡️ 그렇게 보이지만 사실 X.
            
        - **DIP**: 주문서비스 클라이언트(`OrderServiceImpl`)는 `DiscountPolicy`인터페이스에 의존하면서 DIP를 지킨 것 같은데 ?
            
            ➡️ 클래스 의존관계를 분석해보면, 추상(인터페이스) 뿐만 아니라 **구체(구현) 클래스에도 의존**하고
            있다.
            
            - **추상(인터페이스) 의존**: `DiscountPolicy`
            - **구체(구현) 클래스**: `FixDiscountPolicy`, `RateDiscountPolicy`
            
            ![3-2](https://user-images.githubusercontent.com/96513157/213848769-186c1b23-21a0-4f3f-813c-b816180f7305.png)
            
        - **OCP**: 변경하지 않고 확장할 수 있다고 했는데 ?
            
            ➡️ 지금 코드는 기능을 확장해서 변경하면, **클라이언트 코드에 영향**을 줌 → OCP 위반
            
            ![3-3](https://user-images.githubusercontent.com/96513157/213848767-223ede25-5594-4271-8222-a2243118c68f.png)
            
            정책 변경
            
    - **문제 해결**
        
        : 클라이언트 코드인 `OrderServiceImpl`은 `DiscountPolicy`의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존함**(DIP 위반)**→ 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야 함**(OCP 위반)**
        
        ➡️ DIP를 위반하지 않도록 **인터페이스(추상)에만 의존**하도록 의존관계를 변경하면 됨
        

### **관심사 분리**

- **애플리케이션**을 하나의 **공연**이라 생각하고, 각각의 **인터페이스**를 **배역**(배우 역할)이라 생각하자.
- 실제 배역 맞는 배우를 선택하는 것은 누가 하는가?
    - <로미오와 줄리엣> 공연을 하면, 로미오 역할과 줄리엣 역할을 누가 할지는 배우들이 정하는게 아님
    - 이전 코드는 마치 로미오 역할(인터페이스)을 하는 **레오나르도 디카프리오**(구현체, 배우)가 줄리엣
    역할(인터페이스)을 하는 **여자 주인공**(구현체, 배우)을 **직접 초빙**하는 것과 같음
    - 디카프리오는 공연도 해야하고 동시에 여자 주인공도 공연에 직접 초빙해야 하는 **다양한 책임**을 가지고 있다.
- **관심사 분리하자**
    - 배우는 본인의 역할인 배역을 수행하는 것에만 집중해야 함
    - 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 함
    - 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 **별도의 공연
    기획자**가 필요함
    
    ➡️ 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리하자 !
    
- **AppConfig 등장**
    - 애플리케이션의 전체 동작 방식을 구성(config)하기 위함
    - **구현 객체를 생성**하고, **연결**하는 책임을 가지는 별도의 설정 클래스
        
        ```java
        public class AppConfig {
            public MemberService memberService(){
                return new MemberServiceImpl(memberRepository());
            }
        
            private MemoryMemberRepository memberRepository() {
                return new MemoryMemberRepository();
            }
        
            public OrderService orderService(){
                return new OrderServiceImpl(memberRepository(), discountPolicy());
            }
        
            private DiscountPolicy discountPolicy() {
        			//return new FixDiscountPolicy();
                return new RateDiscountPolicy(); //할인 정책 변경 시 이 부분만 수정하면 됨
            }
        }
        ```
        
    - AppConfig는 애플리케이션의 실제 동작에 필요한 **구현 객체를 생성함**
    ➡️ `MemberServiceImpl`, `MemoryMemberRepository`, `OrderServiceImpl`, `FixDiscountPolicy`
    - AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 **생성자를 통해서 주입(연결)**함
        - `MemberServiceImpl` ➡️ `MemoryMemberRepository`
        - `OrderServiceImpl` ➡️ `MemoryMemberRepository`, `FixDiscountPolicy`
        
        <aside>
        💡 각 클래스에 생성자를 만들어야 함
        
        예) `MemberServiceImpl`
        
        ```java
        public class MemberServiceImpl implements MemberService {
            private final MemberRepository memberRepository;
            public MemberServiceImpl(MemberRepository memberRepository) {
                this.memberRepository = memberRepository;
        		}
        		//...
        }
        ```
        
        </aside>
        
    
    - AppConfig의 등장으로 애플리케이션이 크게 **사용 영역**과, **객체를 생성하고 구성(Configuration)하는 영역**으로 분리됨
        
        ![3-4](https://user-images.githubusercontent.com/96513157/213848766-2abc9278-0bd0-4134-902f-dc5b2ecf4d39.png)
        
    
    - `FixDiscountPolicy` ➡️ `RateDiscountPolicy` 로 변경해도 구성 영역만 영향을 받고, 사용 영역은 전혀 영향을 받지 않음
        
        ![3-5](https://user-images.githubusercontent.com/96513157/213848765-237d634e-4356-4452-b70d-461755a00425.png)
        

### 전체 흐름 정리

1. **새로운 할인 정책 개발**
    
     - 다형성 덕분에 새로운 정률 할인 정책 코드를 추가로 개발하는 것 자체는 아무 문제가 없음
    
2. **새로운 할인 정책 적용과 문제점**
    
     - 새로 개발한 정률 할인 정책을 적용하려면 → **클라이언트 코드**인 주문 서비스 구현체도 함께 변경해야함
    
     - 주문 서비스 클라이언트가 인터페이스인 `DiscountPolicy`뿐만 아니라, 구체 클래스인 `FixDiscountPolicy`도 함께 의존 
    
    ➡️ **DIP 위반**
    
3. **관심사의 분리**
    - 애플리케이션을 하나의 공연으로 생각
    - 기존에는 클라이언트가 의존하는 서버 구현 객체를 직접 생성+실행함
        
        → 비유를 하면 기존에는 남자 주인공 배우가 공연도 하고, 동시에 여자 주인공도 직접 초빙하는 다양한 책임을 가지고 있음
        
    - 공연을 구성하고, 담당 배우를 섭외하고, 지정하는 책임을 담당하는 별도의 **공연 기획자** 필요
    → 공연 기획자인 **AppConfig**가 등장
    - AppConfig는 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, **구현 객체를 생성+연결**하는 책임
    - 이제부터 클라이언트 객체는 자신의 역할을 실행하는 것만 집중 & 권한⬇️(=책임이 명확해짐)
4. **새로운 구조와 할인 정책 적용**
    - 정액 할인 정책 ➡️ 정률(%) 할인 정책으로 변경
    - AppConfig의 등장으로 애플리케이션이 크게 **사용 영역**과, 객체를 생성하고 **구성(Configuration)하는 영역**으로 분리
    - 할인 정책을 변경해도 AppConfig가 있는 구성 영역만 변경하면 됨 & 사용 영역은 변경할 필요가 없음 & 클라이언트 코드인 주문 서비스 코드도 변경하지 않음

### **좋은 객체 지향 설계의 5가지 원칙의 적용**

1. **SRP : 단일 책임 원칙**
    
    > **단일 책임 원칙한 클래스는 하나의 책임만 가져야 한다.**
    > 
    - **클라이언트 객체**는 직접 구현 객체를 생성하고, 연결하고, 실행하는 다양한 책임을 가지고 있음
    - SRP 단일 책임 원칙을 따르면서 관심사를 분리함
    - 구현 객체를 **생성**하고 **연결**하는 책임은 **AppConfig**가 담당
    - **클라이언트 객체**는 **실행**하는 책임만 담당
2. **DIP : 의존관계 역전 원칙**
    
    > **프로그래머는 “추상화에 의존해야지, 구체화에 의존하면 안된다.”**
    > 
    > 
    > **의존성 주입은 이 원칙을 따르는 방법 중 하나다.**
    > 
    - 기존 클라이언트 코드(`OrderServiceImpl`)는 DIP를 지키며 `DiscountPolicy` **추상화 인터페이스**에 의존하는 것 같았지만, `FixDiscountPolicy` **구체화 구현 클래스**에도 함께 의존함
        
        ➡️ 새로운 할인 정책을 적용하려면, 클라이언트 코드도 함께 변경해야 함
        
    - 클라이언트 코드가 `DiscountPolicy` 추상화 인터페이스에만 의존하도록 코드를 변경
    - But 클라이언트 코드는 인터페이스만으로는 아무것도 실행할 수 없음
    - **AppConfig**가 `FixDiscountPolicy` 객체 인스턴스 생성(클라이언트 코드를 대신하여) → 클라이언트 코드에 의존관계를 주입
    - DIP 원칙을 따르면서 문제 해결
3. **OCP : 개방-폐쇄 원칙**
    
    > **소프트웨어 요소는 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다.**
    > 
    - 애플리케이션을 **사용 영역**과 **구성 영역**으로 나눔
    - **AppConfig**가 의존관계를 `FixDiscountPolicy` ➡️ `RateDiscountPolicy`로 변경해서 클라이언트 코드에 주입하므로, 클라이언트 코드는 변경하지 않아도 됨
    - 소프트웨어 요소를 새롭게 확장해도, 사용 영역의 변경은 닫혀 있다!

### **IoC, DI, 컨테이너**

- **IoC(Inversion of Control) : 제어의 역전**
    - 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했음 = 한마디로 구현 객체가 프로그램의 제어 흐름을 스스로 조종함
    - **<AppConfig 등장 이후>** 구현 객체는 자신의 로직을 실행하는 역할만 담당함
    - 프로그램의 제어 흐름은 이제 **AppConfig**가 가져감(=프로그램에 대한 제어 흐름에 대한 권한은 모두 **AppConfig**가 가짐)
        
        ex) `OrderServiceImpl`은 필요한 인터페이스들을 호출하지만 어떤 구현 객체들이 실행될지 모름
        
        **AppConfig**는 `OrderServiceImpl`이 아닌, `OrderService` 인터페이스의 다른 구현 객체를 생성하고 실행할 수도 있음
        
         But 그런 사실도 모른체 `OrderServiceImpl`은 묵묵히 자신의 로직을 실행할 뿐
        
    - 프로그램의 제어 흐름을 직접 제어하는 것이 아니라, 외부에서 관리하는 것 = 제어의 역전(IoC)
    
    <aside>
    💡 **프레임워크 vs 라이브러리**
    
    - 프레임워크가 내가 작성한 코드를 제어하고, 대신 실행 → **프레임워크** 맞음 (JUnit)
    - 내가 작성한 코드가 직접 제어의 흐름을 담당 → 그것은 프레임워크가 아니라 **라이브러리**
    </aside>
    

- **DI(Dependency Injection) : 의존관계 주입**
    - `OrderServiceImpl`은 `DiscountPolicy` 인터페이스에 의존 + 실제 어떤 구현 객체가 사용될지는 모름
    - **의존관계**는 정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계 ****둘을
    분리해서 생각해야 함
    - **정적인 클래스 의존관계**
    `OrderServiceImpl`은 `MemberRepository`, `DiscountPolicy`에 의존함
    But 이러한 클래스 의존관계만으로는 실제 어떤 객체가 `OrderServiceImpl`에 주입 될지 알 수 없음
        
        ![3-6](https://user-images.githubusercontent.com/96513157/213848764-53e53159-81f7-453a-b502-e29daa6340da.png)
        
    - **동적인 객체 인스턴스 의존 관계**
        - **의존관계 주입(DI)** : 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고, 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것
        - 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결됨
        - **의존관계 주입(DI)**을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경 가능
            
            (= 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 가능)
            
        
        ![3-7](https://user-images.githubusercontent.com/96513157/213848762-6012c282-18d6-48d2-8ed6-a6265273f324.png)
        
- **IoC 컨테이너 또는 DI 컨테이너**
    - **AppConfig**처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것
    - **의존관계 주입**에 초점을 맞추어 최근에는 주로 DI 컨테이너라고 부름
        
        + 어샘블러, 오브젝트 팩토리 등으로도 부름
        

### **스프링으로 전환하기**

- **AppConfig**
    - 설정을 구성한다는 뜻의 **@Configuration**을 붙임
    - 각 메서드에 **@Bean**을 붙임 ➡️ 스프링 컨테이너에 스프링 빈으로 등록
    
    ```java
    @Configuration
    public class AppConfig {
        @Bean
        public MemberService memberService(){
            return new MemberServiceImpl(memberRepository());
        }
    	//...
    }
    ```
    
- **MemberApp & OrderApp에 스프링 컨테이너 적용**
    
    ```java
    public class OrderApp {
        public static void main(String[] args) {
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
            MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
            OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
    				//...
    	}
    }
    ```
    
    - `ApplicationContext` = **스프링 컨테이너**
    - 기존에는 개발자가 **AppConfig**를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 **스프링
    컨테이너**를 통해서 사용
    - **스프링 컨테이너**는 **@Configuration**이 붙은 **AppConfig**를 설정(구성) 정보로 사용
    - **@Bean**이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록
        
         ➡️ 스프링 컨테이너에 등록된 객체 = **스프링 빈**
        
    - 스프링 빈의 이름 = **@Bean**이 붙은 메서드의 명
        
        ex) `memberService`, `orderService`
        
    - 이전에는 개발자가 필요한 객체를 **AppConfig**를 사용해서 직접 조회했지만, 이제부터는 **스프링
    컨테이너**를 통해서 필요한 **스프링 빈(객체)**를 찾음 (`applicationContext.getBean()`메서드 사용해서)
</details>


## 🏆 소감 
  스프링으로 개발 전, 기본적인 객체지향 개념을 머릿속에 재정리하고 스프링의 기초 문법을 배울 수 있었다. 이를 바탕으로 다음 주차 모임부터는 게시판 만들기 프로젝트와 동시에 동영상 수강을 병행할 수 있게 되었다.
 
