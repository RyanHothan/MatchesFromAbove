--*****************************************************************************************************************
--Our Create Table Statements with constraints
--*****************************************************************************************************************
CREATE DATABASE MatchesFromAbove;

USE MatchesFromAbove;

CREATE TABLE Person (
SSN VARCHAR(11),
Password VARCHAR(20)NOT NULL,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50) NOT NULL,
Street VARCHAR(50) NOT NULL,
City VARCHAR(50) NOT NULL,
State VARCHAR(3) NOT NULL,
ZipCode INTEGER NOT NULL,
Email VARCHAR(50) NOT NULL,
Telephone VARCHAR(50),
PRIMARY KEY(SSN),
CHECK (ZipCode < 100000 AND ZipCode > 9999) );

CREATE TABLE Employee (
SSN VARCHAR(11),
Role VARCHAR(50) NOT NULL,
StartDate DATE NOT NULL,
Rate INTEGER NOT NULL,
Active BIT NOT NULL,
PRIMARY KEY(SSN),
FOREIGN KEY (SSN) REFERENCES Person(SSN)
ON UPDATE CASCADE);

CREATE TABLE Customer (
SSN VARCHAR(11),
PPP VARCHAR(10) NOT NULL,
Rating INTEGER,
LastActive DATETIME NOT NULL,
Active BIT NOT NULL,
PRIMARY KEY(SSN),
FOREIGN KEY (SSN) REFERENCES Person(SSN)
ON UPDATE CASCADE,
CHECK (Rating < 6 AND Rating > 0) );

CREATE TABLE Profile (
ProfileId VARCHAR(24),
OwnerSSN VARCHAR(11) NOT NULL,
Age INTEGER NOT NULL,
AgeRangeStart INTEGER NOT NULL,
AgeRangeEnd INTEGER NOT NULL,
GeoRange INTEGER NOT NULL,
Gender VARCHAR(1) NOT NULL,
Hobbies VARCHAR(50),
Height DECIMAL(2,1) NOT NULL,
Weight INTEGER NOT NULL,
HairColor VARCHAR(20),
ProfileCreationDate DATETIME NOT NULL,
ProfileModDate DATETIME NOT NULL,
Active BIT NOT NULL,
PRIMARY KEY(ProfileId),
FOREIGN KEY (OwnerSSN) REFERENCES Customer(SSN)
ON UPDATE CASCADE,
CHECK(Age < 120 AND Age >= 17),
CHECK(AgeRangeStart >= 17 AND AgeRangeEnd >= AgeRangeStart),
CHECK(GeoRange > 0 ) );

CREATE TABLE Account (
OwnerSSN VARCHAR(11) NOT NULL,
CreditCardNumber VARCHAR(16) NOT NULL,
AccountNumber INTEGER,
AccountCreationDate DATE NOT NULL,
Active BIT NOT NULL,
PRIMARY KEY(AccountNumber),
FOREIGN KEY(OwnerSSN) REFERENCES Customer(SSN)
ON UPDATE CASCADE
ON DELETE CASCADE);

CREATE TABLE Likes (
LikerId VARCHAR(24),
LikeeId VARCHAR(24),
Date_Time DATETIME ,
PRIMARY KEY (LikeeId, LikerId, Date_Time)
);

CREATE TABLE Referral (
ProfileIdA VARCHAR(24),
ProfileIdB VARCHAR(24),
ProfileIdC VARCHAR(24),
Date_Time DATETIME NOT NULL,
PRIMARY KEY (ProfileIdA, ProfileIdB, ProfileIdC, Date_Time));

CREATE TABLE Date (
Profile1Id VARCHAR(24) NOT NULL,
Profile2Id VARCHAR(24) NOT NULL,
CustomerRep VARCHAR(11) NOT NULL,
Date_Time DATETIME NOT NULL,
Location VARCHAR(50) NOT NULL,
Fee DECIMAL(5,2) NOT NULL,
Comments VARCHAR (256),
User1Rating INTEGER,
User2Rating INTEGER,
PRIMARY KEY (Profile1Id, Profile2Id, Date_Time),
CHECK (User1Rating < 6 AND User1Rating > 0),
CHECK (User2Rating < 6 AND User2Rating > 0),
FOREIGN Key(CustomerRep) REFERENCES Employee(SSN)
ON UPDATE CASCADE
);

