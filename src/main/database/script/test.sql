alter table user drop foreign key `user-affiliationId`;
ALTER TABLE conference DROP FOREIGN KEY `conference-conferencetype` ;

INSERT INTO `user`
(`UserId`, `Login`, `Password`, `GivenName`, `FamilyName`, `AcademicTitle`,
`IsActive`, `IsAdmin`, `Email`, `AffiliationId`) VALUES (1,'test','test','a','b','c',1,1,'dasda@wp.pl',1);
INSERT INTO `user`
(`UserId`, `Login`, `Password`, `GivenName`, `FamilyName`, `AcademicTitle`,
`IsActive`, `IsAdmin`, `Email`, `AffiliationId`) VALUES (2,'test2','test2','a2','b2','c2',1,1,'dasda2@wp.pl',1);
INSERT INTO `user`
(`UserId`, `Login`, `Password`, `GivenName`, `FamilyName`, `AcademicTitle`,
`IsActive`, `IsAdmin`, `Email`, `AffiliationId`) VALUES (3,'test3','test3','a3','b3','c3',1,1,'dasda3@wp.pl',1);
INSERT INTO `user`
(`UserId`, `Login`, `Password`, `GivenName`, `FamilyName`, `AcademicTitle`,
`IsActive`, `IsAdmin`, `Email`, `AffiliationId`) VALUES (4,'test4','test2','a4','b4','c4',1,1,'dasda4@wp.pl',1);
INSERT INTO `user`
(`UserId`, `Login`, `Password`, `GivenName`, `FamilyName`, `AcademicTitle`,
`IsActive`, `IsAdmin`, `Email`, `AffiliationId`) VALUES (5,'test5','test5','a5','b5','c5',1,1,'dasda5@wp.pl',1);



INSERT into conference 
(ConferenceId,CreatorId,Name,DateFrom,DateTo,Country,City) 
values(150,1,'moja 1','2016-04-11','2016-06-11','POLAND','nielisz cycow niemce');

INSERT into conference 
(ConferenceId,CreatorId,Name,DateFrom,DateTo,Country,City) 
values(151,2,'moja 11','2016-04-11','2016-06-11','POLAND','nielisz ');

INSERT into conference 
(ConferenceId,CreatorId,Name,DateFrom,DateTo,Country,City) 
values(152,3,'moja 111','2016-06-07','2016-06-11','POLAND',' cycow ');

INSERT into conference 
(ConferenceId,CreatorId,Name,DateFrom,DateTo,Country,City) 
values(153,3,'moja 1111','2015-04-11','2016-06-11','POLAND','niemce');

INSERT into conference 
(ConferenceId,CreatorId,Name,DateFrom,DateTo,Country,City) 
values(154,1,'moja 1','2016-04-11','2016-06-11','POLAND','nielisz cycow niemce');