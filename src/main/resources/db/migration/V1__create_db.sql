create table clients(
ID bigint primary key not null,
name varchar(200) not null check (length(name)>=3));

create table planets(
ID varchar primary key not null CONSTRAINT id_format CHECK (id ~ '^[A-Z0-9]+$'),
name varchar(500) not null check (length(name)>=1));

create table tickets(
ID bigint primary key not null,
created_at TIMESTAMP,
client_id bigint,
from_planet_id varchar,
to_planet_id varchar,

FOREIGN KEY (client_id) REFERENCES clients(ID),
FOREIGN KEY (from_planet_id) REFERENCES planets(ID),
FOREIGN KEY (to_planet_id) REFERENCES planets(ID)
);

create sequence clients_seq start 1;
create sequence tickets_seq start 1;


alter table clients alter column ID set default nextval('clients_seq');
alter table tickets alter column ID set default nextval('tickets_seq');

