# 일정 관리 시스템 (Scheduler)
이 프로젝트는 사용자가 일정을 생성, 조회, 수정 및 삭제할 수 있는 웹 애플리케이션입니다. 사용자는 비밀번호를 통해 자신이 생성한 일정만 수정하거나 삭제할 수 있습니다.

## 기능

- 일정 생성
- 일정 조회
- 모든 일정 조회
- 일정 수정
- 일정 삭제

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
