# 더 모으자 티끌, Themoti ReadMe

## 🔑 프로젝트 기술 스택
**Manner Smoker 의 Backend Api**
*Spring Boot 2.6.6
Gradle
Java 11
Spring Data JPA
Spring Web
Spring Security
MySQL
Lombok*

## :dart: 컨벤션
- GitHub Flow
- Rebase Merge
- Package By Feature


## 🚀 프로그래밍 요구사항
- Issue 생성 > Branch 생성 > PR 생성
- Branch name ex) `[feat/user]`
- PR 에 뭘 어떻게 구현했는지 기술
- PR 별 commit 수는 최대한 적게
- 모르는 건 Issue 급한 건 카톡 하세요.
- 자주 issue / PR 확인, **일정에 맞게 프로젝트 진행하기 !!**

## 🏗️ 기능 목록 및 기능 요구사항

### 1. 화면 구성에 따른 정리

    - 1.1 로그인
        - 소셜로그인
    - 1.2 Home
        - 흡연 버튼을 통해 흡연량 수집
        - 사용자 개인 커스텀
            - 배경색
            - 뱃지
            - 업적
        - 흡연량
            - 년월일 별 체크
            - 갯수
    - 1.3 MAP
        - 위도, 경도 or 주소로 return
        - 흡연장 검색
        - 흡연장 목록 가져오기
        - 즐겨찾기
        - 새로운 흡연장 등록
        - 등록 요청된 장소 리스트 가져오기
    - 1.4 Community
        - 글 CRUD
        - 댓글 CRUD
    - 1.5 My
        - Home 과 상당 부분 동일
    - 1.5.1 업적
        - 후순위
    - 1.6 NEWS 
        - 후순위
    - 1.7 background
        - 푸시기능
            - 일정 흡연량을 넘겼을 시, 건강 주의를 요하는 푸시 알람 기능 사용
    - 1.8 관리자 페이지

### 2. Entity 별 정리 + 필요한 기능
    - User
        - 소셜로그인을 지원한다.
        - 즐겨찾기한 흡연장을 가진다.
        - 개인별 흡연량을 가진다.
        
    - 흡연장
        - 공공 데이터 포털에서 흡연장 위치에 대한 data를 받은 후, 위도 경도 또는 주소로 return 해준다.
        - 사용자 별 즐겨찾기를 제공한다.
        
    - 흡연량
        - 년, 월, 일별로 흡연량 data를 가진다.
        - 흡연장 검색 후 흡연 버튼을 누르면, 그 날의 흡연량이 +1이 된다.
        - 년별, 월별, 일별 정해진 흡연량을 넘겼을 시, 건강 주의를 요하는 푸시알람을 보낸다.
        
    - Community
        - 흡연장에 대한 후기 등을 제공한다.
        - Post
        - Reply
