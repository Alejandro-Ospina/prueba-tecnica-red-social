CREATE TABLE publications
(
    id SERIAL PRIMARY KEY,
    creation_date TIMESTAMP NOT NULL DEFAULT NOW(),
    topic VARCHAR(100) NOT NULL,
    content VARCHAR(5000) NOT NULL,
    likes INT,
    user_id INT NOT NULL
);

ALTER TABLE publications
ADD CONSTRAINT fk_publications_users
FOREIGN KEY (user_id) REFERENCES users (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

CREATE INDEX idx_publications_user_id ON publications (user_id);