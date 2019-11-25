delete
from error;
delete
from users;

INSERT INTO error (id, archived_at, created_at, DESCRIPTION, environment, level, origin, title, userid)
VALUES (1, now(), now(), 'NULL POINTER', 'PRODUCTION', 'ERROR', '127.0.0.1', 'NULL POINTER', 1)

