# 일정 관리 시스템 (Scheduler)
이 프로젝트는 사용자가 일정을 생성, 조회, 수정 및 삭제할 수 있는 웹 애플리케이션입니다. 사용자는 비밀번호를 통해 자신이 생성한 일정만 수정하거나 삭제할 수 있습니다.

## 기능

- 일정 생성
- 일정 조회
- 모든 일정 조회
- 일정 수정
- 일정 삭제

## 클래스 구조

### Controller

#### `ScheduleController`
- **역할**: 일정 관리 기능에 대한 요청을 처리하는 클래스입니다. 클라이언트로부터 전달받은 요청을 서비스 계층으로 전달하고, 처리된 결과를 다시 클라이언트에게 반환합니다.
- **주요 기능**:
  - 일정 생성, 조회, 수정, 삭제와 관련된 HTTP 요청을 처리합니다.
  - 입력된 비밀번호를 검증하는 기능을 제공합니다.
  - API 경로와 메서드 매핑을 통해 클라이언트의 요청을 처리합니다.

### DTO (Data Transfer Object)

#### `ScheduleRequestDto`
- **역할**: 클라이언트로부터 전달받은 데이터를 캡슐화하는 클래스입니다. 주로 일정 생성 및 수정 요청에서 사용되며, 필요한 입력 값들을 담아 서비스 계층에 전달합니다.
- **주요 필드**:
  - `username`: 사용자 이름
  - `password`: 일정의 비밀번호
  - `title`: 일정의 제목
  - `contents`: 일정의 내용

#### `ScheduleResponseDto`
- **역할**: 클라이언트에게 반환할 데이터를 담는 클래스입니다. 주로 일정 조회 및 수정 작업이 완료된 후, 클라이언트에게 결과를 전달하는 데 사용됩니다.
- **주요 필드**:
  - `id`: 일정의 고유 ID
  - `username`: 사용자 이름
  - `title`: 일정의 제목
  - `contents`: 일정의 내용
  - `createdAt`: 일정의 생성 날짜
  - `updatedAt`: 일정의 수정 날짜

### Entity

#### `Schedule`
- **역할**: 데이터베이스와 매핑되는 도메인 객체로, 일정의 핵심 데이터를 정의합니다. 이 클래스는 데이터베이스 테이블의 행(row)과 매핑되며, 데이터베이스에 저장될 데이터를 나타냅니다.
- **주요 필드**:
  - `id`: 일정의 고유 식별자 (Primary Key)
  - `username`: 일정 작성자
  - `password`: 비밀번호 (비밀번호 확인 시 사용)
  - `title`: 일정 제목
  - `contents`: 일정 내용
  - `createdAt`: 일정 생성 시간
  - `updatedAt`: 일정 수정 시간

### Service

#### `ScheduleService`
- **역할**: 비즈니스 로직을 담당하는 계층입니다. 데이터 처리와 관련된 모든 로직은 서비스 계층에서 수행되며, 컨트롤러가 요청한 작업을 처리합니다. 주로 일정 생성, 조회, 수정, 삭제와 관련된 작업을 처리하고, 비밀번호 검증을 수행합니다.
- **주요 기능**:
  - 새로운 일정 생성
  - 특정 일정 조회
  - 일정 수정 및 삭제
  - 비밀번호 검증 로직 처리

### Repository

#### `ScheduleRepository`
- **역할**: 데이터베이스와 직접적으로 소통하는 계층입니다. MySQL과의 연동을 통해 데이터를 삽입, 조회, 수정, 삭제하는 작업을 수행합니다. 이 클래스는 일정 관련 데이터베이스 CRUD 작업을 정의합니다.
- **주요 기능**:
  - 데이터베이스에서 일정 데이터를 조회
  - 일정 데이터를 저장, 수정, 삭제

## 기술 스택
- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **Frontend**: HTML, CSS, JavaScript


##설치 및 실행

