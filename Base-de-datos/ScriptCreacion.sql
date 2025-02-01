-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gardenia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gardenia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gardenia` DEFAULT CHARACTER SET utf8 ;
USE `gardenia` ;

-- -----------------------------------------------------
-- Table `gardenia`.`base_cost`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`base_cost` (
  `idbase_cost` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `amount` MEDIUMINT(1) UNSIGNED NOT NULL,
  PRIMARY KEY (`idbase_cost`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`product` (
  `idproduct` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `associated_cost` MEDIUMINT(1) UNSIGNED NOT NULL,
  `stock` SMALLINT(1) ZEROFILL UNSIGNED NOT NULL,
  `description` VARCHAR(400) NOT NULL,
  `base_cost_idbase_cost` INT UNSIGNED NOT NULL,
  `product_idproduct` INT UNSIGNED NULL,
  PRIMARY KEY (`idproduct`),
  INDEX `fk_product_base_cost1_idx` (`base_cost_idbase_cost` ASC) VISIBLE,
  INDEX `fk_product_product1_idx` (`product_idproduct` ASC) VISIBLE,
  CONSTRAINT `fk_product_base_cost1`
    FOREIGN KEY (`base_cost_idbase_cost`)
    REFERENCES `gardenia`.`base_cost` (`idbase_cost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_product1`
    FOREIGN KEY (`product_idproduct`)
    REFERENCES `gardenia`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`service` (
  `idservice` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `state` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`idservice`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`irrigation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`irrigation` (
  `idirrigation` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL,
  `frequency` VARCHAR(80) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idirrigation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`luminescence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`luminescence` (
  `idluminescence` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idluminescence`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`substratum`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`substratum` (
  `idsubstratum` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idsubstratum`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`species`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`species` (
  `idspecies` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `stockeable_as` TINYINT(1) UNSIGNED NOT NULL,
  `scientific_name` VARCHAR(40) NULL,
  `care_instructions` VARCHAR(500) NOT NULL,
  `product_idproduct` INT UNSIGNED NOT NULL,
  `irrigation_idirrigation` INT UNSIGNED NOT NULL,
  `luminescence_idluminescence` INT UNSIGNED NOT NULL,
  `substratum_idsubstratum` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idspecies`),
  INDEX `fk_species_product1_idx` (`product_idproduct` ASC) VISIBLE,
  INDEX `fk_species_irrigation1_idx` (`irrigation_idirrigation` ASC) VISIBLE,
  INDEX `fk_species_luminescence1_idx` (`luminescence_idluminescence` ASC) VISIBLE,
  INDEX `fk_species_substratum1_idx` (`substratum_idsubstratum` ASC) VISIBLE,
  CONSTRAINT `fk_species_product1`
    FOREIGN KEY (`product_idproduct`)
    REFERENCES `gardenia`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_species_irrigation1`
    FOREIGN KEY (`irrigation_idirrigation`)
    REFERENCES `gardenia`.`irrigation` (`idirrigation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_species_luminescence1`
    FOREIGN KEY (`luminescence_idluminescence`)
    REFERENCES `gardenia`.`luminescence` (`idluminescence`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_species_substratum1`
    FOREIGN KEY (`substratum_idsubstratum`)
    REFERENCES `gardenia`.`substratum` (`idsubstratum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`spent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`spent` (
  `idspent` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `concept` VARCHAR(80) NOT NULL,
  `amount` MEDIUMINT(1) UNSIGNED NOT NULL,
  `quantity` MEDIUMINT(1) UNSIGNED NOT NULL,
  `unit` VARCHAR(20) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`idspent`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`person` (
  `doc_number` BIGINT(1) UNSIGNED NOT NULL,
  `doc_type` VARCHAR(3) NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `lastname` VARCHAR(40) NOT NULL,
  `phone_number` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`doc_number`, `doc_type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`purchase_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`purchase_order` (
  `idpurchase_order` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `amount` MEDIUMINT(1) NOT NULL,
  `state` VARCHAR(40) NOT NULL,
  `person_doc_number` BIGINT(1) UNSIGNED NOT NULL,
  `person_doc_type` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`idpurchase_order`),
  INDEX `fk_purchase_order_person1_idx` (`person_doc_number` ASC, `person_doc_type` ASC) VISIBLE,
  CONSTRAINT `fk_purchase_order_person1`
    FOREIGN KEY (`person_doc_number` , `person_doc_type`)
    REFERENCES `gardenia`.`person` (`doc_number` , `doc_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`department` (
  `iddepartment` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`iddepartment`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`city` (
  `idcity` INT UNSIGNED NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `shipping_cost` SMALLINT(1) NOT NULL,
  `department_iddepartment` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idcity`),
  INDEX `fk_city_department1_idx` (`department_iddepartment` ASC) VISIBLE,
  CONSTRAINT `fk_city_department1`
    FOREIGN KEY (`department_iddepartment`)
    REFERENCES `gardenia`.`department` (`iddepartment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`address` (
  `idaddress` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(80) NOT NULL,
  `references` VARCHAR(80) NULL,
  `city_idcity` INT UNSIGNED NOT NULL,
  `person_doc_number` BIGINT(1) UNSIGNED NOT NULL,
  `person_doc_type` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`idaddress`),
  INDEX `fk_address_city1_idx` (`city_idcity` ASC) VISIBLE,
  INDEX `fk_address_person1_idx` (`person_doc_number` ASC, `person_doc_type` ASC) VISIBLE,
  CONSTRAINT `fk_address_city1`
    FOREIGN KEY (`city_idcity`)
    REFERENCES `gardenia`.`city` (`idcity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_address_person1`
    FOREIGN KEY (`person_doc_number` , `person_doc_type`)
    REFERENCES `gardenia`.`person` (`doc_number` , `doc_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`category` (
  `idcategory` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `description` VARCHAR(400) NULL,
  `product_idproduct` INT UNSIGNED NOT NULL,
  `service_idservice` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idcategory`),
  INDEX `fk_category_product1_idx` (`product_idproduct` ASC) VISIBLE,
  INDEX `fk_category_service1_idx` (`service_idservice` ASC) VISIBLE,
  CONSTRAINT `fk_category_product1`
    FOREIGN KEY (`product_idproduct`)
    REFERENCES `gardenia`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_service1`
    FOREIGN KEY (`service_idservice`)
    REFERENCES `gardenia`.`service` (`idservice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`experiment_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`experiment_log` (
  `idexperiment_log` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `conclusions_url` VARCHAR(200) NOT NULL,
  `irrigation_idirrigation` INT UNSIGNED NOT NULL,
  `luminescence_idluminescence` INT UNSIGNED NOT NULL,
  `substratum_idsubstratum` INT UNSIGNED NOT NULL,
  `species_idspecies` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idexperiment_log`),
  INDEX `fk_experiment_log_irrigation1_idx` (`irrigation_idirrigation` ASC) VISIBLE,
  INDEX `fk_experiment_log_luminescence1_idx` (`luminescence_idluminescence` ASC) VISIBLE,
  INDEX `fk_experiment_log_substratum1_idx` (`substratum_idsubstratum` ASC) VISIBLE,
  INDEX `fk_experiment_log_species1_idx` (`species_idspecies` ASC) VISIBLE,
  CONSTRAINT `fk_experiment_log_irrigation1`
    FOREIGN KEY (`irrigation_idirrigation`)
    REFERENCES `gardenia`.`irrigation` (`idirrigation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_experiment_log_luminescence1`
    FOREIGN KEY (`luminescence_idluminescence`)
    REFERENCES `gardenia`.`luminescence` (`idluminescence`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_experiment_log_substratum1`
    FOREIGN KEY (`substratum_idsubstratum`)
    REFERENCES `gardenia`.`substratum` (`idsubstratum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_experiment_log_species1`
    FOREIGN KEY (`species_idspecies`)
    REFERENCES `gardenia`.`species` (`idspecies`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`image` (
  `idimage` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(200) NOT NULL,
  `product_idproduct` INT UNSIGNED NULL,
  `service_idservice` INT UNSIGNED NULL,
  `category_idcategory` INT UNSIGNED NULL,
  `experiment_log_idexperiment_log` INT UNSIGNED NULL,
  PRIMARY KEY (`idimage`),
  INDEX `fk_image_product1_idx` (`product_idproduct` ASC) VISIBLE,
  INDEX `fk_image_service1_idx` (`service_idservice` ASC) VISIBLE,
  INDEX `fk_image_category1_idx` (`category_idcategory` ASC) VISIBLE,
  INDEX `fk_image_experiment_log1_idx` (`experiment_log_idexperiment_log` ASC) VISIBLE,
  CONSTRAINT `fk_image_product1`
    FOREIGN KEY (`product_idproduct`)
    REFERENCES `gardenia`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_image_service1`
    FOREIGN KEY (`service_idservice`)
    REFERENCES `gardenia`.`service` (`idservice`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_image_category1`
    FOREIGN KEY (`category_idcategory`)
    REFERENCES `gardenia`.`category` (`idcategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_image_experiment_log1`
    FOREIGN KEY (`experiment_log_idexperiment_log`)
    REFERENCES `gardenia`.`experiment_log` (`idexperiment_log`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`spent_has_base_cost`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`spent_has_base_cost` (
  `spent_idspent` INT UNSIGNED NOT NULL,
  `base_cost_idbase_cost` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`spent_idspent`, `base_cost_idbase_cost`),
  INDEX `fk_spent_has_base_cost_base_cost1_idx` (`base_cost_idbase_cost` ASC) VISIBLE,
  INDEX `fk_spent_has_base_cost_spent1_idx` (`spent_idspent` ASC) VISIBLE,
  CONSTRAINT `fk_spent_has_base_cost_spent1`
    FOREIGN KEY (`spent_idspent`)
    REFERENCES `gardenia`.`spent` (`idspent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_spent_has_base_cost_base_cost1`
    FOREIGN KEY (`base_cost_idbase_cost`)
    REFERENCES `gardenia`.`base_cost` (`idbase_cost`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`role` (
  `idrole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idrole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`account` (
  `idaccount` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(40) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `birthdate` DATE NOT NULL,
  `role_idrole` INT NOT NULL,
  `person_doc_number` BIGINT(1) UNSIGNED NOT NULL,
  `person_doc_type` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`idaccount`),
  INDEX `fk_account_role1_idx` (`role_idrole` ASC) VISIBLE,
  INDEX `fk_account_person1_idx` (`person_doc_number` ASC, `person_doc_type` ASC) VISIBLE,
  CONSTRAINT `fk_account_role1`
    FOREIGN KEY (`role_idrole`)
    REFERENCES `gardenia`.`role` (`idrole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_person1`
    FOREIGN KEY (`person_doc_number` , `person_doc_type`)
    REFERENCES `gardenia`.`person` (`doc_number` , `doc_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gardenia`.`order_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gardenia`.`order_detail` (
  `idorder_detail` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `quantity` SMALLINT(1) UNSIGNED NOT NULL,
  `purchase_order_idpurchase_order` INT UNSIGNED NOT NULL,
  `product_idproduct` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idorder_detail`, `purchase_order_idpurchase_order`, `product_idproduct`),
  INDEX `fk_order_detail_purchase_order1_idx` (`purchase_order_idpurchase_order` ASC) VISIBLE,
  INDEX `fk_order_detail_product1_idx` (`product_idproduct` ASC) VISIBLE,
  CONSTRAINT `fk_order_detail_purchase_order1`
    FOREIGN KEY (`purchase_order_idpurchase_order`)
    REFERENCES `gardenia`.`purchase_order` (`idpurchase_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_detail_product1`
    FOREIGN KEY (`product_idproduct`)
    REFERENCES `gardenia`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
