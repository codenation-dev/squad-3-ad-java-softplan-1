delete
from error;
delete
from users;

INSERT INTO users (user_id, email, name, password, created_at)
VALUES (1, 'teste@testemail.com', 'teste', 'teste', now());

INSERT INTO error (id, archived_at, created_at, DESCRIPTION, environment, level, origin, title, user_id)
VALUES (1, now(), now(), 'NULL POINTER', 'PRODUCTION', 'ERROR', '127.0.0.1', 'NULL POINTER', 1);