CREATE TABLE BlindDate (
CustRep VARCHAR(11),
ProfileIdA VARCHAR(24),
ProfileIdB VARCHAR(24),
Date_Time DATETIME NOT NULL,
PRIMARY KEY (ProfileIdA, ProfileIdB, CustRep, Date_Time),
FOREIGN KEY(CustRep) REFERENCES Employee(SSN)
ON UPDATE CASCADE
);


--*****************************************************************************************************************
--Our Demo data input statements.  Run these queries to populate the database with demo data
--*****************************************************************************************************************

INSERT INTO Person
VALUES ('111-11-1111', '111@11', 'Veronica', 'Alvarado', '45 Rockefeller Plaza', 'New York', 'NY', '10111', 'Fusce@velitPellentesque.net', '(612) 506-2244'),
('222-22-2222', '222@22','Bo', 'Osborne', '45 Rockefeller Plaza', 'New York', 'NY', '10111', 'mattis.Integer.eu@elit.org','(592) 765-8277'),
('333-33-3333', '333@33','Hashim','Ross','350 5th Ave', 'New York', 'NY', '10118', 'vulputate@Curae.co.uk','(276) 634-6949'),
('444-44-4444', '444@44', 'Shaine', 'Terrell', '350 5th Ave', 'New York', 'NY', '10118', 'tincidunt.nibh@risus.com', '(600) 803-9508'),
('555-55-5555', '555@55', 'Isabelle', 'Odonnell', 'Schomburg Apartments, 350 Circle Road', 'Stony Brook', 'NY', '11790', 'magna.tellus.faucibus@amet.edu', '(934) 241-3862'),
('666-66-6666', '666@66', 'Fletcher', 'Trujillo', '700 Health Sciences Dr', 'Stony Brook', 'NY', '11790', 'elementum.dui.quis@utlacus.net', '(990) 760-1480'),
('777-77-7777', '777@77', 'Malachi', 'Vazquez', '700 Health Sciences Dr', 'Stony Brook', 'NY', '11790', 'tellus.lorem.eu@atlacus.org', '(226) 193-8257'),
('888-88-8888', '888@88', 'Brenna', 'Cross', 'Schomburg Apartments, 350 Circle Road', 'Stony Brook', 'NY', '11790', 'sed.turpis@vehiculaaliquet.com','(968) 409-7641'),
('999-99-9999', '999@99', 'Desirae', 'Berg', '116th St & Broadway', 'New York', 'NY', '10027', 'vitae@magnased.com', '(237) 321-3189');

INSERT INTO Employee
VALUES('111-11-1111', 'Manager', '2014-10-04', 250, 1),
('222-22-2222', 'CustRep', '2014-10-04', 150, 1),
('333-33-3333', 'CustRep', '2014-10-04', 100, 1),
('444-44-4444', 'CustRep', '2014-10-04', 75, 1);

INSERT INTO Customer
VALUES('555-55-5555', 'Super-User', 3, '2014-10-07 05:53:13', 1),
('666-66-6666', 'Good-User', 3, '2014-10-05 05:27:28', 1),
('777-77-7777', 'Good-User', 4, '2014-10-08 22:37:07', 1),
('888-88-8888', 'User-User', 3, '2014-10-04 09:10:12', 1),
('999-99-9999', 'User-User', 2, '2014-10-05 18:28:02', 1);

INSERT INTO Account
VALUES('555-55-5555', '5186330464994532', '12345', '2013-10-07', 1),
('555-55-5555', '349454276731232', '23456', '2012-09-07', 1),
('666-66-6666', '5192383525185287', '34567', '2013-09-23', 1),
('777-77-7777', '5144751168293870', '45678', '2014-05-28', 1),
('888-88-8888', '5167593514262698', '56789', '2014-04-22', 1),
('999-99-9999', '4482704287348312', '67891', '2011-10-07', 1);

