/* Benutzer */
insert into T_USER (username, password, created, lastlogin) values ('admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 0, 0);

/* Kapitel */
insert into T_CHAPTER (chapterid, title) values (1, 'OSI Stack');
insert into T_CHAPTER (chapterid, title) values (2, 'IP Addressing');

/* Lektionen */
insert into T_LESSON (lessonid, title, chapter, content) values (1, 'Introduction', 1, '<applet code="de.adv_boeblingen.seegerj.amed.lernsoftware.applets.IsoOsiApplet.class" codebase="../Applet/" width=750 height=250>');
insert into T_LESSON (lessonid, title, chapter, content) values (2, 'Layer 1', 1, '<ul><li>Unipolar Encoding (Return to zero)</li><ul><li>Between 0 and 5 Volts</li></ul><img src="../RZcode.png"><li>Polar Encoding (Non return to zero)</li><ul><li>Shift between -5 and +5 depending on value</li></ul><img src="../NRZ_code.png"><li>Phase Encoding (Manchester)</li><ul><li>Between -5 and +5 Volts depending on phase</li></ul><img src="../Manchester_code.png"></ul>');
insert into T_LESSON (lessonid, title, chapter, content) values (3, 'Layer 2', 1, '<ul><li>Provides communication in the local (switched) network</li><li>Simple addressing scheme based on hardware identifiers</li></ul>');
insert into T_LESSON (lessonid, title, chapter, content) values (4, 'Layer 3', 1, 'Test content3 und so&hellip;');
insert into T_LESSON (lessonid, title, chapter, content) values (5, 'Subnetting', 2, '');
insert into T_LESSON (lessonid, title, chapter, content) values (6, 'Classful Addressing', 2, '');
insert into T_LESSON (lessonid, title, chapter, content) values (7, 'Classless Addressing', 2, '');
insert into T_LESSON (lessonid, title, chapter, content) values (8, 'Supernetting', 2, '');

/* Frage 1 */
insert into T_QUESTION (questionid, lesson, question, type) values (1, 2, 'Test frage 1', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (1, 1, 'Antwort 1');
insert into T_ANSWER (answerid, questionid, answer) values (2, 1, 'Antwort 2 (r)');
insert into T_ANSWER (answerid, questionid, answer) values (3, 1, 'Antwort 3');
update T_QUESTION set correctanswer = 2 WHERE questionid = 1;

/* Frage 2 */
insert into T_QUESTION (questionid, lesson, question, type, isvalanswer, valanswer) values (2, 2, 'Test frage 2 {q1a2} mit Drag&{q1a2}', 'dragndrop', true, 'q2a4q2a5');
insert into T_ANSWER (answerid, questionid, answer) values (4, 2, 'Antwort 1');
insert into T_ANSWER (answerid, questionid, answer) values (5, 2, 'Antwort 2');

/* Frage 3 */
insert into T_QUESTION (questionid, lesson, question, type) values (3, 5, 'Test frage', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (7, 3, 'Antwort 1 (r)');
insert into T_ANSWER (answerid, questionid, answer) values (8, 3, 'Antwort 2');
insert into T_ANSWER (answerid, questionid, answer) values (9, 3, 'Antwort 3');
update T_QUESTION set correctanswer = 7 WHERE questionid = 3;