USE master
CREATE DATABASE WWI
go

USE [WWI]
GO
CREATE SCHEMA UserSchema; /*User Group*/
GO 
CREATE SCHEMA ProductsSchema; /*Products Group*/
GO
CREATE SCHEMA SalesSchema; /*Sales Group*/
GO
CREATE SCHEMA RegionsSchema; /*Regions Group*/
GO


/* IMPORTANTE
Devido ao ambiente onde o trabalho foi desenvolvido,
Nao foi possivel criar os Files noutro diretorio! */

ALTER DATABASE [WWI]
ADD FILEGROUP ProductFileGroup;
ALTER DATABASE [WWI] 
ADD FILE
(
	NAME = ProductFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ProductFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = ColorFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ColorFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = PackageFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PackageFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = StockFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\StockFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = CategoryFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\CategoryFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
)
TO FILEGROUP ProductFileGroup
GO

ALTER DATABASE [WWI]
ADD FILEGROUP SaleFileGroup;
ALTER DATABASE [WWI] 
ADD FILE
(
	NAME = DiscountFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\DiscountFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = SaleFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SaleFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = SaleProducttFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SaleProductFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = InvoiceFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\InvoiceFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
)
TO FILEGROUP SaleFileGroup
GO

ALTER DATABASE [WWI]
ADD FILEGROUP PersonFileGroup;
ALTER DATABASE [WWI] 
ADD FILE
(
	NAME = AuthenticationEventFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\AuthenticationEventFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = BuyingGroupFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\BuyingGroupFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = PersonFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PersonFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = EmployeeFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\EmployeeFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = PersonTypeFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\PersonTypeFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = TokenPasswordFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\TokenPasswordFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = CustomerFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\CustomerFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
)
TO FILEGROUP PersonFileGroup
GO

ALTER DATABASE [WWI]
ADD FILEGROUP RegionFileGroup;
ALTER DATABASE [WWI] 
ADD FILE
(
	NAME = CityFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\CityFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = ContinentFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\ContinentFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = CountryFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\CountryFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = StateFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\StateFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = CountryStateFile,
    FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\CountryStateFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
)

TO FILEGROUP RegionFileGroup
GO

