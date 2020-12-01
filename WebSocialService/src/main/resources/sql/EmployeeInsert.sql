INSERT INTO "public".person (id, socialid, birthdate, fname, lname, mname) VALUES (2, '', '1243-04-30', 'Иванов', 'Ивван', 'Васильевич');
INSERT INTO "public".employee (id, pos_id, person_id) 
	VALUES (1, 1, 2);
