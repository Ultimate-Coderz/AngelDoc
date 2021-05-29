
drop table users

create sequence coordinator_seq
start with 1
increment by 1;

select coordinator_seq.nextval from dual;

drop table coordinator
drop sequence coordinator_seq;

create sequence doctor_seq
start with 3000000
increment by 1;

drop table doctor
drop sequence doctor_seq

create sequence patient_seq
start with 5000000
increment by 1;

drop table patient
drop sequence patient_seq

create sequence appointment_seq
start with 1
increment by 1;

drop table appointment;
drop sequence appointment_seq

commit