INSERT INTO Profile 
VALUES 
('Isabelle2014',	'555-55-5555',	'22',	'20',	'25',	'5',	'F',	'Shopping, Cooking',	'5.7',	'110',	'Black',	'2014-10-04 22:43:25',	'2014-10-09 11:51:19', 1),
('Isabelle2013',	'555-55-5555',	'22',	'20',	'22',	'29',	'F',	'Shopping, Dance, Mountain Claiming',	'5.7',	'120',	'Black',	'2014-10-04 00:37:12',	'2014-10-04 17:08:38', 1),
('Fletcher2013',	'666-66-6666',	'25',	'20',	'28',	'18',	'F',	'Reading, Basketball',	'5.6',	'150',	'Brown',	'2014-10-04 19:21:37',	'2014-10-07 01:25:55', 1),
('Fletcher_Trujillo',	'666-66-6666',	'23',	'19',	'30',	'8',	'F','	Shopping, Volleyball',	'5.6',	'150',	'Brown',	'2014-10-04 18:26:49',	'2014-10-05 00:42:03', 1),
('VazquezFromAlajuela','777-77-7777',	'26',	'20',	'28',	'15',	'M',	'Hunting, Running',	'5.7',	'170',	'Black',	'2014-10-04 17:13:30',	'2014-10-07 04:16:43', 1),
('Brenna_Berlin',	'888-88-8888',	'18',	'19',	'21',	'8',	'F','	Dance, Acting',	'6',	'180',	'Blonde',	'2014-10-04 20:20:55',	'2014-10-07 12:21:58', 1),
('DesiraeBerg',	'999-99-9999',	'20',	'17',	'25',	'5',	'M',	'Water sports, Football',	'5.6',	'200',	'Red',	'2014-10-04 19:13:18',	'2014-10-04 15:54:48', 1);

INSERT INTO Referral
VALUES('Isabelle2014', 'Fletcher2013', 'VazquezFromAlajuela' ,'2014-10-07 09:56:08'),
('DesiraeBerg', 'VazquezFromAlajuela','Fletcher_Trujillo', '2014-10-04 13:59:20');

INSERT INTO BlindDate
VALUES('333-33-3333',  'Fletcher_Trujillo' ,'VazquezFromAlajuela', '2014-10-09 20:59:22'),
('222-22-2222', 'Fletcher2013' ,'DesiraeBerg' ,'2014-10-05 15:07:44');

INSERT INTO Date
VALUES('Isabelle2014', 'VazquezFromAlajuela', '222-22-2222', '2014-10-06 21:49:30', 'The Mall', 90.91, 'Comments Here', 3, 3),
('Isabelle2013', 'DesiraeBerg', '222-22-2222', '2014-10-04 21:39:42', 'Port Jeff Cinema', 65.25, 'Comments Here', 4, 5),
('Fletcher2013', 'VazquezFromAlajuela', '333-33-3333', '2014-10-06 04:30:52', 'Ruvos Restaurant', 42.75, 'Comments Here', 3, 1),
('Brenna_Berlin', 'DesiraeBerg', '333-33-3333', '2014-10-06 12:21:06', 'The Mall', 36.46, 'Comments Here', 2, 3),
('VazquezFromAlajuela', 'Brenna_Berlin', '444-44-4444', '2014-10-06 05:34:04', 'Turkish Restaurant', 69.26, 'Comments Here', 4, 4);

INSERT INTO Likes 
VALUES ('Isabelle2014', 'VazquezFromAlajuela', '2014-10-06 05:28:39'),
('Isabelle2014', 'DesiraeBerg', '2014-10-07 21:04:09'),
('Isabelle2013', 'VazquezFromAlajuela', '2014-10-08 09:15:49'),
('Isabelle2013', 'DesiraeBerg', '2014-10-06 23:06:12'),
('Fletcher2013', 'VazquezFromAlajuela', '2014-10-06 03:46:48'),
('Brenna_Berlin', 'DesiraeBerg', '2014-10-05 05:05:08'),
('VazquezFromAlajuela', 'Brenna_Berlin', '2014-10-06 21:13:02'),
('Brenna_Berlin', 'DesiraeBerg', '2014-10-05 11:02:05');

