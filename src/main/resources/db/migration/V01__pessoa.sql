CREATE TABLE IF NOT EXISTS `wk`.`pessoa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(128) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `data_nasc` DATETIME NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `mae` VARCHAR(128) NULL,
  `pai` VARCHAR(128) NULL,
  `email` VARCHAR(128) NULL,
  `cep` CHAR(9) NULL,
  `endereco` VARCHAR(128) NULL,
  `numero` VARCHAR(6) NULL,
  `bairro` VARCHAR(128) NULL,
  `cidade` VARCHAR(128) NULL,
  `estado` CHAR(2) NOT NULL,
  `telefone_fixo` VARCHAR(14) NULL,
  `celular` VARCHAR(15) NULL,
  `altura` FLOAT NULL,
  `peso` INT NOT NULL,
  `tipo_sanguineo` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  INDEX `idx_estado` (`estado` ASC) VISIBLE,
  INDEX `idx_data_nasc` (`data_nasc` ASC) VISIBLE,
  INDEX `idx_sexo` (`sexo` ASC) VISIBLE,
  INDEX `idx_tipo_sanguineo` (`tipo_sanguineo` ASC) VISIBLE)
ENGINE = InnoDB