<오늘 한 거>
* 계정 생성 시 사용할 CreateUserDto.ts 생성
* 계정 업뎃 시 사용할 UpdateUserDto.ts 생성
* 카카오 로그인 시, 개인정보 보호를 위해서 이름, 프로필 이미지 필수동의가 아니라 선택동의로 변경함 + 코드 수정 -> 비동의 시, 널 값으로 전달
* 로그아웃은 jwt 토큰을 만료 시키는 것이 아니라, 세션을 통해 구현해야함. clearCookie 사용하여 로그아웃 수행 후, 다시 로그인 로직을 실행하였을 때 새로운 쿠키값이 생성이 안되는 문제점 발생하여서 조금 더 공부한 다음에 완성 시킬 얘정
* userService 코드 작성 (계정 생성, 업뎃, 탈퇴 로직)