--*****************************************************************************************************************
--OUR FUCKING TRIGGERS
--*****************************************************************************************************************
GO
CREATE TRIGGER [dbo].[tr_UpdateLiker]
ON [dbo].[Profile] FOR UPDATE
AS 
BEGIN
UPDATE Likes
SET LikerId = (SELECT ProfileId FROM INSERTED)
WHERE LikerId = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateLikee]
ON [dbo].[Profile] FOR UPDATE
AS 
BEGIN
UPDATE Likes
SET LikeeId = (SELECT ProfileId FROM INSERTED)
WHERE LikeeId = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateProfile1Id]
ON [dbo].[Profile] FOR UPDATE
AS
BEGIN 
UPDATE Date
SET Profile1Id = (SELECT ProfileId FROM INSERTED)
WHERE Profile1Id = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateProfile2Id]
ON [dbo].[Profile] FOR UPDATE
AS
BEGIN 
UPDATE Date
SET Profile2Id = (SELECT ProfileId FROM INSERTED)
WHERE Profile2Id = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateProfileIdA]
ON [dbo].[Profile] FOR UPDATE
AS 
BEGIN
UPDATE Referral
SET ProfileIdA = (SELECT ProfileId FROM INSERTED)
WHERE ProfileIdA = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateProfileIdB]
ON [dbo].[Profile] FOR UPDATE
AS 
BEGIN
UPDATE Referral
SET ProfileIdB = (SELECT ProfileId FROM INSERTED)
WHERE ProfileIdB = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateProfileIdC]
ON [dbo].[Profile] FOR UPDATE
AS 
BEGIN
UPDATE Referral
SET ProfileIdC = (SELECT ProfileId FROM INSERTED)
WHERE ProfileIdC = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateBlindDateIdA]
ON [dbo].[Profile] FOR UPDATE
AS 
BEGIN
UPDATE BlindDate
SET ProfileIdA = (SELECT ProfileId FROM INSERTED)
WHERE ProfileIdA = (SELECT ProfileId FROM DELETED)
END;
GO
CREATE TRIGGER [dbo].[tr_UpdateBlindDateIdB]
ON [dbo].[Profile] FOR UPDATE
AS 
BEGIN
UPDATE BlindDate
SET ProfileIdB = (SELECT ProfileId FROM INSERTED)
WHERE ProfileIdB = (SELECT ProfileId FROM DELETED)
END;


--*****************************************************************************************************************
--When a query is looking for a customer we are expecting a SSN as a String parameter.
--When a query is looking for a profile we are looking for a ProfileId as a String parameter
--When a query is looking for a date we are looking for a DATE as a Strign parameter(YYYY-MM-DD)
--When a query is looking for a date we are looking for a DATETIME as a Strign parameter(YYYY-MM-DD HH:MM:SS)
--When a query is looking for a rate we are looking for a Rate as an integer.
--Most other parameters are going to be passed in as Strings, and you can see this in sample usages of our queries

--Note: Users are customers and vice versa.  
--*****************************************************************************************************************

--Add, Edit and Delete information for an employee
--editing.  Though you SHOULDN'T change an SSN of an employee, the query is here
UPDATE Employee
SET Role = '?'
WHERE SSN = '?';

UPDATE Employee
SET SSN = '?'
WHERE SSN = '?';

UPDATE Employee
SET StartDate = '?'
WHERE SSN = '?';

UPDATE Employee
SET Rate = ?
WHERE SSN = '?';
--deleting
DELETE FROM Employee
WHERE SSN = '?';

--adding
INSERT INTO Person
VALUES('?', '?', '?', '?','?','?','?',?,'?','?');

INSERT INTO Employee
VALUES('?', '?', CURDATE(), ?);

