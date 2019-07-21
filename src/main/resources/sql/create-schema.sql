-- delete the water change comments table
DROP TABLE IF EXISTS `water-change-db`.`water_change_comments`;

-- delete water changes table
DROP TABLE IF EXISTS `water-change-db`.`water_changes`;

  -- delete tanks table
  DROP TABLE IF EXISTS `water-change-db`.`tanks`;

-- delete users table
DROP TABLE IF EXISTS `water-change-db`.`users`;

-- create users table
CREATE TABLE `water-change-db`.`users` (
  `id_user` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `idusers_UNIQUE` (`id_user` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
  
  -- create tanks table
  CREATE TABLE `water-change-db`.`tanks` (
  `id_tank` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `size` DOUBLE UNSIGNED, 
  `location` VARCHAR(45) NULL DEFAULT '',
  `id_user` INT UNSIGNED NOT NULL COMMENT 'Foreign key linking a tank to a user',
  PRIMARY KEY (`id_tank`),
  UNIQUE INDEX `id_tank_UNIQUE` (`id_tank` ASC) INVISIBLE,
  UNIQUE INDEX `name_id_UNIQUE` (`name` ASC, `id_tank` ASC) INVISIBLE,
  INDEX `name_INDEX` (`name` ASC) INVISIBLE,
  INDEX `size_INDEX` (`size` ASC) INVISIBLE,
  INDEX `location_INDEX` (`location` ASC) INVISIBLE,
  INDEX `tank_user_FK_idx` (`id_user` ASC) VISIBLE,
  UNIQUE INDEX `tank_name_user_UNIQUE` (`id_user` ASC, `name` ASC) VISIBLE,
  CONSTRAINT `tank_user_FK`
    FOREIGN KEY (`id_user`)
    REFERENCES `water-change-db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
   
-- create the water changes table
CREATE TABLE `water-change-db`.`water_changes` (
  `id_water_change` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `amount` DOUBLE UNSIGNED NULL COMMENT 'the amount of the water change',
  `comments` MEDIUMTEXT NULL,
  `id_tank` INT UNSIGNED NOT NULL COMMENT 'Foreign key linking a water change to a tank',
  PRIMARY KEY (`id_water_change`), 
  UNIQUE INDEX `id_water_change_UNIQUE` (`id_water_change` ASC) INVISIBLE,
  INDEX `date_INDEX` (`date` ASC) INVISIBLE,
  INDEX `amount_INDEX` (`amount` ASC) INVISIBLE,
  CONSTRAINT `water_change_tank_FK`
    FOREIGN KEY (`id_tank`)
    REFERENCES `water-change-db`.`tanks` (`id_tank`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- create the water change comment table
CREATE TABLE `water-change-db`.`water_change_comments` (
  `id_comment` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment` LONGTEXT NOT NULL,
  `id_water_change` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_comment`),
  UNIQUE INDEX `id_comment_UNIQUE` (`id_comment` ASC) INVISIBLE,
  INDEX `id_water_change_INDEX` (`id_water_change` ASC) INVISIBLE,
  CONSTRAINT `comment_water_change_FK`
    FOREIGN KEY (`id_water_change`)
    REFERENCES `water-change-db`.`water_changes` (`id_water_change`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);