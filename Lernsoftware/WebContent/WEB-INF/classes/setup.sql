/* Benutzer */
insert into T_USER (username, password) values ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997');

/* Kapitel */
insert into T_CHAPTER (chapterid, title) values (1, 'OSI Stack');
insert into T_CHAPTER (chapterid, title) values (2, 'IP Addressing');

/* Lektionen */
insert into T_LESSON (lessonid, title, chapter) values (1, 'Layer 1', 1);
insert into T_LESSON (lessonid, title, chapter) values (2, 'Layer 2', 1);
insert into T_LESSON (lessonid, title, chapter) values (3, 'Layer 3', 1);

insert into T_LESSON (lessonid, title, chapter) values (4, 'Classful Addressing', 2);
insert into T_LESSON (lessonid, title, chapter) values (5, 'Classless Addressing', 2);
insert into T_LESSON (lessonid, title, chapter) values (6, 'Supernetting', 2);