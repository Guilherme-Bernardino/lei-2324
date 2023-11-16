-- Programa��o
-- Gest�o de utilizadores:
-- Insert User
/*Descri��o: Storage Procedure que cria um utilizador (Person);
Parametros de entrada: Colunas da Tabela 
Fun��o: Inserir na tabela Person, atrav�s dos valores dos parametros de entradas, as respetivas colunas da tabela
*/
Use WWI
GO
CREATE or ALTER PROCEDURE Person_insertInto
(@name nvarchar(100),
@preferredName nvarchar(50),
@isSalesPerson bit,
@email nvarchar(50),
@password nvarchar(50),
@photo varbinary(max),
@isActive bit ,
@personTypeId int)
AS
BEGIN 
	INSERT INTO [dbo].[Person](name, preferredName, isSalesPerson,email, password, photo, isActive, personTypeId)
	VALUES (@name, @preferredName, @isSalesPerson, @email, @password, @photo, @isActive, @personTypeId)
END

-- Update User
/*Descri��o: Storage Procedure que atualiza um utilizador (Person);
Parametros de entrada: Colunas da Tabela 
Fun��o: Atualizar na tabela Person, atrav�s dos valores dos parametros de entrada, as respetivas colunas da tabela
*/
Use WWI
GO
CREATE or ALTER PROCEDURE Person_update
(@idPerson int,
@name nvarchar(100),
@preferredName nvarchar(50),
@isSalesPerson bit,
@email nvarchar(50),
@password nvarchar(50),
@photo varbinary(max),
@isActive bit ,
@personTypeId int)
AS
BEGIN 
	UPDATE [dbo].[Person] 
	SET name = @name,
	preferredName = @preferredName,
	isSalesPerson = @isSalesPerson,
	email = @email,
	password = @password,
	photo = @photo,
	isActive = @isActive,
	personTypeId = @personTypeId
	WHERE idPerson = @idPerson
END

--Remove User
/*Descri��o: Storage Procedure que remove um utilizador (Person);
Parametros de entrada: idPerson
Fun��o: Remover na tabela Person o registo equivalente ao @idPerson
*/
Use WWI
GO
CREATE or ALTER PROCEDURE Person_removeFrom
(@idPerson int)
AS
BEGIN 
	DELETE FROM [dbo].[Person] 
	WHERE idPerson = @idPerson
END

-- Gest�o de promo��es
-- Definir o conceito de promo��o sobre um ou mais produtos
/*Descri��o: Storage Procedure que cria uma promo��o (Discount);
Parametros de entrada: Colunas da Tabela 
Fun��o: Inserir na tabela Discount, atrav�s dos valores dos parametros de entradas, as respetivas colunas da tabela
*/
Use WWI
GO
CREATE or ALTER PROCEDURE newDiscount
(@productId int,
@discountPercentage int,
@beginDateGDH datetime,
@endDateGDH datetime)
AS
BEGIN 
	INSERT INTO [dbo].[Discount](productId, discountPercentage, beginDateGDH, endDateGDH)
	VALUES (@productId, @discountPercentage, @beginDateGDH, @endDateGDH)
END

-- Alterar as datas de In�cio e Fim de uma promo��o 
/*Descri��o: Storage Procedure que atualica uma promo��o (Discount);
Parametros de entrada: idDiscount, data de inicio, data de fim
Fun��o: Atualizar na tabela Person, atrav�s dos valores dos parametros de entrada, as respetivas colunas da tabela, relacionado com o valor do @idDiscount
*/
Use WWI
GO
CREATE or ALTER PROCEDURE updateDiscountDates
(@idDiscount int,
@beginDateGDH datetime,
@endDateGDH datetime)
AS
BEGIN 
	UPDATE [dbo].[Discount] 
	SET beginDateGDH = @beginDateGDH,
		endDateGDH = @endDateGDH 
	WHERE idDiscount = @idDiscount
