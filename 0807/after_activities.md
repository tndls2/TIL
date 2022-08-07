- GET http://localhost:3000/auth/kakao
- GET http://localhost:3000/auth/kakao/callback
    
    → 카카오계정 로그인 페이지 
    

1. 사용자가 카카오 로그인 요청
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a3fe6fd7-30fe-4cbe-a97d-e42da45c34b8/Untitled.png)
    
2. 인가 코드 받기
    - AuthController의 `kakaoLoginCallback`함수
    - 사용자에게 동의화면 출력
    - 사용자가 [동의하고 시작하기] 버튼 클릭
    - 카카오 인증 서버가 해당 사용자에 대한 인가 코드를 발급
    
    → 서비스 앱의 Redirect URI로 인가 코드를 전달
    
3. 토큰 받기
    - AuthService의 `kakaoLogin` 함수
    - 토큰 : (1) Access token, (2) Refresh token
    - 토큰은 동의 화면을 통해 사용자 동의를 받은 인가 정보를 포함
4. 로그인 완료
