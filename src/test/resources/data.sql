-- Authorities

-- Authority Authorities
insert into authority(authority_id, permission, created_date, updated_date) values ('67cdcff0-f180-421a-bd5f-69cbc62f3cfe', 'authority.create', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('f06cc9be-0c37-4dac-8d36-941e7dd03e95', 'authority.read', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('1fba12c8-da0d-4d15-be62-5cdcc0d40240', 'authority.update', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('4a12573c-288f-4218-9a1f-8effcd2b8637', 'authority.delete', sysdate(), sysdate());

-- Role Authorities
insert into authority(authority_id, permission, created_date, updated_date) values ('866d7126-8281-42c6-a02d-3087b0bb1cab', 'role.create', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('14e8773c-6649-4c21-9c3c-39a94017ee15', 'role.read', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('4cf0bfda-d703-4ce0-a3ab-298d5d0fd5be', 'role.update', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('baf01a6a-de78-4c2d-8ee8-28401cb5225b', 'role.delete', sysdate(), sysdate());

-- User Authorities
insert into authority(authority_id, permission, created_date, updated_date) values ('598ea376-0c17-474a-a0a7-cd26ce2c5754', 'user.create', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('9be33d52-605e-4d29-92e2-3209c1b8b79d', 'user.read', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('a0f3679a-ef3d-4175-a792-bc2376e11d08', 'user.update', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('dd14ff93-294a-469e-86e0-97289df45bb6', 'user.delete', sysdate(), sysdate());

-- Account Authorities
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('b995d822-03ba-46a1-a6cf-1d75dd51e57b', 'account.create', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('21c69c34-3939-41af-9797-33962a701f00', 'account.read', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('2bb12b6c-e399-4b54-85a4-383048399ce2', 'account.update', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('e7ad0679-aace-4def-8898-26cd30147128', 'account.delete', sysdate(), sysdate());

-- Transaction Authorities
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('62743bb2-c6c8-4194-ae68-cfb2d2414fc6', 'transaction.create', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('78ff18a5-4411-4f09-91ca-f16469fdf075', 'transaction.read', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('c8ae9d23-6e40-4604-acd5-7b350feeebd0', 'transaction.update', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('965386ba-7f77-4cf1-8a70-b5b5fd58f4d6', 'transaction.delete', sysdate(), sysdate());

-- Roles

INSERT INTO ROLE(role_id, role_name, created_date, updated_date) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'ADMIN', sysdate(), sysdate());
INSERT INTO ROLE(role_id, role_name, created_date, updated_date) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', 'ENROLLMENT_SPECIALIST', sysdate(), sysdate());
INSERT INTO ROLE(role_id, role_name, created_date, updated_date) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'ENROLLMENT_MANAGER', sysdate(), sysdate());

-- Role Authority Relationship

-- Admin Role
-- Account entity

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'b995d822-03ba-46a1-a6cf-1d75dd51e57b');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '2bb12b6c-e399-4b54-85a4-383048399ce2');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'e7ad0679-aace-4def-8898-26cd30147128');

-- Transaction entity

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '62743bb2-c6c8-4194-ae68-cfb2d2414fc6');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '78ff18a5-4411-4f09-91ca-f16469fdf075');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'c8ae9d23-6e40-4604-acd5-7b350feeebd0');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '965386ba-7f77-4cf1-8a70-b5b5fd58f4d6');

-- Authority Entity
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '67cdcff0-f180-421a-bd5f-69cbc62f3cfe');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'f06cc9be-0c37-4dac-8d36-941e7dd03e95');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '1fba12c8-da0d-4d15-be62-5cdcc0d40240');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '4a12573c-288f-4218-9a1f-8effcd2b8637');

-- Role Entity
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '866d7126-8281-42c6-a02d-3087b0bb1cab');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '14e8773c-6649-4c21-9c3c-39a94017ee15');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '4cf0bfda-d703-4ce0-a3ab-298d5d0fd5be');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'baf01a6a-de78-4c2d-8ee8-28401cb5225b');

-- User Entity
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '598ea376-0c17-474a-a0a7-cd26ce2c5754');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '9be33d52-605e-4d29-92e2-3209c1b8b79d');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'a0f3679a-ef3d-4175-a792-bc2376e11d08');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'dd14ff93-294a-469e-86e0-97289df45bb6');

-- Enrollment Manager Role
-- Account entity

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'b995d822-03ba-46a1-a6cf-1d75dd51e57b');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '2bb12b6c-e399-4b54-85a4-383048399ce2');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'e7ad0679-aace-4def-8898-26cd30147128');

-- Transaction entity

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '62743bb2-c6c8-4194-ae68-cfb2d2414fc6');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '78ff18a5-4411-4f09-91ca-f16469fdf075');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'c8ae9d23-6e40-4604-acd5-7b350feeebd0');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '965386ba-7f77-4cf1-8a70-b5b5fd58f4d6');

-- Enrollment Specialist Role
-- Account entity

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', '2bb12b6c-e399-4b54-85a4-383048399ce2');

-- Transaction entity

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', '78ff18a5-4411-4f09-91ca-f16469fdf075');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', 'c8ae9d23-6e40-4604-acd5-7b350feeebd0');

-- Users

INSERT INTO SECURITY_USER(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', 'john', '{bcrypt}$2a$10$RILDxbGK4jPwVu4LLtAzJ.inZyTZIIif0L8JdFAZo7BscxhtQCEgu', true, true, true, true, sysdate(), sysdate());
INSERT INTO SECURITY_USER(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', 'mary', '{bcrypt}$2a$10$A//IPwppA4uxB9ElZk/R.OqSy9xs5RPwBWCyTjtiqVcUlPv/2e6E6', true, true, true, true, sysdate(), sysdate());
INSERT INTO SECURITY_USER(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled,created_date, updated_date) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', 'cindy', '{bcrypt}$2a$10$JhPtDZiWLU.G3YX.oAhz2uXB0PYBhzlJ7q6QTf2a1ZhU83SheFtq.', true, true, true, true, sysdate(), sysdate());

-- User Role Relationship

INSERT INTO USER_ROLE(user_id, role_id) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', '100ad35e-04ee-4da5-952c-e840b7a8d1ea');
INSERT INTO USER_ROLE(user_id, role_id) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', '0bbf5895-9084-4b8d-860c-c722d649ff66');
INSERT INTO USER_ROLE(user_id, role_id) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', 'fbf390fc-99b7-40ad-b368-20523dda8e12');
