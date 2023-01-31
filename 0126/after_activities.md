## 🔥 목표 
  스프링 기초 공부
## 👩‍💻 활동
  스프링 핵심 원리(기본편) sec 4 ~ 6 완강 및 내용 정리
  
  ### 공부내용
  <details>
  <summary>sec4 스프링 컨테이너와 스프링 빈</summary>

### 스프링 컨테이너 등록

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

- **스프링 컨테이너**
    - `ApplicationContext` ⬅️ 인터페이스임
    - XML 기반 or 애노테이션 기반의 자바 설정 클래스로 만들 수 있음
    - 직전에 **AppConfig**를 사용했던 방식 → 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너 만듬
    - 자바 설정 클래스를 기반으로 스프링 컨테이너(`ApplicationContext`)를 만들면
        - `new AnnotationConfigApplicationContext(AppConfig.class);`
        - 해당 클래스 = **ApplicationContext 인터페이스의 구현체**
    
    <aside>
    ➕ 더 정확히는 **스프링 컨테이너**를 부를 때 **BeanFactory**, **ApplicationContext**로 구분함
    
    - `BeanFactory`를 직접 사용하는 경우는 거의 없으므로, 일반적으로 `ApplicationContext`를 **스프링 컨테이너**라 한다.
    </aside>
    

