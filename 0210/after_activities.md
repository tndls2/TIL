## 🔥 목표 
  스프링 기초 공부
## 👩‍💻 활동
  스프링 핵심 원리(기본편) sec 7 ~ 9 완강 및 내용 정리 + 토이프로젝트 아이디어 구체화하기
  
  ### 공부내용
  <details>
  <summary>sec7 의존관계 자동 주입</summary>


### 다양한 의존관계 주입 방법

1. **생성자 주입**
    - 생성자를 통해서 의존 관계를 주입 받는 방법
    - 생성자 호출시점에 딱 1번만 호출되는 것이 보장
    - **불변, 필수 의존관계**에 사용
    
    ```java
    @Component
    public class OrderServiceImpl implements OrderService {
    
    		private final MemberRepository memberRepository;
    		private final DiscountPolicy discountPolicy;
    
    		@Autowired
        public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    				this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }
    }
    ```
    
    <aside>
    💡 생성자가 **딱 1개**만 있으면 `@Autowired`를 생략해도 자동 주입 됨 (스프링 빈에만 해당)
    
    ```java
    @Component
    public class OrderServiceImpl implements OrderService {
    
    		private final MemberRepository memberRepository;
        private final DiscountPolicy discountPolicy;
        
    		public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
       }
    }
    ```
    
    </aside>
    
	
	
2. **수정자 주입(setter 주입)**
    - **setter**라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법
    - **선택, 변경 가능성이 있는 의존관계**에 사용
    - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법
    
    ```java
    @Component
    public class OrderServiceImpl implements OrderService {
    
    		private final MemberRepository memberRepository;
        private final DiscountPolicy discountPolicy;
        
    		@Autowired
        public void setMemberRepository(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
        
    		@Autowired
        public void setDiscountPolicy(DiscountPolicy discountPolicy) {
            this.discountPolicy = discountPolicy;
        }
    }
    ```
    
    <aside>
    💡 참고 : `@Autowired`의 기본 동작은 주입할 대상이 없으면 오류가 발생
    
    ➡️ 주입할 대상이 없어도 동작하게 하려면 `@Autowired(required = false)`로 지정하면 됨
    
    </aside>
    
    <aside>
    💡 참고: 자바빈 프로퍼티, 자바에서는 과거부터 필드의 값을 직접 변경하지 않고, **setXxx, getXxx 라는 메서드**를 통해서 값을 읽거나 수정하는 규칙 만듬 ⬅️ **자바빈 프로퍼티 규약**
    
    ```java
    class Data {
      private int age;
      
    	public void setAge(int age){ this.age = age; }
      
    	public int getAge(){ return age; } 
    }
    ```
    
    </aside>
    
	
	
3. **필드 주입**
    - 필드에 바로 주입하는 방법
    - 장점: 코드가 간결해서 많은 개발자들을 유혹
    - 단점: 외부에서 변경이 불가능해서 테스트 하기 힘듬
    - DI 프레임워크가 없으면 아무것도 할 수 없음
    - 스프링 설정을 목적으로 하는 `@Configuration` 같은 곳에서만 특별한 용도로 사용할 것
    
    ```java
    @Component
    public class OrderServiceImpl implements OrderService {
        @Autowired
        private MemberRepository memberRepository;
        
    		@Autowired
        private DiscountPolicy discountPolicy;
    }
    ```
    
    <aside>
    💡 참고: 순수한 자바 테스트 코드에는 당연히 `@Autowired`가 동작하지 않음
    `@SpringBootTest`처럼 스프링 컨테이너를 테스트에 통합한 경우에만 가능
    
    </aside>


    <aside>
    💡 참고: `@Bean`에서 파라미터에 의존관계는 자동 주입됨
    수동 등록시 자동 등록된 빈의 의존관계가 필요할 때 문제를 해결할 수 있다.
    
    ```java
    @Bean
    OrderService orderService(MemberRepository memberRepoisitory, DiscountPolicy discountPolicy) {
        new OrderServiceImpl(memberRepository, discountPolicy)
    }
    ```
    
    </aside>
    
	
	
