USE master
CREATE DATABASE WWI

USE [WWI]
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
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\ProductFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = ColorFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\ColorFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = PackageFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\PackageFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = StockFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\StockFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = CategorykFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\CategorykFile.ndf',
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
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\DiscountFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = SaleFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\SaleFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = SaleProducttFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\SaleProducttFile.ndf',
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
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\AuthenticationEventFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = BuyingGroupFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\BuyingGroupFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = PersonFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\PersonFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = EmployeeFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\EmployeeFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = PersonTypeFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\PersonTypeFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = TokenPasswordFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\TokenPasswordFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
)
TO FILEGROUP PersonFileGroup
GO

ALTER DATABASE [WWI]
ADD FILEGROUP InvoiceFileGroup;
ALTER DATABASE [WWI] 
ADD FILE
(
	NAME = CityFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\CityFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = ContinentFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\ContinentFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = CountryFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\CountryFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = InvoiceFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\InvoiceFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
),
(
	NAME = InvoiceCustomerFile,
    FILENAME = 'D:\Work\MSSQL15.MSSQLSERVER\MSSQL\DATA\InvoiceCustomerFile.ndf',
    SIZE = 5MB,
    MAXSIZE = 100MB,
    FILEGROWTH = 5MB
)
TO FILEGROUP InvoiceFileGroup
GO