- **스프링 컨테이너 생성 과정**
    1. **스프링 컨테이너 생성**
        
        ![4-1](https://user-images.githubusercontent.com/96513157/215312516-8feaec35-fba8-446a-b270-c93084ce45f7.png)
        
        - `new AnnotationConfigApplicationContext(AppConfig.class)`
        - 스프링 컨테이너를 생성 시, 구성 정보 지정해줘야 함
            
            ex) 위에서는 `AppConfig.class`를 구성 정보로 지정함
            
    2. **스프링 빈 등록**
        
        ![4-2](https://user-images.githubusercontent.com/96513157/215312518-800f7820-66f0-44c3-9056-56d998ce12ee.png)
        
        - 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용 → 스프링 빈을 등록
        - **빈 이름** : 메서드 이름을 사용 or 직접 부여 가능
            
            `@Bean(name="memberService2")`
            
            <aside>
            💡 **빈 이름은 항상 다른 이름을 부여**해야 함
            
            - 같은 이름 부여 시 → 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생
            </aside>
            
        
    3. **스프링 빈 의존관계 설정**
        
        ![4-3](https://user-images.githubusercontent.com/96513157/215312519-c7754503-8c89-441a-b1b6-9e2713c80a52.png)
        
        ![4-4](https://user-images.githubusercontent.com/96513157/215312521-d9fa54dc-9609-4392-a65d-7f23faf7ab38.png)
        
        - 스프링 컨테이너는 설정 정보를 참고해서 **의존관계를 주입(DI)**
        - 단순히 자바 코드를 호출하는 것 같지만, 차이가 있음 ➡️ **싱글톤 컨테이너**
        
        <aside>
        💡 스프링은 **(1)빈 생성**, **(2)의존관계 주입** 단계가 나뉨
        
        But 자바 코드로 스프링 빈을 등록 → 생성자를 호출하면서 의존관계 주입도 한번에 처리
        
        ➡️ **의존관계 자동 주입**
        
        </aside>
        

### **컨테이너에 등록된 모든 빈 조회**

- **모든 빈 출력하기** : 스프링에 등록된 모든 빈 정보를 출력 가능
    - `ac.getBeanDefinitionNames()` : 스프링에 등록된 모든 빈 이름 조회
    - `ac.getBean()` : 빈 이름으로 빈 객체(인스턴스)를 조회
    
    ```java
    class ApplicationContextInfoTest {
        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);
    
        @Test
        @DisplayName("모든 빈 출력하기")
        void findAllBean(){
            String[] beanDefinitionNames = ac.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = "+ bean);
            }
        }
    }
    ```
    
- **애플리케이션 빈 출력하기** : 스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력 가능
    - 스프링이 내부에서 사용하는 빈은 `getRole()`로 구분 가능
        - `ROLE_APPLICATION` : 일반적으로 사용자가 정의한 빈
        - `ROLE_INFRASTRUCTURE` : 스프링이 내부에서 사용하는 빈
    
    ```java
    class ApplicationContextInfoTest {
        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);
    
        @Test
        @DisplayName("애플리케이션 빈 출력하기")
        void findApplicationBean(){
            String[] beanDefinitionNames = ac.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
    						
    						//Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
    						//Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
                if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                    Object bean = ac.getBean(beanDefinitionName);
                    System.out.println("name = " + beanDefinitionName + " object = "+ bean);
                }
            }
        }
    }
    ```
    

### 스프링 빈 조회 - 기본

- 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
- `ac.getBean(빈이름, 타입)`
- `ac.getBean(타입)`
- 조회 대상 스프링 빈이 없으면 예외 발생 ! ➡️ NoSuchBeanDefinitionException: No bean named 'xxxxx' available

```java
class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    } //구체 타입으로 조회하면 변경시 유연성 떨어짐

    @Test
    @DisplayName("빈 이름으로 조회X") 
		void findBeanByNameX() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("xxxxx", MemberService.class));
    } //예외 발생(조회 대상 스프링 빈 없음): NoSuchBeanDefinitionException: No bean named 'xxxxx' available
}
```

### 스프링 빈 조회 - **동일한 타입이 둘 이상**

- 타입으로 조회시, 같은 타입의 스프링 빈이 둘 이상이면 오류 발생 ➡️ 빈 이름 지정
- `ac.getBeansOfType()`을 사용하면 해당 타입의 모든 빈을 조회 가능

```java
public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입만으로 조회시 같은 이름 존재하면, 오류 발생함")
    void findBeanByTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입만으로 조회시 같은 이름 존재하면, 빈 이름 지정하면 됨")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        
				for (String key : beansOfType.keySet()) {
            System.out.println("key = "+key+" value= "+ beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
```

### 스프링 빈 조회 - **상속 관계**

- 부모 타입으로 조회 → 자식 타입도 함께 조회
➡️모든 자바 객체의 최고 부모인 **Object** 타입으로 조회하면, 모든 스프링 빈 조회됨
    
    ![4-5](https://user-images.githubusercontent.com/96513157/215312522-b4461810-5d44-415e-85ad-38629e756bc7.png)
    

```java
public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 존재하면 중복 오류 발생")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 존재하면 빈 이름을 지정하면 됨")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = "+beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기-object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = "+beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
         return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
```

### **BeanFactory와 ApplicationContext**

![4-6](https://user-images.githubusercontent.com/96513157/215312523-31507381-5b37-4642-8825-80acfd31f82c.png)

- **BeanFactory**
    - 스프링 컨테이너의 최상위 인터페이스
    - 스프링 빈을 관리 & 조회하는 역할 담당
    - `getBean()` 제공
    - 우리가 사용했던 대부분의 기능은 **BeanFactory**가 제공하는 기능
- **ApplicationContext**
    - **BeanFactory** 기능을 모두 상속받아서 제공
    - 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능은 물론이고, 수많은 부가기능이 필요함 !
    - ApplicatonContext가 제공하는 부가기능
        
        ![4-7](https://user-images.githubusercontent.com/96513157/215312524-eab8c39e-784e-4e8a-8686-b8100aad19b7.png)
        
        1. **메시지소스를 활용한 국제화 기능** 
            
            ex) ****한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력
            
        2. **환경변수** : 로컬, 개발, 운영 등을 구분해서 처리
        3. **애플리케이션 이벤트** : 이벤트 발행, 구독하는 모델을 편리하게 지원
        4. **편리한 리소스 조회** : 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

<**정리>**

- **ApplicationContext**는 **BeanFactory**의 기능을 상속받음
- **ApplicationContext**는 빈 관리기능 + 편리한 부가 기능 제공
- **BeanFactory**를 직접 사용할 일은 거의 없음 ➡️ 부가기능이 포함된 **ApplicationContext** 사용함
- **BeanFactory**나 **ApplicationContext** = 스프링 컨테이너

### **다양한 설정 형식 지원 - 자바 코드, XML**

- 스프링 컨테이너는 다양한 형식의 설정 정보를 받아드릴 수 있게 유연하게 설계됨
    - 자바 코드, XML, Groovy 등등
    
    ![4-8](https://user-images.githubusercontent.com/96513157/215312526-7541a708-b474-4243-8c06-800f67ccf41c.png)
    
- **애노테이션 기반 자바 코드 설정 사용**
    - 지금까지 했던 것
    - `new AnnotationConfigApplicationContext(AppConfig.class)`
    - `AnnotationConfigApplicationContext` 클래스 사용 → 자바 코드로된 설정 정보를 넘기면 됨
- **XML 설정 사용**
    - 최근에는 스프링 부트를 많이 사용하면서 XML기반의 설정은 잘 사용하지 않음
    - XML을 사용하면 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점 있음
    - `GenericXmlApplicationContext`를 사용하면서 xml 설정 파일을 넘기면 됨
    - **XmlAppConfig 사용 자바 코드**
        
        `ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");`
        
    - **xml 기반의 스프링 빈 설정 정보**
        
        ```java
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
        
            <bean id="memberService" class="hello.core.member.MemberServiceImpl">
                <constructor-arg name="memberRepository" ref="memberRepository"/>
            </bean>
        
            <bean id="memberRepository" class="hello.core.member.MemoryMemberRepository"/>
        
            <bean id="orderService" class="hello.core.order.OrderServiceImpl">
                <constructor-arg name="memberRepository" ref="memberRepository"/>
                <constructor-arg name="discountPolicy" ref="discountPolicy"/>
            </bean>
            <bean id="discountPolicy" class="hello.core.discount.RateDiscountPolicy"/>
        </beans>
        ```
        
        <aside>
        💡 xml 기반의 appConfig.xml 스프링 설정 정보와 자바 코드로 된 AppConfig.java 설정 정보가 비슷하다는 것을 알 수 있음
        
        </aside>
        

### **스프링 빈 설정 메타 정보 - BeanDefinition**

- **역할과 구현을 개념적으로 나눔 → 다양한 설정 형식을 지원**
    - XML을 읽어서 **BeanDefinition**을 만듬
    - 자바 코드를 읽어서 **BeanDefinition**을 만듬
    - **스프링 컨테이너**는 오직 BeanDefinition만 알면 됨
- **BeanDefinition = 빈 설정 메타정보**
    - `@Bean` , `<bean>` 당 각각 하나씩 메타 정보 생성됨
- 스프링 컨테이너는 위의 메타정보를 기반으로 스프링 빈 생성
    
    ![4-9](https://user-images.githubusercontent.com/96513157/215312527-a771d620-87f2-4fdd-97ee-20f00b56376f.png)
    
    ![4-10](https://user-images.githubusercontent.com/96513157/215312528-80f0c7f5-5322-4fc8-b92d-46cf62b558f1.png)
    
    - `AnnotationConfigApplicationContext` : `AnnotatedBeanDefinitionReader`를 사용해서
    **AppConfig.class** 읽음 → **BeanDefinition** 생성
    - `GenericXmlApplicationContext` : `XmlBeanDefinitionReader`를 사용해서 **appConfig.xml** 설정 정보 읽음 → **BeanDefinition** 생성
    - 새로운 형식의 설정 정보가 추가되면, `XxxBeanDefinitionReader`를 만들어서 **BeanDefinition**
    생성하면 됨
- **BeanDefinition 정보**
    - **BeanClassName** : 생성할 빈의 클래스명(자바 설정처럼 팩토리 역할의 빈을 사용하면 없음)
    - **factoryBeanName** : 팩토리 역할의 빈을 사용할 경우 이름 *ex) appConfig*
    - **factoryMethodName** : 빈을 생성할 팩토리 메서드 지정 *ex) memberService*
    - **Scope** : 싱글톤(기본값)
    - **lazyInit** : 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때까지 최대한
    생성을 지연처리 하는지 여부
    - **InitMethodName** : 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드명
    - **DestroyMethodName** : 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드명
    - **Constructor arguments, Properties** : 의존관계 주입에서 사용 (자바 설정처럼 팩토리 역할의 빈을 사용하면 없음)
    

<**정리>**

- **BeanDefinition**을 직접 생성해서 **스프링 컨테이너**에 등록 가능함
- 스프링이 다양한 형태의 **설정 정보**를 **BeanDefinition**으로 추상화해서 사용함
  </details>
  
  <details>
  <summary>sec5 싱글톤 컨테이너</summary>

**웹 애플리케이션과 싱글톤**

- 대부분의 스프링 애플리케이션 = **웹 애플리케이션** (웹이 아닌 애플리케이션 개발도 얼마든지
개발 가능) ➡️ 웹 애플리케이션은 보통 여러 고객이 동시에 요청
    
    ![5-1](https://user-images.githubusercontent.com/96513157/215772487-44f44c07-4649-4386-b546-4630b356e193.png)
    

- **AppConfig** (스프링 없는 순수한 DI 컨테이너)
    - 요청을 할 때마다 객체를 새로 생성
        
        ```java
        public class SingletonTest {
        	
        	@Test
        	@DisplayName("스프링 없는 순수한 DI 컨테이너")
        	void pureContainer() {
        			AppConfig appConfig = new AppConfig();
        			//1. 조회: 호출할 때 마다 객체를 생성
        			MemberService memberService1 = appConfig.memberService();
        			
        			//2. 조회: 호출할 때 마다 객체를 생성
        			MemberService memberService2 = appConfig.memberService();
        
        			//참조값이 다른 것을 확인
        			System.out.println("memberService1 = " + memberService1); 
        			System.out.println("memberService2 = " + memberService2);
              
        			//memberService1 != memberService2
              assertThat(memberService1).isNotSameAs(memberService2);
        	}
        }
        ```
        
        ➡️ 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸됨 (메모리 낭비)
        
        ➡️ 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계 = **싱글톤 패턴**
        

### 싱글톤 패턴

- **싱글톤 패턴**
    - 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
    - 객체 인스턴스 2개 이상 생성하지 못하도록 막아야 됨
        - **private** 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 됨
    
    ```java
    public class SingletonService {
    
    	//1. static 영역에 객체를 딱 1개만 생성해둔다.
    	private static final SingletonService instance = new SingletonService();
    	
    	//2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
      public static SingletonService getInstance() {
    		return instance;
    	}
    
    	//3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다. 
    	private SingletonService() {
    	}
    public void logic() { System.out.println("싱글톤 객체 로직 호출");
    } }
    ```
    
    1. **static 영역**에 **객체 instance**를 미리 하나 생성해서 올려둠
    2. 해당 객체 인스턴스가 필요하면 오직 `getInstance()`메서드를 통해서만 조회 가능
        
        + 해당 메서드를 호출하면 항상 같은 인스턴스 반환
        
    3. 딱 1개의 객체 인스턴스만 존재해야 함 ➡️ 생성자를 **private**으로 막아서 혹시라도 외부에서 `new`키워드로 객체 인스턴스가 생성되는 것을 막음
        
        ```java
        @Test
        @DisplayName("싱글톤 패턴을 적용한 객체 사용") public void singletonServiceTest() {
        //private으로 생성자를 막아두었다. 컴파일 오류가 발생한다. //new SingletonService();
        //1. 조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService1 = SingletonService.getInstance();
        //2. 조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();
        //참조값이 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1); System.out.println("singletonService2 = " + singletonService2);
                // singletonService1 == singletonService2
                assertThat(singletonService1).isSameAs(singletonService2);
                singletonService1.logic();
        }
        ```
        

- **싱글톤 패턴 문제점**
    - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어감
    - 의존관계상 클라이언트가 구체 클래스에 의존함 (DIP 위반)
    - 클라이언트가 구체 클래스에 의존함 (OCP 원칙 위반 가능성 높음)
    - 테스트하기 어려움

### 싱글톤 컨테이너

- 스프링 컨테이너 → 싱글톤 패턴의 문제점 해결 + 객체 인스턴스를 싱글톤(1개만 생성)으로
관리
    
    <aside>
    💡 지금까지 우리가 학습한 스프링 빈 = 싱글톤으로 관리되는 빈
    
    </aside>
    
- **싱글톤 컨테이너**
    - **싱글톤 컨테이너** : 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리
    - **컨테이너** : 객체를 하나만 생성해서 관리
    - **스프링 컨테이너** → 싱글톤 컨테이너 역할
        - **싱글톤 레지스트리** : 싱글톤 객체 생성+관리하는 기능
        - 싱글턴 패턴의 모든 단점을 해결
            - 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 됨
            - DIP, OCP, 테스트, private 생성자로부터 자유롭게 싱글톤 사용 가능
    
- 고객의 요청이 올 때마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용
    
    ![5-2](https://user-images.githubusercontent.com/96513157/215772506-b5e6818d-ebb9-4930-96e9-ad85a55154cd.png)
    
    스프링 컨테이너 적용 후
    

### 싱글톤 방식의 주의점

- 객체 인스턴스를 하나만 생성해서 공유하는 **싱글톤 방식**은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유함
    
    ➡️ 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안됨 
    
    ```java
    package hello.core.singleton;
    
    public class StatefulService {
    
    		private int price; //상태를 유지하는 필드
    		
    		public void order(String name, int price) { 
    				System.out.println("name = " + name + " price = " + price); 
    				this.price = price; //문제 지점
    		}
        
    		public int getPrice() {
              return price;
    		}
    }
    ```
    
    ```java
    public class StatefulServiceTest {
    
        @Test
    		void statefulServiceSingleton() {
    
    				ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
            StatefulService statefulService1 = ac.getBean("statefulService",StatefulService.class);
            StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
    				
    				//**ThreadA** : A사용자 10000원 주문
    				statefulService1.order("userA", 10000);
    
    				//**ThreadB** : B사용자 20000원 주문 
    				statefulService2.order("userB", 20000);
    
    				//**ThreadA** : 사용자A 주문 금액 조회
    				int price = statefulService1.getPrice();
    				
    				//**ThreadA**: 사용자A 기대값=10000원, 결과값=20000원 출력
    				System.out.println("price = " + price);
            Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        }
    ```
    
    - 가정 : ThreadA가 **사용자A** 코드를 호출 & ThreadB가 **사용자B** 코드를 호출
    - `StatefulService`의 `price`필드 → 공유되는 필드 but 특정 클라이언트가 값 변경함
    - **사용자A**의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과 나옴
        
        ➡️ 공유필드는 조심해야 함! 스프링 빈은 항상 무상태(stateless)로 설계할 것
        
- **무상태(stateless)**로 설계해야 됨
    - 특정 클라이언트에 의존적인 필드가 있으면 안됨
    - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안됨
    - 가급적 읽기만 가능해야 함
    - 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등 사용해야 함

### @Configuration과 바이트코드 조작의 마법

- 스프링 컨테이너 = 싱글톤 레지스트
    
    ➡️ 스프링 빈이 싱글톤이 되도록 보장해주어야 함 but 스프링이 자바 코드까지 어떻게 하기는 어려움
    
    ➡️ 스프링은 클래스의 바이트코드를 조작하는 라이브러리(CGLIB)를 사용함
    
    (`@Configuration`을 적용한 **AppConfig**에 의한 것)
    

```java
@Test
void configurationDeep() {
      ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
			
			//AppConfig도 스프링 빈으로 등록된다.
			AppConfig bean = ac.getBean(AppConfig.class);

			System.out.println("bean = " + bean.getClass());
			//출력: bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70
}
```

- `AnnotationConfigApplicationContext`에 파라미터로 넘긴 값 → 스프링 빈으로 등록됨
    
    ➡️ AppConfig도 스프링 빈이 됨
    
- AppConfig 스프링 빈을 조회해서 클래스 정보를 출력한 결과를 보면, 예상과는 다르게 클래스 명에 **xxxCGLIB**가 붙어있음
    
    ![5-3](https://user-images.githubusercontent.com/96513157/215772515-ff46e0d5-c37b-4197-9a58-715d0fa26697.png)
    
    ➡️ 내가 만든 클래스가 아니라, 스프링이 **바이트코드 조작 라이브러리(CGLIB)**를 사용해서 **AppConfig** 클래스를 상속받은 **임의의 다른 클래스**를 만들고, 그 다른 클래스를 **스프링 빈**으로 등록한 것
    
- **AppConfig@CGLIB**에 의해  ****`@Bean`이 붙은 메서드마다 이미 스프링 빈이 존재하면, 존재하는 빈을 반환 & 스프링 빈이 없으면, 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만듬
    
    ➡️ 덕분에 싱글톤이 보장됨
    

- **@Configuration을 적용하지 않고, @Bean만 적용하면 어떻게 될까?**
    - `@Configuration`을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 **싱글톤**을 보장함
    - `@Configuration`을 지우고, `@Bean`만 적용하고 똑같이 코드를 실행하면
        
        ➡️ 출력 결과 : bean = class hello.core.AppConfig
        
    - **AppConfig**가 CGLIB 기술 없이 순수한 **AppConfig**로 스프링 빈에 등록된 것을 확인 가능

<**정리>**

- `@Bean`만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않음
    - `memberRepository()`처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때, 싱글톤을 보장하지 않음
- 스프링 설정 정보는 항상 **@Configuration**을 사용하기 !
  </details>
  
  <details>
  <summary>sec6 컴포넌트 스캔</summary>
  </details>
  
## 🏆 소감 
  
