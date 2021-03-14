CREATE SEQUENCE user_sequence START with 10001 increment by 1;
CREATE SEQUENCE profile_sequence START with 10001 increment by 1;

CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50) UNIQUE NOT NULL,
    email       VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(255)       NOT NULL,
    create_date TIMESTAMP          NOT NULL DEFAULT NOW(),
    is_active   BOOLEAN                     DEFAULT TRUE
);

CREATE TABLE whitelist
(
    user_id BIGINT UNIQUE NOT NULL REFERENCES users (id)
);

CREATE TABLE gender_type
(
    id     BIGSERIAL PRIMARY KEY,
    gender VARCHAR(50) NOT NULL
);

CREATE TABLE profile
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL UNIQUE REFERENCES users (id),
    avatar_url VARCHAR(512),
    gender_id  int    NOT NULL REFERENCES gender_type (id),
    residence  TEXT DEFAULT 'Mars',
    dob        DATE,
    about_me   TEXT
);

CREATE TABLE post_type
(
    id   SERIAL PRIMARY KEY,
    type VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE topic
(
    id          SERIAL PRIMARY KEY,
    create_date DATE                NOT NULL DEFAULT NOW(),
    name        VARCHAR(255) UNIQUE NOT NULL,
    name_cn     VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE location
(
    id       SERIAL PRIMARY KEY,
    zip_code VARCHAR(20) UNIQUE  NOT NULL,
    name     VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE post
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT NOT NULL REFERENCES users (id),
    title        TEXT   NOT NULL,
    context      TEXT   NOT NULL,
    create_date  DATE   NOT NULL DEFAULT NOW(),
    location_id  BIGINT REFERENCES location (id),
    post_type_id BIGINT NOT NULL REFERENCES post_type (id),
    is_deleted   BOOLEAN         DEFAULT FALSE
);

CREATE TABLE post_topics
(
    post_id  BIGINT NOT NULL REFERENCES post (id),
    topic_id BIGINT NOT NULL REFERENCES topic (id),
    PRIMARY KEY (post_id, topic_id)
);

CREATE TABLE favorite_topics
(
    user_id  BIGINT NOT NULL REFERENCES users (id),
    topic_id BIGINT NOT NULL REFERENCES topic (id),
    PRIMARY KEY (user_id, topic_id)
);

-- users password 12345678
INSERT INTO users(id, username, email, password)
VALUES (1, 'test123', 'developerforfun2020@gmail.com', '$2a$10$NiRFezagti1J8Nk7uGwbKeIA8ADF14pM8OLS6gc5hU0Tf5gXo92Me');

-- whitelist
INSERT INTO whitelist(user_id)
VALUES (1);

-- gender_type
INSERT INTO gender_type(id, gender)
VALUES (1, 'male');

-- profile
INSERT INTO profile(id, user_id, avatar_url, gender_id, dob)
VALUES (1, 1, 'https://pa1.narvii.com/6404/35b2929ca438e295554d2460707145d35456f2c2_128.gif', 1, '5/26/1991');

-- post_type
INSERT INTO post_type(id, type)
VALUES (1, 'normal');
INSERT INTO post_type(id, type)
VALUES (2, 'anonymous');

-- topic
INSERT INTO topic(id, name, name_cn)
VALUES (1, 'wandering', '灌水');
INSERT INTO topic(id, name, name_cn)
VALUES (2, 'job', '职场');
INSERT INTO topic(id, name, name_cn)
VALUES (3, 'campus', '校园');
INSERT INTO topic(id, name, name_cn)
VALUES (4, 'immigration', '移民');

-- post
INSERT INTO post(id, user_id, title, context, post_type_id)
VALUES (1, 1, '第一灌水', '<b>呀呀呀</b 测试水贴', 1);

-- post_topic
INSERT INTO post_topics(post_id, topic_id)
VALUES (1, 1);
INSERT INTO post_topics(post_id, topic_id)
VALUES (1, 2);