/****** Object:  Table [dbo].[AuthenticationEvent]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AuthenticationEvent](
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
CREATE TABLE [dbo].[BuyingGroup](
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
CREATE TABLE [dbo].[Category](
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
CREATE TABLE [dbo].[City](
	[idCity] [int] NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[stateId] [varchar](2) NOT NULL,
	[salesTerritory] [nvarchar](100) NOT NULL,
	[latestRecordedPopulation] [int] NOT NULL,
 CONSTRAINT [PK_City] PRIMARY KEY CLUSTERED 
(
	[idCity] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [InvoiceFileGroup]
) ON [InvoiceFileGroup]
GO
/****** Object:  Table [dbo].[Color]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Color](
	[idColor] [int] NOT NULL,
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
CREATE TABLE [dbo].[Continent](
	[idContinent] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
 CONSTRAINT [PK_Continent] PRIMARY KEY CLUSTERED 
(
	[idContinent] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [InvoiceFileGroup]
) ON [InvoiceFileGroup]
GO
/****** Object:  Table [dbo].[Country]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Country](
	[idCountry] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](300) NOT NULL,
	[continentId] [int] NOT NULL,
 CONSTRAINT [PK_Country] PRIMARY KEY CLUSTERED 
(
	[idCountry] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [InvoiceFileGroup]
) ON [InvoiceFileGroup]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[idCustomer] [int] IDENTITY(1,1) NOT NULL,
	[personId] [int] NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[buyingGroupId] [int] NOT NULL,
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
CREATE TABLE [dbo].[Discount](
	[idDiscount] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NOT NULL,
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
CREATE TABLE [dbo].[Employee](
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
CREATE TABLE [dbo].[Invoice](
	[idInvoice] [int] NOT NULL,
	[cityId] [int] NOT NULL,
	[invoiceDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Invoice] PRIMARY KEY CLUSTERED 
(
	[idInvoice] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [InvoiceFileGroup]
) ON [InvoiceFileGroup]
GO
/****** Object:  Table [dbo].[InvoiceCustomer]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InvoiceCustomer](
	[idInvoiceCustomer] [int] IDENTITY(1,1) NOT NULL,
	[invoiceId] [int] NOT NULL,
	[customerId] [int] NOT NULL,
	[saleId] [int] NOT NULL,
 CONSTRAINT [PK_InvoiceCustomer_1] PRIMARY KEY CLUSTERED 
(
	[idInvoiceCustomer] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [InvoiceFileGroup]
) ON [InvoiceFileGroup]
GO
/****** Object:  Table [dbo].[Package]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Package](
	[idPackage] [int] NOT NULL,
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
CREATE TABLE [dbo].[Person](
	[idPerson] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[preferredName] [nvarchar](50) NOT NULL,
	[isSalesPerson] [bit] NOT NULL,
	[email] [nvarchar](500) NULL,
	[password] [nvarchar](50) NULL,
	[photo] [varbinary](max) NULL,
	[isActive] [bit] NOT NULL,
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
CREATE TABLE [dbo].[PersonType](
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
CREATE TABLE [dbo].[Product](
	[idProduct] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](500) NOT NULL,
	[colorId] [int] NOT NULL,
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
CREATE TABLE [dbo].[Sale](
	[idSale] [int] NOT NULL,
	[deliveryDate] [datetime] NULL,
	[employeeId] [int] NOT NULL,
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
CREATE TABLE [dbo].[SaleProduct](
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
CREATE TABLE [dbo].[State](
	[idState] [varchar](2) NOT NULL,
	[name] [nvarchar](300) NOT NULL,
	[countryId] [int] NOT NULL,
 CONSTRAINT [PK_State] PRIMARY KEY CLUSTERED 
(
	[idState] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [SaleFileGroup]
) ON [SaleFileGroup]
GO
/****** Object:  Table [dbo].[Stock]    Script Date: 07-11-2022 11:03:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Stock](
	[idStock] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NOT NULL,
	[colorId] [int] NOT NULL,
	[quantity] [int] NOT NULL,
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
CREATE TABLE [dbo].[TokenPassword](
	[idTokenPassword] [int] IDENTITY(1,1) NOT NULL,
	[personId] [int] NOT NULL,
	[token] [uniqueidentifier] NOT NULL,
	[generationGDH] [datetime] NOT NULL
) ON [PersonFileGroup]
GO
ALTER TABLE [dbo].[AuthenticationEvent]  WITH CHECK ADD  CONSTRAINT [FK_AuthenticationEvent_Person] FOREIGN KEY([personId])
REFERENCES [dbo].[Person] ([idPerson])
GO
ALTER TABLE [dbo].[AuthenticationEvent] CHECK CONSTRAINT [FK_AuthenticationEvent_Person]
GO
ALTER TABLE [dbo].[City]  WITH CHECK ADD  CONSTRAINT [FK_City_State] FOREIGN KEY([stateId])
REFERENCES [dbo].[State] ([idState])
GO
ALTER TABLE [dbo].[City] CHECK CONSTRAINT [FK_City_State]
GO
ALTER TABLE [dbo].[Country]  WITH CHECK ADD  CONSTRAINT [FK_Country_Continent] FOREIGN KEY([continentId])
REFERENCES [dbo].[Continent] ([idContinent])
GO
ALTER TABLE [dbo].[Country] CHECK CONSTRAINT [FK_Country_Continent]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_BuyingGroup] FOREIGN KEY([buyingGroupId])
REFERENCES [dbo].[BuyingGroup] ([idBuyingGroup])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FK_Customer_BuyingGroup]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_Category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([idCategory])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FK_Customer_Category]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_Person] FOREIGN KEY([personId])
REFERENCES [dbo].[Person] ([idPerson])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FK_Customer_Person]
GO
ALTER TABLE [dbo].[Discount]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([idProduct])
GO
ALTER TABLE [dbo].[Discount] CHECK CONSTRAINT [FK_Discount_Product]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Person] FOREIGN KEY([personId])
REFERENCES [dbo].[Person] ([idPerson])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Person]
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD  CONSTRAINT [FK_Invoice_City] FOREIGN KEY([cityId])
REFERENCES [dbo].[City] ([idCity])
GO
ALTER TABLE [dbo].[Invoice] CHECK CONSTRAINT [FK_Invoice_City]
GO
ALTER TABLE [dbo].[InvoiceCustomer]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceCustomer_Customer] FOREIGN KEY([customerId])
REFERENCES [dbo].[Customer] ([idCustomer])
GO
ALTER TABLE [dbo].[InvoiceCustomer] CHECK CONSTRAINT [FK_InvoiceCustomer_Customer]
GO
ALTER TABLE [dbo].[InvoiceCustomer]  WITH CHECK ADD  CONSTRAINT [FK_InvoiceCustomer_Invoice] FOREIGN KEY([invoiceId])
REFERENCES [dbo].[Invoice] ([idInvoice])
GO
ALTER TABLE [dbo].[InvoiceCustomer] CHECK CONSTRAINT [FK_InvoiceCustomer_Invoice]
GO
ALTER TABLE [dbo].[Person]  WITH CHECK ADD  CONSTRAINT [FK_Person_PersonType] FOREIGN KEY([personTypeId])
REFERENCES [dbo].[PersonType] ([idPersonType])
GO
ALTER TABLE [dbo].[Person] CHECK CONSTRAINT [FK_Person_PersonType]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Color] FOREIGN KEY([colorId])
REFERENCES [dbo].[Color] ([idColor])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Color]
GO
ALTER TABLE [dbo].[Sale]  WITH CHECK ADD  CONSTRAINT [FK_Sale_Employee] FOREIGN KEY([employeeId])
REFERENCES [dbo].[Employee] ([idEmployee])
GO
ALTER TABLE [dbo].[Sale] CHECK CONSTRAINT [FK_Sale_Employee]
GO
ALTER TABLE [dbo].[SaleProduct]  WITH CHECK ADD  CONSTRAINT [FK_SaleProduct_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([idProduct])
GO
ALTER TABLE [dbo].[SaleProduct] CHECK CONSTRAINT [FK_SaleProduct_Product]
GO
ALTER TABLE [dbo].[SaleProduct]  WITH CHECK ADD  CONSTRAINT [FK_SaleProduct_Sale] FOREIGN KEY([saleId])
REFERENCES [dbo].[Sale] ([idSale])
GO
ALTER TABLE [dbo].[SaleProduct] CHECK CONSTRAINT [FK_SaleProduct_Sale]
GO
ALTER TABLE [dbo].[State]  WITH CHECK ADD  CONSTRAINT [FK_State_Country] FOREIGN KEY([countryId])
REFERENCES [dbo].[Country] ([idCountry])
GO
ALTER TABLE [dbo].[State] CHECK CONSTRAINT [FK_State_Country]
GO
ALTER TABLE [dbo].[Stock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_Package] FOREIGN KEY([sellingPackageId])
REFERENCES [dbo].[Package] ([idPackage])
GO
ALTER TABLE [dbo].[Stock] CHECK CONSTRAINT [FK_Stock_Package]
GO
ALTER TABLE [dbo].[Stock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_Package1] FOREIGN KEY([buyingPackageId])
REFERENCES [dbo].[Package] ([idPackage])
GO
ALTER TABLE [dbo].[Stock] CHECK CONSTRAINT [FK_Stock_Package1]
GO
ALTER TABLE [dbo].[Stock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([idProduct])
GO
ALTER TABLE [dbo].[Stock] CHECK CONSTRAINT [FK_Stock_Product]
GO
ALTER TABLE [dbo].[TokenPassword]  WITH CHECK ADD  CONSTRAINT [FK_TokenPassword_Person] FOREIGN KEY([personId])
REFERENCES [dbo].[Person] ([idPerson])
GO
ALTER TABLE [dbo].[TokenPassword] CHECK CONSTRAINT [FK_TokenPassword_Person]
GO