4. **일반 메서드 주입**
    - 일반 메서드를 통해서 주입하는 방법
    - 한번에 여러 필드를 주입 받을 수 있음
    - 일반적으로 잘 사용하지 않음
    
    ```java
    @Component
    public class OrderServiceImpl implements OrderService {
        private MemberRepository memberRepository;
        private DiscountPolicy discountPolicy;
    		
    		@Autowired
        public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }
    }
    ```
    

<aside>
💡 참고: 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작함 
스프링 빈이 아닌 Member 같은 클래스에서 `@Autowired` 코드를 적용해도 아무 기능도 동작하지 않음

</aside>



### 옵션 처리

- 주입할 스프링 빈이 없어도 동작해야 할 때가 있음
- `@Autowired`만 사용하면 **required 옵션의 기본값 = true** 로 되어 있음 ➡️ 자동 주입 대상이 없으면 오류 발생

- 자동 주입 대상을 옵션으로 처리하는 방법
    - `@Autowired(required=false)` : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
        
        ```java
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1 = " + member); //호출 안됨
        		//meber는 스프링 빈이 아님
        }
        ```
        
    - `org.springframework.lang.@Nullable` : 자동 주입할 대상이 없으면 **null**이 입력됨
        
        ```java
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("setNoBean2 = " + member); //null 호출
        }
        ```
        
    - `Optional<>` : 자동 주입할 대상이 없으면 **Optional.empty**가 입력됨
        
        ```java
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
            System.out.println("setNoBean3 = " + member); //Optional.empty 호출
        }
        ```
        



### 생성자 주입 선택

과거 : **수정자 주입**과 **필드 주입** 많이 사용

최근 : 스프링을 포함한 DI 프레임워크 대부분이 **생성자 주입** 권장

- 이유
    1. **불변**
        
        대부분의 의존관계 주입은 한번 일어나면 ➡️ 애플리케이션 종료시점까지 의존관계를 변경할 일 없음
        
        대부분의 의존관계는 애플리케이션 종료 전까지 불변해야 함
        
        - **수정자 주입** 사용 시
            - **setXxx 메서드**를 **public**으로 열어두어야 함
            - 누군가 실수로 변경할 수 도 있음 + 변경하면 안되는 메서드를 열어두는 것 = 좋은 설계 방법 X
        - **생성자 주입** 사용 시
            - 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일 없음 ➡️ 불변하게 설계 가능
    2. **누락**
        
        생성자 주입을 사용하면 주입 데이터를 누락 시 **컴파일 오류** 발생 + IDE에서 바로 어떤 값을 필수로 주입해야 하는지 알 수 있음
        
    3. **final 키워드**
        
        생성자 주입을 사용하면 필드에 **final 키워드** 사용 가능 ➡️ 생성자에서 혹시라도 값이 설정되지 않는 오류를 **컴파일 시점**에 막아줌 (컴파일 오류 - 세상에서 가장 빠르고, 좋은 오류)
        
        <aside>
        💡 참고: **수정자 주입을 포함한 나머지 주입 방식**은 모두 생성자 이후에 호출되므로, 필드에 final 키워드 사용 불가
        
        오직 **생성자 주입** 방식만 final 키워드 사용 가능
        
        </aside>
        


**<정리>**

- **생성자 주입 방식** : 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법
- 기본으로 생성자 주입을 사용 + 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여
(생성자 주입 & 수정자 주입 동시에 사용 가능)
- 항상 **생성자 주입** 선택 + 옵션이 필요하면 **수정자 주입** 선택할 것 !



### 롬복과 최신 트랜드

개발을 하면, 대부분이 불변임 → 필드에 **final** 키워드 사용

**롬복 라이브러리**가 제공하는 `@RequiredArgsConstructor` 기능을 사용하면 **final**이 붙은 필드를 모아서
생성자를 자동으로 만들어줌

(롬복이 자바의 **애노테이션 프로세서**라는 기능을 이용해서 **컴파일 시점**에 생성자 코드를 자동으로 생성해줌)
	


