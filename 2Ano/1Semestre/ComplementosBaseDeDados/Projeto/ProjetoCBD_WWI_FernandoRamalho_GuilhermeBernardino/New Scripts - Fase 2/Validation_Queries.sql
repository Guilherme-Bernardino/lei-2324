-- Verificação de dados 
-- Nº de Customers
-- Nova Tabela
SELECT count(idCustomer) as [Number Of Customers On New WWI]
FROM [WWI].[UserSchema].[Customer]

-- Tabela Antiga
SELECT count([CUSTOMER KEY]) as [Number Of Customers On Old WWI]
FROM [WWI_DS].[dbo].[Customer]

-- Nº de Customers por categoria
-- Nova Tabela
SELECT ca.name , count(c.idCustomer) as NumberOfCustomersOnNewWWI
FROM [WWI].[UserSchema].[Customer] as c
JOIN [WWI].[ProductsSchema].[Category] as ca on ca.idCategory = c.categoryId
group by ca.name

-- Tabela Antiga
SELECT [Category] , count([CUSTOMER KEY]) as [Number Of Customers On Old WWI]
FROM [WWI_DS].[dbo].[Customer]
GROUP BY [Category]

-- Total de Vendas por Employee
-- Nova Tabela
SELECT Person.name , count(Sale.employeeId) as  [Total of Sales]
FROM [WWI].[SalesSchema].[Sale] as Sale
join [WWI].[UserSchema].[Employee] as Employee on Employee.idEmployee = Sale.employeeId
join [WWI].[UserSchema].[Person] as Person on Person.idPerson = Employee.personId
group by Person.name

-- Tabela Antiga
SELECT Emp.[Employee], count(sa.[Salesperson Key]) FROM [WWI_DS].[dbo].[Sale] as sa
join [WWI_DS].[dbo].[Employee] as Emp on Emp.[Employee Key] = sa.[Salesperson Key]
group by Emp.[Employee]

-- Total monetário de vendas por “Stock Item”
-- Nova Tabela
SELECT p.description , sum(sp.quantity * p.unitPrice) as [MonetaryValue] 
FROM [WWI].[SalesSchema].[SaleProduct] as sp
JOIN [WWI].[ProductsSchema].[Product] as p on p.idProduct = sp.productId
group by p.description

-- OU --

SELECT p.description , sum(totalExcludingTax) as [MonetaryValue] 
FROM [WWI].[SalesSchema].[SaleProduct] as sp
JOIN [WWI].[ProductsSchema].[Product] as p on p.idProduct = sp.productId
group by p.description

-- Tabela Antiga
SELECT si.[Stock Item], sum(sale.[Quantity] * sale.[Unit Price]) as [MonetaryValue]
FROM [WWI_DS].[dbo].[Sale] as sale
JOIN [WWI_DS].[dbo].[Stock Item] as si on si.[Stock Item Key] = sale.[Stock Item Key]
group by si.[Stock Item]

-- Total monetário de vendas por ano por “Stock Item”
-- Nova Tabela
SELECT p.description , YEAR(s.deliveryDate) as YearOfSale, sum(sp.quantity * p.unitPrice) as [MonetaryValue] 
FROM [WWI].[SalesSchema].[SaleProduct] as sp
JOIN [WWI].[ProductsSchema].[Product] as p on p.idProduct = sp.productId
JOIN [WWI].[SalesSchema].[Sale] as s on s.idSale = sp.saleId
group by p.description, YEAR(s.deliveryDate)
order by YEAR(s.deliveryDate) desc

-- OU -- 

SELECT p.description , YEAR(s.deliveryDate) as YearOfSale, sum(totalExcludingTax) as [MonetaryValue] 
FROM [WWI].[SalesSchema].[SaleProduct] as sp
JOIN [WWI].[ProductsSchema].[Product] as p on p.idProduct = sp.productId
JOIN [WWI].[SalesSchema].[Sale] as s on s.idSale = sp.saleId
group by p.description, YEAR(s.deliveryDate)
order by YEAR(s.deliveryDate) desc

-- Tabela Antiga
SELECT si.[Stock Item], YEAR(sale.[Delivery Date Key]) AS YearOfSale, sum(sale.[Quantity] * sale.[Unit Price]) as [MonetaryValue]
FROM [WWI_DS].[dbo].[Sale] as sale
JOIN [WWI_DS].[dbo].[Stock Item] as si on si.[Stock Item Key] = sale.[Stock Item Key]
group by si.[Stock Item], YEAR(sale.[Delivery Date Key])
order by  YEAR(sale.[Delivery Date Key]) desc

-- Total monetário de vendas por ano por “City”
-- Nova Tabela
SELECT c.name , YEAR(s.deliveryDate) as YearOfSale, sum(sp.quantity * p.unitPrice) as [MonetaryValue] 
FROM [WWI].[SalesSchema].[SaleProduct] as sp
JOIN [WWI].[ProductsSchema].[Product] as p on p.idProduct = sp.productId
JOIN [WWI].[SalesSchema].[Sale] as s on s.idSale = sp.saleId
JOIN [WWI].[RegionsSchema].[City] as c on c.idCity = s.cityId
group by c.name, YEAR(s.deliveryDate)
order by YEAR(s.deliveryDate) desc

-- OU -- 

SELECT c.name , YEAR(s.deliveryDate) as YearOfSale, sum(totalExcludingTax) as [MonetaryValue] 
FROM [WWI].[SalesSchema].[SaleProduct] as sp
JOIN [WWI].[ProductsSchema].[Product] as p on p.idProduct = sp.productId
JOIN [WWI].[SalesSchema].[Sale] as s on s.idSale = sp.saleId
JOIN [WWI].[RegionsSchema].[City] as c on c.idCity = s.cityId
group by c.name, YEAR(s.deliveryDate)
order by YEAR(s.deliveryDate) desc

-- Tabela Antiga
SELECT c.[City], YEAR(sale.[Delivery Date Key]) AS YearOfSale, sum(sale.[Quantity] * sale.[Unit Price]) as [MonetaryValue]
FROM [WWI_DS].[dbo].[Sale] as sale
JOIN [WWI_DS].[dbo].[City] as c on c.[City Key] = sale.[City Key]
group by c.[City], YEAR(sale.[Delivery Date Key])
order by  YEAR(sale.[Delivery Date Key]) desc