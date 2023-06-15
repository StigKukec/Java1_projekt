/*
create database Auth
go 
use Auth
go

create table Account
(
	IDAccount int primary key identity,
	Username nvarchar(50),
	Password nvarchar(50),
	AccountType nvarchar(15)
)

go
drop database MovieAPP
*/
create database MovieAPP
go
use MovieAPP
go

------------------------------------------------------------------------------------------------------------------------------------------------
--tables
------------------------------------------------------------------------------------------------------------------------------------------------
create table Account
(
	IDAccount int primary key identity,
	Username nvarchar(50) not null,
	[Password] nvarchar(200) not null,
	Administrator tinyint ,
	constraint chk_Administrator check (Administrator in(0,1)),
)

go
create table Movie
(
	IDMovie int primary key identity(1,1),
	Title nvarchar(75) not null,
	Genre nvarchar(50) not null,
	ReleaseYear int not null,
	Duration int not null,
	MovieDescription nvarchar(500) not null,
	Poster nvarchar(300) not null,
)

go
create table Actor
(
	IDActor int primary key identity,
	FirstName nvarchar(75) not null,
	LastName nvarchar(75) not null,
)

go
create table MovieDirector
(
	IDMovieDirector int primary key identity,
	FirstName nvarchar(75) not null,
	LastName nvarchar(75) not null,
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
------------------------------------------------------------------------------------------------------------------------------------------------
--procedures for create
------------------------------------------------------------------------------------------------------------------------------------------------
create proc createMovie
(
@Title nvarchar(75) ,
@Genre nvarchar(50) ,
@ReleaseYear int ,
@Duration int ,
@MovieDescription nvarchar(500) ,
@Poster nvarchar(300)
)
as
if not exists(select * from Movie where Title = @Title and ReleaseYear = @ReleaseYear and Duration = @Duration and MovieDescription = @MovieDescription )
begin
insert into Movie(Title,Genre,ReleaseYear,Duration,MovieDescription,Poster) 
values(@Title,@Genre,@ReleaseYear,@Duration,@MovieDescription,@Poster)
end
go
create proc createMovieDirector
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
if not exists (select * from MovieDirector where FirstName = @FirstName and LastName = @LastName)
begin
insert into MovieDirector(FirstName,LastName) 
values(@FirstName,@LastName)
end
go
create proc createActor
(
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
if not exists (select * from Actor where FirstName = @FirstName and LastName = @LastName)
begin
insert into Actor(FirstName,LastName) 
values(@FirstName,@LastName)
end
go
create proc createMovieWithId
(
@Title nvarchar(75) ,
@Genre nvarchar(50) ,
@ReleaseYear int ,
@Duration int ,
@MovieDescription nvarchar(500) ,
@Poster nvarchar(300),
@IDMovie int output
)
as

if not exists(select * from Movie where Title = @Title and ReleaseYear = @ReleaseYear and Duration = @Duration and MovieDescription = @MovieDescription )
begin
insert into Movie(Title,Genre,ReleaseYear,Duration,MovieDescription,Poster) 
values(@Title,@Genre,@ReleaseYear,@Duration,@MovieDescription,@Poster)
set @IDMovie = SCOPE_IDENTITY()
end
else
begin
set @IDMovie = 0
end
GO
create proc createActorWithId
(
@FirstName nvarchar(50),
@LastName nvarchar(50),
@IDActor int output
)
as
if not exists (select * from Actor where FirstName = @FirstName and LastName = @LastName)
begin
insert into Actor(FirstName,LastName) 
values(@FirstName,@LastName)
set @IDActor = SCOPE_IDENTITY()
end
else
begin
set @IDActor = 0
end

GO
create proc createMovieDirectorWithId
(
@FirstName nvarchar(50),
@LastName nvarchar(50),
@IDMovieDirector int output
)
as
if not exists (select * from MovieDirector where FirstName = @FirstName and LastName = @LastName)
begin
insert into MovieDirector(FirstName,LastName)
values(@FirstName,@LastName)
set @IDMovieDirector = SCOPE_IDENTITY()
end
else
begin
set @IDMovieDirector = 0
end
GO


------------------------------------------------------------------------------------------------------------------------------------------------
--procedures for insert
------------------------------------------------------------------------------------------------------------------------------------------------
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

go

create proc insertDirectorInMovieByName
(
@Title nvarchar(50),
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
if exists (select top 1 MovieID,MovieDirectorID from Movie_MovieDirector where MovieID = (select IDMovie from Movie where Title = @Title) and MovieDirectorID = (select IDMovieDirector from MovieDirector where FirstName = @FirstName and LastName = @LastName))
begin
print N'Direktor u tom filmu veæ postoji'
end
else
begin
declare @IDMovie int
set @IDMovie = (select IDMovie from Movie where Title = @Title)
declare @IDMovieDirector int
set @IDMovieDirector = (select IDMovieDirector from MovieDirector where FirstName = @FirstName and LastName = @LastName)
insert into Movie_MovieDirector(MovieID,MovieDirectorID)
values(@IDMovie,@IDMovieDirector)
end
go

create proc insertActorInMovieByName
(
@Title nvarchar(50),
@FirstName nvarchar(50),
@LastName nvarchar(50)
)
as
if exists (select top 1 MovieID,ActorID from Movie_Actor where MovieID = (select IDMovie from Movie where Title = @Title) and ActorID = (select IDActor from Actor where FirstName = @FirstName and LastName = @LastName))
begin
print N'Glumac u tom filmu veæ postoji'
end
else
begin
declare @IDMovie int
set @IDMovie = (select IDMovie from Movie where Title = @Title)
declare @IDActor int
set @IDActor = (select IDActor from Actor where FirstName = @FirstName and LastName = @LastName)
insert into Movie_Actor(MovieID,ActorID)
values(@IDMovie,@IDActor)
end

go
------------------------------------------------------------------------------------------------------------------------------------------------
--procedures for delete
------------------------------------------------------------------------------------------------------------------------------------------------
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
create proc deleteMovie
(
@IDMovie int 
)
as
delete from Movie_Actor where MovieID = @IDMovie 
delete from Movie_MovieDirector where MovieID = @IDMovie  
delete from Movie where IDMovie = @IDMovie
go
create proc deleteActor
(
@IDActor int 
)
as
delete from Movie_Actor where ActorID = @IDActor 
delete from Actor where IDActor = @IDActor

go
create proc deleteMovieDirector
(
@IDMovieDirector int 
)
as
delete from Movie_MovieDirector where MovieDirectorID = @IDMovieDirector 
delete from MovieDirector where IDMovieDirector = @IDMovieDirector

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
create proc deleteAllMovies
as
delete from Movie_Actor
delete from Movie_MovieDirector
delete from Movie
go
create proc deleteAllActors
as
delete from Movie_Actor
delete from Actor
go
create proc deleteAllMovieDirectors
as
delete from Movie_MovieDirector
delete from MovieDirector
go



------------------------------------------------------------------------------------------------------------------------------------------------
--procedures for update
------------------------------------------------------------------------------------------------------------------------------------------------
create proc updateMovie 
(
@IDMovie int,
@Title nvarchar(50) ,
@Genre nvarchar(50) ,
@ReleaseYear int ,
@Duration int ,
@MovieDescription nvarchar(50) ,
@Poster nvarchar(300))
as
update Movie
set Title = @Title, Genre = @Genre,ReleaseYear = @ReleaseYear,Duration = @Duration, MovieDescription = @MovieDescription, Poster = @Poster
where IDMovie = @IDMovie
go
create proc updateActor
(
@IDActor int,
@FirstName nvarchar(50),
@LastName nvarchar(50))
as
update Actor
set FirstName = @FirstName, LastName = @LastName
where IDActor = @IDActor
go
create proc updateMovieDirector
(
@IDMovieDirector int,
@FirstName nvarchar(50),
@LastName nvarchar(50))
as
update MovieDirector
set FirstName = @FirstName, LastName = @LastName
where IDMovieDirector = @IDMovieDirector
go




------------------------------------------------------------------------------------------------------------------------------------------------
--procedures for select
------------------------------------------------------------------------------------------------------------------------------------------------
create proc selectMovie
(
@IDMovie int
)
as
select * from Movie where IDMovie = @IDMovie

go
create proc selectMovies
as
select * from Movie 

go
create proc selectActors
as
select * from Actor 
go
create proc selectMovieDirectors
as
select * from MovieDirector
go
create proc selectActor
(
@IDActor int

)
as
select * from Actor where IDActor = @IDActor
go

create proc selectMovieDirector
(
@IDMovieDirector int
)
as
select * from MovieDirector where IDMovieDirector = @IDMovieDirector


go

create proc selectFullMovieStatistic

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
END


------------------------------------------------------------------------------------------------------------------------------------------------
--Account procedures
------------------------------------------------------------------------------------------------------------------------------------------------
go
create proc createAccount
(
@Username nvarchar(75),
@Password nvarchar(50),
@Administrator tinyint
)
as
insert into Account(Username,Password,Administrator)values(@Username,@Password,@Administrator)
go
create proc updateAccount
(
@IDAccount int,
@Username nvarchar(50),
@Password nvarchar(50),
@Administrator tinyint
)
as
update Account
set Username = @Username, Password = @Password, Administrator = @Administrator
where IDAccount = @IDAccount
go
create proc deleteAccount
(
@IDAccount int 
)
as
delete from Account where IDAccount = @IDAccount
go

create proc selectAccounts
as
select * from Account 
go

create proc selectAccount
(
@IDAccount int 
)
as
select * from Account where IDAccount = @IDAccount

go

------------------------------------------------------------------------------------------------------------------------------------------------
--Upload procedures
------------------------------------------------------------------------------------------------------------------------------------------------
create proc uploadAllMovies
(
@Title nvarchar(75),
@Genre nvarchar(50),
@ReleaseYear int,
@Duration int,
@MovieDescription nvarchar(500),
@Poster nvarchar(300),
@ActorFirstName nvarchar(50),
@ActorLastName nvarchar(50),
@MovieDirectorFirstName nvarchar(50),
@MovieDirectorActorLastName nvarchar(50)
)
as
exec createMovie @Title,@Genre,@ReleaseYear,@Duration,@MovieDescription,@Poster
declare @Movie int
SET @Movie = SCOPE_IDENTITY()
exec createActor @ActorFirstName,@ActorLastName
declare @Actor int
SET @Movie = SCOPE_IDENTITY()
exec createMovieDirector @MovieDirectorFirstName,@MovieDirectorActorLastName
declare @MovieDirector int
SET @Movie = SCOPE_IDENTITY()

exec insertActorInMovie @Movie,@Actor
exec insertDirectorInMovie @Movie,@MovieDirector

---------------------------------------------------------------------
/*
select * from Movie

select * from Actor

select * from MovieDirector

select * from Movie_Actor

select * from Movie_MovieDirector

select * from Accounts

delete from Movie

delete from Actor

delete from MovieDirector

delete from Movie_Actor

delete from Movie_MovieDirector

delete from Accounts
*/