--Obtain a Sales report for a particular month
SELECT D.Fee, D.Date_Time
FROM Date D
WHERE D.Date_Time > '?' AND D.Date_Time < '?'

--Produce a comprehensive listing of all users
SELECT *
FROM Customer C

--Produce a list of dates by calendar date or by customer name
--By calendar date
SELECT *
FROM Date D
WHERE D.Date_Time > '?' AND D.Date_Time < '?';
--By customer name
SELECT D.*
FROM Date D, Profile P, Person Pe
WHERE (D.Profile1Id = P.ProfileId AND P.OwnerSSN = Pe.SSN AND Pe.FirstName = '?' AND Pe.LastName = '?') OR 
(D.Profile2Id = P.ProfileId AND P.OwnerSSN = Pe.SSN AND Pe.FirstName = '?' AND Pe.LastName = '?');

--Produce a summary listing of revenue generated by dates on a particular calendar date or involving a particular customer
SELECT D.Fee, D.Date_Time
FROM Date D
WHERE D.Date_Time > '?' AND D.Date_Time < '?'

SELECT C.FirstName, C.LastName, D.Fee, D.Date_Time
FROM Date D, Customer C, Profile P
WHERE (D.Profile1Id = P.ProfileId AND P.OwnerSSN = C.SSN AND C.SSN = '?') OR 
(D.Profile2Id = P.ProfileId AND P.OwnerSSN = C.SSN AND C.SSN = '?');

--Determine which customer representative generated most total revenue
SELECT CustomerRep, MAX(sumFee) AS maxFee
FROM (
SELECT D.CustomerRep, SUM(D.Fee) AS sumFee
FROM Date D
GROUP BY CustomerRep
) first_group

--Determine which customer generated the most total revenue
SELECT OwnerSSN, MAX(totalSumFee)
FROM(
	SELECT OwnerSSN, SUM(sumFee) AS totalSumFee
	FROM(
		SELECT P.OwnerSSN, SUM(D.Fee) AS sumFee
		FROM  Profile P, Date D
		WHERE D.Profile1Id = P.ProfileId
		GROUP BY OwnerSSN
		UNION ALL
		SELECT P.OwnerSSN, SUM(D.Fee) AS sumFee
		FROM Profile P, Date D
		WHERE D.Profile2Id = P.ProfileId
		GROUP BY OwnerSSN
		)SSNsWithDuplicates 
	GROUP BY(OwnerSSN)
	)SSNs
	
--Produce a list of the most active customers
--We interpreted this as all the users who have had over 3 dates and likes in the last month. Obviously this number is arbitrary and can be anything that you set as a threshhold.
--If you wanted a 'top X' list you can also limit this query to a variable X
SELECT OwnerSSN, SUM(points) AS totalPoints
FROM
	(SELECT OwnerSSN, SUM(numDates) AS points
	FROM
		(
		SELECT P.OwnerSSN, COUNT(*) AS numDates
		FROM  Profile P, Date D
		WHERE D.Profile1Id = P.ProfileId AND D.Date_Time > DATE_SUB(CURDATE(),INTERVAL 1 MONTH)
		GROUP BY OwnerSSN
		UNION ALL
		SELECT P.OwnerSSN, COUNT(*) AS numDates
		FROM Profile P, Date D
		WHERE D.Profile2Id = P.ProfileId AND D.Date_Time > DATE_SUB(CURDATE(),INTERVAL 1 MONTH)
		GROUP BY OwnerSSN
		) dateSSNs
	GROUP BY(OwnerSSN)
	UNION ALL
	SELECT OwnerSSN, SUM(numLikes) AS points
	FROM
		(
		SELECT P.OwnerSSN, COUNT(*) AS numLikes
		FROM  Profile P, Likes L
		WHERE L.LikerId = P.ProfileId AND L.Date_Time > DATE_SUB(CURDATE(),INTERVAL 1 MONTH)
		GROUP BY OwnerSSN
		) likeSSNs
	GROUP BY (OwnerSSN)) tableA