/****** Object:  Table [dbo].[AuthenticationEvent]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [UserSchema].[AuthenticationEvent](
	[idAuthenticationEvent] [int] IDENTITY(1,1) NOT NULL,
	[personId] [int] NOT NULL,
	[authenticationGDH] [datetime] NOT NULL,
	[ipAddress] [nvarchar](15) NOT NULL,
	[actionResult] [bit] NOT NULL,
 CONSTRAINT [PK_AuthenticationEvent] PRIMARY KEY CLUSTERED 
(
	[idAuthenticationEvent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PersonFileGroup]
) ON [PersonFileGroup]
GO
/****** Object:  Table [dbo].[BuyingGroup]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [UserSchema].[BuyingGroup](
	[idBuyingGroup] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_BuyingGroup] PRIMARY KEY CLUSTERED 
(
	[idBuyingGroup] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PersonFileGroup]
) ON [PersonFileGroup]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [ProductsSchema].[Category](
	[idCategory] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[idCategory] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [ProductFileGroup]
) ON [ProductFileGroup]
GO
/****** Object:  Table [dbo].[City]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [RegionsSchema].[City](
	[idCity] [int] NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[stateId] [varchar](2) NOT NULL,
	[salesTerritory] [nvarchar](100) NOT NULL,
	[latestRecordedPopulation] [int] NOT NULL,
 CONSTRAINT [PK_City] PRIMARY KEY CLUSTERED 
(
	[idCity] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [RegionFileGroup]
) ON [RegionFileGroup]
GO
/****** Object:  Table [dbo].[Color]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [ProductsSchema].[Color](
	[idColor] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Color] PRIMARY KEY CLUSTERED 
(
	[idColor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [ProductFileGroup]
) ON [ProductFileGroup]
GO
/****** Object:  Table [dbo].[Continent]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [RegionsSchema].[Continent](
	[idContinent] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK_Continent] PRIMARY KEY CLUSTERED 
(
	[idContinent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [RegionFileGroup]
) ON [RegionFileGroup]
GO
/****** Object:  Table [dbo].[Country]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [RegionsSchema].[Country](
	[idCountry] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](300) NOT NULL,
	[continentId] [int] NOT NULL,
	--[stateId] [int] NOT NULL,
 CONSTRAINT [PK_Country] PRIMARY KEY CLUSTERED 
(
	[idCountry] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [RegionFileGroup]
) ON [RegionFileGroup]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [UserSchema].[Customer](
	[idCustomer] [int] IDENTITY(1,1) NOT NULL,
	[personId] [int] NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[buyingGroupId] [int] NOT NULL,
	--[cityId] [int] NOT NULL,
	[categoryId] [int] NOT NULL,
	[postalCode] [nvarchar](25) NULL,
	[wwiCustomerId] [int] NOT NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[idCustomer] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PersonFileGroup]
) ON [PersonFileGroup]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [SalesSchema].[Discount](
	[idDiscount] [int] IDENTITY(1,1) NOT NULL,
	[discountPercentage] [int] NOT NULL,
	[beginDateGDH] [datetime] NOT NULL,
	[endDateGDH] [datetime] NOT NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[idDiscount] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [SaleFileGroup]
) ON [SaleFileGroup]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [UserSchema].[Employee](
	[idEmployee] [int] IDENTITY(1,1) NOT NULL,
	[personId] [int] NOT NULL,
	[vatNumber] [int] NULL,
	[beginDateGDH] [datetime] NOT NULL,
	[endDateGDH] [datetime] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[idEmployee] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PersonFileGroup]
) ON [PersonFileGroup]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [SalesSchema].[Invoice](
	[idInvoice] [int] NOT NULL,
	--[cityId] [int] NOT NULL,
	[invoiceDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Invoice] PRIMARY KEY CLUSTERED 
(
	[idInvoice] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [SaleFileGroup]
) ON [SaleFileGroup]
GO
--/****** Object:  Table [dbo].[InvoiceCustomer]    Script Date: 07-11-2022 11:03:13 ******/
--SET ANSI_NULLS ON
--GO
--SET QUOTED_IDENTIFIER ON
--GO
--CREATE TABLE [dbo].[InvoiceCustomer](
--	[idInvoiceCustomer] [int] IDENTITY(1,1) NOT NULL,
--	[invoiceId] [int] NOT NULL,
--	[customerId] [int] NOT NULL,
--	[saleId] [int] NOT NULL,
-- CONSTRAINT [PK_InvoiceCustomer_1] PRIMARY KEY CLUSTERED 
--(
--	[idInvoiceCustomer] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [InvoiceFileGroup]
--) ON [InvoiceFileGroup]
--GO
/****** Object:  Table [dbo].[Package]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [ProductsSchema].[Package](
	[idPackage] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_Package] PRIMARY KEY CLUSTERED 
(
	[idPackage] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [ProductFileGroup]
) ON [ProductFileGroup]
GO
/****** Object:  Table [dbo].[Person]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [UserSchema].[Person](
	[idPerson] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[preferredName] [nvarchar](50) NOT NULL,
	--[isSalesPerson] [bit] NOT NULL,
	[email] [nvarchar](500) NULL,
	[password] [nvarchar](50) NULL,
	[photo] [varbinary](max) NULL,
	--[isActive] [bit] NOT NULL,
	[personTypeId] [int] NOT NULL,
 CONSTRAINT [PK_Person] PRIMARY KEY CLUSTERED 
(
	[idPerson] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PersonFileGroup]
) ON [PersonFileGroup] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PersonType]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [UserSchema].[PersonType](
	[idPersonType] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK_PersonType] PRIMARY KEY CLUSTERED 
(
	[idPersonType] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PersonFileGroup]
) ON [PersonFileGroup]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [ProductsSchema].[Product](
	[idProduct] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[colorId] [int] NOT NULL,
	[discountId] [int] NOT NULL,
	[unitPrice] [money] NOT NULL,
	[taxRate] [int] NOT NULL,
	[brand] [nvarchar](250) NOT NULL,
	[size] [nvarchar](25) NOT NULL,
	[barCode] [nvarchar](30) NOT NULL,
	[recommendedRetailPrice] [money] NOT NULL,
	[typicalWeight] [float] NOT NULL,
	[leadTimedays] [int] NOT NULL,
	[quantityPerOuter] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[idProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [ProductFileGroup]
) ON [ProductFileGroup]
GO
/****** Object:  Table [dbo].[Sale]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [SalesSchema].[Sale](
	[idSale] [int] NOT NULL,
	[deliveryDate] [datetime] NULL,
	[employeeId] [int] NOT NULL,
	[customerId] [int] NOT NULL,
	[invoiceId] [int] NOT NULL,
	[cityId] [int] NOT NULL,
	[notes] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_Sale] PRIMARY KEY CLUSTERED 
(
	[idSale] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [SaleFileGroup]
) ON [SaleFileGroup]
GO
/****** Object:  Table [dbo].[SaleProduct]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [SalesSchema].[SaleProduct](
	[idSaleProduct] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NOT NULL,
	[saleId] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[description] [nvarchar](250) NOT NULL,
	[totalExcludingTax] [money] NOT NULL,
	[taxAmout] [money] NOT NULL,
	[profit] [money] NOT NULL,
	[totalIncludingTax] [money] NOT NULL,
	[totalDryItens] [int] NOT NULL,
	[totalChillerItens] [int] NOT NULL,
 CONSTRAINT [PK_ProductInvoice] PRIMARY KEY CLUSTERED 
(
	[idSaleProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [SaleFileGroup]
) ON [SaleFileGroup]
GO
/****** Object:  Table [dbo].[State]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [RegionsSchema].[State](
	[idState] [varchar](2) NOT NULL,
	[name] [nvarchar](300) NOT NULL,
	--[countryId] [int] NOT NULL,
 CONSTRAINT [PK_State] PRIMARY KEY CLUSTERED 
(
	[idState] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [RegionFileGroup]
) ON [RegionFileGroup]
GO
/****** Object:  Table [dbo].[CountryState]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [RegionsSchema].[CountryState](
	[idCountryState] [int] IDENTITY(1,1) NOT NULL,
	[stateId] [varchar](2) NOT NULL,
	[countryId] [int] NOT NULL,
 CONSTRAINT [PK_CountryState] PRIMARY KEY CLUSTERED 
(
	[idCountryState] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [RegionFileGroup]
) ON [RegionFileGroup]
GO
/****** Object:  Table [dbo].[Stock]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [ProductsSchema].[Stock](
	[idStock] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NOT NULL,
	--[colorId] [int] NOT NULL,
	[quantity] [int] NULL,
	[sellingPackageId] [int] NOT NULL,
	[buyingPackageId] [int] NOT NULL,
	[isChillerStock] [bit] NOT NULL,
 CONSTRAINT [PK_Stock] PRIMARY KEY CLUSTERED 
(
	[idStock] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [ProductFileGroup]
) ON [ProductFileGroup]
GO
/****** Object:  Table [dbo].[TokenPassword]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [UserSchema].[TokenPassword](
	[idTokenPassword] [int] IDENTITY(1,1) NOT NULL,
	[personId] [int] NOT NULL,
	[token] [uniqueidentifier] NOT NULL,
	[generationGDH] [datetime] NOT NULL
) ON [PersonFileGroup]
GO
ALTER TABLE [UserSchema].[AuthenticationEvent]  WITH CHECK ADD  CONSTRAINT [FK_AuthenticationEvent_Person] FOREIGN KEY([personId])
REFERENCES [UserSchema].[Person] ([idPerson])
GO
ALTER TABLE [UserSchema].[AuthenticationEvent] CHECK CONSTRAINT [FK_AuthenticationEvent_Person]
GO
ALTER TABLE [RegionsSchema].[City]  WITH CHECK ADD  CONSTRAINT [FK_City_State] FOREIGN KEY([stateId])
REFERENCES [RegionsSchema].[State] ([idState])
GO
ALTER TABLE [RegionsSchema].[City] CHECK CONSTRAINT [FK_City_State]
GO
--
ALTER TABLE [SalesSchema].[Sale] WITH CHECK ADD  CONSTRAINT [FK_Sale_City] FOREIGN KEY([cityId])
REFERENCES [RegionsSchema].[City] ([idCity])
GO
ALTER TABLE [SalesSchema].[Sale] CHECK CONSTRAINT [FK_Sale_City]
GO
--
ALTER TABLE [RegionsSchema].[Country]  WITH CHECK ADD  CONSTRAINT [FK_Country_Continent] FOREIGN KEY([continentId])
REFERENCES [RegionsSchema].[Continent] ([idContinent])
GO
ALTER TABLE [RegionsSchema].[Country] CHECK CONSTRAINT [FK_Country_Continent]
GO
ALTER TABLE [UserSchema].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_BuyingGroup] FOREIGN KEY([buyingGroupId])
REFERENCES [UserSchema].[BuyingGroup] ([idBuyingGroup])
GO
ALTER TABLE [UserSchema].[Customer] CHECK CONSTRAINT [FK_Customer_BuyingGroup]
GO
ALTER TABLE [UserSchema].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_Category] FOREIGN KEY([categoryId])
REFERENCES [ProductsSchema].[Category] ([idCategory])
GO
ALTER TABLE [UserSchema].[Customer] CHECK CONSTRAINT [FK_Customer_Category]
GO
ALTER TABLE [UserSchema].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_Person] FOREIGN KEY([personId])
REFERENCES [UserSchema].[Person] ([idPerson])
GO
ALTER TABLE [UserSchema].[Customer] CHECK CONSTRAINT [FK_Customer_Person]
GO
ALTER TABLE [ProductsSchema].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Discount] FOREIGN KEY([discountId])
REFERENCES [SalesSchema].[Discount] ([idDiscount])
GO
ALTER TABLE [ProductsSchema].[Product] CHECK CONSTRAINT [FK_Product_Discount]
GO
ALTER TABLE [UserSchema].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Person] FOREIGN KEY([personId])
REFERENCES [UserSchema].[Person] ([idPerson])
GO
ALTER TABLE [UserSchema].[Employee] CHECK CONSTRAINT [FK_Employee_Person]
GO
--
ALTER TABLE [SalesSchema].[Sale]  WITH CHECK ADD  CONSTRAINT [FK_Sale_Customer] FOREIGN KEY([customerId])
REFERENCES [UserSchema].[Customer] ([idCustomer])
GO
ALTER TABLE  [SalesSchema].[Sale]  CHECK CONSTRAINT [FK_Sale_Customer]
GO
ALTER TABLE [UserSchema].[Person]  WITH CHECK ADD  CONSTRAINT [FK_Person_PersonType] FOREIGN KEY([personTypeId])
REFERENCES [UserSchema].[PersonType] ([idPersonType])
GO
ALTER TABLE [UserSchema].[Person] CHECK CONSTRAINT [FK_Person_PersonType]
GO
ALTER TABLE [ProductsSchema].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Color] FOREIGN KEY([colorId])
REFERENCES [ProductsSchema].[Color] ([idColor])
GO
ALTER TABLE [ProductsSchema].[Product] CHECK CONSTRAINT [FK_Product_Color]
GO
ALTER TABLE [SalesSchema].[Sale]  WITH CHECK ADD  CONSTRAINT [FK_Sale_Employee] FOREIGN KEY([employeeId])
REFERENCES [UserSchema].[Employee] ([idEmployee])
GO
ALTER TABLE [SalesSchema].[Sale] CHECK CONSTRAINT [FK_Sale_Employee]
GO
ALTER TABLE [SalesSchema].[Sale]  WITH CHECK ADD  CONSTRAINT [FK_Sale_Invoice] FOREIGN KEY([invoiceId])
REFERENCES [SalesSchema].[Invoice] ([idInvoice])
GO
ALTER TABLE [SalesSchema].[Sale] CHECK CONSTRAINT [FK_Sale_Invoice]
GO
ALTER TABLE [SalesSchema].[SaleProduct]  WITH CHECK ADD  CONSTRAINT [FK_SaleProduct_Product] FOREIGN KEY([productId])
REFERENCES [ProductsSchema].[Product] ([idProduct])
GO
ALTER TABLE [SalesSchema].[SaleProduct] CHECK CONSTRAINT [FK_SaleProduct_Product]
GO
ALTER TABLE [SalesSchema].[SaleProduct]  WITH CHECK ADD  CONSTRAINT [FK_SaleProduct_Sale] FOREIGN KEY([saleId])
REFERENCES [SalesSchema].[Sale] ([idSale])
GO
ALTER TABLE [SalesSchema].[SaleProduct] CHECK CONSTRAINT [FK_SaleProduct_Sale]
GO
ALTER TABLE [RegionsSchema].[CountryState]  WITH CHECK ADD  CONSTRAINT [FK_CountryState_Country] FOREIGN KEY([countryId])
REFERENCES [RegionsSchema].[Country] ([idCountry])
GO
ALTER TABLE [RegionsSchema].[CountryState] CHECK CONSTRAINT [FK_CountryState_Country]
GO
ALTER TABLE [RegionsSchema].[CountryState]  WITH CHECK ADD  CONSTRAINT [FK_CountryState_State] FOREIGN KEY([stateId])
REFERENCES [RegionsSchema].[State] ([idState])
GO
ALTER TABLE [RegionsSchema].[CountryState] CHECK CONSTRAINT [FK_CountryState_State]
GO
ALTER TABLE [ProductsSchema].[Stock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_Package] FOREIGN KEY([sellingPackageId])
REFERENCES [ProductsSchema].[Package] ([idPackage])
GO
ALTER TABLE [ProductsSchema].[Stock] CHECK CONSTRAINT [FK_Stock_Package]
GO
ALTER TABLE [ProductsSchema].[Stock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_Package1] FOREIGN KEY([buyingPackageId])
REFERENCES [ProductsSchema].[Package] ([idPackage])
GO
ALTER TABLE [ProductsSchema].[Stock] CHECK CONSTRAINT [FK_Stock_Package1]
GO
ALTER TABLE [ProductsSchema].[Stock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_Product] FOREIGN KEY([productId])
REFERENCES [ProductsSchema].[Product] ([idProduct])
GO
ALTER TABLE [ProductsSchema].[Stock] CHECK CONSTRAINT [FK_Stock_Product]
GO
ALTER TABLE [UserSchema].[TokenPassword]  WITH CHECK ADD  CONSTRAINT [FK_TokenPassword_Person] FOREIGN KEY([personId])
REFERENCES [UserSchema].[Person] ([idPerson])
GO
ALTER TABLE [UserSchema].[TokenPassword] CHECK CONSTRAINT [FK_TokenPassword_Person]
GO