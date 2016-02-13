create table SearchHistory(
  id serial primary key,
  word varchar(255) not null,
  memo text,
  resister_day date default ('now':text)::date,
  activation boolean default TRUE
);

CREATE TABLE users (
  user_id SERIAL primary key,
  user_name varchar(008) not null,
  pw varchar(008) not null,
  registered date default('now'::text)::date,
  activation boolean default true
);

CREATE TABLE tagMaster (
  tag_id SERIAL primary key,
  user_id int not null,
  tag_name varchar(255) not null,
  tag_regi_date date default ('now'::text)::date,
  activation boolean default TRUE,
  tag_color varchar(255)
);

CREATE TABLE rel_tag_word (
  id int,
  tag_id int,
  registered_date date default ('now'::text)::date,
  activation boolean default TRUE
);
