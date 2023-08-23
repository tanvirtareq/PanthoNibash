ALTER TABLE customer
    ALTER COLUMN password TYPE VARCHAR(200);

ALTER TABLE hotel
    ALTER COLUMN password TYPE VARCHAR(200);

ALTER TABLE customer
    RENAME COLUMN profilepicture TO profile_picture;