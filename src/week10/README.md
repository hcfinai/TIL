# Likelion Member & Assignment Manager

멋쟁이사자처럼(Likelion) 동아리의 멤버(LION/STAFF)와 과제를 관리하는 Spring Boot 기반 REST API 서버입니다. 프론트엔드 정적 페이지를 통해 멤버 등록/조회/수정/삭제, 과제 등록/조회/검색/수정/삭제 기능을 제공하며, 전역 예외 처리로 일관된 에러 응답을 반환합니다.

## 기술 스택

| 구분 | 기술 | 버전 |
|---|---|---|
| Language | Java | 17 |
| Framework | Spring Boot | 3.5.14 |
| Data Access | Spring Data JPA (Hibernate) | Spring Boot 3.5.14 관리 버전 |
| Database | MySQL | 8.x |
| DB Driver | mysql-connector-j | Spring Boot 3.5.14 관리 버전 |
| Build Tool | Gradle (Wrapper) | - |
| Frontend | HTML / CSS / Vanilla JavaScript (정적 리소스) | - |

## 실행 방법

### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd TIL/src/week10
```

### 2. MySQL 데이터베이스 준비

로컬에 MySQL 서버가 실행 중이어야 하며, 아래 이름으로 데이터베이스를 생성합니다.

```sql
CREATE DATABASE likelion_pbl CHARACTER SET utf8mb4;
```

### 3. 데이터베이스 접속 정보 설정

`spring.datasource.username`, `spring.datasource.password`는 [application.properties](src/main/resources/application.properties)에서 환경변수로 주입받도록 되어 있습니다(비밀번호를 코드/저장소에 하드코딩하지 않기 위함). 실행 전 환경변수를 설정하세요.

```properties
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
```

```bash
# macOS / Linux
export DB_USERNAME=root
export DB_PASSWORD={본인의 MySQL 비밀번호}

# Windows (PowerShell)
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "{본인의 MySQL 비밀번호}"
```

> `spring.jpa.hibernate.ddl-auto=create`로 설정되어 있어, 애플리케이션을 시작할 때마다 테이블이 새로 생성됩니다(기존 데이터는 초기화됩니다).

### 4. 애플리케이션 실행

```bash
./gradlew bootRun
```

Windows에서는 다음 명령을 사용합니다.

```bash
gradlew.bat bootRun
```

### 5. 접속 확인

브라우저에서 아래 주소로 접속하면 프론트엔드 화면이 표시됩니다.

```
http://localhost:8080
```

## API 목록

### Member API

| HTTP 메서드 | URI | 설명 |
|---|---|---|
| GET | `/members` | 전체 멤버 목록 조회 (`?part=` 쿼리 파라미터로 파트별 필터링) |
| GET | `/members/{id}` | 멤버 단건 조회 |
| POST | `/members/lions` | LION(아기사자) 멤버 등록 |
| POST | `/members/staffs` | STAFF(운영진) 멤버 등록 |
| PUT | `/members/lions/{id}` | LION 멤버 정보 수정 |
| PUT | `/members/staffs/{id}` | STAFF 멤버 정보 수정 |
| DELETE | `/members/{id}` | 멤버 삭제 |

### Assignment API

| HTTP 메서드 | URI | 설명 |
|---|---|---|
| POST | `/members/{memberId}/assignments` | 특정 멤버에게 과제 등록 |
| GET | `/members/{memberId}/assignments` | 특정 멤버의 과제 목록 조회 |
| GET | `/assignments` | 전체 과제 목록 조회 |
| GET | `/assignments/{id}` | 과제 단건 조회 |
| GET | `/assignments/search?keyword=` | 제목에 키워드가 포함된 과제 검색 |
| PUT | `/assignments/{id}` | 과제 수정 |
| DELETE | `/assignments/{id}` | 과제 삭제 |

### 에러 응답

모든 예외 상황은 `GlobalExceptionHandler`가 아래와 같은 공통 형식으로 응답합니다.

```json
{
  "status": 404,
  "message": "과제를 찾을 수 없습니다. id=999"
}
```

| 상황 | 상태 코드 |
|---|---|
| 멤버를 찾을 수 없음 | 404 Not Found |
| 과제를 찾을 수 없음 | 404 Not Found |
| 중복된 이름으로 멤버 등록 | 409 Conflict |

## 프로젝트 구조

```
src/main/java/com/likelion/pbl
├── MemberApplication.java        # 스프링 부트 실행 클래스
├── member                        # 멤버 도메인
│   ├── controller                # 멤버 API 엔드포인트
│   ├── domain                    # Member 엔티티, RoleType(LION/STAFF) enum
│   ├── dto                       # 요청/응답 DTO
│   ├── repository                # MemberRepository (Spring Data JPA)
│   └── service                   # 멤버 관련 비즈니스 로직
├── assignment                    # 과제 도메인
│   ├── controller                # 과제 API 엔드포인트
│   ├── domain                    # Assignment 엔티티
│   ├── dto                       # 요청/응답 DTO
│   ├── repository                # AssignmentRepository (Spring Data JPA)
│   └── service                   # 과제 관련 비즈니스 로직
└── global                        # 도메인에 종속되지 않는 공통 모듈
    ├── dto                       # ErrorResponse 등 공통 응답 DTO
    └── exception                 # 커스텀 예외, GlobalExceptionHandler

src/main/resources
├── application.properties        # 데이터소스, JPA 설정
└── static                        # 프론트엔드 정적 리소스
    ├── index.html                 # 화면 구조, 탭 전환, HTTP 통신 래퍼
    ├── css/style.css               # 스타일
    └── js
        ├── member.js               # Member API 호출 및 UI 로직
        └── assignment.js           # Assignment API 호출 및 UI 로직
```