GROUP BY(OwnerSSN)
HAVING(totalPoints > 2)


--Produce a list of all customers who have dated a particular customer
SELECT P.OwnerSSN 
FROM 
	(
	SELECT D.Profile1Id
	FROM Date D,
		(
		SELECT P.ProfileId AS ProfilesWeAreLookingFor
		FROM Profile P
		WHERE OwnerSSN = '?'
		) profilesOfOurCustomer
	WHERE D.Profile2Id = profilesOfOurCustomer.ProfilesWeAreLookingFor
UNION
	SELECT D.Profile2Id 
	FROM Date D, 
		(
		SELECT P.ProfileId AS ProfilesWeAreLookingFor
		FROM Profile P
		WHERE OwnerSSN = '?'
		) profilesOfOurCustomer
	WHERE D.Profile1Id = profilesOfOurCustomer.ProfilesWeAreLookingFor
	) profilesWhoHaveDatedOurCustomer, Profile P
WHERE P.ProfileId = profilesWhoHaveDatedOurCustomer.Profile1Id

--Produce a list of the highest-rated customers
--We interpreted this as any customer who has a rating of 4 or greater
--If you wanted a 'top X' list you can also limit this query to a variable X and sort it in descending order
SELECT C.SSN
FROM Customer C
WHERE C.Rating > 3

--Produce a list of the highest-rated calendar dates to have a date on
--We interpreted this as any day with an average combined user rating of 7.
SELECT CAST(D.Date_Time AS DATE), AVG(D.User1Rating+D.User2Rating)
FROM Profile P, Date D
GROUP BY CAST(D.Date_Time AS DATE)
HAVING (AVG(D.User1Rating+D.User2Rating) >= 7)

--Record a Date
INSERT INTO Date
VALUES('?', '?', '?', '?', '?', ?, '?', ?, ?)

--Add, Edit, Delete information for a customer
--Add
INSERT INTO Person
VALUES('?', '?', '?', '?','?','?','?',?,'?','?');

INSERT INTO Customer
VALUES('?', '?', ?, '?')

--Edit
UPDATE Customer
SET PPP = '?'
WHERE SSN = '?';

UPDATE Customer
SET SSN = '?'
WHERE SSN = '?';

UPDATE Customer
SET LastActive = '?'
WHERE SSN = '?';

UPDATE Customer
SET Rating = ?
WHERE SSN = '?'

--Delete
DELETE FROM Customer
WHERE SSN = '?'

--Produce customer mailing list
SELECT P.FirstName, P.LastName, P.Email
FROM Person P, Customer C
WHERE C.SSN = P.SSN

--Produce a list of profiles as date suggestions for a given profile (based on that profiles past dates)
--We interpreted this as finding all profiles that fall within the dating age range of our profile in question, and matching them with people who have the same 
--gender as their most frequently dated gender, and then finding profiles within a range of the average height, and weight of past profiles dated.
SELECT P.ProfileId
FROM Profile P,
	(SELECT AgeRangeStart, AgeRangeEnd
	FROM Profile P2
	WHERE P2.ProfileId = '?') ageRange,
	(SELECT Gender
	FROM
		(SELECT P.Gender, P.Height, P.Age, P.Weight, P.HairColor
		FROM Profile P, Date D
		WHERE D.Profile1Id = '?' AND D.Profile2Id = P.ProfileId
		UNION
		SELECT P.Gender, P.Height, P.Age, P.Weight, P.HairColor
		FROM Profile P, Date D
		WHERE D.Profile2Id = '?' AND D.Profile1Id = P.ProfileId) profilesDated
		GROUP BY (Gender)
		ORDER BY COUNT(*) DESC
		LIMIT    1) topGender,
		
	(SELECT AVG(Height) AS Height, AVG(Weight) AS Weight
	FROM
		(SELECT P.Gender, P.Height, P.Age, P.Weight, P.HairColor
		FROM Profile P, Date D
		WHERE D.Profile1Id = '?' AND D.Profile2Id = P.ProfileId
		UNION
		SELECT P.Gender, P.Height, P.Age, P.Weight, P.HairColor
		FROM Profile P, Date D
		WHERE D.Profile2Id = '?' AND D.Profile1Id = P.ProfileId) profilesDated) avgHeightWeight,
	(SELECT P.ProfileId, Pe.State
	FROM Person Pe, Profile P
	WHERE P.OwnerSSN = Pe.SSN AND P.ProfileId = '?') ourState,
	(SELECT P.ProfileId, Pe.State
	FROM Person Pe, Profile P
	WHERE P.OwnerSSN = Pe.SSN AND P.ProfileId != '?') profilesStates
