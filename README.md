# Spring-boot-rest-docs-demo
Spring-boot-rest-docs-junit5-demo

# 내용
스프링 부트 기반 Rest Docs Demo.
API 문서 자동화를 위한 방법.   

# 개발 환경
JDK 11, STS 4.8.1,  Spring Boot 2.6.3, Maven 4   

# 설명
Test Unit 으로는 Junit 5 사용.    
다른 Test Unit 도 사용 가능.   
Test Unit 4 예제는 https://github.com/codejcd/spring-boot-rest-docs-junit4-demo.git 참고.   
MockMvc, WebTestClient, REST Assured 중 MVC 패턴의 API 문서를 위한 것이므로 MockMvc 사용.   
snippet 을 include 하여 최종적으로 html 포맷의 문서 생성을 위해 asciidoc 사용하므로 해당 문서 작성법을 알아야함.   
상세 설명은 블로그 글을 https://codejcd.tistory.com/15 참고.   

# 테스트
1. maven-resources-plugin 에 snippet 과 최종 결과문서가 생성될 위치 설정.
2. src/test/java 경로의 Test 코드(@Test) 작성.
3. Test 통과하면 mvn install 하여 /target/generated-snippets/~ 생성.
4. 생성된 snippet 토대로 최종 결과문서를 작성할 /src/main/asciidoc/api-docs.adoc 작성. 
5. mvn install 하여 최종 결과문서인 api-docs.html 생성.   

# 참고
https://docs.spring.io/spring-restdocs/docs/2.0.6.RELEASE/reference/html5   
https://www.baeldung.com/spring-rest-docs   
