/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  shaki694
 * Created: 7/08/2018
 */

create table Product (
Product_ID varchar(50),
Product_Name varchar(50) not null,
Product_Description varchar(50) not null,
Product_Category varchar(20) not null,
Product_Price decimal(5,2) not null,
Product_Quantity double not null,
constraint Product_PK primary key (Product_ID)
);

create table Customer (
Person_ID varchar(50) auto_increment(1000),
Username varchar(50) not null unique,
Firstname varchar(50) not null,
Surname varchar(50) not null,
Password varchar(50) not null,
Email_Address varchar(50) not null unique,
Address varchar(50) not null,
Credit_Card_Details varchar(50) not null,
constraint Customer_PK primary key (Person_ID)
);


create table Sale (
Person_ID varchar(50),
Sale_ID varchar(50) auto_increment(5000),
Date_time timestamp,
Status varchar(50),
constraint Sale_PK primary key (Sale_ID),
constraint Sale_FK foreign key (Person_ID) references Customer,
);



create table SaleItem (
Product_ID varchar(50) not null,
sale_id varchar(50) not null,
Quantity double not null,
Price decimal(5,2) not null,
constraint saleItem_FK foreign key (Product_ID) references Product,
constraint saleitem_pk primary key (sale_id, product_id)
);