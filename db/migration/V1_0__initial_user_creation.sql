CREATE TABLE users (
    id uuid DEFAULT gen_random_uuid(),
	username varchar(50) NOT NULL,
	password varchar(256) NOT NULL,
	enabled boolean not null,
	PRIMARY KEY (id)
);

CREATE UNIQUE index ix_user_username on users(UPPER(username));

CREATE TABLE authorities (
    id uuid DEFAULT gen_random_uuid(),
    user_id uuid NOT NULL,
	username varchar(50) NOT NULL,
	authority varchar(50) NOT NULL,
	CONSTRAINT fk_authorities_users FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE UNIQUE index ix_auth_username on authorities(user_id,authority);