END

-- Criar uma venda
/*Descri��o: Storage Procedure que cria uma venda (Sale);
Parametros de entrada: Colunas da Tabela 
Fun��o: Inserir na tabela Sale, atrav�s dos valores dos parametros de entradas, as respetivas colunas da tabela
*/
Use WWI
GO
CREATE or ALTER PROCEDURE createSale
(@idSale int,
@deliveryDate datetime,
@employeeId int,
@notes nvarchar(500))
AS
BEGIN 
	INSERT INTO [dbo].[Sale](idSale, deliveryDate, employeeId, notes)
	VALUES (@idSale, @deliveryDate, @employeeId, @notes)
END

-- Adicionar um produto a uma venda
/*Descri��o: Storage Procedure que cria um venda relacionada a um produto (SaleProduct);
Parametros de entrada: Colunas da Tabela 
Fun��o: Inserir na tabela SaleProduct, atrav�s dos valores dos parametros de entradas, as respetivas colunas da tabela
*/
Use WWI
GO
CREATE or ALTER PROCEDURE addProductToSale
(@productId int,
@saleId int,
@quantity int,
@description nvarchar(250),
@totalExcludingTax money,
@taxAmout money,
@profit money,
@totalIncludingTax money,
@totalDryItens int,
@totalChillerItens int)
AS
BEGIN 
	INSERT INTO [dbo].[SaleProduct](productId, saleId, quantity, description, totalExcludingTax, taxAmout, profit, totalIncludingTax, totalDryItens, totalChillerItens)
	VALUES (@productId, @saleId, @quantity, @description, @totalExcludingTax, @taxAmout, @profit, @totalIncludingTax, @totalDryItens, @totalChillerItens)
END

-- Alterar a quantidade de um produto numa venda
/*Descri��o: Storage Procedure que atualiza a quantidade de um produto da venda (SaleProduct);
Parametros de entrada: idSaleProduct, quantity, money-related params, total-related params
Fun��o: Atualizar na tabela SaleProduct, atrav�s dos valores dos parametros de entradas, as respetivas colunas da tabela (quantity and ) 
atrav�s do valor relacionada com @idSaleProduct
*/
Use WWI
GO
CREATE or ALTER PROCEDURE updateProductQuantityOnSale
(@idSaleProduct int,
@quantity int,
@totalExcludingTax money,
@taxAmout money,
@profit money,
@totalIncludingTax money,
@totalDryItens int,
@totalChillerItens int)
AS
BEGIN 
	UPDATE [dbo].[SaleProduct] 
	SET quantity = @quantity,
		totalExcludingTax = @totalExcludingTax,
		taxAmout = @taxAmout,
		profit = @profit,
		totalIncludingTax = @totalIncludingTax,
		totalDryItens = @totalDryItens,
		totalChillerItens = @totalChillerItens
	WHERE idSaleProduct = @idSaleProduct
END

-- Remover um produto de uma venda
/*Descri��o: Storage Procedure que remove uma relacionada a um produto (SaleProduct);
Parametros de entrada: idSaleProduct
Fun��o: Remover na tabela SaleProduct o registo equivalente ao idSaleProduct
*/
Use WWI
GO
CREATE or ALTER PROCEDURE deleteFromSaleProductTable
(@idSaleProduct int,
@idSale int)
AS
BEGIN 
	DELETE FROM [dbo].[SaleProduct] 
	WHERE idSaleProduct = @idSaleProduct

	DECLARE @saleEmpty bit
	SET @saleEmpty = [WWI].[dbo].[returnIfSaleEmpty](@idSale);

	if (@saleEmpty = 1)
	BEGIN
		DELETE FROM [dbo].[Sale]
		WHERE idSale = @idSale
	END
END

