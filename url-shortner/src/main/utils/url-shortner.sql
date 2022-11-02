create database url_shortner;

create table short_url(
	id BIGSERIAL primary key not null,
	distributed_uid bigint unique not null,
	long_url text not null,
	short_code text unique not null,
	short_url text unique not null,
	created_at timestamptz not null default now(),
	expire_at timestamptz not null
);
