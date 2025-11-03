# 7주차 과제

### 스프링 어노테이션
- 코드 사이에 특별한 의미, 기능을 수행하도록 하는 기술.
- 함수의 행동을 바꾸지는 않지만, 어노테이션에 대응 하는 processor가 어노테이션에 맞는 적절한 행동을 하게 해주는 마커.


- 사용 순서:
    1. 어노테이션 정의
    2. 클래스에 어노테이션 배치
    3. 실행 중, reflection 이용 -> 추가 정보 획득 -> 기능 실시

- 종류
  - @Configuration: Bean 정의의 소스로 쓰이는 클래스 마크.
  - @Bean: 메서드에서 반환된 결과를 빈으로 저장하는 애너테이션.
  - @Autowired: 스프링이 의존성 주입을 할 클래스를 명시하는 마크.
  - @Override: 


### 스프링 컨테이너
 - 컨테이너가 생성하고 관리하는 객체 = bean(어노테이션으로 추가 할 수 있음)
 - 스프링 컨테이너 = bean의 생명주기를 관리하는(생성/관리/제거) 역할.
 - 생성자의 사용을 줄여 -> 객체간의 결합도를 낮출 수 있음.
 - 객체들의 의존성을 줄이고 인터페이스만 참조하도록 설계 가능.
 - 컨테이너, 애플리케이션 시작할 때 빈 정의를 로드 -> 인스턴스 생성

### 스프링의 의존성 주입
 - AppConfig를 사용하여 직접 의존성을 주입하지 않고 Spring Boot에서 자동으로 객체간 의존관계를 설정해주는 것.
 - @Component 어노테이션이 붙은 클래스들을 스캔한다.
 - 스캔한 클래스들을 빈으로 등록한다.
   - @Autowired 어노테이션이 붙은 클래스들 타입의 빈을 찾아 의존성을 주입한다.
     - ex)
        @Component // 빈으로 등록할 객체
        public interface MyRepository{

        }
    
        public class MyService { 
          private final MyRepository myRepository;
          
          @Autowired // 여기에 의존성 주입을 해줘..!
          public MyService(MyRepository myRepository){
            this.myRepository = myRepository;
          }  
        }