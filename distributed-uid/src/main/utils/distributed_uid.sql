create database distributed_uid;

use distributed_uid;

create table uid (
id bigserial primary key not null,
uid UUID NOT NULL DEFAULT gen_random_uuid(),
distributed_uid bigint unique not null,
created_at timestamptz default now(),
duid_timestamp bigint not null,
duid_mid_id int2 not null,
duid_sequence int2 not null);

select * from uid;
