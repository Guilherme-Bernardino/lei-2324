/**** Generate Insert-like Storage Procedure(param Table-Name)****/
/*Descrição: Storage Procedure que recebe o argumento do nome de tabela (@table_name),
e constrói um sp de Insert Into @table_name, e obtém e armazena informação em variáveis da tabela escolhida: parametros, colunas e tipos de dados associados
@SPQuery é a variável de construção da criação da procedure de Insert
*/
USE WWI
GO
CREATE OR ALTER PROCEDURE sp_insertProcedure
(@table_name NVARCHAR(255))
AS
BEGIN
DECLARE @table_cols varchar(MAX)
DECLARE @table_cols_params VARCHAR(MAX)
DECLARE @sp_params VARCHAR(MAX)

	-- Colunas da Tabela
	SET @table_cols =  STUFF((SELECT ',' +COLUMN_NAME
					   FROM INFORMATION_SCHEMA.COLUMNS 
                       WHERE TABLE_NAME = @table_name
					   FOR XML PATH ('')), 1, 1, '')
	-- Parametros das colunas da Tabela
	SET @table_cols_params = STUFF((SELECT ',' + '@' + COLUMN_NAME
							 FROM INFORMATION_SCHEMA.COLUMNS 
                             WHERE TABLE_NAME = @table_name
							 FOR XML PATH ('')), 1, 1, '')
    -- Parametros da Storage Procedure (Colunas da tabela + o tipo de dados)
	SET @sp_params = STUFF((SELECT ',' + '@'+ COLUMN_NAME + ' ' + DATA_TYPE +(CASE WHEN DATA_TYPE = 'nvarchar' THEN '('+CAST(100 as nvarchar)+')' ELSE '' END
																		    + CASE WHEN DATA_TYPE = 'varchar' THEN '('+CAST(2 as varchar)+')'  ELSE '' END)
							FROM INFORMATION_SCHEMA.COLUMNS
                            WHERE TABLE_NAME = @table_name
							FOR XML PATH ('')), 1, 1, '')

--Construção da Storage Procedure
DECLARE @SPQuery VARCHAR(MAX)
	SET @SPQuery = 'CREATE PROCEDURE '+@table_name+'_Insert ('+@sp_params+')
	AS BEGIN 
		INSERT INTO '+@table_name+' ('+@table_cols+')
		VALUES ('+@table_cols_params+')
	END'

  PRINT(@SPQuery); 
  EXEC (@SPQuery);  
END

exec sp_insertProcedure 'Color'

/**** Generate Update-like Storage Procedure(param Table-Name)****/
/*Descrição: Storage Procedure que recebe o argumento do nome de tabela (@table_name),
e constroi um sp de Update @table_name, e obtem e armazena informação em variáveis da tabela escolhida: parametros, coluna e tipo de dados no ID, colunas e tipos de dados associados
@Query é a variável de construção da criação da procedure de Insert
*/
USE WWI
GO
CREATE OR ALTER PROCEDURE sp_updateProcedure
(@table_name NVARCHAR(255))
AS
BEGIN
DECLARE @table_cols_params VARCHAR(MAX)
DECLARE @sp_params VARCHAR(MAX)
DECLARE @idparam varchar(MAX)
DECLARE @idcol varchar(MAX)
	
	-- Colunas da Tabela + Parametros das colunas da Tabela
	SET @table_cols_params = STUFF((SELECT ',' + COLUMN_NAME + '=' + '@' + COLUMN_NAME 
							 FROM INFORMATION_SCHEMA.COLUMNS 
                             WHERE TABLE_NAME = @table_name
							 FOR XML PATH ('')), 1, 1, '')
   -- Parametros da Storage Procedure (Colunas da tabela + o tipo de dados)     
	SET @sp_params = STUFF((SELECT ',' + '@'+ COLUMN_NAME + ' ' + DATA_TYPE + (CASE WHEN DATA_TYPE = 'nvarchar' THEN '('+CAST(100 as nvarchar)+')' ELSE '' END
																			+ CASE WHEN DATA_TYPE = 'varchar' THEN '('+CAST(2 as varchar)+')'  ELSE '' END)
							FROM INFORMATION_SCHEMA.COLUMNS
                            WHERE TABLE_NAME = @table_name
							FOR XML PATH ('')), 1, 1, '')
	-- ID da Tabela
    SET @idcol = STUFF((SELECT ',' + COLUMN_NAME
						  FROM INFORMATION_SCHEMA.COLUMNS 
                          WHERE TABLE_NAME = @table_name  AND COLUMN_NAME like 'id%'
					      FOR XML PATH ('')), 1, 1, '')
	-- Parametro ID
	SET @idparam =  STUFF((	SELECT ',' + '@' + COLUMN_NAME
							 FROM INFORMATION_SCHEMA.COLUMNS
                             WHERE TABLE_NAME = @table_name  AND COLUMN_NAME like 'id%'
							 FOR XML PATH ('')), 1, 1, '')

