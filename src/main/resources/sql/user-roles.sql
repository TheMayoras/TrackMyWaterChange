CREATE TABLE `water-change-db`.`users_user_roles` (
  `id` INT UNSIGNED AUTO_INCREMENT NOT NULL,
  `id_user` INT UNSIGNED NOT NULL,
  `id_user_role` INT UNSIGNED NOT NULL,
  PRIMARY KEY `id_PK` (`id`),
  UNIQUE INDEX `combination_UNIQUE` (`id_user` ASC, `id_user_role` ASC) INVISIBLE,
  INDEX `user_INDEX` (`id_user` ASC) INVISIBLE,
  INDEX `user_role_INDEX` (`id_user_role` ASC) VISIBLE,
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


CREATE TABLE `water-change-db`.`user_roles` (
  `id_user_roles` INT UNSIGNED AUTO_INCREMENT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user_roles`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE
);

-- Insert ADMIN and USER
INSERT INTO `water-change-db`.`user_roles` (`role`)
	VALUES 
		('ADMIN'),
		('USER');

-- Create the view to associate usernames with roles
CREATE VIEW `water-change-db`.`users_roles` AS (
	WITH `id_users_roles` AS (
		SELECT `id_user`, `role` 
		FROM `users_user_roles` `join_table` INNER JOIN `user_roles` 
			ON `join_table`.`id_user_role` = `user_roles`.`id_user_roles`
    )
    
    SELECT `username`, `role`
    FROM `users` INNER JOIN `id_users_roles` 
		ON `users`.`id_user` = `id_users_roles`.`id_user`
)