WHERE P.Gender = topGender.Gender AND (P.Height > avgHeightWeight.Height-.5) AND (P.Height < avgHeightWeight.Height+.5) AND
(P.Weight > avgHeightWeight.Weight-50) AND (P.Weight < avgHeightWeight.Weight+50) AND (P.Age >= ageRange.AgeRangeStart) AND (P.Age <= ageRange.AgeRangeEnd) AND
(ourState.State = profilesStates.State AND P.ProfileId = profilesStates.ProfileId)

--Make a date with another customer profile
INSERT INTO Date
VALUES('?', '?', '?', '?', '?', ?, NULL, NULL, NULL);

--Make a Geo-date with another customer profile
--It is just a date but with someone near you? so its still just an insert statement?
INSERT INTO Date
VALUES('?', '?', '?', '?', '?', ?, NULL, NULL, NULL);

--Cancel a date
--We are setting date time as null so that we still have record of the purchase and all the information that went along with the date.
UPDATE Date
SET Date_Time = NULL
WHERE Profile1Id = '?', Profile2Id = '?', Date_Time = '?'

--Comment on a date he/she went on or is going on
UPDATE Date
SET Comments = '?'
WHERE Profile1Id = '?', Profile2Id = '?', Date_Time = '?'

--Like another customer's profile
INSERT INTO Likes
VALUES('?', '?', '?')

--Refer a profile B to Profile C so that Profile C can go on a "blind" with Profile B
INSERT INTO Referral
VALUES('?','?','?','?')

--A Profile's pending dates
SELECT DISTINCT D.*
FROM Date D
WHERE D.Date_Time > CURDATE() AND (D.Profile1Id = '?' OR D.Profile2Id = '?')

--A profile's past Dates
SELECT DISTINCT D.*
FROM Date D
WHERE D.Date_Time < CURDATE() AND (D.Profile1Id = '?' OR D.Profile2Id = '?')

--A profile's favorites list(based on "likes")
--We interpreted this as a profiles top 3 liked profiles.
SELECT L.LikeeId, COUNT(*)
FROM Likes L
WHERE L.LikerId = '?'
GROUP BY(L.LikeeId)
ORDER BY COUNT(*) DESC
LIMIT    3

--Search for profiles based on physical characteristics, location, etc.
--Find by height
SELECT P.*
FROM Profile P
WHERE P.Height = '?'
--Find by Weight
SELECT P.*
FROM Profile P
WHERE P.Weight = '?'
--Find by age
SELECT P.*
FROM Profile P
WHERE P.Age = '?'
--Find by Haircolor
SELECT P.*
FROM Profile P
WHERE P.HairColor = '?'
--Find by Hobbies
SELECT P.*
FROM Profile P
WHERE P.Hobbies = '?'
--Find by State
SELECT P.*
FROM Profile P,
	(SELECT P.ProfileId, Pe.State
	FROM Person Pe, Profile P
	WHERE P.OwnerSSN = Pe.SSN AND P.ProfileId != '?') profilesStates
WHERE profilesStates.State = '?' AND P.ProfileId = profilesStates.ProfileId
--Find by Zipcode
SELECT P.*
FROM Profile P,
	(SELECT P.ProfileId, Pe.ZipCode
	FROM Person Pe, Profile P
	WHERE P.OwnerSSN = Pe.SSN AND P.ProfileId != '?') profilesZips
