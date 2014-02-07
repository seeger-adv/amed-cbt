/* Benutzer */
insert into T_USER (username, password, created, lastlogin) values ('admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 0, 0);

/* Kapitel */
insert into T_CHAPTER (chapterid, title) values (1, 'OSI Stack');
insert into T_CHAPTER (chapterid, title) values (2, 'IP Addressing');

/* Lektionen */
insert into T_LESSON (lessonid, title, chapter, content) values (1, 'Introduction', 1, '<p>Hi I&apos;m Troy McClure. You may remember me from educational software like <ul><li>Clicky the Mouse</li> <li>Kerberos, your friendly authentication mechanism</li>  <li>Wait, where did my Reference go! Garbage Collection 101</li></ul></p><p>For every Layer there&apos;s a so-called <abbr title="Prototol Data Unit">PDU</abbr> that receives data from the previous layer and carries the payload to the next one.</p><iframe width="853" height="480" src="//www.youtube.com/embed/ekKDoBhoH-U?list=PLMM1dVgDCD2SVekXIYjKvOJNZRSYz2dXI" frameborder="0" allowfullscreen></iframe>');
insert into T_LESSON (lessonid, title, chapter, content) values (2, 'Layer 1:<br>Physical Layer', 1, '<p>The first layer&apos;s <abbr title="Prototol Data Unit">PDU</abbr> is the bare bit on the wire.</p><p>In order to successfully transmit data sender and receiver must have a common understanding of the symbols used for the transmission and their meaning.</p><h2>Polar Encoding (Non return to zero)</h2><img src="../content/NRZ_code.png" class="abbildung"><p>The simplest form of signal encoding is the <abbr title="non return to zero">NRTZ</abbr> method where each symbol gets mapped by a voltage and is directly applied onto the line.</p><h2>Unipolar Encoding (Return to zero)</h2><img src="../content/RZcode.png" class="abbildung"><p>For the <abbr title="return to zero">RTZ</abbr> method the actual encoding of 1s and 0s on the line alternate between -5 and 5 Volts while returning to 0 Volts every half phase.</p><h2>Phase Encoding (Manchester)</h2><img src="../content/Manchester_code.png" class="abbildung"><p>The Manchester code also transmits the timing signal which reduces the available bandwith the payload.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (3, 'Layer 2:<br>Data Link Layer', 1, '<p>The second layer&apos;s <abbr title="Prototol Data Unit">PDU</abbr> is the so-called Ethernet Frame.</p><p>This layer establishes the communication in the local (switched) network using the <abbr title="Media-Access-Control">MAC</abbr>-Address given by the <abbr title="Network Interface Card">NIC</abbr>-Vendor.</p><img src="../content/frame.png"><p>The Frame also contains checksums to verify the data integrity on the recipient side.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (4, 'Layer 3:<br>Network Layer', 1, '<p>The third layer&apos;s <abbr title="Prototol Data Unit">PDU</abbr> is the packet.</p>This layer wraps the frame by appending a logical addressing scheme, the so-called <abbr title="Internet Protocol">IP</abbr>-Address.</p><p>These logical addressing scheme pretty much behaves like the telephone number scheme, where a common prefix (area code) denotes spacial adjacency.</p><p>IP-Addresses are controlled by <abbr title="Internet Assigned Numbers Authority">IANA</abbr>.</p><p>But for your <abbr title="Small Office HOme">SOHO</abbr> network to work you don&apos;t need to apply for public addresses. Theres the so called Private Address Space for everyone to use.<p>The private address space mainly consists of:<ul><li>10.0.0.0/8</li><li>172.16.0.0/12</li><li>192.168.0.0/16</li></ul></p><p>We&apos;ll get into the details of what all these numbers mean in the next chapter.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (5, 'Subnetting', 2, '<p>The prevoius chapter gave you a quick primer on what the ISO/OSI network stack consists of - This chapter will show you around the Layer 3 and have some addressing fun.</p>');
insert into T_LESSON (lessonid, title, chapter, content) values (6, 'Classful Addressing', 2, '<applet code="de.adv_boeblingen.seegerj.amed.lernsoftware.applets.SubnetApplet.class" codebase="../Applet/" width=750 height=250><param name="mode" value="class"></applet>');
insert into T_LESSON (lessonid, title, chapter, content) values (7, 'Classless Addressing', 2, '<applet code="de.adv_boeblingen.seegerj.amed.lernsoftware.applets.SubnetApplet.class" codebase="../Applet/" width=750 height=250><param name="mode" value="cidr"></applet>');
insert into T_LESSON (lessonid, title, chapter, content) values (8, 'Supernetting', 2, '<p>Supernetting simplifies the configuration of routers by summarizing small adjacent networks reachable by the same router into one route.</p><img src="../content/supernetting.png" class="abbildung">');

/* Fragen */
insert into T_QUESTION (questionid, lesson, question, type) values (1, 2, 'What&apos;s the first layer&apos;s <abbr title="Prototol Data Unit">PDU</abbr>?', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (1, 1, 'Frame');
insert into T_ANSWER (answerid, questionid, answer) values (2, 1, 'Bit');
insert into T_ANSWER (answerid, questionid, answer) values (3, 1, 'Packet');
update T_QUESTION set correctanswer = 2 WHERE questionid = 1;

insert into T_QUESTION (questionid, lesson, question, type) values (2, 3, 'What&apos;s the second layer&apos;s <abbr title="Prototol Data Unit">PDU</abbr>?', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (4, 2, 'Frame');
insert into T_ANSWER (answerid, questionid, answer) values (5, 2, 'Bit');
insert into T_ANSWER (answerid, questionid, answer) values (6, 2, 'Packet');
update T_QUESTION set correctanswer = 4 WHERE questionid = 2;

insert into T_QUESTION (questionid, lesson, question, type) values (3, 4, 'What&apos;s the third layer&apos;s <abbr title="Prototol Data Unit">PDU</abbr>?', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (7, 3, 'Frame');
insert into T_ANSWER (answerid, questionid, answer) values (8, 3, 'Bit');
insert into T_ANSWER (answerid, questionid, answer) values (9, 3, 'Packet');
update T_QUESTION set correctanswer = 9 WHERE questionid = 3;

insert into T_QUESTION (questionid, lesson, question, type, isvalanswer, valanswer) values (4, 6, 'Enter the first available address that can be used by a host for 192.168.34.0/24.', 'addressing', true, '192.168.34.1');

insert into T_QUESTION (questionid, lesson, question, type, isvalanswer, valanswer) values (5, 5, 'A {q1a2} network consists of 256^1 addresses and a {q1a2} Network constists of 256^2-2 addressable hosts.', 'dragndrop', true, 'q5a10q5a12');
insert into T_ANSWER (answerid, questionid, answer) values (10, 5, 'Class A');
insert into T_ANSWER (answerid, questionid, answer) values (11, 5, 'Class B');
insert into T_ANSWER (answerid, questionid, answer) values (12, 5, 'Class C');

insert into T_QUESTION (questionid, lesson, question, type) values (6, 1, 'What does PDU stand for?', 'multiplechoice');
insert into T_ANSWER (answerid, questionid, answer) values (13, 6, 'Protocol Data Unit');
insert into T_ANSWER (answerid, questionid, answer) values (14, 6, 'Public Data Unit');
insert into T_ANSWER (answerid, questionid, answer) values (15, 6, 'Private Data Unit');
update T_QUESTION set correctanswer = 13 WHERE questionid = 6;

insert into T_QUESTION (questionid, lesson, question, type, isvalanswer, valanswer) values (7, 8, 'What is the best way to summarize the following subnets?<br>192.168.1.0/25<br>192.168.1.128/25', 'addressing', true, '192.168.1.0/24');

insert into T_QUESTION (questionid, lesson, question, type, isvalanswer, valanswer) values (8, 7, 'What&apos;s the last usable host address for the subnet 192.168.1.128/26?', 'addressing', true, '192.168.1.191/26');