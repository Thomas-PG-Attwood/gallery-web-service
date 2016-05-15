DROP TABLES image, gallery, config;

CREATE TABLE config (
    parameter VARCHAR(30) PRIMARY KEY,
    value VARCHAR(30)
);

CREATE TABLE gallery (
    gallery_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    gallery_ref VARCHAR(30) NOT NULL UNIQUE KEY,
    name TEXT CHARACTER SET utf8,
    description TEXT CHARACTER SET utf8,
    date_taken DATE
);

CREATE TABLE image (
    image_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    gallery_ref VARCHAR(30) NOT NULL,
    CONSTRAINT `fk_gallery_ref`
        FOREIGN KEY (gallery_ref) REFERENCES gallery (gallery_ref)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    file_name TEXT CHARACTER SET utf8 COMMENT 'file location relative to gallery\'s image location',
    name TEXT CHARACTER SET utf8,
    description TEXT CHARACTER SET utf8
);

INSERT INTO config (parameter, value)
VALUES ('FULL_PATH', '/images/gallery/full/');

INSERT INTO config (parameter, value)
VALUES ('THUMBS_PATH', '/images/gallery/thumbs/');

INSERT INTO gallery (gallery_ref, name, description, date_taken)
VALUES ('kew',
    'Wandering Kew Gardens',
    '<p class="first-paragraph">I visited Kew Gardens with my girlfriend in June, 2013. For most of it we did lounge around; however, when we did move about,it was a perfect day for taking photographs. These are my favourite photographs from that day.</p>',
    '2013-06-30');

INSERT INTO image (gallery_ref, file_name)
VALUES
    ('kew', 'P1080940.JPG'),
    ('kew', 'P1080970.JPG'),
    ('kew', 'P1080983.JPG'),
    ('kew', 'P1090066.JPG'),
    ('kew', 'P1090067.JPG'),
    ('kew', 'P1090078.JPG'),
    ('kew', 'P1090079.JPG'),
    ('kew', 'P1090081.JPG'),
    ('kew', 'P1090146.JPG'),
    ('kew', 'P1090154.JPG');

INSERT INTO gallery (gallery_ref, name, description, date_taken)
VALUES ('devon-ireland',
    'Devon and Ireland',
    '<p class="first-paragraph">In the summer of 2014 I visited Devon with my friends and Ireland with my family. These photos were taken while I was there with a Pentax K1000 using the standard 50mm f/2 Pentax-M lens and 200 or 400 ISO film.</p>',
    '2013-06-30');

INSERT INTO image (gallery_ref, file_name)
VALUES
    ('devon-ireland', 'img01.jpg'),
    ('devon-ireland', 'img02.jpg'),
    ('devon-ireland', 'img03.jpg'),
    ('devon-ireland', 'img04.jpg'),
    ('devon-ireland', 'img05.jpg'),
    ('devon-ireland', 'img06.jpg'),
    ('devon-ireland', 'img07.jpg'),
    ('devon-ireland', 'img08.jpg'),
    ('devon-ireland', 'img09.jpg'),
    ('devon-ireland', 'img10.jpg'),
    ('devon-ireland', 'img11.jpg'),
    ('devon-ireland', 'img12.jpg'),
    ('devon-ireland', 'img13.jpg'),
    ('devon-ireland', 'img14.jpg'),
    ('devon-ireland', 'img15.jpg');
