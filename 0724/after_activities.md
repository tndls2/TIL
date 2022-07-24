### [ 활동소감 ]

1. TypeORM@0.3.0부터 @EntityRepository를 쓸 수 없다고 한다…슬프다… 직접 repository파일을 만들어서 생성하고, import export를 하여야 한다고 한다.. 열심히 하고 있다. ..

### [ 활동내용 ]
- 진행상황
    - src/auth에 카카오로그인 관련 코드 작성 중
- **yaml** 파일 (야믈)
    - 데이터 전송 파일
    - xml vs json vs yaml : [https://www.inflearn.com/questions/16184](https://www.inflearn.com/questions/16184)
- **API**
    - 프론트엔드 → 백엔드에 데이터 요청 : 사용규칙 제공
- **MVC 패턴** : [https://m.blog.naver.com/jhc9639/220967034588](https://m.blog.naver.com/jhc9639/220967034588)
- **JWT**(Json Web Token)
    - 인증에 필요한 정보를 암호화 시킨 json 토큰
    - 헤더.페이로드.시그니처
    - [https://inpa.tistory.com/entry/WEB-📚-JWTjson-web-token-란-💯-정리](https://inpa.tistory.com/entry/WEB-%F0%9F%93%9A-JWTjson-web-token-%EB%9E%80-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC)
- **passport** 라이브러리
    - 인증 미들웨어
    
    ```bash
    $ npm install --save @nestjs/passport passport passport-kakako
    $ npm install --save-dev @types/passport-kakao
    ```
    
- **entity** cs **repository**
    - repository : Entity에 의해 생성된 DB에 접근하는 메서드(ex. findAll() )를 사용하기 위한 인터페이스
        
        [https://whitepro.tistory.com/265](https://whitepro.tistory.com/265)
        
- Nest cli를 이용한 코드 생성
    - nest에서 cli로 코드를 생성할 수 있도록 지원
    - `Controller` 와 `Service` 코드 생성 : 원하는 디렉토리로 이동 → 다음과 같이 [명령어](https://docs.nestjs.com/cli/usages#nest-generate)를 적어준다.
    
    ```bash
    $ nest g module asdf
    $ nest g controller asdf
    $ nest g service asdf
    ```
    
- **Module** : 의존성을 주입 하기 위해서 `Module`코드의 `@Module` 애노테이션에 설정에 맞춰서 클래스를 넣어야 함
    - `imports` : 다른 모듈에서 `export`한 모듈을 사용하기 위함
    - `controllers` : 해당 모듈에서 사용할 `controller` 클래스를 작성
    - `providers`: 해당 모듈에서 사용할 `service` 클래스를 작성
    - `exports` : 다른 모듈에서 사용하기 위한 클래스를 작성
- **Controller**
    - `@Controller()` : 해당 클래스가 Controller 임을 명시
        
        (괄호안에 적인 텍스트 →URL 경로 의미함 - http://localhost:3000/login으로 호출하면 해당 클래스로 요청이 옴)
        
    - `@Get()` : login으로 요청이 왔을 때, GET 메서드일 경우 해당 메서드를 실행시킴
        
         - GET [http://localhost:3000/login](http://localhost:3000/login) 요청일 경우 실행
        
         - `@Get('test')`로 작성할 경우 -> GET http://localhost:포트/login/test 로 요청이 와야지 login()메서드가 실행
        
    - main.ts 코드가 실행되면서 `NestApplication`이 생성이되고 서버가 실행이 됨 → 이때 `AppModule` 이 등록되면서 시작됨
    - 프로젝트에서 모듈이 추가될 때 마다 `AppModule` 에 등록을 해주어야함 !
    - `npm run start:dev`→ main.ts 실행 -> AppModule 등록 (AppModule에 등록된 모든 코드가 등록됨)
- **Service** : 비즈니스 로직 작성
    - `@Injectable` - 의존성 주입을 할 수 있는 클래스라고 명시하는 애노테이션
    
    [https://velog.io/@junsugi/Nest.js-로그인-서비스-만들어보기-1](https://velog.io/@junsugi/Nest.js-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%84%9C%EB%B9%84%EC%8A%A4-%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0-1)
