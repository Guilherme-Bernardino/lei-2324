/*Admin User*/
/*Create Login and User*/
USE [master]
GO
CREATE LOGIN [Admin] WITH PASSWORD=N'admin123' MUST_CHANGE, DEFAULT_DATABASE=[WWI], CHECK_EXPIRATION=ON, CHECK_POLICY=ON
GO
use [master];
GO
USE [WWI]
GO
CREATE USER [Admin] FOR LOGIN [Admin]
GO
USE [WWI]
GO
ALTER ROLE [db_datareader] ADD MEMBER [Admin]
GO
USE [WWI]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [Admin]
GO

/*EmployeeSalesPerson*/
/*Role db_employee*/
USE [WWI]
GO
CREATE ROLE [db_employee]
GO
USE [WWI]
GO
ALTER AUTHORIZATION ON SCHEMA::[SalesSchema] TO [db_employee]
GO

/*Create Login and User*/
USE [master]
GO
CREATE LOGIN [EmployeeSalesPerson] WITH PASSWORD=N'employee123' MUST_CHANGE, DEFAULT_DATABASE=[WWI], CHECK_EXPIRATION=ON, CHECK_POLICY=ON
GO
use [master];
GO
USE [WWI]
GO
CREATE USER [EmployeeSalesPerson] FOR LOGIN [EmployeeSalesPerson]
GO
USE [WWI]
GO
ALTER ROLE [db_employee] ADD MEMBER [EmployeeSalesPerson]
GO

/*SalesTerritory*/
/*Create Login and User*/
USE [master]
GO
CREATE LOGIN SalesTerritory WITH PASSWORD=N'guest' MUST_CHANGE, DEFAULT_DATABASE=[WWI], CHECK_EXPIRATION=ON, CHECK_POLICY=ON
GO
use [master];
GO
USE [WWI]
GO
CREATE USER SalesTerritory FOR LOGIN SalesTerritory
GO
GRANT SELECT ON dbo.view_SalesTerritory TO SalesTerritory;
GO
ALTER USER SalesTerritory WITH DEFAULT_SCHEMA=[RegionsSchema]
GO
ALTER AUTHORIZATION ON SCHEMA::[RegionsSchema] TO SalesTerritory
GO

/*View for SalesTerritory*/
USE [WWI]
GO
CREATE OR ALTER VIEW view_SalesTerritory AS
select * from RegionsSchema.City
WHERE salesTerritory = 'Rocky Mountain'

/*Tem permissões*/
EXECUTE AS USER = 'SalesTerritory'
GO
select * from view_SalesTerritory
REVERT;

/*Não tem permissões*/
EXECUTE AS USER = 'SalesTerritory'
GO
select * from view_SalesTerritory
REVERT;

EXECUTE AS USER = 'Admin'
GO
select * from [SalesSchema].[Sale]
REVERT;


