CREATE TABLE IF NOT EXISTS `chat_user` (
		`id` int(11) NOT NULL AUTO_INCREMENT,
		`username` varchar(12) COLLATE utf8_bin NOT NULL UNIQUE,
		`password` varchar(12) NOT NULL,
		`email` varchar(12) NOT NULL,
		`last_update` int(11) NOT NULL,
		PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `chat_message` (
		`id` int(11) NOT NULL AUTO_INCREMENT,
		`user_id` int(11) NOT NULL,
		`content` varchar(255)  COLLATE utf8_bin NOT NULL,
		`time` int(11) NOT NULL,
		`target_id` int(11) NOT NULL,
		PRIMARY KEY (`id`)
)

