
CREATE TABLE users (
    username varchar(128) PRIMARY KEY,
    password varchar(500) NOT NULL,
    fullname varchar(128) ,
    street varchar(500) ,
    city varchar(500) ,
    state varchar(500),
	zip varchar(128) ,
	enabled varchar(1) NOT NULL
);


CREATE TABLE authorities (
    USERNAME varchar(128) NOT NULL,
    AUTHORITY varchar(128) NOT NULL
);
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_UNIQUE UNIQUE (USERNAME, AUTHORITY);
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_FK1 FOREIGN KEY (USERNAME) REFERENCES USERS (USERNAME) ;

create table Ingredient (
id  varchar(4)  PRIMARY KEY not null,
name varchar(25) not null,
type varchar(10) not null
);
CREATE TABLE Taco (
id BIGINT PRIMARY KEY,
name varchar(50) not null,
createdAt timestamp not null
);

create table Taco_Ingredients (
    taco bigint PRIMARY KEY not null ,
    ingredient varchar(4) not null
);

alter table Taco_Ingredients
add foreign key (taco) references Taco(id);
alter table Taco_Ingredients
add foreign key (ingredient) references Ingredient(id);
create table Taco_Order (
id BIGINT PRIMARY KEY,
deliveryName varchar(50) not null,
deliveryStreet varchar(50) not null,
deliveryCity varchar(50) not null,
deliveryState varchar(2) not null,
deliveryZip varchar(10) not null,
ccNumber varchar(16) not null,
ccExpiration varchar(5) not null,
ccCVV varchar(3) not null,
placedAt timestamp not null
);
create table Taco_Order_Tacos (
    tacoOrder bigint  PRIMARY KEY not null,
    taco bigint not null
);
 alter table Taco_Order_Tacos
 add foreign key (tacoOrder) references Taco_Order(id);
 alter table Taco_Order_Tacos
 add foreign key (taco) references Taco(id);