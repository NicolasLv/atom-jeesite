
drop table if exists adm_mutex_ticket;
CREATE TABLE adm_mutex_ticket (
    name varchar(64) primary key,
    value bigint,
    stamp bigint
)Engine=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO adm_mutex_ticket VALUES('SEQ-MUTEX-TICKET', 0, 0);
