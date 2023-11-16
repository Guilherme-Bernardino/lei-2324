/*Password Encryption*/
/*Função que faz hashing da password quando é criado um utilizador
Returns: hashed password*/
USE WWI
GO
CREATE or ALTER FUNCTION encryptPassword
(@password nvarchar(50))  
RETURNS nvarchar
AS
BEGIN
	RETURN HashBytes('MD5', @password)
END


/*----------------------------------------------------------*/
/*Price Encryption*/
/*Cria-se uma master key, cria-se um certificado, cria-se uma chave de encriptação com o algoritmo AES_256
Altera-se depois a tabela produtos para adicionar uma coluna com as chaves (unit price como coluna original)*/

USE WWI
GO
CREATE MASTER KEY ENCRYPTION
BY PASSWORD = 'SADA'
GO
CREATE CERTIFICATE WWI_CERT
WITH SUBJECT = 'Protect Data'
GO
CREATE SYMMETRIC KEY EncryptKey
WITH ALGORITHM = AES_256 ENCRYPTION 
BY CERTIFICATE WWI_CERT
GO
ALTER TABLE ProductsSchema.Product
ADD encryptedUnitPrice VARBINARY(256)
GO
OPEN SYMMETRIC KEY EncryptKey DECRYPTION 
BY CERTIFICATE WWI_CERT
UPDATE ProductsSchema.Product
SET encryptedUnitPrice = ENCRYPTBYKEY(KEY_GUID('EncryptKey'), CAST(unitPrice AS VARCHAR(20)))
GO

/*Drop Column*/
ALTER TABLE ProductsSchema.Product
DROP COLUMN encryptedUnitPrice

select * from UserSchema.Person