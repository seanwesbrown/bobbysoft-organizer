ALTER TABLE authorities
DROP COLUMN username;

ALTER TABLE users
ADD COLUMN email varchar(100),
ADD COLUMN avatar_url varchar(200);

CREATE UNIQUE index ix_user_email on users(UPPER(email));
