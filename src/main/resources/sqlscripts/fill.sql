INSERT INTO roles(name)
VALUES ('admin'), ('mentor'), ('student');

INSERT INTO privileges(name)
VALUES ('create mentor'), ('create student'), ('create artifact'), ('create quest');

INSERT INTO users
VALUES ('marcintuleja', 'Marcin', 'Tuleja', 'marcintuleja@gmail.com', (SELECT id FROM roles WHERE name='student'), 'pass1'),
       ('edobkowski', 'Eryk', 'Dobkowski', 'edobkowski@gmail.com', (SELECT id FROM roles WHERE name='student'), 'pass2'),
       ('kubset', 'Kuba', 'Setla', 'kubasetla@gmail.com', (SELECT id FROM roles WHERE name='student'), 'pass3'),
       ('kongad', 'Konrad', 'Gadzina', 'konradgadzina@gmail.com', (SELECT id FROM roles WHERE name='mentor'), '$%$#'),
       ('agi', 'Agnieszka', 'Koszany', 'aga.koszany@gmail.com', (SELECT id FROM roles WHERE name='mentor'), 'brzozowski'),
       ('admin', 'Admin', 'Adminowski', 'admin@admin.com', (SELECT id FROM roles WHERE name='admin'), 'admin');

INSERT INTO admins
SELECT login FROM users
    INNER JOIN roles
    ON (users.role_id=roles.id)
    WHERE roles.name='admin';

INSERT INTO mentors
SELECT login FROM users 
    INNER JOIN roles 
    ON (users.role_id=roles.id) 
    WHERE roles.name='mentor';

INSERT INTO students
SELECT login FROM users 
    INNER JOIN roles 
    ON (users.role_id=roles.id) 
    WHERE roles.name='student';

INSERT INTO classes(name)
VALUES ('KRK.2017.11'), ('KRK.2018.03'), ('KRK.2018.05');

INSERT INTO class_mentors
VALUES ((SELECT id FROM classes WHERE name='KRK.2017.11'), (SELECT login FROM mentors WHERE login='agi')),
       ((SELECT id FROM classes WHERE name='KRK.2018.03'), (SELECT login FROM mentors WHERE login='agi')),
       ((SELECT id FROM classes WHERE name='KRK.2017.11'), (SELECT login FROM mentors WHERE login='kongad')),
       ((SELECT id FROM classes WHERE name='KRK.2018.05'), (SELECT login FROM mentors WHERE login='kongad'));

INSERT INTO wallets(owner_login, balance)
VALUES ('kubset', DEFAULT),
       ('edobkowski', DEFAULT),
       ('marcintuleja', DEFAULT);

INSERT INTO quests(name, description, reward)
VALUES ('Slaying a dragon', 'Passing a checkpoint', 500),
       ('Exploring a dungeon', 'Finishing a TW week', 100),
       ('Solving the magic puzzle', 'Finishing a SI assignment', 100);
 
INSERT INTO artifacts(name, description, price)
VALUES ('Combat training', 'Private mentoring', 50),
       ('Sanctuary', 'You can spend a day in home office', 300),
       ('Time travel', 'Extend SI week assignment deadline by one day', 500);

INSERT INTO wallet_artifacts(wallet_id, artifact_id)
VALUES (1, 2, 2),
       (1, 3, 1),
       (3, 2, 1);

INSERT INTO levels(name, threshold)
VALUES ('Noob', 100),
       ('Peasant', 200),
       ('PHP Developer', 300),
       ('Checkpoint slayer', 500);
