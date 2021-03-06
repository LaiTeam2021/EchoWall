-- users password 12345678
INSERT INTO users(id, username, email, password) VALUES(1, 'test123', 'developerforfun2020@gmail.com', '$2a$10$NiRFezagti1J8Nk7uGwbKeIA8ADF14pM8OLS6gc5hU0Tf5gXo92Me') ON CONFLICT DO NOTHING;
INSERT INTO users(id, username, email, password) VALUES(2, 'gary', 'garywangop@gmail.com', '123') ON CONFLICT DO NOTHING;

-- whitelist
INSERT INTO whitelist(user_id) VALUES (1) ON CONFLICT DO NOTHING;

-- gender_type
INSERT INTO gender_type(gender) VALUES ('male') ON CONFLICT DO NOTHING;
INSERT INTO gender_type(gender) VALUES ('female') ON CONFLICT DO NOTHING;

-- profile
INSERT INTO profile(user_id, avatar_url, gender, dob) VALUES (1, 'https://pa1.narvii.com/6404/35b2929ca438e295554d2460707145d35456f2c2_128.gif', 1, '5/26/1991') ON CONFLICT DO NOTHING;
INSERT INTO profile(user_id, avatar_url, gender, dob) VALUES (2, 'https://i.pinimg.com/originals/33/32/6d/33326dcddbf15c56d631e374b62338dc.jpg', 1, '1/16/1990') ON CONFLICT DO NOTHING;

-- follow
INSERT INTO follow(user_id, follow_id, create_date) VALUES (1, 2, '3/5/2021') ON CONFLICT DO NOTHING;
INSERT INTO follow(user_id, follow_id, create_date) VALUES (2, 1, '3/4/2021') ON CONFLICT DO NOTHING;