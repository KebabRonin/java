DROP TABLE albums CASCADE CONSTRAINTS
/
DROP TABLE artists CASCADE CONSTRAINTS
/
DROP TABLE genres CASCADE CONSTRAINTS
/
DROP TABLE album_genres CASCADE CONSTRAINTS
/

CREATE TABLE artists(
    id NUMBER PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
)
/
CREATE TABLE albums(
    id NUMBER PRIMARY KEY,
    release_year NUMBER(5),
    title VARCHAR(100),
    id_artist NUMBER,
    FOREIGN KEY (id_artist) REFERENCES artists(id)
)
/
CREATE TABLE genres(
    id NUMBER PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
)
/
CREATE TABLE album_genres(
    id_album NUMBER,
    id_genre NUMBER,
    FOREIGN KEY (id_album) REFERENCES albums(id),
    FOREIGN KEY (id_genre) REFERENCES genres(id)
)
/
CREATE OR REPLACE TRIGGER artists_auto_increment_id
  BEFORE INSERT ON artists
  FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT (NVL(MAX(id), 0) + 1) INTO :NEW.id FROM artists;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER albums_auto_increment_id
  BEFORE INSERT ON albums
  FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT (NVL(MAX(id), 0) + 1) INTO :NEW.id FROM albums;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER genres_auto_increment_id
  BEFORE INSERT ON genres
  FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT (NVL(MAX(id), 0) + 1) INTO :NEW.id FROM genres;
  END IF;
END;
/
--DELETE FROM album_genres;
--DELETE FROM genres;
--DELETE FROM albums;
--DELETE FROM artists;
--COMMIT;
--INSERT INTO artists(NAME) VALUES('MAMA MIA');
--SELECT * FROM artists;
--SELECT * FROM genres;
--SELECT * FROM albums;
--SELECT * FROM album_genres;