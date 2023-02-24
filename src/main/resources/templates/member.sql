CREATE TABLE tbl_member (
    memberId Long auto_increment,
    name varchar (30) not null,
    password varchar (10) not null,
    primary key(memberId)
);
INSERT INTO tbl_member( name, password ) VALUES ('박수인', 'asdf!');