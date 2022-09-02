create database db1;
create database db2;

use db1;
use db2;

create table test
(
  id bigint not null primary key,
  name varchar(200) not null
);
use db1;
insert into test values(1, "db-1 Toji");
insert into test values(2, "db-1 Toji");
insert into test values(3, "db-1 Toji");

use db1;
insert into test values(1, "db-2 Toji");
insert into test values(2, "db-2 Toji");
insert into test values(3, "db-2 Toji");

select * from test;


# show global variables like 'wait_timeout';
# SET @@GLOBAL.wait_timeout=10;