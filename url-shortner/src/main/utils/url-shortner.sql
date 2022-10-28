create database url_shortner;

create table users(
	id BIGSERIAL primary key not null,
	user_id UUID not null unique DEFAULT gen_random_uuid(),
	first_name text,
	last_name text,
	username text unique not null,
	created_at timestamptz not null default now()
);