--Construção da Storage Procedure
DECLARE @SPQuery VARCHAR(MAX)
	SET @SPQuery = 'CREATE PROCEDURE '+@table_name+'_Update ('+@sp_params+')
	AS BEGIN 
		UPDATE '+@table_name+'
		SET '+  @table_cols_params +'
		WHERE ('+@idcol+' = ' + @idparam +')
	END'

	PRINT(@SPQuery); 
	EXEC (@SPQuery);  
END

exec sp_updateProcedure 'Color'

/**** Generate Delete-like Storage Procedure(param Table-Name)****/
/*Descrição: Storage Procedure que recebe o argumento do nome de tabela (@table_name),
e constroi um sp de Delete From @table_name, e obtem e armazena informação em variáveis da tabela escolhida: parametro ID, coluna do ID e tipo de dado no ID
@SPQuery é a variável de construção da criação da procedure de Insert
*/
USE WWI
GO
CREATE OR ALTER PROCEDURE sp_deleteProcedure
(@table_name NVARCHAR(255))
AS
BEGIN
DECLARE @idparam_sp varchar(MAX)
DECLARE @idparam varchar(MAX)
DECLARE @idcol varchar(MAX)

	-- Parametro ID da Storage Procedure (Colunas da tabela + o tipo de dados)  
	SET @idparam_sp = STUFF((SELECT ',' + '@'+ COLUMN_NAME + ' ' + DATA_TYPE + (CASE WHEN DATA_TYPE = 'nvarchar' THEN '('+CAST(100 as nvarchar)+')' ELSE '' END 
																			 + CASE WHEN DATA_TYPE = 'varchar' THEN '('+CAST(2 as varchar)+')'  ELSE '' END)
							FROM INFORMATION_SCHEMA.COLUMNS
                            WHERE TABLE_NAME = @table_name  AND COLUMN_NAME like 'id%'
							FOR XML PATH ('')), 1, 1, '')
	-- ID da Tabela
    SET @idcol = STUFF((SELECT ',' + COLUMN_NAME
						  FROM INFORMATION_SCHEMA.COLUMNS 
                          WHERE TABLE_NAME = @table_name AND COLUMN_NAME like 'id%'
					      FOR XML PATH ('')), 1, 1, '')
	-- Parametro ID
	SET @idparam =  STUFF((	SELECT ',' + '@' + COLUMN_NAME
							 FROM INFORMATION_SCHEMA.COLUMNS
                             WHERE TABLE_NAME = @table_name AND COLUMN_NAME like 'id%'
							 FOR XML PATH ('')), 1, 1, '')

--Construção da Storage Procedure
DECLARE @SPQuery VARCHAR(MAX)
	SET @SPQuery = 'CREATE PROCEDURE '+@table_name+'_Delete ('+@idparam_sp+')
		AS BEGIN DELETE FROM '+@table_name+'
		WHERE (' + @idcol + ' = ' + @idparam +')
	END'

	PRINT(@SPQuery); 
	EXEC (@SPQuery);  
END

exec sp_deleteProcedure 'State'

/***************Monitoring Storage Procedures*********************/

