/**
Transações

-Add Product to a Sale:
Este processo deve usar o nível de isolamento READ COMMITTED. Evita dirty reads,
mas permitirá que outras sessões façam alterações nos dados enquanto o processo estiver em execução.
*/

BEGIN TRANSACTION
    SET TRANSACTION ISOLATION LEVEL READ COMMITTED

	EXEC addProductToSale 1, 2, 2 , 'adasd', 20, 2, 3, 22, 2, 0

    -- Check if the product was added successfully
    IF @@ROWCOUNT = 0
        ROLLBACK TRANSACTION
    ELSE
        COMMIT TRANSACTION

/**
-Update the price of a product:
Este processo deve usar o nível de isolamento SERIALIZABLE.
Impede que outras sessões façam alterações nos dados até que o processo seja concluído.
Impede também que qualquer nível de isolamento de leitura confirmada e repetível leia os dados que estão a ser atualizados.
*/

BEGIN TRANSACTION
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE

    DECLARE @productId INT, 
	@unitPrice DECIMAL(18, 2)
    SET @productId = 1
    SET @unitPrice = 5.5

    -- Update the price of the product
    UPDATE [ProductsSchema].[Product]
    SET unitPrice = @unitPrice
    WHERE idProduct = @productId 
    AND idProduct NOT IN (SELECT productId FROM [SalesSchema].[Sale] as s
							JOIN [SalesSchema].[SaleProduct] as sp ON sp.saleId = s.idSale
							WHERE deliveryDate > GETDATE())

    -- Check if the product was updated successfully
	IF @@ROWCOUNT = 0
        ROLLBACK TRANSACTION
    ELSE
        COMMIT TRANSACTION

/**
-Calculate the total sale and/or the quantity of products
Este processo deve usar o nível de isolamento REPEATABLE READ.
Impede que outras sessões modifiquem os dados enquanto o processo estiver em execução.
Garante que os dados lidos pelo processo são consistentes durante a transação.
*/

BEGIN TRANSACTION
    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ

    DECLARE @saleId INT, 
	@totalSale DECIMAL(18, 2), 
	@totalQuantity INT
    SET @saleId = 2

    -- Calculate the total sale and quantity of products in the sale
    SELECT @totalSale = SUM(totalIncludingTax), @totalQuantity = SUM(quantity)
    FROM [SalesSchema].[SaleProduct]
    WHERE saleId = @saleId

    PRINT 'Total Sale: ' + CAST(@totalSale AS VARCHAR(20))
    PRINT 'Total Quantity: ' + CAST(@totalQuantity AS VARCHAR(20))

COMMIT TRANSACTION