CREATE TABLE IF NOT EXISTS `conferencechanges` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conferenceid` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `changedate` datetime NOT NULL,
  `changedFields` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
    CONSTRAINT `conference-changes` FOREIGN KEY (`ConferenceId`) REFERENCES `Conference` (`ConferenceId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1  ;

ALTER TABLE `conferencechanges` ADD `changeType` VARCHAR( 1 ) NULL AFTER `changedFields` ;


Alter table conference add column FullPaperDate DATE;
Alter table conference add column Www varchar(500);
Alter table conference add column PaymentDate DATE;
Alter table conference add column CreationDate DATETIME;

/*                                 
 * CREATION DATE IN CONFERENCE
 */ 

DROP trigger IF EXISTS conf_ins_trig;

DELIMITER ;;
CREATE TRIGGER `conf_ins_trig` BEFORE INSERT ON `conference`
FOR EACH ROW
BEGIN IF( new.creationDate IS NULL )
THEN
SET new.creationDate = SYSDATE( ) ;
END IF ;
END ;;

DELIMITER ;


/*                                 
 * 2a, update, modify COMMENT
 */ 

DELIMITER ;

DROP trigger IF EXISTS comment_upd_trig;

DELIMITER ;;
CREATE TRIGGER `comment_upd_trig` BEFORE UPDATE ON `comment`
FOR EACH ROW
BEGIN 

INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(new.conferenceid,'COMMENT',sysdate(),null,'U');

END ;;

DELIMITER ;


/*                                 
 * 2a, insert, new record COMMENT
 */ 

DELIMITER ;

DROP trigger IF EXISTS comment_ins_trig;

DELIMITER ;;
CREATE TRIGGER `comment_ins_trig` BEFORE INSERT ON `comment`
FOR EACH ROW
BEGIN 

INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(new.conferenceid,'COMMENT',sysdate(),null,'I');

END ;;

DELIMITER ;


/*                                 
 * 2b, insert, new record FILE
 */ 

DROP trigger IF EXISTS file_ins_trig;

DELIMITER ;;
CREATE TRIGGER `file_ins_trig` BEFORE INSERT ON `file`
FOR EACH ROW
BEGIN 

INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(new.conferenceid,'FILE',sysdate(),null,'I');

END ;;

DELIMITER ;

/*                                 
 * 2b, update, modify FILE
 */ 

DROP trigger IF EXISTS file_upd_trig;

DELIMITER ;;
CREATE TRIGGER `file_upd_trig` BEFORE UPDATE ON `file`
FOR EACH ROW
BEGIN 

INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(new.conferenceid,'FILE',sysdate(),null,'U');

END ;;

DELIMITER ;

/*                                 
 *  after UPDATE, za bardzo nie rozumiem punktu 2c z opisu zyly..............................
 * 2c, update, modify SCORE
 */ 

DROP trigger IF EXISTS score_upd_trig;

DELIMITER ;;
CREATE TRIGGER `score_upd_trig` AFTER UPDATE ON `score`
FOR EACH ROW
BEGIN 

DECLARE X int;
DECLARE changeYear varchar(100);


SELECT cs.conferenceid,s.year INTO X, changeYear  FROM score s  join magazine m on new.magazineid =m.magazineid
join conferencemagazine cs on cs.magazineid=m.magazineid limit 1;

IF(old.year <> new.year) THEN
	SET changeYear = 'YEAR' ;
ELSE 
	SET changeYear = NULL;
END IF;

INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(X,'SCORE',sysdate(),changeYear,'U');

END ;;

DELIMITER ;

/*                                 
 * 2c, insert, new record SCORE
 */

DROP trigger IF EXISTS score_ins_trig;

DELIMITER ;;
CREATE TRIGGER `score_ins_trig` AFTER INSERT ON `score`
FOR EACH ROW
BEGIN 

DECLARE X int;
DECLARE changeYear varchar(100);


SELECT cs.conferenceid,s.year INTO X, changeYear  FROM score s  join magazine m on new.magazineid =m.magazineid
join conferencemagazine cs on cs.magazineid=m.magazineid limit 1;


INSERT INTO conferencechanges (conferenceid,type,changedate,changeType) values(X,'SCORE',sysdate(),'I');

END ;;

DELIMITER ;

/*                                 
 * 2c magazine update, modify MAGAZINE
 */


DROP trigger IF EXISTS magazine_upd_trig;

DELIMITER ;;
CREATE TRIGGER `magazine_upd_trig` AFTER UPDATE ON `magazine`
FOR EACH ROW
BEGIN 

DECLARE X int;
DECLARE action varchar(1);

SELECT cs.conferenceid INTO X  FROM magazine m join conferencemagazine cs on new.magazineid =cs.magazineid;


INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(X,'MAGAZINE',sysdate(),changeYear,'U');

END ;;

DELIMITER ;

/*                                 
 * 2c Magazine, insert, new record MAGAZINE
 */

DROP trigger IF EXISTS magazine_ins_trig;

DELIMITER ;;
CREATE TRIGGER `magazine_ins_trig` AFTER INSERT ON `magazine`
FOR EACH ROW
BEGIN 

