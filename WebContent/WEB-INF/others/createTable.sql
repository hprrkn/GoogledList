create table SearchHistory(
  id serial primary key,
  searchword varchar(255) not null,
  memo text,
  resister_day date default 'now',
  activation boolean default TRUE
);

CREATE TABLE users (
  user_id SERIAL primary key,
  user_name varchar(008) not null,
  pw varchar(008) not null,
  registered date default('now'::text)::date,
  activation boolean default true
);
