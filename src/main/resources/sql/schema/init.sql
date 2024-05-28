CREATE TABLE IF NOT EXISTS `user` (
    `id` SERIAL PRIMARY KEY,
    `user_name` varchar(255) DEFAULT NULL,
    `use_yn` bit(1) DEFAULT b'0'
) COLLATE utf8mb4_general_ci;