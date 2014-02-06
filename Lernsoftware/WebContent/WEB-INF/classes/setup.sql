/* Benutzer */
insert into T_USER (username, password, created, lastlogin) values ('admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 0, 0);

/* Kapitel */
insert into T_CHAPTER (chapterid, title) values (1, 'OSI Stack');
insert into T_CHAPTER (chapterid, title) values (2, 'IP Addressing');

/* Lektionen */
insert into T_LESSON (lessonid, title, chapter, content) values (1, 'Introduction', 1, '<p>Hi I&apos;m Troy McClure. You may remember me from educational software like <ul><li>Clicky the Mouse</li> <li>Kerberos, your friendly authentication mechanism</li>  <li>Wait, where did my Reference go! Garbage Collection 101</li></ul></p><p>For every Layer there&apos;s a so-called <abbr title="Prototol Data Unit">PDU</abbr> that receives data from the previous layer and carries the payload to the next one.</p><iframe width="853" height="480" src="//www.youtube.com/embed/ekKDoBhoH-U?list=PLMM1dVgDCD2SVekXIYjKvOJNZRSYz2dXI" frameborder="0" allowfullscreen></iframe>');
insert into T_LESSON (lessonid, title, chapter, content) values (2, 'Layer 1:<br>Physical Layer', 1, '<p>The first layer&apos;s PDU is the bare bit on the wire.</p><p>In order to successfully transmit data sender and receiver must have a common understanding of the symbols used for the transmission and their meaning.</p><h2>Polar Encoding (Non return to zero)</h2><img src="../content/NRZ_code.png" class="abbildung"><p>The simplest form of signal encoding is the <abbr title="non return to zero">NRTZ</abbr> method where each symbol gets mapped by a voltage and is directly applied onto the line.</p><h2>Unipolar Encoding (Return to zero)</h2><img src="../content/RZcode.png" class="abbildung"><p>For the <abbr title="return to zero">RTZ</abbr> method the actual encoding of 1s and 0s on the line alternate between -5 and 5 Volts while returning to 0 Volts every half phase.</p><h2>Phase Encoding (Manchester)</h2><img src="../content/Manchester_code.png" class="abbildung"><p>The Manchester code also transmits the timing signal which reduces the available bandwith the payload.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (3, 'Layer 2:<br>Data Link Layer', 1, '<p>The second layer&apos;s PDU is the so-called Ethernet Frame.</p><p>This layer establishes the communication in the local (switched) network using the <abbr title="Media-Access-Control">MAC</abbr>-Address given by the <abbr title="Network Interface Card">NIC</abbr>-Vendor.</p><p>The Frame also contains checksums to verify the data integrity on the recipient side.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (4, 'Layer 3:<br>Network Layer', 1, '<p>The third layer&apos;s PDU is the packet.</p>This layer wraps the frame by appending a logical addressing scheme, the so-called <abbr title="Internet Protocol">IP</abbr>-Address.</p><p>These logical addressing scheme pretty much behaves like the telephone number scheme, where a common prefix (area code) denotes spacial adjacency.</p><p>IP-Addresses are controlled by <abbr title="Internet Assigned Numbers Authority">IANA</abbr>.</p><p>But for your <abbr title="Small Office HOme">SOHO</abbr> network to work you don&apos;t need to apply for public addresses. Theres the so called Private Address Space for everyone to use.<p>The private address space mainly consists of:<ul><li>10.0.0.0/8</li><li>172.16.0.0/12</li><li>192.168.0.0/16</li></ul></p><p>We&apos;ll get into the details of what all these numbers mean in the next chapter.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (5, 'Subnetting', 2, '<p>The prevoius chapter gave you a quick primer on what the ISO/OSI network stack consists of - This chapter will show you around the Layer 3 and have some addressing fun.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (6, 'Classful Addressing', 2, '<applet code="de.adv_boeblingen.seegerj.amed.lernsoftware.applets.SubnetApplet.class" codebase="../Applet/" width=750 height=250><param name="mode" value="class"></applet>');
insert into T_LESSON (lessonid, title, chapter, content) values (7, 'Classless Addressing', 2, '<applet code="de.adv_boeblingen.seegerj.amed.lernsoftware.applets.SubnetApplet.class" codebase="../Applet/" width=750 height=250><param name="mode" value="cidr"></applet>');
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