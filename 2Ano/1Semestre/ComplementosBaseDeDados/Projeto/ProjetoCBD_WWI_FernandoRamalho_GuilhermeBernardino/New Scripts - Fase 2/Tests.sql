--Testes (exec, selects) --
-- Procedures
-- Person_insertInto
EXEC [WWI].[dbo].Person_insertInto 'Luís', 'Luís', 'lui.luis@hotmail.com', '123', null, 1

SELECT * FROM [WWI].[UserSchema].[Person]
WHERE name = 'Luís'

-- Person_update
EXEC [WWI].[dbo].Person_update 1615, 'Luís', 'Lui', 'lui.luis@hotmail.com', '123', null, 2

SELECT * FROM [WWI].[UserSchema].[Person]
WHERE name = 'Luís'

-- Person_removeFrom
EXEC [WWI].[dbo].Person_removeFrom 1615

SELECT * FROM [WWI].[UserSchema].[Person]
WHERE name = 'Luís'

-- newDiscount
EXEC [WWI].[dbo].newDiscount 10,'2022-11-20 00:00:00','2022-11-30 00:00:00'

SELECT * FROM [WWI].[SalesSchema].[Discount]


-- updateDiscountDates
EXEC [WWI].[dbo].updateDiscountDates 1 ,'2022-11-25 00:00:00','2022-12-05 00:00:00'

SELECT * FROM [WWI].[SalesSchema].[Discount]


-- createSale
EXEC [WWI].[dbo].createSale 600000, '2022-11-25 00:00:00',2,2,2, 2, 'Notas'

SELECT * FROM [WWI].[SalesSchema].[Sale] 
where idSale = 600000

-- addProductToSale
EXEC [WWI].[dbo].addProductToSale 10, 600000, 5, 5 ,200, 10, 0, 220, 0, 0

SELECT * FROM [WWI].[SalesSchema].[SaleProduct] 
WHERE saleId = 600000

-- updateProductQuantityOnSale
EXEC [WWI].[dbo].updateProductQuantityOnSale 228270, 12, 0,0, 0, 0, 0, 0

SELECT * FROM [WWI].[SalesSchema].[SaleProduct] 
WHERE saleId = 600000

-- deleteFromSaleProductTable
EXEC [WWI].[dbo].deleteFromSaleProductTable 228268, 600000

SELECT * FROM [WWI].[SalesSchema].[SaleProduct] 
WHERE saleId = 600000

-- totalSalePriceProcedure
EXEC [WWI].[dbo].totalSalePriceProcedure 600000

SELECT * FROM [WWI].[SalesSchema].[SaleProduct] 
WHERE saleId = 600000

-- authenticateUser
EXEC [WWI].[dbo].authenticateUser 'lui.luis@hotmail.com', '123'

SELECT * FROM [WWI].[UserSchema].Person
WHERE email = 'lui.luis@hotmail.com'

SELECT * FROM [WWI].[UserSchema].AuthenticationEvent

-- tokenGeneration
EXEC [WWI].[dbo].tokenGeneration 'lui.luis@hotmail.com' 

SELECT * FROM [WWI].[UserSchema].TokenPassword
-- Functions
-- returnIfSaleEmpty
select [WWI].[dbo].returnIfSaleEmpty (600000)

-- totalSalePriceFunction
select [WWI].[dbo].totalSalePriceFunction (600000) [Total Price Including Tax]

-- verifyLeadTimeDaysFunction 
select [WWI].[dbo].verifyLeadTimeDaysFunction (600000)

-- verifySaleContainsChillerStockFunction
select [WWI].[dbo].verifySaleContainsChillerStockFunction (600000)

SELECT * FROM [WWI].[SalesSchema].[SaleProduct]
join [WWI].[SalesSchema].[Sale] as sp on sp.idSale = saleId
WHERE saleId = 600000

--Triggers
-- dbo_Insert_User_Email_TriggerDuplicate
INSERT INTO [WWI].[UserSchema].[Person] VALUES ('Guilherme', 'Gui', 0, 'guib@hotmail.com', '123', null, 0, 1)
SELECT * FROM [WWI].[UserSchema].[Person]
WHERE name = 'Guilherme'

-- Metadata/Catalog Procedures
--Insert Generator
EXEC [WWI].[dbo].sp_insertProcedure 'Color'
EXEC Color_Insert '10', 'NavyBlue'
SELECT * FROM [WWI].[ProductsSchema].[Color]

--Update Generator
EXEC [WWI].[dbo].sp_updateProcedure 'Color'
EXEC Color_Update '10', 'LightBlue'
SELECT * FROM [WWI].[ProductsSchema].[Color]

-- Remove Generator
EXEC [WWI].[dbo].sp_deleteProcedure 'Color'
EXEC Color_Delete '10'
SELECT * FROM [WWI].[ProductsSchema].[Color]

-- Record Database
EXEC [WWI].[dbo].sp_RecordOfDatabase
SELECT * FROM ViewRecordOfWWI

-- Record number of records per table
EXEC [WWI].[dbo].sp_NumberOfRecords
SELECT * FROM ViewNumberOfRecords