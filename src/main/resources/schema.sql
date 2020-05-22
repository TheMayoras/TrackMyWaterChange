SET FOREIGN_KEY_CHECKS = FALSE;

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
  `password` VARCHAR(60) BINARY NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `idusers_UNIQUE` (`id_user` ASC) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) );
  
  -- create tanks table
CREATE TABLE `water-change-db`.`tanks` (
  `id_tank` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `size` DOUBLE UNSIGNED, 
  `units` VARCHAR(45) NOT NULL,
  `location` VARCHAR(45) NULL DEFAULT '',
  `id_user` INT UNSIGNED NOT NULL COMMENT 'Foreign key linking a tank to a user',
  PRIMARY KEY (`id_tank`),
  UNIQUE INDEX `id_tank_UNIQUE` (`id_tank` ASC) ,
  UNIQUE INDEX `name_id_UNIQUE` (`name` ASC, `id_tank` ASC) ,
  INDEX `name_INDEX` (`name` ASC) ,
  INDEX `size_INDEX` (`size` ASC) ,
  INDEX `location_INDEX` (`location` ASC) ,
  INDEX `tank_user_FK_idx` (`id_user` ASC) ,
  UNIQUE INDEX `tank_name_user_UNIQUE` (`id_user` ASC, `name` ASC) ,
  CONSTRAINT `tank_user_FK`
    FOREIGN KEY (`id_user`)
    REFERENCES `water-change-db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
   
-- create the water changes table
CREATE TABLE `water-change-db`.`water_changes` (
  `id_water_change` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `amount` DOUBLE UNSIGNED NULL COMMENT 'the amount of the water change',
  `units` VARCHAR(45) NOT NULL,
  `id_tank` INT UNSIGNED NOT NULL COMMENT 'Foreign key linking a water change to a tank',
  PRIMARY KEY (`id_water_change`), 
  UNIQUE INDEX `id_water_change_UNIQUE` (`id_water_change` ASC) ,
  INDEX `date_INDEX` (`date` ASC) ,
  INDEX `amount_INDEX` (`amount` ASC) ,
  CONSTRAINT `water_change_tank_FK`
    FOREIGN KEY (`id_tank`)
    REFERENCES `water-change-db`.`tanks` (`id_tank`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- create the water change comment table
CREATE TABLE `water-change-db`.`water_change_comments` (
  `id_comment` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(255) NOT NULL,
  `id_water_change` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_comment`),
  UNIQUE INDEX `id_comment_UNIQUE` (`id_comment` ASC) ,
  INDEX `id_water_change_INDEX` (`id_water_change` ASC) ,
  CONSTRAINT `comment_water_change_FK`
    FOREIGN KEY (`id_water_change`)
    REFERENCES `water-change-db`.`water_changes` (`id_water_change`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
DROP TABLE IF EXISTS `water-change-db`.`users_user_roles`;

DROP TABLE IF EXISTS `water-change-db`.`user_roles`;

DROP VIEW IF EXISTS `water-change-db`.`users_roles`;


CREATE TABLE `water-change-db`.`user_roles` (
  `id_user_roles` INT UNSIGNED AUTO_INCREMENT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user_roles`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC)
);

-- Insert ADMIN and USER
INSERT INTO `water-change-db`.`user_roles` (`role`)
	VALUES
		('ROLE_ADMIN'),
		('ROLE_USER');

CREATE TABLE `water-change-db`.`users_user_roles` (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL,
  `id_user` INT UNSIGNED NOT NULL,
  `id_user_role` INT UNSIGNED NOT NULL,
  PRIMARY KEY `id_PK` (`id`),
  UNIQUE INDEX `combination_UNIQUE` (`id_user` ASC, `id_user_role` ASC) ,
  INDEX `user_INDEX` (`id_user` ASC) ,
  INDEX `user_role_INDEX` (`id_user_role` ASC) ,
  CONSTRAINT `id_user_FK`
    FOREIGN KEY (`id_user`)
    REFERENCES `water-change-db`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_user_role_FK`
    FOREIGN KEY (`id_user_role`)
    REFERENCES `water-change-db`.`user_roles` (`id_user_roles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

-- Create the view to associate usernames with roles
--CREATE VIEW `water-change-db`.`users_roles` AS (
--	WITH `id_users_roles` AS (
--		SELECT `id_user`, `role`
--		FROM `users_user_roles` `join_table` INNER JOIN `user_roles`
--			ON `join_table`.`id_user_role` = `user_roles`.`id_user_roles`
--    )
--
--    SELECT `username`, `role`
--    FROM `users` INNER JOIN `id_users_roles`
--		ON `users`.`id_user` = `id_users_roles`.`id_user`
--);

CREATE VIEW `water-change-db`.`users_roles` AS (
    SELECT `username`, `role`
    FROM `users` INNER JOIN (
        `users_user_roles` `roles` INNER JOIN `user_roles`
        ON `roles`.`id_user_role` = `user_roles`.`id_user_roles`
    ) ON `users`.`id_user` = `users`.`id_user`
);

SET FOREIGN_KEY_CHECKS = TRUE;