1. **MySQL 데이터베이스 생성**
    MySQL에 접속하여 다음 명령어로 데이터베이스를 생성합니다.
    ```sql
    CREATE DATABASE scheduler;
    USE scheduler;

    CREATE TABLE schedule (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL,
        password VARCHAR(255) NOT NULL,
        title VARCHAR(100) NOT NULL,
        contents TEXT NOT NULL,
        created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
    ```
2. **웹 애플리케이션 접속**
    브라우저에서 [http://localhost:8080/schedule.html](http://localhost:8080/schedule.html)로 접속합니다.

## API 명세서

### 1. 일정 생성 (Create Schedule)

- **HTTP Method**: `POST`
- **URL**: `/api/schedules`
- **Request Body**:
    ```json
    {
        "username": "사용자 이름",
        "password": "비밀번호",
        "title": "제목",
        "contents": "내용"
    }
    ```
- **Response**:
  - **Status Code**: `201 Created`
  - **Body**:
    ```json
    {
        "id": 1,
        "username": "사용자 이름",
        "title": "제목",
        "contents": "내용",
        "createdAt": "2024-01-01 10:00:00",
        "updatedAt": "2024-01-01 10:00:00"
    }
    ```

### 2. 일정 조회 (Get Schedule)

- **HTTP Method**: `GET`
- **URL**: `/api/schedules/{id}`
- **Path Parameter**:
  - `id`: 일정의 고유 ID
- **Response**:
  - **Status Code**: `200 OK`
  - **Body**:
    ```json
    {
        "id": 1,
        "username": "사용자 이름",
        "title": "제목",
        "contents": "내용",
        "createdAt": "2024-01-01 10:00:00",
        "updatedAt": "2024-01-01 10:00:00"
    }
    ```

### 3. 모든 일정 조회 (Get All Schedules)

- **HTTP Method**: `GET`
- **URL**: `/api/schedules`
- **Response**:
  - **Status Code**: `200 OK`
  - **Body**:
    ```json
    [
        {
            "id": 1,
            "username": "사용자 이름",
            "title": "제목",
            "contents": "내용",
            "createdAt": "2024-01-01 10:00:00",
            "updatedAt": "2024-01-01 10:00:00"
        },
        {
            "id": 2,
            "username": "사용자2",
            "title": "제목2",
            "contents": "내용2",
            "createdAt": "2024-01-02 11:00:00",
            "updatedAt": "2024-01-02 11:00:00"
        }
    ]
    ```

### 4. 일정 수정 (Update Schedule)

- **HTTP Method**: `PUT`
- **URL**: `/api/schedules/{id}`
- **Path Parameter**:
  - `id`: 수정할 일정의 고유 ID
- **Request Body**:
    ```json
    {
        "password": "비밀번호",
        "title": "수정된 제목",
        "contents": "수정된 내용"
    }
    ```
- **Response**:
  - **Status Code**: `200 OK`
  - **Body**:
    ```json
    {
        "id": 1,
        "username": "사용자 이름",
        "title": "수정된 제목",
        "contents": "수정된 내용",
        "createdAt": "2024-01-01 10:00:00",
        "updatedAt": "2024-01-02 12:00:00"
    }
    ```

### 5. 일정 삭제 (Delete Schedule)

- **HTTP Method**: `DELETE`
- **URL**: `/api/schedules/{id}`
- **Path Parameter**:
  - `id`: 삭제할 일정의 고유 ID
- **Request Body**:
    ```json
    {
        "password": "비밀번호"
    }
    ```
- **Response**:
  - **Status Code**: `204 No Content`
  - **Body**: 없음

### 6. 비밀번호 확인 오류 응답

- 비밀번호가 틀린 경우:
  - **Status Code**: `400 Bad Request`
  - **Body**:
    ```json
    {
        "error": "비밀번호가 틀렸습니다."
    }
    ```
## ERD
<img width="863" alt="썸네일" src="https://github.com/user-attachments/assets/6a685830-be09-46a2-8278-9af7de7c487c">