-- Fun��o que recebe um par�metro(idSale) que indica se a venda � removida no caso de n�o ter mais produtos associados
/*Descri��o: Fun��o que retorna um valor verdadeiro ou falso
Parametros de entrada: idSale
Fun��o: Verifica��o de que o idSale correspondente com o parametro @idSale n�o cont�m algum produto associada � venda. Caso verificar a existencia, retorna false, caso contr�rio
retorna verdade
*/
CREATE OR ALTER FUNCTION returnIfSaleEmpty
(@idSale int)
RETURNS bit
as
BEGIN
	IF(EXISTS (SELECT idSale from [dbo].[Sale] as s join [dbo].[SaleProduct] as c on (c.saleId = s.idSale) WHERE idSale = @idSale))
	BEGIN
		RETURN 0;
	END
	RETURN 1;
END;

-- Calcular o pre�o total de uma venda
/*Descri��o: Storage Procedure que retorna o valor total de uma venda (sum de todos os totais por produto na venda)
Parametros de entrada: saleId
Fun��o: Calcular o valor total de todos os produtos relacionados com uma venda
*/
Use WWI
GO
CREATE or ALTER PROCEDURE totalSalePriceProcedure
(@saleId int)
AS
BEGIN 
	select saleId as [Sale ID], sum(totalIncludingTax) as [Total Sale Price Taxed], sum(totalExcludingTax) as [Total Price NotTax]
	from [dbo].[SaleProduct]
	where saleId = @saleId 
	group by saleId
END

/*Descri��o: Fun��o que retorna o valor total de uma venda (sum de todos os totais por produto na venda)
Parametros de entrada: saleId
Fun��o: Calcular o valor total de todos os produtos relacionados com uma venda
*/
Use WWI
GO
CREATE OR ALTER FUNCTION totalSalePriceFunction
(@saleId int)
RETURNS int
AS
BEGIN 
	DECLARE @totalIncludingTax int
	SET @totalIncludingTax = (select sum(totalIncludingTax) as [Total Sale Price Taxed]
	from [dbo].[SaleProduct]
	where saleId = @saleId 
	group by saleId)

	RETURN @totalIncludingTax
END

-- Implementar a regra de neg�cio que verifique se a data de entrega est� de acordo com o tempo previsto de entrega de um produto (�Lead Time Days�)/*Descri��o: Fun��o que retorna uma mensagem caso a diferen�a entre data de entrega e a data corrente de uma venda n�o esteja de acordo com o leadTimeDays de um produtoParametros: idSaleFun��o: Calcular a diferen�a entre a data de entrega e a data corrente e verificar, retornando uma mensagem de erro, que o valor da diferen�a n�o corresponde ao lead time days*/Use WWI
GO
CREATE OR ALTER FUNCTION verifyLeadTimeDaysFunction
(@idSale int)
RETURNS nvarchar(100)
AS
BEGIN
DECLARE @ddate date
DECLARE @leadTimed int
DECLARE @currentDate date
DECLARE @dateCalc int
--Select deliveryDate from sale
SET @ddate = (SELECT s.deliveryDate
FROM [WWI].[dbo].[SaleProduct] as sp
JOIN [WWI].[dbo].[Product] as p on p.idProduct = sp.productId
JOIN [WWI].[dbo].[Sale] as s on s.idSale = sp.saleId
where s.idSale = @idSale)
--Select leadTimeDays from sale
SET @leadTimed = (SELECT p.leadTimedays
FROM [WWI].[dbo].[SaleProduct] as sp
JOIN [WWI].[dbo].[Product] as p on p.idProduct = sp.productId
JOIN [WWI].[dbo].[Sale] as s on s.idSale = sp.saleId
where s.idSale = @idSale)
--Get current date
SET @currentDate = CAST(GETDATE() AS DATE)
--Calculate difference between current date and delivery date
SET @dateCalc = DATEDIFF(day, @currentDate, @ddate )


IF(@dateCalc != @leadTimed)
	BEGIN
		return ('Lead Time Days doesnt correspond with delivery date!');
	END
