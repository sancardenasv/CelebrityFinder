DROP TABLE IF EXISTS team;

CREATE TABLE team (
  id INT AUTO_INCREMENT PRIMARY KEY,
  celebrity BOOLEAN NOT NULL,
  known VARCHAR NOT NULL
);

INSERT INTO team (celebrity, known) VALUES
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (TRUE, ''),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9'),
  (FALSE, '9');