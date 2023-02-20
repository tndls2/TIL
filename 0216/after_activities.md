## 🔥 목표 
  스프링 기초 공부
## 👩‍💻 활동
  스프링 입문을 위한 자바 객체지향의 원리와 이해(김종민) 도서 완독 + 게시판 구축
  ### 공부내용
  * 스프링 입문을 위한 자바 객체지향의 원리와 이해(김종민) 도서 내용 정리
  
  <details>
  <summary>스프링 기초 개념 재정리</summary>
  # 자바 객체지향의 원리와 이해

### 03 자바와 객체지향

- **클래스**(분류에 대한 개념) : 객체(실체) = 펭귄 : 뽀로로 = 사람 : 김연아
    - 클래스 class : 분류. 집합. 같은 속성과 기능을 가진 객체를 총칭하는 개념
    - 객체 object = 클래스의 인스턴스 instance: 세상에 존재하는 유일무이한 사물
    - 설계
        1. 클래스(= 정적) 멤버 `static` - (1) 클래스 멤버 속성 (2) 클래스 멤버 메서드
        2. 객체(= 인스턴스) 멤버 - (1) 객체 멤버 속성 (2) 객체 멤버 메서드
- **변수 유형**
    
    
    | static 변수 | 클래스 [멤버] 속성, 정적 변수, 정적 속성 … | 스태틱 영역 (T메모리) |
    | --- | --- | --- |
    | 인스턴스 변수 | 객체 [멤버] 속성, 객체 변수 … | 힙 영역 (T메모리) |
    | local 변수 | 지역 변수 | 스택 영역 (스택 프레임 내부) |
    
    ![Untitled 1](https://user-images.githubusercontent.com/96513157/220033568-6ab52009-93e2-4799-a9bc-e4ce2fb8967b.png)

- **인터페이스** : be able to (무엇을 할 수 있는)
    - 인터페이스는 클래스가 ‘무엇을 할 수 있다’라고 하는 기능을 구현하도록 강제함
    - public 추상 메서드 & public 정적 상수만 가질 수 있음
    - 상위 클래스는 하위 클래스에게 특성(속성, 메서드)을 상속함
- **객체지향의 4대 특성**
    1. 캡 - **캡슐화 Encapsulation** : 정보 은닉
        - 접근 제어자
            
            (1) private 
            
            (2) [default] - 같은 패키지 내의 클래스에서 접근 가능
            
            (3) protected - 상속 / 같은 패키지 내의 클래스에서 접근 가능
            
            (4) public
            
        - 접근자 / 설정자 메서드
    2. 상 - **상속 extends** : 재사용 + 확장
        - 분류도 O (조직도, 계층도 X)
        - 슈퍼(상위) 클래스 - 서브(하위) 클래스
            
            →추상화, 일반화     → 구체화, 특수화
            
        - 하위 클래스는 상위 클래스다 (고래는 포유류다. 고래는 동물이다.)
        - 하위 클래스 is a kind of 상위 클래스
    3. 추 - **추상화 Abstraction** : 모델링 = 구체적인 것을 분해해서 관심 영역에 대한 특성만 가지고 재조합하는 것
        - 클래스 설계에 추상화가 사용됨
        - 관심 영역 = 애플리케이션 경계 Application Boundary
        - 추상화의 결과 → 클래스
        - 자바는 `class`키워드를 통해 지원하고 있음
            
             - `클래스 객체_참조_변수 = new 클래스();`
            
             - 새로운 객체를 하나 생성해 그 객체의 주소값(포인터)를 객체 참조 변수에 할당
            
             - 클래스명 → 분류스럽게 / 객체 참조 변수명 → 유일무이한 사물처럼(객체스러벡) 작명
            
    4. 다 - **다형성 Pollymorphism** : 사용편의성
        - **오버라이딩** overriding : 재정의 - 상위 클래스의 메서드와 같은 메서드 이름, 같은 인자 리스트
        - **오버로딩** overloading : 중복정의 - 같은 메서드 이름, 다른 인자 리스트

### 04 자바가 확장한 객체지향

- **abstract 키워드** - 추상 메서드, 추상 클래스
    - 추상 클래스는 인스턴스(객체) 생성 불가 = `new` 사용 불가
    - 추상 메서드는 하위 클래스에게 메서드의 구현을 강제함 (오버라이딩 강제)
    - 추상 메서드를 포함하는 클래스는 must 추상 클래스여야함
- **생성자** : 클래스의 인스턴스(객체) 만들 때마다 `new` 키워드 사용
    - 개발자가 아무런 생성자도 만들지 않으면, 자바는 인자가 없는 기본 생성자를 자동으로 만들어줌
    - 인자가 있는 생성자를 하나라도 만든다면, 자바는 기본 생성자를 만들지 X
- **static 블록** : 클래스 생성 시의 실행 블록, 단 한번 실행
- **final 키워드**
    - 클래스에 사용 → 상속 금지
    - 키워드에 사용 → 상수, static 블록(정적 생성자에 해당) 내부에서 초기화 가능
    - 메서드에 사용 → 오버라이딩 금지
- **instanceof 연산자** : 만들어진 객체가 특정 클래스의 인스턴스인지 물어보는 연산자 → return true/false
    
    `객체_참주_변수 instanceof 클래스명`
    
- **package 키워드** : 네임스페이스를 만들어주는 역할, 소유자
- **interface 키워드 & implements 키워드**
    - 인터페이스는 public 추상 메서드 & public 정적 상수만 가질 수 있음
    - 따로 메서드에 `public abstract`, 속성에 `public static final`을 붙이지 않아도 자바가 자동으로 붙여줌
- **this 키워드** : 객체 멤버 메서드 내부에서 객체 자신을 지칭
    - 지역 변수명 = 속성(객체 변수, 정적 변수)명 → 지역 변수 우선
    - 객체 변수명 = 지역 변수명 → 객체 변수 사용하려면 `this`를 접두사로 사용
    - 정젹 변수명 = 지역 변수명 → 정적 변수 사용하려면 클래스명을 접두사로 사용
- **super 키워드** : 바로 위 상위 클래스의 인스턴스를 지칭
    - `super.super` 형태로 상위의 상위 클래스 인스턴스에 접근 불가

### 05 객체 지향 설계 5원칙 - SOLID

‘ High Cohesion, Loose Coupling ’

1. **SRP - 단일 책임 원칙**
    - ‘어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이여야 한다‘
2. **OCP - 개방 폐쇄 원칙 ⭐️**
    - ‘자신의 확장에는 열려 있고, 주변의 변화에 대해서는 닫혀 있어야 한다‘
    - 해당 원칙을 위배하면 → 유연성, 재사용성, 유지보수성 등의 장점을 얻을 수 없음
3. **LSP - 리스코프 치환 원칙**
    - ‘서브 타입은 언제나 자신의 기반 타입(base type)으로 교체할 수 있어야 한다‘
    - ‘하위 클래스의 인스턴스는 상위형 객체 참조 변수에 대입해 상위 클래스의 인스턴스 역할을 하는데 문제 없어야 한다’
    - 하위 클래스 is a kind of 상위 클래스
    - 구현 클래스 is able to 인터페이스
    - 계층도/조직도 구조 - 해당 원칙 위배, 분류도 - 만족
4. **ISP - 인터페이스 분리 원칙**
    - ‘클라이언트는 자신이 사용하지 않는 메서드에 의존 관계를 맺으면 안된다’
5. **DIP - 의존 역전 원칙**
    - ‘추상화된 것은 구체적인 것에 의존하면 안된다. 구체적인 것이 추상화된 것에 의존해야 한다’

### 06 스프링이 사랑한 디자인 패턴

| 요리 | OOP |
| --- | --- |
| 요리도구 | 4대 원칙 |
| 요리도구 사용법 | 설계 원칙(SOLID) |
| 레시피 | 디자인 패턴 |
- **디자인 패턴** : 비즈니스 요구사항을 프로그래밍으로 처리하면서 만들어진 해결책들 중 베스트 프랙티스를 정리한 것
- **스프링 프레임워크** : OOP 프레임워크
1. **Adapter Pattern**
    - 변환기 역할 - 서로 다른 두 인터페이스 사이에 통신 가능하게 함
    - 호출 당하는 쪽의 메서드를 호출하는 쪽의 코드에 대응하도록 중간에 변환기를 통해 호출하는 패턴
2. **Proxy Pattern**
    - 대리자/대변인 - 대신하여 역할을 수행
    - 제어 흐름을 조정하기 위한 목적으로 중간에 대리자를 두는 패턴
    - 클라이언트가 받는 반환값을 특별한 경우가 아니면 변경하지 않음
3. **Decorator Pattern**
    - 프록시 패턴과 유사함
    - 메서드 호출의 반환값에 변화를 주기 위해 중간에 장식자를 두는 패턴
    - 클라이언트가 받는 반환값에 장식을 더함
4. **Singleton Pattern**
    - 클래스의 인스턴스(객체)를 하나만 만들어 사용하는 패턴
        - `new`를 실행할 수 없도록  `pirvate`생성자 갖음
        - 유일한 단일 객체를 반환할 수 있는 정적 메서드 필요
        - 유일한 단일 객체를 참조할 정적 참조 변수 필요
5. **Template Method Pattern**
    - 상위 클래스의 견본 메서드에서 하위 클래스가 오버라이딩한 메서드를 호출하는 패턴
    - 상위 클래스에 공통 로직 수행하는 템플릿 메서드
    - 하위 클래스에 오버라이딩을 강제하는 추상 메서드 / 선택적 오버라이딩이 가능한 훅(Hook) 메서드
6. **Factory Method Pattern**
    - 팩터리 메서드 : 객체를 반환하는 메서드
    - 오버라이드된 메서드가 객체를 반환하는 패턴
7. **Strategy Pattern**
    - 클라이언트가 전략을 생성해 전략을 실행할 컨텍스트에 주입하는 패턴
    - 3가지 요소
        
        (1) 전략 메서드를 가진 전략 객체
        
        (2) 전략 객체를 사용하는 컨텍스트(전략 객체의 사용자/소비자)
        
        (3) 전략 객체를 생성해 컨텍스트에 주입하는 클라이언트(전략 객체의 공급자, 제 3자)
        
        ![Untitled 2](https://user-images.githubusercontent.com/96513157/220033575-59e003f1-7e52-4e5e-802b-1c9c5658bda7.png)
        
    - 클라이언트는 다양한 전략 중 하나 선택해 생성 → 컨텍스트에 주입
8. **Template Callback Pattern - 견본/회신 패턴**
    - 전략을 익명 내부 클래스로 구현한 전략 패턴
    - strategy pattern의 변형 : 전략 → 익명 내부 클래스로 정의해서 사용
    - DI에서 사용

### 07 스프링 삼각형과 설정 정보

![Untitled 3](https://user-images.githubusercontent.com/96513157/220033584-2e0923ee-b85d-4ac4-a9e8-008a3aee918d.png)

- 스프링의 3대 프로그래밍 모델
    1. **IoC/DI - 제어의 역전/의존성 주입**
        - 자바에서 의존성 : (1) 의존관계가 `new`로 표현됨, (2) 전체가 부분에 의존함
        
        ```java
        interface Tire {
        	String getBrand();
        }
        ```
        
        ```java
        public class KoreaTire implements Tire {
        	public String getBrand() {
        		return "코리아 타이어";
        	}
        }
        
        public class AmericaTire implements Tire {
        	public String getBrand() {
        		return "미국 타이어";
        	}
        }
        ```
        
        ```java
        import org.springframework.beans.factory.annotation.Autowired;
        
        public class Car {
        	@Autowired //setter메소드 대신 속성을 자동으로 주입해줌
        	//@Resource
        	Tire tire;
        
        	public Tire getTire() {
        		return tire;
        	}
        
        	public String getTireBrand() {
        		return "장착된 타이어:" + tire.getBrand();
        	}
        }
        ```
        
        ```java
        import org.springframework.context.ApplicationContext;
        import org.springframework.context.support.ClassPathXmlApplicationContext;
        
        public class Driver{
        	public static void main(String[] args) {
        		//종합 쇼핑몰(스프링 프레임워크)에 대한 정보
        		ApplicationContext context = new ClassPathXmlApplicationContext("ex002/ex002.xml");
        		
        		//종합 쇼핑몰에서 상품(Car, Tire) 구매
        		Car car = context.getBean("car", Car.class);
        
        		System.out.println(car.getTireBrand());
        	}
        }
        ```
        
        ```xml
        <xml ...>
        <beans ...>
        
        <context:annotation-config />
        
        <bean id ="tire" class="ex002.KoreaTire"></bean>
        <bean id ="tireOther" class="ex002.AmericaTire"></bean>
        
        <bean id ="car" class="ex002.Car"></bean>
        
        </beans>
        ```
        
        - `@Autowired`는 id 매칭 < type 매칭이 우선
        
        - `@Autowired` & `@Resource` → 두 객체의 의존성 해결해줌
        
        |  | @Autowired | @Resource |
        | --- | --- | --- |
        | 출처 | 스프링 프레임워크 | 표준 자바 |
        | 소속 패키지 | org.springframework.beans.factory.annotation.Autowired | javax.annotation.Resource |
        | 빈 검색 방식 | byType 우선, 못찾으면 byName | byName 우선, 못찾으면 byType |
        | 특이사항 | @Qualifier(””) 협업 | name 어트리뷰트 |
        | byName강제하기 | @Autowired
        @Qualifier(”…”) | @Resource(name=”…”) |
    2. **AOP(Aspect-Oriented Programming) - 관점 지향 프로그래밍**
        - 로직(code) 주입
        - 스프링 AOP는 인터페이스 기반 & 프록시 기반 & 런타임 기반
        - `@Aspect` 이용 - 해당 클래스는 AOP에서 사용하겠다는 의미
        
        ![Untitled](https://user-images.githubusercontent.com/96513157/220033586-f6bb8d98-701f-4b8e-bc07-a6f54823ec1c.png)
        
        횡단 관심사 : 다수의 모듈에 공통적으로 나타나는 부분
        
        - 용어
            
            
            | Aspect | Advisor의 집합체 | JoinPoint | 연결 가능한 지점 |
            | --- | --- | --- | --- |
            | Advisor | 1개의 Advice+1개의 Pointcut | Piontcut | Aspect 적용 위치 지정자 |
            | Advice | 언제, 무엇을 
            (코드 주입 위치 애노테이션) |  |  |
        - 코드 주입 위치 - Around, Before, After, AfterReturning, AfterThrowing
        
        ```java
        public interface Person {
        	void runSomthing();
        }
        ```
        
        ```java
        public class Boy implements Person {
        	public void runSomething() {
        		System.out.println("게임을 한다");
        	}
        }
        
        public class Girl implements Person {
        	public void runSomething() {
        		System.out.println("잠을 잔다");
        	}
        }
        ```
        
        ```java
        @Aspect
        public class MyAspect {
        	//횡단 관심사를 실행하는 메서드
        	@Before("execution(* runSomething())") 
        	//* runSomething() -> PointCut
        	public void before(JoinPoint joinPoint) {
        		//JoinPoint -> Aspect 적용 가능한 모든 지점
        		System.out.println("얼굴 인식 확인: 문을 개방하라");
        	}
        }
        ```
        
        ```xml
        <xml ...>
        <beans ...>
        	//프록시 패턴을 이용해 횡단 관심사를 핵심 관심사에 주입하는 것
        	<aop:aspectj-autoproxy/>
        	<bean id="myaspect", class="ex003.Myaspect"/>
        	<bean id="boy", class="ex003.Boy"/>
        	<bean id="girl", class="ex003.Girl"/>
        <beans/>
        ```
        
        - 적용시 이점 : 단일 책임 원칙(SRP) 적용 가능 → 핵심 관심사만 남음
    3. **PSA(Portable Service Abstraction) - 일관성 있는 서비스 추상화**
        - 다양한 기술들이 제공하는 API는 제각각임
        - 스프링은 제각각인 API를 위한 어댑터를 제공 → 실제로 어떤 OXM(Object XML Mapping;객체와 XML 매핑) 기술을 쓰든 일관된 방식으로 코드 작성할 수 있게 지원함

  </details>
  
  * 게시판 구축
  
## 🏆 소감 

