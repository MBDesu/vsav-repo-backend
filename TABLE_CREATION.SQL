--------------------------------------------------------------------------------
-- User/database setup
--------------------------------------------------------------------------------
CREATE USER 'username'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' WITH GRANT OPTION;

-- Log out of root account and log in as user you just created
-- Be sure you do create a user; you don't want your service account to use root
CREATE DATABASE vs CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;


--------------------------------------------------------------------------------
-- Table creation SQL
--------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS vs.videos (
	I_VID int NOT NULL AUTO_INCREMENT UNIQUE,
	C_YT_ID varchar(20) NOT NULL UNIQUE,
	Z_UPDT DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
	PRIMARY KEY (I_VID)
);

CREATE TABLE IF NOT EXISTS vs.matches (
	I_MTCH_ID int NOT NULL AUTO_INCREMENT,
    I_VID int NOT NULL,
	C_PLYR_ONE varchar(50) NOT NULL,
	C_PLYR_TWO varchar(50) NOT NULL,
	C_CHAR_ONE varchar(24) NOT NULL,
	C_CHAR_TWO varchar(24) NOT NULL,
	C_YT_TIMESTAMP varchar(9) NOT NULL,
	C_PLYR_ONE_IDX varchar(50) AS (LOWER(C_PLYR_ONE)),
	C_PLYR_TWO_IDX varchar(50) AS (LOWER(C_PLYR_TWO)),
	C_CHAR_ONE_IDX varchar(24) AS (LOWER(C_CHAR_ONE)),
	C_CHAR_TWO_IDX varchar(24) AS (LOWER(C_CHAR_TWO)),
	Z_UPDT DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
	FOREIGN KEY (I_VID) REFERENCES VIDEOS(I_VID) ON DELETE CASCADE,
    PRIMARY KEY(I_MTCH_ID)
);

CREATE TABLE IF NOT EXISTS vs.video_metadata (
	I_META_ID int NOT NULL AUTO_INCREMENT UNIQUE,
	I_VID int NOT NULL UNIQUE,
	C_TITLE varchar(100) NOT NULL,
	C_DESC varchar(5000) NOT NULL,
	C_CHNNL varchar(50) NOT NULL,
	Z_DATE DATETIME NOT NULL,
	FOREIGN KEY (I_VID) REFERENCES VIDEOS(I_VID) ON DELETE CASCADE,
	PRIMARY KEY(I_META_ID)
);