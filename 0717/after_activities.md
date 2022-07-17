### [ 활동내용 ]

- github 세팅
    
     - [https://velog.io/@hyowon_lee/Git-GitHub로-협업하기-Forking-Workflow](https://velog.io/@hyowon_lee/Git-GitHub%EB%A1%9C-%ED%98%91%EC%97%85%ED%95%98%EA%B8%B0-Forking-Workflow)
    
    [https://seungwubaek.github.io/tools/git/contributing_using_pull_request](https://seungwubaek.github.io/tools/git/contributing_using_pull_request/)
    
     - 위의 사이트를 바탕으로 협업 세팅
    
- 개발환경 구성
    
     - mysql 설치 확인
    
     - Node v17 → v16로 변경 (nvm이용)
    
- 카카오 REST API 사용법 확인 & kakao developer에서 애플리케이션 생성
    
     - [https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api](https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api)
    
     - [https://codegear.tistory.com/86?category=1034916](https://codegear.tistory.com/86?category=1034916)
    
    [Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/31e84dc8-732e-4ddc-b2fc-1224784ae667/Untitled.png)

- TypeORM 공부
    - **ORM**(Object Relational Mapping, 객체-관계 매핑)
        
         - 각 테이블 또는 구분하고자 하는 데이터 단위로 객체 구현, 데이터 간의 관계 형성
        
         - **객체**와 RDB의 **데이터**를 자동으로 매핑해주는 framework
        
         - OOP는 클래스 사용, RDB는 테이블 사용 → 객체 모델과 관계형 모델 간의 불일치 존재 
        
        ⇒ ORM으로 객체 간 관계를 바탕으로 SQL을 자동으로 생성하여 불일치 해결(→객체를 통해 간접적으로 DB의 데이터를 다룸)
        
        (참조 : [https://eun-jeong.tistory.com/31](https://eun-jeong.tistory.com/31) , [https://gmlwjd9405.github.io/2019/02/01/orm.html](https://gmlwjd9405.github.io/2019/02/01/orm.html) )
        
    - **TypeORM**
        1. User entity 생성
            
             - entity : DB table에 대한 구조
            
            ```tsx
            // entity/User.js
            import { Entity, PrimaryGeneratedColumn, Column } from "typeorm"
            
            @Entity()
            export class User {
                @PrimaryGeneratedColumn()
                id: number
            
                @Column()
                firstName: string
            
                @Column()
                lastName: string
            
                @Column()
                age: number
            }
            ```
            
        2. class 이용하여 DB 접근
            
            ```tsx
            //app.ts
            import {createConnection} from "typeorm";
            import {User} from "./entity/User";
            
            createConnection(/*..*/).then(connection => {
            	let user = new User();
            	user.firstName = "Timber"
            	user.lastName = "Saw"
            	user.age = 25
            	
            	return connection.manager.save(user).then((user)=>{
            		//...
            	});
            }).catch(error => console.log(error));
            ```
            
        
        ( 참조 : [https://velog.io/@tilto0822/TypeORM-처음-만나본-혁신](https://velog.io/@tilto0822/TypeORM-%EC%B2%98%EC%9D%8C-%EB%A7%8C%EB%82%98%EB%B3%B8-%ED%98%81%EC%8B%A0) )
        
    - **Active Record vs Data Mapper**
        
         - 관련 글 : [https://aonee.tistory.com/77](https://aonee.tistory.com/77)
        
    - **기본 CRUD 작성**
        
         - 관련 글 : [https://medium.com/crocusenergy/nestjs-typeorm-기본-crud-작성하기-69b9640dc826](https://medium.com/crocusenergy/nestjs-typeorm-%EA%B8%B0%EB%B3%B8-crud-%EC%9E%91%EC%84%B1%ED%95%98%EA%B8%B0-69b9640dc826)