<적용 전>
```java
@Component
public class OrderServiceImpl implements OrderService {
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;
  
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
      this.memberRepository = memberRepository;
      this.discountPolicy = discountPolicy;
  }
}
```

<적용 후>
```java
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;
}
```


- **롬복 라이브러리 적용 방법**
    
    : **build.gradle**에 라이브러리 및 환경 추가
    
    (or 초기세팅시 [https://start.spring.io/](https://start.spring.io/) 에서 ADD DEPENDENCIES 이용)
    
    ```java
    plugins {
          id 'org.springframework.boot' version '2.3.2.RELEASE'
          id 'io.spring.dependency-management' version '1.0.9.RELEASE'
          id 'java'
    }
      
    group = 'hello'
    version = '0.0.1-SNAPSHOT'
    sourceCompatibility = '11'
    
    //lombok 설정 추가 시작 
    configurations {
    		compileOnly {
    				extendsFrom annotationProcessor
    			} 
    }
    //lombok 설정 추가 끝
    
    repositories {
    	mavenCentral()
    }
    
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter'
    
    		//lombok 라이브러리 추가 시작
    		compileOnly 'org.projectlombok:lombok' 
    		annotationProcessor 'org.projectlombok:lombok'
    		testCompileOnly 'org.projectlombok:lombok' 
    		testAnnotationProcessor 'org.projectlombok:lombok' 
    		//lombok 라이브러리 추가 끝
    
        testImplementation('org.springframework.boot:spring-boot-starter-test') {
            exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    		} 
    }
    
    test {
        useJUnitPlatform()
    }
    ```
    
    1. Preferences ➡️ plugin ➡️ **lombok** 검색 설치 실행
        
        ( IntelliJ 2020.03 ~ 버전에서는 기본Plugin으로 Lombok 설치됨 )
        
    2. Preferences  ➡️ Annotation Processors 검색  ➡️ Enable annotation processing 체크 (재시작)
    3. 임의의 테스트 클래스를 만들고 @Getter, @Setter 확인
    


### 조회 대상 빈이 2개이상일 때 오류 해결

- `@Autowired`는 **타입(Type)**으로 조회함
    
    ```java
      @Autowired
      private DiscountPolicy discountPolicy
    ```
    
    ➡️ 다음 코드와 유사하게 동작 (실제로는 더 많은 기능을 제공)
    
    `ac.getBean(DiscountPolicy.class)`
    
- 타입으로 조회하면 선택된 빈이 2개 이상일 때 오류 발생
    
    ex) `DiscountPolicy`의 하위 타입인 `FixDiscountPolicy`, `RateDiscountPolicy` 둘다 스프링 빈으로 선언
    
    ```java
    @Component
      public class FixDiscountPolicy implements DiscountPolicy {}
    ```
    
    ```java
    @Component
      public class RateDiscountPolicy implements DiscountPolicy {}
    ```
    
    의존관계 자동 주입 실행하면 → NoUniqueBeanDefinitionException 오류 발생
    


**해결 방법**

1. **@Autowired 필드 명 매칭**
    - `@Autowired` : 타입 매칭 시도 (이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭)
    - **필드 명 매칭** :  먼저 타입 매칭을 시도 ➡️ 결과에 여러 빈이 있을 때 추가로 동작하는 기능
    - **@Autowired 매칭 정리**
        1. 타입 매칭
        2. 타입 매칭의 결과가 2개 이상일 때 : 필드 명, 파라미터 명으로 빈 이름 매칭
	
	
2. **@Qualifier ➡️ @Qualifier끼리 매칭 ➡️ 빈 이름 매칭**
    - `@Qualifier` : 추가 구분자를 붙여주는 방법 (주입 시 추가적인 방법을 제공하는 것이지, 빈 이름을 변경하는 것이 아님)
    - 빈 등록시 `@Qualifier`를 붙여줌 + 등록한 이름 적음
        
        ```java
        @Component
        @Qualifier("mainDiscountPolicy")
        public class FixDiscountPolicy implements DiscountPolicy {}
        ```
        
	
	
3. **@Primary 사용**
    - `@Primary` : 우선순위를 정하는 방법
    - `@Autowired`시에 여러 빈이 매칭 된 경우 ➡️ `@Primary`가 우선권 가짐
        
        ```java
        @Component
        @Primary
        public class RateDiscountPolicy implements DiscountPolicy {}
        
        @Component
        public class FixDiscountPolicy implements DiscountPolicy {}
        ```
        

- **@Primary, @Qualifier 활용**
    
    (1) 코드에서 자주 사용하는 **메인 데이터베이스의 커넥션을 획득하는 스프링 빈**
    
    (2) 코드에서 특별한 기능으로 가끔 사용하는 **서브 데이터베이스의 커넥션을 획득하는 스프링 빈**
    
    이 있다고 가정
    
    ➡️ **메인 데이터베이스의 커넥션을 획득하는 스프링 빈**은 `@Primary`를 적용해서, 조회하는 곳에서 `@Qualifier` 지정 없이 편리하게 조회
    
    ➡️ **서브 데이터베이스 커넥션 빈**을 획득할 때는 `@Qualifier`를 지정해서, 명시적으로 획득 하는 방식으로 사용하면 코드를 깔끔하게 유지 가능 
    
- **우선순위**
    - `@Primary`: 기본값 처럼 동작하는 것
    - `@Qualifier`: 매우 상세하게 동작
    - 우선순위 = 스프링은 자동 < 수동이, 넒은 범위의 선택권 < 좁은 범위의 선택권
        
        ➡️ `@Qualifier`가 우선권 높음
        


### List, Map

조회한 빈 모두 필요할 때 사용

```java
private final Map<String, DiscountPolicy> policyMap;
private final List<DiscountPolicy> policies
```

- `Map<String, DiscountPolicy>` : map의 키 = 스프링 빈의 이름 ➡️ 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담음
- `List<DiscountPolicy>` : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담음.
    - 만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입

<**스프링 컨테이너를 생성하면서 스프링 빈 등록하기>**
`new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);`

- 스프링 컨테이너는 생성자에 클래스 정보를 받음
- 여기에 클래스 정보를 넘기면 해당 클래스가 스프링 빈으로 자동 등록
  </details>
  
  <details>
  <summary>sec8 빈 생명주기 콜백</summary>

### **빈 생명주기 콜백 시작**

- **객체의 초기화와 종료 작업** : 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하기 위해 필요 (like 데이터베이스 커넥션 풀이나, 네트워크 소켓)
- 간단하게 외부 네트워크에 미리 연결하는 객체를 하나 생성한다고 가정 (실제로 네트워크에 연결하는 것 X, 단순히 문자만 출력 O )

```java
public class NetworkClient {
		private String url;
		
		public NetworkClient() { 
				System.out.println("생성자 호출, url = " + url);
				connect();
				call("초기화 연결 메시지");
		}
		public void setUrl(String url) {
        this.url = url;
		}
	
		//서비스 시작시 호출
		public void connect() {
		    System.out.println("connect: " + url);
    }
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
		}
		//서비스 종료시 호출
		public void disconnect() {
        System.out.println("close: " + url);
    }
}
```

- `NetworkClient`는 애플리케이션 시작 시점에 `connect()` 호출 ➡️ 연결 맺음 / 애플리케이션 종료지점에 `disConnect()` 호출 ➡️ 연결 끊음

```java
public class BeanLifeCycleTest {
    @Test
		public void lifeCycleTest() {
		    ConfigurableApplicationContext ac = new
				AnnotationConfigApplicationContext(LifeCycleConfig.class);
				NetworkClient client = ac.getBean(NetworkClient.class);

			  ac.close(); //스프링 컨테이너를 종료, ConfigurableApplicationContext 필요 
		}
	  
		@Configuration
    static class LifeCycleConfig {
		    @Bean
        public NetworkClient networkClient() {
		        NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
				} 
		}
}

/* 결과 :
	생성자 호출, url = null
	connect: null
	call: null message = 초기화 연결 메시지
*/
```

- 객체를 생성하는 단계에 url이 없음
- 객체를 생성한 다음에 외부에서 수정자 주입을 통해서 `setUrl()`이 호출되어야 url이 존재하게 됨



<**스프링 빈의 이벤트 라이프사이클>**

**스프링 컨테이너 생성** ➡️ **스프링 빈 생성** ➡️ **의존관계 주입** ➡️ **초기화 콜백** ➡️ **사용** ➡️ **소멸 전 콜백** ➡️ **스프링 종료**

- 객체 생성 & 의존관계 주입 모두 완료 ➡️ 필요한 데이터를 사용할 준비 완료됨
- 초기화 작업은 의존관계 주입이 모두 완료된 후 호출해야 함
    - **초기화 콜백**: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
- 의존관계 주입이 모두 완료된 시점을 알 수 있는 방법
    - **스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능 제공함**
    - **스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백**을 줌
    
    ➡️ 따라서 안전하게 종료 작업을 진행 가능
    
    - **소멸 전 콜백**: 빈이 소멸되기 직전에 호출


<aside>
💡 객체의 생성과 초기화를 분리하자 !

- 생성자 - 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가짐
- 초기화 - 위처럼 생성된 값들을 활용해서 외부 커넥션을 연결하는 등 무거운 동작 수행

➡️ 생성자 안에서 무거운 초기화 작업을 함께 하는 것 **<** 객체를 생성하는 부분과 초기화하는 부분을 명확하게 나누는 것이 유지보수 관점에서 좋음

</aside>



<**스프링의 빈 생명주기 콜백을 지원 방법 3가지>**

1. 인터페이스(InitializingBean, DisposableBean)
2. 설정 정보에 초기화 메서드, 종료 메서드 지정
3. `@PostConstruct`, `@PreDestroy` 애노테이션 지원

### **인터페이스 InitializingBean, DisposableBean**

```java
public class NetworkClient implements InitializingBean, DisposableBean {
		private String url;
    
		public NetworkClient() {
				System.out.println("생성자 호출, url = " + url); 
		}
    public void setUrl(String url) {
			  this.url = url;
		}
		//서비스 시작시 호출
		public void connect() {
        System.out.println("connect: " + url);
    }
    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
		}
		//서비스 종료시 호출
		public void disConnect() {
        System.out.println("close + " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
				connect();
				call("초기화 연결 메시지");
		}

    @Override
    public void destroy() throws Exception {
        disConnect();
    }
```

- **InitializingBean**은 `afterPropertiesSet()`메서드로 **초기화 지원**
- **DisposableBean**은 `destroy()`메서드로 **소멸 지원**


<**초기화, 소멸 인터페이스 단점>**

- 스프링 전용 인터페이스임 ➡️ 해당 코드가 스프링 전용 인터페이스에 의존
- 초기화, 소멸 메서드의 이름 변경 불가
- 내가 코드를 고칠 수 없는 외부 라이브러리에 적용 불가

<aside>
💡 인터페이스를 사용하는 초기화, 종료 방법 - 스프링 초창기에 나온 방법들

지금은 다음의 더 나은 방법들이 있어서 거의 사용 X !

</aside>


### **빈 등록 초기화, 소멸 메서드 지정**

설정 정보에 `@Bean(initMethod = "init", destroyMethod = "close")`처럼 초기화, 소멸 메서드 지정 가능

```java
@Configuration
  static class LifeCycleConfig {
      @Bean(initMethod = "init", destroyMethod = "close")
      public NetworkClient networkClient() {
          NetworkClient networkClient = new NetworkClient();
          networkClient.setUrl("http://hello-spring.dev");
          return networkClient;
} }
```

<**설정 정보 사용 특징>**

- 메서드 이름을 자유롭게 설정 가능
- 스프링 빈이 스프링 코드에 의존 X
- 코드가 아니라 설정 정보를 사용함 ➡️ 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료
메서드 적용 가능



### **@PostConstruct, @PreDestroy**

```java
@PostConstruct
public void init() {
		System.out.println("NetworkClient.init"); connect();
		call("초기화 연결 메시지");
}

@PreDestroy
public void close() {
    System.out.println("NetworkClient.close");
    disConnect();
}
```

<**@PostConstruct, @PreDestroy 애노테이션 특징>**

- 최신 스프링에서 가장 권장하는 방법
- 애노테이션 하나만 붙이면 되므로 매우 편리함
- 스프링에 종속적인 기술이 아니라 JSR-250라는 자바 표준 이용 ➡️ 스프링이 아닌 다른 컨테이너에서도 동작함
- 단점 : 외부 라이브러리에는 적용 불가
    - 외부 라이브러리를 초기화, 종료 해야 하면 `@Bean`의 기능 사용해야 함
  </details>
  
  <details>
  <summary>sec9 빈 스코프</summary>
### 빈 스코프

- 스프링 빈 : 스프링 컨테이너의 시작과 함께 생성됨 + 스프링 컨테이너가 종료될 때 까지 유지됨
    
    (스프링 빈이 기본적으로 싱글톤 스코프로 생성되기 때문)
    
- **스코프** :  빈이 존재할 수 있는 범위

<**스프링이 지원하는 다양한 스코프>**

- **싱글톤**: 기본 스코프, 스프링 컨테이너의 시작 ~ 종료까지 유지되는 가장 넓은 범위의 스코프
- **프로토타입**: 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는
매우 짧은 범위의 스코프
- **웹 관련 스코프**
    1. **request** : 웹 요청이 들어오고 나갈때까지 유지되는 스코프
    2. **session** : 웹 세션이 생성되고 종료될 때까지 유지되는 스코프
    3. **application** : 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프

**<빈 스코프 지정>**

```java
@Scope("prototype")
@Component
public class HelloBean {}
```

```java
@Scope("prototype")
@Bean
PrototypeBean HelloBean() {
		return new HelloBean();
}
```

	
### 프로토타입 스코프

- **싱글톤 스코프**의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환함
    
    ![9-1](https://user-images.githubusercontent.com/96513157/218309942-c3692128-7c5a-4230-a215-498c67c57932.png)
    
- **프로토타입 스코프**를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환함
    1. **클라이언트** - 싱글톤 스코프의 빈을 스프링 컨테이너에 요청
        
        ![9-2](https://user-images.githubusercontent.com/96513157/218309948-1f46e0f5-46ef-4cb3-ad22-ab20a837b459.png)
        
    2. **스프링 컨테이너** - 본인이 관리하는 스프링 빈을 반환
        
        ![9-3](https://user-images.githubusercontent.com/96513157/218309949-c57c5373-340c-46ee-b1b9-2ecbfe4f72f9.png)
        
    3. 이후, 스프링 컨테이너에 같은 요청이 와도 같은 객체 인스턴스의 스프링 빈을 반환
    
	
    <aside>
    💡 *스프링 컨테이너*는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리
    
    ➡️ 클라이언트에 빈을 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈 관리X
    
    **프로토타입 빈을 관리할 책임** ← 프로토타입 빈을 받은 클라이언트에 있음
    
    ➡️ 종료 메서드가 호출 X
    
    </aside>
    


	
### 웹 스코프

- 특징
    - 웹 환경에서만 동작
    - 스프링이 해당 스코프의 종료시점까지 관리 (따라서 종료 메서드가 호출됨)
- 종류
    1. **request :** HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성 & 관리됨
    2. **session :** HTTP Session과 동일한 생명주기를 가지는 스코프
    3. **application :** 서블릿 컨텍스트(`ServletContext`)와 동일한 생명주기를 가지는 스코프
    4. **websocket :** 웹 소켓과 동일한 생명주기를 가지는 스코프
	  
  </details>
  
## 🏆 소감 

의존관계 주입 방법 중 1.생성자 주입, 2.수정자 주입 방법 을 직접 코드로 입력하며 강의를 들으니 이해가 잘 되었다. 또한 다양한 스프링은 자바의 annotation(@Primary, @PostConstruct, @PreDestroy 등)을 이용하여 편하게 기능을 구현할 수 있어서 편리하다고 느꼈다.
