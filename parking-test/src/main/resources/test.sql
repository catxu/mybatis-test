delimiter $$
DROP FUNCTION
IF EXISTS `func_extract_json`$$

CREATE FUNCTION `func_extract_json` (
	details TEXT,
	required_field VARCHAR (255)
) RETURNS text CHARSET latin1
BEGIN

SET details = SUBSTRING_INDEX(details, "{", - 1) ;
SET details = SUBSTRING_INDEX(details, "}", 1) ; RETURN TRIM(
	BOTH '"'
	FROM
		SUBSTRING_INDEX(
			SUBSTRING_INDEX(
				SUBSTRING_INDEX(
					CONCAT('"":"",', details),
					CONCAT(
						'"',
						SUBSTRING_INDEX(required_field, '$.', - 1),
						'":'
					),
					- 1
				),
				',"',
				1
			),
			':',
			- 1
		)
) ;
END $$
delimiter ;