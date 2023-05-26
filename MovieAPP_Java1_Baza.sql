/*
create database Auth
go 
use Auth
go

create table Accounts
(
	IDAccounts int primary key identity,
	Username nvarchar(50),
	Password nvarchar(50),
	AccountType nvarchar(15)
)

go
*/
create database MovieAPP
go
use MovieAPP
go
create table Accounts
(
	IDAccounts int primary key identity,
	Username nvarchar(50),
	[Password] nvarchar(50),
	Administrator tinyint ,
	[User] tinyint,
	constraint chk_Administrator check (Administrator in(0,1)),
	constraint chk_User check ([User] in(0,1))

)

go
create table Movie
(
	IDMovie int primary key identity(1,1),
	Title nvarchar(50) not null,
	Genre nvarchar(50) not null,
	ReleaseYear int not null,
	Duration int not null,
	MovieDescription nvarchar(50) not null,
	Poster nvarchar(300) not null,
)

go
create table Actor
(
	IDActor int primary key identity,
	FirstName nvarchar(50),
	LastName nvarchar(50),
)

go
create table MovieDirector
(
	IDMovieDirector int primary key identity,
	FirstName nvarchar(50),
	LastName nvarchar(50),
)
go

create table Movie_MovieDirector
(
 IDMovie_MovieDirector int not null identity,
 MovieID int not null,
 MovieDirectorID int not null,
 constraint PK_Movie_MovieDirector primary key(IDMovie_MovieDirector),
 constraint FK_Movie foreign key(MovieID) references Movie(IDMovie),
  constraint FK_MovieDirector  foreign key(MovieDirectorID) references MovieDirector(IDMovieDirector)
)
go

create table Movie_Actor
(
 IDMovie_Actor int not null identity,
 MovieID int not null,
 ActorID int not null,
 constraint PK_Movie_Actor primary key(IDMovie_Actor),
  foreign key(MovieID) references Movie(IDMovie),
  constraint FK_Actor  foreign key(ActorID) references Actor(IDActor)
)


go

--create
create proc createMovie
(
@Title nvarchar(50) ,
@Genre nvarchar(50) ,
@ReleaseYear int ,
@Duration int ,
@MovieDescription nvarchar(50) ,
@Poster nvarchar(300)
)
as
insert into Movie(Title,Genre,ReleaseYear,Duration,MovieDescription,Poster) 
values(@Title,@Genre,@ReleaseYear,@Duration,@MovieDescription,@Poster)
go
create proc createMovieDirector
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
insert into MovieDirector(FirstName,LastName) 
values(@FirstName,@LastName)
go
create proc createActor
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
insert into Actor(FirstName,LastName) 
values(@FirstName,@LastName)
go
create proc insertDirectorInMovie
(
@IDMovie int,
@IDMovieDirector int
)
as
insert into Movie_MovieDirector(MovieID,MovieDirectorID)
values(@IDMovie,@IDMovieDirector)
go
create proc insertActorInMovie
(
@IDMovie int,
@IDActor int
)
as
insert into Movie_Actor(MovieID,ActorID)
values(@IDMovie,@IDActor)


--nisam još executao procedure nadalje dolje
--delete
go
create proc deleteMovieByName
(
@Title nvarchar(50) ,
/*@Genre nvarchar(50) ,*/
@ReleaseYear int 
)
as
delete from Movie_Actor where MovieID = (select IDMovie from Movie where Title = @Title	and ReleaseYear = @ReleaseYear) 
delete from Movie where Title = @Title	and ReleaseYear = @ReleaseYear

go
create proc deleteMovieById
(
@MovieID int 
)
as
delete from Movie_Actor where (select IDMovie from Movie) = @MovieID 
delete from Movie where IDMovie = @MovieID

go
create proc deleteMovieDirectorByName
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
delete from Movie_MovieDirector where MovieDirectorID = (select IDMovieDirector from MovieDirector where  FirstName = @FirstName and LastName = @LastName)
delete from MovieDirector where FirstName = @FirstName and LastName = @LastName

go
create proc deleteActorByName
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
delete from Movie_Actor where ActorID = (select IDActor from Actor where  FirstName = @FirstName and LastName = @LastName)
delete from Actor where FirstName = @FirstName and LastName = @LastName
go
create proc removeDirectorInMovie
(
@Title nvarchar(50),
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
delete from Movie_MovieDirector where MovieDirectorID = (select IDMovieDirector from MovieDirector where  FirstName = @FirstName and LastName = @LastName)
and MovieID = (select IDMovie from Movie where Title = @Title)

go
create proc removeActorInMovie
(
@Title nvarchar(50),
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
delete from Movie_Actor where ActorID = (select IDActor from Actor where  FirstName = @FirstName and LastName = @LastName)
and MovieID = (select IDMovie from Movie where Title = @Title)
go
-----------------------------------
--select
create proc selectMovie
(
@Title nvarchar(50),
@ReleaseYear nvarchar(50)
)
as
select * from Movie where Title = @Title and ReleaseYear = @ReleaseYear

go

create proc selectActor
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
select * from Actor where FirstName = @FirstName and LastName = @LastName
go

create proc selectMovieDirector
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
select * from MovieDirector where FirstName = @FirstName and LastName = @LastName

go

create proc selectFullMovieStatistic
(
@Title nvarchar(50),
@ReleaseYear nvarchar(50)
)
as
BEGIN
    SELECT m.Title, m.Genre, m.Duration, m.MovieDescription, m.ReleaseYear,
	STUFF((SELECT ', ' + CONCAT(a.FirstName, ' ', a.LastName)
		FROM Movie_Actor AS ma
		INNER JOIN Actor AS a ON a.IDActor = ma.ActorID
		WHERE ma.MovieID = m.IDMovie
		FOR XML PATH('')), 1, 2, '') AS Actors,
    STUFF((SELECT ', ' + CONCAT(md.FirstName, ' ', md.LastName)
               FROM Movie_MovieDirector AS mmd
               INNER JOIN MovieDirector AS md ON md.IDMovieDirector = mmd.MovieDirectorID
               WHERE mmd.MovieID = m.IDMovie
               FOR XML PATH('')), 1, 2, '') AS Directors,
        m.Poster
    FROM Movie AS m
    WHERE m.Title = @Title AND m.ReleaseYear = @ReleaseYear
END


/*

exec createMovie 'Star Wars','fikcija',2003,230,'najbolji film ikada',null
go
exec createMovie 'Katarina','komedija',1234,100,'peder',null
go
exec createActor 'Milica','Drago'
go
exec createActor 'Baka','Kaka'

exec createMovieDirector'vvvvvvvvv','zzzzzzzzzzzzz'

go
exec insertActorInMovie 4,2
go
exec insertActorInMovie 4,4
go
exec insertActorInMovie 4,3
go
exec insertActorInMovie 5,5
select * from Movie
select * from Actor
select * from Movie_Actor
select * from MovieDirector

delete from Movie where IDMovie = 2
delete from Actor where IDActor = 1

delete from Movie_Actor where MovieID = (select IDMovie from Movie where Title = 'Matrix'	and ReleaseYear = 2002) 
delete from Movie_Actor 

exec selectFullMovieStatistic 'Star Wars',2003

exec insertDirectorInMovie 4,1
go
exec insertDirectorInMovie 4,2
go
exec insertDirectorInMovie 5,1
*/

