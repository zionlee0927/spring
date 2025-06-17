create table manager (
    id char(26) not null primary key,
    account varchar(255) not null comment '계정',
    password varchar(255) not null comment '비밀번호',
    status varchar(20) not null comment '상태',
    name varchar(12) not null comment '이름',
    email varchar(255) not null comment '이메일',
    phone char(13) not null comment '전화번호',
    roles varchar(255) not null comment '권한',
    type varchar(20) not null comment '매니저 타입',
    created_at timestamp not null comment '생성 시간',
    constraint uk_account unique (account)
) comment = '관리자 정보' DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table member (
    id char(26) primary key,
    manager_id char(26) not null comment '관리자 아이디',
    account varchar(255) not null comment '계정',
    password varchar(255) not null comment '비밀번호',
    status varchar(20) not null comment '상태',
    name varchar(255) not null comment '이름',
    birthday date not null comment '생년월일',
    gender varchar(10) not null comment '성별',
    education varchar(20) not null comment '학력',
    phone varchar(255) not null comment '전화번호',
    protector_phone varchar(255) not null comment '보호자 연락처',
    memo varchar(500) null comment '메모',
    created_at timestamp null comment '생성 시간',
    constraint uk_account unique (account)
) comment = '회원 정보' DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table authentication (
    id char(26) not null primary key comment '아이디',
    platform_type varchar(10) not null comment '인증 플랫폼. 이메일, 모바일',
    target varchar(255) not null comment '이메일 주소 or 전화번호',
    token varchar(255) null comment '인증용 임시 토큰',
    number char(6) not null comment '인증번호 6 자리',
    created_at timestamp not null comment '생성 시간'
) comment = '임시 인증 정보' DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;