return ('On time!');
END

-- N�o permitir uma venda conter produtos com e sem �Chiller Stock� - Function/*Descri��o: Fun��o que verifica se uma venda cont�m produtos com chiller stock e sem chiller stock ao mesmo tempoParametros: idSaleFun��o: Verificar a exist�ncia de chiller stock items contendo 0 ou multiplos na mesma venda, em que caso se verifique, retornar true*/Use WWI
GO
CREATE OR ALTER FUNCTION verifySaleContainsChillerStockFunction(@idSale int)
RETURNS bitAS
BEGIN	IF EXISTS(SELECT totalChillerItens FROM [WWI].[dbo].[SaleProduct] as sp
		JOIN [WWI].[dbo].[Sale] as s on s.idSale = sp.saleId
		where s.idSale = @idSale and totalChillerItens LIKE 0) 		BEGIN			IF EXISTS(SELECT totalChillerItens FROM [WWI].[dbo].[SaleProduct] as sp
				JOIN [WWI].[dbo].[Sale] as s on s.idSale = sp.saleId
				where s.idSale = @idSale and totalChillerItens not like 0) 				BEGIN					RETURN 1; -- Return true if there's multiple products with chill stock and non-chill stock at teh same time				END		ENDRETURN 0;END

-- Check if email is duplicate on insert into User Table (Person), and trigger
/*Descri��o: Trigger para verificar a exist�ncia de emails duplicados em utilizadores (Person)
Fun��o: Quando inserir um novo utilizador, disparar uma mensagem de erro, se existir um email associado a um utilizador
*/
CREATE or ALTER TRIGGER dbo_Insert_User_Email_TriggerDuplicate
ON [dbo].[Person]
AFTER INSERT
AS
IF exists ( SELECT * FROM [dbo].[Person] t inner join inserted i on i.email=t.email and i.idPerson <> t.idPerson)
BEGIN
    ROLLBACK
    RAISERROR ('Email alreay exists!', 16, 1);
END

--  A autentica��o perante a aplica��o ser� feita com recurso � conta de email e password
/*Descri��o: Stored Procedure para verificar a exist�ncia de um utilizador com atrav�s de email e password 
Parametros: email e password
Fun��o: Quando inserir o email e password, a procedure verifica se pertencem ao mesmo user, se pertencer a autentica��o � v�lida, e guarda na tabela de AuthenticationEvent com
o respetivo id do user, se n�o, apenas n�o guarda e n�o autentifica
*/
USE WWI
GO
CREATE or ALTER PROCEDURE authenticateUser
(@email nvarchar(50),
@password nvarchar(50))
as
BEGIN
DECLARE @idvalue int
	IF(EXISTS(SELECT * FROM [dbo].[Person] as p
	where p.email = @email and p.password = @password and p.personTypeId = 2))
	BEGIN
		PRINT('Aunthentication valid!');
		SET @idvalue = (SELECT p.idPerson FROM [dbo].[Person] as p
						where p.email = @email and p.password = @password and p.personTypeId = 2)
		INSERT INTO [dbo].[AuthenticationEvent] 
		VALUES (@idvalue, GETDATE(), '0.0.0.0', 1)
	END
	else
	BEGIN
		PRINT('Aunthentication invalid!');
	END
END

-- Recuperar Password
/*Descri��o: Gera um token na tabela TokenPassword para recuperar a password agregada a um email da conta de utilizador
Parametros: email
Fun��o: Cria um registo na tabela TokenPassword para ser usado para recupera��o de password
*/
USE WWI
GO
CREATE or ALTER PROCEDURE tokenGeneration
(@email nvarchar(50))  
AS
BEGIN
DECLARE @idPerson INT

SET @idPerson = (SELECT idPerson FROM [dbo].[Person]
				WHERE email = @email)

	INSERT INTO [dbo].[TokenPassword]
	VALUES (@idPerson, NEWID(), GETDATE())
END