WHERE profilesStates.ZipCode = '?' AND P.ProfileId = profilesStates.ProfileId
--Find by City
SELECT P.*
FROM Profile P,
	(SELECT P.ProfileId, Pe.City
	FROM Person Pe, Profile P
	WHERE P.OwnerSSN = Pe.SSN AND P.ProfileId != '?') profilesCity
WHERE profilesStates.City = '?' AND P.ProfileId = profilesStates.ProfileId

--Most active profiles
--We interpreted this as all the users who have had over 3 dates and likes in the last month. Obviously this number is arbitrary and can be anything that you set as a threshhold.
SELECT ProfileId, SUM(points) AS totalPoints
FROM
	(SELECT ProfileId, SUM(numDates) AS points
	FROM
		(
		SELECT P.ProfileId, COUNT(*) AS numDates
		FROM  Profile P, Date D
		WHERE D.Profile1Id = P.ProfileId AND D.Date_Time > DATE_SUB(CURDATE(),INTERVAL 1 MONTH)
		GROUP BY (ProfileId)
		UNION ALL
		SELECT P.ProfileId, COUNT(*) AS numDates
		FROM Profile P, Date D
		WHERE D.Profile2Id = P.ProfileId AND D.Date_Time > DATE_SUB(CURDATE(),INTERVAL 1 MONTH)
		GROUP BY (ProfileId)
		) dateSSNs
	GROUP BY(ProfileId)
	UNION ALL
	SELECT ProfileId, SUM(numLikes) AS points
	FROM
		(
		SELECT P.ProfileId, COUNT(*) AS numLikes
		FROM  Profile P, Likes L
		WHERE L.LikerId = P.ProfileId AND L.Date_Time > DATE_SUB(CURDATE(),INTERVAL 1 MONTH)
		GROUP BY (ProfileId)
		) likeSSNs
	GROUP BY (ProfileId)) tableA
GROUP BY(ProfileId)
HAVING(totalPoints > 2)

--Most highly rated profiles
--We interpreted this as any profile that had an average user rating from their dates above a 4
--maybe in the future limit this by the number of dates, so that a profile that goes on 1 date and gets 1 good rating doesn't beat a serial dater with an overall good ratings
SELECT ProfileId, AVG(UserRating) AS avgProfileRating
FROM 
	(
	SELECT P.ProfileId, D.User2Rating AS UserRating
	FROM  Profile P, Date D
	WHERE D.Profile1Id = P.ProfileId
	UNION ALL
	SELECT P.ProfileId, D.User1Rating AS UserRating
	FROM Profile P, Date D
	WHERE D.Profile2Id = P.ProfileId
	) dateRatings
GROUP BY(ProfileId)
HAVING(avgProfileRating > 4)

--Popular Geo-date locations
--we interpreted this as any location where multiple dates occurred(possibly limit this by a larger number once we have bigger data)
SELECT D.Location, COUNT(*)
FROM Date D
GROUP BY(D.Location)
HAVING(COUNT(*) > 1)

--Personalized date suggestion list
--We interpreted this as a search function for our users to find profiles based on inputted preferences.
SELECT P.ProfileId
FROM Profile P,
	(SELECT AgeRangeStart, AgeRangeEnd
	FROM Profile P2
	WHERE P2.ProfileId = '?') ageRange,
	(SELECT P.ProfileId, Pe.State
	FROM Person Pe, Profile P
	WHERE P.OwnerSSN = Pe.SSN AND P.ProfileId = '?') ourState,
	(SELECT P.ProfileId, Pe.State
	FROM Person Pe, Profile P
	WHERE P.OwnerSSN = Pe.SSN AND P.ProfileId != '?') profilesStates
WHERE P.Gender = '?' AND (P.Height > '?') AND (P.Height < '?') AND
(P.Weight > '?') AND (P.Weight < '?') AND (P.Age >= ageRange.AgeRangeStart) AND (P.Age <= ageRange.AgeRangeEnd) AND
(profilesStates.State = '?' AND P.ProfileId = profilesStates.ProfileId)






























