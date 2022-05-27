#Create the database
DROP DATABASE IF EXISTS `flight_manager`;
CREATE DATABASE `flight_manager`;

#Create user and grante permission on the created database
DROP USER IF EXISTS '19120383_19120426_19120469'@'localhost';
CREATE USER '19120383_19120426_19120469'@'localhost' IDENTIFIED BY '19120383_19120426_19120469';
GRANT ALL PRIVILEGES ON `flight_manager`.* TO '19120383_19120426_19120469'@'localhost';

USE `flight_manager`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
  	`user_name` varchar(70) NOT NULL,
  	`encrypted_password` varchar(70) NOT NULL,
  	`role` varchar(15) DEFAULT NULL,
  	
  	UNIQUE (`user_name`),
 	 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `base_account`;
CREATE TABLE `base_account` (
 	 `id` int(11) NOT NULL,
 
  	CONSTRAINT `fk_base_account_user` FOREIGN KEY(`id`) REFERENCES user(`id`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL,
 
  CONSTRAINT `fk_student_base` FOREIGN KEY(`id`) REFERENCES base_user_role(`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `ministry`;
CREATE TABLE `ministry` (
  `id` int(11) NOT NULL,
  
  CONSTRAINT `fk_ministry_base` FOREIGN KEY(`id`) REFERENCES base_user_role(`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` varchar(15) NOT NULL,
  `name` nvarchar(45) DEFAULT NULL,
  
  UNIQUE (`course_id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`subject_id` int(11) DEFAULT NULL,
	
	CONSTRAINT `fk_course_subject` FOREIGN KEY(`subject_id`) REFERENCES subject(`id`),
	
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student` (
	`course_id` int(11),
	`student_id` int(11),
	
	CONSTRAINT `fk_student_course` FOREIGN KEY(`student_id`) REFERENCES student(`id`),
	CONSTRAINT `fk_course_student` FOREIGN KEY(`course_id`) REFERENCES course(`id`),
	PRIMARY KEY (course_id, student_id)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
	`id` int(11) NOT NULL,
	`start_date` date DEFAULT NULL,
	`end_date` date DEFAULT NULL,
	`time` time DEFAULT NULL,
	`week_day` varchar(10) DEFAULT NULL,
	
	CONSTRAINT `fk_schedule_course` FOREIGN KEY(`id`) REFERENCES course(`id`),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `subject_week`;
CREATE TABLE `subject_week` (
	`schedule_id` int(11) NOT NULL,
	`week_index` int NOT NULL,
	`date` date DEFAULT NULL,
	
	CONSTRAINT `fk_week_schedule` FOREIGN KEY(`schedule_id`) REFERENCES schedule(`id`),
	PRIMARY KEY (`schedule_id`, `week_index`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `student_attendance_status`;
CREATE TABLE `student_attendance_status`(
	`student_id` int(11) NOT NULL,
	`schedule_id` int(11) NOT NULL,
	`week_index` int NOT NULL,
	
	`attendance_status` nvarchar(15) DEFAULT NULL,
	
	CONSTRAINT `fk_status_student` FOREIGN KEY(`student_id`)  REFERENCES student(`id`),
	CONSTRAINT `fk_status_schedule` FOREIGN KEY(`schedule_id`) REFERENCES schedule(`id`),
	PRIMARY KEY (`student_id`, `schedule_id`, `week_index`)
);

#Ministry with username = 'admin' and password = hashing('admin')
INSERT INTO `user` VALUES(1, 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'Role_Ministry', true);
INSERT INTO `base_user_role` VALUES(1, 'admin', 'admin');
INSERT INTO `ministry` VALUES(1);