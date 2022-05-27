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

DROP TABLE IF EXISTS `customer_account`;
CREATE TABLE `customer_account` (
  	`id` int(11) NOT NULL,
 	`name` nvarchar(70) DEFAULT NULL,
	`identity_code` nvarchar(15) DEFAULT NULL,
	`phone_number` nvarchar(15) DEFAULT NULL,
 	 CONSTRAINT `fk_customer_base` FOREIGN KEY(`id`) REFERENCES base_account(`id`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `manager_account`;
CREATE TABLE `manager_account` (
  	`id` int(11) NOT NULL,
 	`name` nvarchar(70) DEFAULT NULL,
	`identity_code` nvarchar(15) DEFAULT NULL,
	`phone_number` nvarchar(15) DEFAULT NULL,
 	 CONSTRAINT `fk_manager_base` FOREIGN KEY(`id`) REFERENCES base_account(`id`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `admin_account`;
CREATE TABLE `admin_account` (
  	`id` int(11) NOT NULL,
 	`name` nvarchar(70) DEFAULT NULL,
	`identity_code` nvarchar(15) DEFAULT NULL,
	`phone_number` nvarchar(15) DEFAULT NULL,
 	 CONSTRAINT `fk_admin_base` FOREIGN KEY(`id`) REFERENCES base_account(`id`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `airport`;
CREATE TABLE `airport` (
  	`id` int(11) NOT NULL AUTO_INCREMENT,
 	`name` nvarchar(70) DEFAULT NULL,
	
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `ticket_class`;
CREATE TABLE `ticket_class` (
  	`id` int(11) NOT NULL AUTO_INCREMENT,
 	`name` nvarchar(70) DEFAULT NULL,
	
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `transition_airport`;
CREATE TABLE `transition_airport` (
  	`id` int(11) NOT NULL AUTO_INCREMENT,
 	`airport_id` int(11) NOT NULL,
	`transition_time` int(15) DEFAULT NULL,
	`note` nvarchar(70) DEFAULT NULL,
 	 CONSTRAINT `fk_transition_airport` FOREIGN KEY(`airport_id`) REFERENCES airport(`id`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight` (
  	`id` int(11) NOT NULL AUTO_INCREMENT,
 	`departure_airport_id` int(11) NOT NULL,
	`arrival_airport_id` int(11) NOT NULL,
	
 	 CONSTRAINT `fk_flight_departure` FOREIGN KEY(`departure_airport_id`) REFERENCES airport(`id`),
	CONSTRAINT `fk_flight_arrival` FOREIGN KEY(`arrival_airport_id`) REFERENCES airport(`id`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `flight_detail`;
CREATE TABLE `flight_detail` (
  	`flight id` int(11) NOT NULL,
 	`departure_airport_id` int(11) NOT NULL,
	`arrival_airport_id` int(11) NOT NULL,
	`flight_time` int(11) DEFAULT 0,
	`first_class_seat_size` int(11) DEFAULT 0,
	`second_class_seat_size` int(11) DEFAULT 0,

 	 CONSTRAINT `fk_flight_detail` FOREIGN KEY(`flight_id`) REFERENCES flight(`id`),
  	PRIMARY KEY (`flight_id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `flight_transition`;
CREATE TABLE `flight_transition` (
 	`transition_airport_id` int(11) NOT NULL,
	`flight_id` int(11) NOT NULL,
 	 CONSTRAINT `fk_transition_flight` FOREIGN KEY(`transition_airport_id`) REFERENCES transition_airport(`id`),
	 CONSTRAINT `fk_flight_transition` FOREIGN KEY(`flight_id`) REFERENCES flight(`id`),
  	PRIMARY KEY (transition_airport_id, flight_id)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  	`id` int(11) NOT NULL AUTO_INCREMENT,
 	`flight_id` int(11) NOT NULL,
	`ticket_class_id` int(11) NOT NULL,
	`customer_id` int(11) NOT NULL,
	`price` int(11) DEFAULT 0,
	`name` nvarchar(70) DEFAULT NULL,
	`identity_code` nvarchar(15) DEFAULT NULL,
	`phone_number` nvarchar(15) DEFAULT NULL,
	`is_booked` boolean DEFAULT FALSE,	

 	 CONSTRAINT `fk_ticket_flight` FOREIGN KEY(`flight_id`) REFERENCES flight(`id`),
	CONSTRAINT `fk_ticket_ticket_class` FOREIGN KEY(`ticket_class_id`) REFERENCES ticket_class(`id`),
	CONSTRAINT `fk_ticket_customer` FOREIGN KEY(`customer_id`) REFERENCES customer_account(`id`),
  	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

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