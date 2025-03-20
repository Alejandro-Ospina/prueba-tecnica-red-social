ALTER TABLE publications DROP CONSTRAINT fk_publications_users;

ALTER TABLE publications
ADD CONSTRAINT fk_publications_users
FOREIGN KEY (user_id) REFERENCES users (id)
ON DELETE SET NULL;
