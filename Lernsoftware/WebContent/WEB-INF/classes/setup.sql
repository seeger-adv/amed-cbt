/* Benutzer */
insert into T_USER (username, password, created, lastlogin) values ('admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 0, 0);

/* Kapitel */
insert into T_CHAPTER (chapterid, title) values (1, 'OSI Stack');
insert into T_CHAPTER (chapterid, title) values (2, 'IP Addressing');

/* Lektionen */
insert into T_LESSON (lessonid, title, chapter, content) values (1, 'Introduction', 1, '<p>Hi I&apos;m Troy McClure. You may remember me from educational software like <ul><li>Clicky the Mouse</li> <li>Kerberos, your friendly authentication mechanism</li>  <li>Wait, where did my Reference go! Garbage Collection 101</li></ul></p><iframe width="853" height="480" src="//www.youtube.com/embed/ekKDoBhoH-U?list=PLMM1dVgDCD2SVekXIYjKvOJNZRSYz2dXI" frameborder="0" allowfullscreen></iframe>');
insert into T_LESSON (lessonid, title, chapter, content) values (2, 'Layer 1:<br>Physical Layer', 1, '<p>In order to successfully transmit data sender and receiver must have a common understanding of the symbols used for the transmission and their meaning.</p><h2>Polar Encoding (Non return to zero)</h2><img src="../content/NRZ_code.png" class="abbildung"><p>The simplest form of signal encoding is the <abbr title="non return to zero">NRTZ</abbr> method where each symbol gets mapped by a voltage and is directly applied onto the line.</p><h2>Unipolar Encoding (Return to zero)</h2><img src="../content/RZcode.png" class="abbildung"><p>For the <abbr title="return to zero">RTZ</abbr> method the actual encoding of 1s and 0s on the line alternate between -5 and 5 Volts while returning to 0 Volts every half phase.</p><h2>Phase Encoding (Manchester)</h2><img src="../content/Manchester_code.png" class="abbildung"><p>The Manchester code also transmits the timing signal which reduces the available bandwith the payload.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (3, 'Layer 2:<br>Data Link Layer', 1, '<ol><li>Provides communication in the local (switched) network</li><li>Simple addressing scheme based on hardware identifiers</li></ol>');
insert into T_LESSON (lessonid, title, chapter, content) values (4, 'Layer 3:<br>Network Layer', 1, 'Test content3 und so&hellip;');
insert into T_LESSON (lessonid, title, chapter, content) values (5, 'Subnetting', 2, '');
insert into T_LESSON (lessonid, title, chapter, content) values (6, 'Classful Addressing', 2, '<applet code="de.adv_boeblingen.seegerj.amed.lernsoftware.applets.SubnetApplet.class" codebase="../Applet/" width=750 height=250>');
insert into T_LESSON (lessonid, title, chapter, content) values (7, 'Classless Addressing', 2, '');
insert into T_LESSON (lessonid, title, chapter, content) values (8, 'Supernetting', 2, '');

/* Frage 1 */
insert into T_QUESTION (questionid, lesson, question, type) values (1, 2, 'Test frage 1', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (1, 1, 'Antwort 1');
insert into T_ANSWER (answerid, questionid, answer) values (2, 1, 'Antwort 2 (r)');
insert into T_ANSWER (answerid, questionid, answer) values (3, 1, 'Antwort 3');
update T_QUESTION set correctanswer = 2 WHERE questionid = 1;

/* Frage 2 */
insert into T_QUESTION (questionid, lesson, question, type, isvalanswer, valanswer) values (2, 3, 'Test frage 2 {q1a2} mit Drag&{q1a2}', 'dragndrop', true, 'q2a4q2a5');
insert into T_ANSWER (answerid, questionid, answer) values (4, 2, 'Antwort 1');
insert into T_ANSWER (answerid, questionid, answer) values (5, 2, 'Antwort 2');

/* Frage 3 */
insert into T_QUESTION (questionid, lesson, question, type) values (3, 5, 'Test frage', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (7, 3, 'Antwort 1 (r)');
insert into T_ANSWER (answerid, questionid, answer) values (8, 3, 'Antwort 2');
insert into T_ANSWER (answerid, questionid, answer) values (9, 3, 'Antwort 3');
update T_QUESTION set correctanswer = 7 WHERE questionid = 3;

/* Frage 4 */
insert into T_QUESTION (questionid, lesson, question, type, isvalanswer, valanswer) values (4, 6, 'Enter the first available address that can be used by a host for 192.168.34.0/24.', 'addressing', true, '192.168.34.1');