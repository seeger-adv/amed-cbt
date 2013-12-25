/* Benutzer */
insert into T_USER (username, password, created, lastlogin) values ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 0, 0);

/* Kapitel */
insert into T_CHAPTER (chapterid, title) values (1, 'OSI Stack');
insert into T_CHAPTER (chapterid, title) values (2, 'IP Addressing');

/* Lektionen */
insert into T_LESSON (lessonid, title, chapter, content) values (1, 'Introduction', 1, '<applet code="de.adv_boeblingen.seegerj.amed.lernsoftware.applets.IsoOsiApplet.class" codebase="../Applet/" width=100% height=99%>');
insert into T_LESSON (lessonid, title, chapter, content) values (2, 'Layer 1', 1, 'Test content');
insert into T_LESSON (lessonid, title, chapter, content) values (3, 'Layer 2', 1, 'Test content2');
insert into T_LESSON (lessonid, title, chapter, content) values (4, 'Layer 3', 1, 'Test content3 und so&hellip;');

insert into T_LESSON (lessonid, title, chapter) values (5, 'Subnetting', 2);
insert into T_LESSON (lessonid, title, chapter) values (6, 'Classful Addressing', 2);
insert into T_LESSON (lessonid, title, chapter) values (7, 'Classless Addressing', 2);
insert into T_LESSON (lessonid, title, chapter) values (8, 'Supernetting', 2);