DECLARE X int;

SELECT cs.conferenceid INTO X  FROM magazine m join conferencemagazine cs on new.magazineid =cs.magazineid;

INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(X,'MAGAZINE',sysdate(),changeYear,'I');

END ;;

DELIMITER ;

/*    2e i 2d                             

FILED;OLD_VALUE;NEW_VALUE , (split in JAVA by ',' ) 

APPLICATION_DATE;2016-06-14 00:00:00;2016-06-10 05:08:11,
DATE_FROM;2016-09-06;2016-09-07,
FULLPAPER_DATE;null;2016-06-01,
PAYMENT_DATE;null;2016-06-01
 **/

DROP trigger IF EXISTS conferenc_upd_trig;

DELIMITER ;;
CREATE TRIGGER `conferenc_upd_trig` BEFORE UPDATE ON `conference`
FOR EACH ROW
BEGIN 

/* BEGIN  2.D  */	
	
DECLARE changedFields1 varchar(200);
SET changedFields1 = '';

IF(old.applicationDate <> new.applicationDate) THEN
	SET changedFields1 = CONCAT(changedFields1,'APPLICATION_DATE;',old.applicationDate,';',new.applicationDate,',') ;
ELSEIF (old.applicationDate is null and new.applicationDate is not null) THEN
	SET changedFields1 = CONCAT(changedFields1,'APPLICATION_DATE;null;',new.applicationDate,',') ; 
END IF;

IF(old.DateFrom is not null and old.DateFrom <> new.DateFrom) THEN
	SET changedFields1 = CONCAT(changedFields1,'DATE_FROM;',old.DateFrom,';',new.DateFrom,',') ; 
	ELSEIF (old.DateFrom is null and new.DateFrom is not null) THEN
	SET changedFields1 = CONCAT(changedFields1,'DATE_FROM;null;',new.DateFrom,',') ; 
END IF;

IF(old.DateTo is not null and old.DateTo <> new.DateTo ) THEN
	SET changedFields1 = CONCAT(changedFields1,'DATE_TO;',old.DateTO,';',new.DateTO,',') ; 
	ELSEIF (old.DateTo is null and new.DateTo is not null) THEN
	SET changedFields1 = CONCAT(changedFields1,'DATE_TO;null;',new.DateTo,',') ;  	
END IF;

IF(old.FullPaperDate is not null and old.FullPaperDate <> new.FullPaperDate) THEN
	SET changedFields1 = CONCAT(changedFields1,'FULLPAPER_DATE;',old.FullPaperDate,';',new.FullPaperDate,',') ; 
	ELSEIF (old.FullPaperDate is null and new.FullPaperDate is not null) THEN
	SET changedFields1 = CONCAT(changedFields1,'FULLPAPER_DATE;null;',new.FullPaperDate,',') ; 	
END IF;

IF(old.PaymentDate is not null and old.PaymentDate <> new.PaymentDate) THEN
	SET changedFields1 = CONCAT(changedFields1,'PAYMENT_DATE;',old.PaymentDate,';',new.PaymentDate,',') ; 
	ELSEIF (old.PaymentDate is null and new.PaymentDate is not null) THEN
	SET changedFields1 = CONCAT(changedFields1,'PAYMENT_DATE;null;',new.PaymentDate,',') ; 	
END IF;

SET changedFields1 =  TRIM(BOTH ',' FROM changedFields1); 
INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(new.conferenceid,'CONFERENCE',sysdate(),changedFields1,'U');

/* END ;  2.D  */

/* BEGIN  2.E  */
SET changedFields1 = '';
IF(old.City <> new.city) THEN SET changedFields1 = 'x' ; 
ELSEIF (old.conferencetypeid <> new.conferencetypeid) THEN SET changedFields1 = 'x' ; 
ELSEIF (old.country <> new.country) THEN SET changedFields1 = 'x' ; 
ELSEIF (old.creatorid <> new.creatorid) THEN SET changedFields1 = 'x' ; 
ELSEIF (old.description <> new.description) THEN SET changedFields1 = 'x' ; 
ELSEIF (old.edition <> new.edition) THEN SET changedFields1 = 'x' ; 
ELSEIF (old.name <> new.name) THEN SET changedFields1 = 'x' ; 
ELSEIF (old.www <> new.www) THEN SET changedFields1 = 'x' ; 
END IF;

IF(changedFields1 <> '') THEN
	INSERT INTO conferencechanges (conferenceid,type,changedate,changedFields,changeType) values(new.conferenceid,'CONFERENCE',sysdate(),changedFields1,'E');
END IF;

/* END ; 2.E  */
END ;;

DELIMITER ;
/*
 * 
 *                                  
 */
/*
 * add usersettings table
 */
CREATE TABLE `conference`.`usersettings` (
`id` INT NOT NULL AUTO_INCREMENT,
`userid` INT NOT NULL , 
`email` VARCHAR(200) NOT NULL ,
`newsletterlevel` INT NOT NULL,
`active` INT NOT NULL, PRIMARY KEY (`id`),
CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB;


