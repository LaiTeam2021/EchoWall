-- drop all tables
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- profile test sql
SELECT username,
       email,
       password,
       create_date,
       is_active,
       avatar_url,
       gender
FROM users
         LEFT JOIN profile p on users.id = p.user_id
         LEFT JOIN gender_type gt on p.gender_id = gt.id;

-- post test sql
SELECT post.title,
       post.context,
       u.username,
       t.name,
       t.name_cn
FROM post
         INNER JOIN users u on post.user_id = u.id
         INNER JOIN post_type pt on post.post_type_id = pt.id
         INNER JOIN post_topics p on post.id = p.post_id
         INNER JOIN topic t on p.topic_id = t.id