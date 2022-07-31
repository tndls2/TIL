### [ 활동소감 ]

1. configService를 활용하는 방법을 배워야겠다.
2. github에 익숙하지 않은 거 같다. 좀 더 활용하는 방법에 대해 계속 공부해야겠다.

### [ 활동내용 ]

### 1. passport

- 사용자 인증을 구현하는 Node.js 미들웨어
- **Strategies ( Strategy ) -로그인 인증 방식**
    - 이를 이용하여 사용자 요청에 대한 인증을 처리
    1. Local Strategy(passport-local) : 로컬 DB에서 로그인 인증 방식
    2. Social Authentication (passport-kakao, passport-twitter 등) : 소셜 네트워크 로그인 인증 방식
    - 대표적으로 localLocalStrategy, Oauth , OpenID.
        - **localLocalStrategy** : 직접 유저에게 신규 가입(아이디/패스워드)를 통하여 인증
        - **Oauth** : 외부 서비스를 통하여 access Token을 발급받음
            
            [https://inpa.tistory.com/491](https://inpa.tistory.com/491)
            
        - **OpenId** : 외부 서비스를 통하여 ID Token을 발급받음 ( Oauth 2.0을 이용하여 만들어진 기술 )
        
        [https://mingoogle.tistory.com/10](https://mingoogle.tistory.com/10)
        
- **bcrypt** : 비밀번호 암호화

## 2. guards

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/99d6f3ed-b6c7-4ce4-bbf4-fb4ab25e0218/Untitled.png)

- Guards 는 모든 middleware의 다음에 실행되고, interceptor 나 pipe 이전에 실행됨
- **Authorization Guard**
    - 특정 routes 들은 충분한 permission 이 주어지고 난 후에만 가능 (해당 유저가 아닐 경우 해당 유저의 정보를 수정할 수 없어야 함)

- 뱃지 회의 결과
    - 뱃지 시스템 종류
        
        : 산, 바다/계곡, 호캉스/호텔, 축제, 캠핑, 야경 (별자리, 도시), 액티비티 (+놀이공원), 박물관/역사, 해외 여행, 쇼핑
        
    - 뎁스: 금은동
    - 사용자 게시물로 분야별 누적해놓기
    - 분야나 종류는 점점 확장
