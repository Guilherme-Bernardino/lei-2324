/*Views*/

/*Pesquisa de vendas por cidade. Deve ser retornado o nome da cidade, o nome do vendedor,o total de vendas*/
CREATE or ALTER VIEW view_SalesTotalPerCity AS
SELECT City.name as City, Person.name as Employee, count(Sale.idSale) as  [Total of Sales]
FROM [WWI].[SalesSchema].[Sale] as Sale
join [WWI].[UserSchema].[Employee] as Employee on Employee.idEmployee = Sale.employeeId
join [WWI].[UserSchema].[Person] as Person on Person.idPerson = Employee.personId
join [WWI].[RegionsSchema].[City] as City on City.idCity = Sale.cityId
group by City.name, Person.name

/*Para as vendas calcular a taxa de crescimento de cada ano, face ao ano anterior, por categoria de cliente*/
CREATE or ALTER VIEW view_CategoryGrowthPerYear AS
SELECT Category.name, Year(Sale.deliveryDate) as [Current Year],
COUNT(idSale) as [Current Year Sales Amount],
LAG(Year(Sale.deliveryDate)) OVER (ORDER BY Category.name) AS [Previous Year],
LAG(COUNT(idSale)) OVER (ORDER BY Category.name) AS [Previous Year Sales Amount],
(COUNT(idSale) - LAG(COUNT(idSale)) OVER (ORDER BY Category.name)) as [Difference],
(CAST(COUNT(idSale) - LAG(COUNT(idSale)) OVER (ORDER BY Category.name)AS DECIMAL(10,4)) / LAG(COUNT(idSale)) OVER (ORDER BY Category.name)) as [Growth]
FROM [WWI].[SalesSchema].[Sale] as Sale
JOIN [WWI].[UserSchema].[Customer] as Customer on Sale.customerId = Customer.idCustomer
JOIN [WWI].[ProductsSchema].[Category] as Category on Customer.categoryId = Category.idCategory
group by Category.name, Year(Sale.deliveryDate)

/*Nº de produtos (stockItem) nas vendas por cor*/
CREATE or ALTER VIEW view_NOfProductsOnSalesPerColor AS
SELECT c.Name as Color, count(sp.productId) as [Total of Products]
FROM [WWI].[SalesSchema].[SaleProduct] as sp
JOIN [WWI].[ProductsSchema].[Product] AS p on sp.productId = p.idProduct
join [WWI].[ProductsSchema].[Color] as c on c.idColor = p.colorId
group by c.name

/*Indices*/
/*1 View*/
SELECT * FROM view_SalesTotalPerCity

SET ANSI_PADDING ON

/* INDEX CREATION */
CREATE NONCLUSTERED INDEX [_dta_index_City_6_997578592__K1_K2] ON [RegionsSchema].[City]
(
	[idCity] ASC,
	[name] ASC
)WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

/*2 View*/
SELECT * FROM view_CategotyGrowthPerYear
order by name, [Current Year]

/* INDEX CREATION */
CREATE NONCLUSTERED INDEX [_dta_index_Sale_6_1381579960__K4_2] ON [SalesSchema].[Sale]
(
	[customerId] ASC
)
INCLUDE([deliveryDate]) WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]

/*3 View*/
SELECT * FROM view_NOfProductsOnSalesPerColor

/* INDEX CREATION */
CREATE NONCLUSTERED INDEX [_dta_index_Product_6_1349579846__K3] ON [ProductsSchema].[Product]
(
	[colorId] ASC
)WITH (SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF) ON [PRIMARY]


-- Medicao do Tempo de execucao
set statistics time on
go

set statistics time off
go