/*** Keep Record of WWI Data***/
/*Descrição: Storage Procedure que não recebe parametros de entrada,
e que cria uma tabela (se não existir) que guarda os Records e os Record_Previous de cada tabela da base de dados(nome da tabela, colunas, etc);
Tabela RecordOfWWI guarda dados novos(de uma tabela temporária) e que a RecordOfWWI_Previous guarda dados anteriores contidos na RecordOfWWI, 
isto através de um update a ambas as tabelas.
*/
USE WWI
GO
CREATE or ALTER PROCEDURE sp_RecordOfDatabase
AS
BEGIN	
	-- Check if RecordOfWWI and RecordOfWWI_Previous Exists, if not create new tables --
	if NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'RecordOfWWI ')
	BEGIN
		SELECT c.TABLE_CATALOG, c.TABLE_SCHEMA, c.COLUMN_NAME, c.COLLATION_NAME, c.IS_NULLABLE, c.DATA_TYPE, C.CHARACTER_MAXIMUM_LENGTH, cc.CONSTRAINT_CATALOG, cc.CONSTRAINT_SCHEMA, cc.CONSTRAINT_NAME
		INTO RecordOfWWI 
		FROM INFORMATION_SCHEMA.COLUMNS AS c
		join INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE as cc on c.COLUMN_NAME = cc.COLUMN_NAME
	END

	if NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'RecordOfWWI_Previous')
	BEGIN
		SELECT c.TABLE_CATALOG, c.TABLE_SCHEMA, c.COLUMN_NAME, c.COLLATION_NAME, c.IS_NULLABLE, c.DATA_TYPE, C.CHARACTER_MAXIMUM_LENGTH, cc.CONSTRAINT_CATALOG, cc.CONSTRAINT_SCHEMA, cc.CONSTRAINT_NAME
		INTO RecordOfWWI_Previous 
		FROM INFORMATION_SCHEMA.COLUMNS AS c
		join INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE as cc on c.COLUMN_NAME = cc.COLUMN_NAME
	END

	--Update Previous Session--
	update RecordOfWWI_Previous
	set RecordOfWWI_Previous.TABLE_CATALOG = RecordOfWWI.TABLE_CATALOG
	from RecordOfWWI
	
	-- Select Info from Metadata into temp table
	SELECT c.TABLE_CATALOG, c.TABLE_SCHEMA, c.COLUMN_NAME, c.COLLATION_NAME, c.IS_NULLABLE, c.DATA_TYPE, C.CHARACTER_MAXIMUM_LENGTH, cc.CONSTRAINT_CATALOG, cc.CONSTRAINT_SCHEMA, cc.CONSTRAINT_NAME
	INTO #RecordOfWWI 
	FROM INFORMATION_SCHEMA.COLUMNS AS c
	join INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE as cc on c.COLUMN_NAME = cc.COLUMN_NAME
	
	--Update Values Into Current Session --
	UPDATE RecordOfWWI 
	SET RecordOfWWI.TABLE_CATALOG = #RecordOfWWI.TABLE_CATALOG
	FROM #RecordOfWWI

	-- View
	SELECT * FROM RecordOfWWI
	SELECT * FROM RecordOfWWI_Previous
END

/**View RecordOfWWI**/
/*Descrição: Vizualizar a inserção mais recente de dados na tabela de Records, ou seja, Select na tabela RecordOfWWI
Se o procedure for executado uma segunda vez, já existe a tabela RecordOfWWI em guarda dados novos e que a RecordOfWWI_Previous guarda dados anteriores contidos
na RecordOfWWI, isto através de um update a ambas as tabelas.
*/
CREATE or ALTER VIEW ViewRecordOfWWI AS
SELECT *
FROM RecordOfWWI;

/** Store NumberOfRecords per Table**/
/*Descrição: Storage Procedure que não recebe parametros de entrada,
e que cria uma tabela (se não existir) que guarda o número de registos de cada tabela da base de dados;
Tabela NumberOfRecords guarda dados novos(de uma tabela temporária), na coluna de Row_Count() e guarda os dados anteriores na coluna previousRowCount
*/
USE WWI
GO
CREATE or ALTER PROCEDURE sp_NumberOfRecords
AS
BEGIN	
	-- Check if NumberOfRecords, if not create new table --
	if NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'NumberOfRecords ')
	BEGIN
		SELECT obj.name AS [TableName] , SUM(par.Rows) AS [Row_Count], SUM(par.Rows) AS [previousRowCount]
		INTO NumberOfRecords
		FROM  sys.objects AS obj
		INNER JOIN sys.partitions AS par ON obj.object_id = par.object_id
		WHERE obj.type = 'U'
		GROUP BY obj.name
		ORDER BY [TableName]
	END
	else
	BEGIN
		-- Select Info from Metadata into temp table
		SELECT obj.name AS [TableName] , SUM(par.Rows) AS [NewCount]
		INTO #Number_Of_Records
		FROM  sys.objects AS obj
		INNER JOIN sys.partitions AS par ON obj.object_id = par.object_id
		WHERE obj.type = 'U'
		GROUP BY obj.name
		ORDER BY [TableName]
	
		--Update Values Into Current Session --
		UPDATE NumberOfRecords
		SET NumberOfRecords.previousRowCount = NumberOfRecords.Row_Count,
			NumberOfRecords.Row_Count = #Number_Of_Records.NewCount
		FROM #Number_Of_Records
		where NumberOfRecords.TableName = #Number_Of_Records.TableName
	END
	-- View
	SELECT * FROM NumberOfRecords
	SELECT * FROM #Number_Of_Records
END

/**View NumberOfRecords**/
/*Descrição: Vizualizar a inserção mais recente de dados na tabela de Records, ou seja, Select na tabela NumberOfRecords
*/
CREATE or ALTER VIEW ViewNumberOfRecords AS
SELECT *
FROM NumberOfRecords;