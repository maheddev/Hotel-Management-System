create database HotelManagement
use HotelManagement
create table login(
                      userName varchar(50) not null,
                      password varchar(50) not null
)

select * from login
create table Guests(
                       GNumber varchar(10) primary key,
                       GName varchar(256) not null,
                       GCnic varchar(15) not null,
                       GContact varchar(20) not null,
                       GAddress varchar(256) not null,
                       GCity varchar(30),
                       GMail varchar(256),
                       GDOB date not null,
)

create table Staff(
                      SNumber varchar(10) primary key,
                      SName varchar(256) not null,
                      SCnic varchar(15) not null,
                      SContact varchar(20) not null,
                      SAddress varchar(256) not null,
                      SCity varchar(30),
                      SMail varchar(256),
                      SDOB date not null,
                      Spos varchar(50) not null,
                      SDoj date not null
)
create table RoomsData(
                          RoomNo varchar(10) primary key,
                          Floor int not null,
                          roomType varchar(30) not null,
                          AC varchar(30) not null,
                          Beds int not null,
                          MiniBar varchar(4) not null,
                          miniKitchen varchar(4) not null,
                          isRes varchar(4) not null,
)



create table Reservations(
                             GNumber varchar(10) foreign key references Guests(GNumber),
                             RoomNo varchar(10) foreign key references RoomsData(RoomNo),
                             BookingDate date not null,
                             resNum varchar(256) primary key
)

Create table CheckIn(
                        resNum varchar(256) foreign key references Reservations(resNum),
                        Checkind Date,
)
create table staffAttendance(
                                SNumber varchar(10) foreign key references Staff(SNumber),
                                MarkingDate date not null,
                                HoursWorked int not null,
                                constraint pk primary key(SNumber,MarkingDate)
);
