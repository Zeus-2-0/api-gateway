-- Authorities

-- Authority Authorities
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('277875d5-84cd-47b6-b398-13426f01ad8a', 'authority.create', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('037e658d-f476-4834-9b75-a94f43bc73f9', 'authority.read', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('1bf80f70-c92f-4387-83cf-0e8308447193', 'authority.update', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('02dc6b02-5724-4960-a646-bcadf9a69d5b', 'authority.delete', sysdate(), sysdate());

-- Role Authorities
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('e339e0a9-93ae-4e42-8e2d-a29ede5aef8a', 'role.create', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('0dbe2c1a-8842-4206-8d73-c15d243af21b', 'role.read', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('77974238-6180-43db-a965-98290f593a7b', 'role.update', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('12b0b0e1-a31d-4219-ae18-a8f1a49ad6e0', 'role.delete', sysdate(), sysdate());

-- User Authorities
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('c45e56f5-4f24-47e6-a7d4-a6ce06a132dd', 'user.create', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('ac8f8dad-c53e-4bc6-968f-a8ae9e886372', 'user.read', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('00362283-464d-4db8-9455-ca9cf461453d', 'user.update', sysdate(), sysdate());
insert into apigatewaydb.authority(authority_id, permission, created_date, updated_date) values ('f9e0cd03-9528-4bf5-aff4-345b0e327126', 'user.delete', sysdate(), sysdate());

-- Trading Partner Authorities
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('8658fc2f-8c7c-4a81-90e2-2a58b649ebaf', 'tp.create', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('5203ff1b-ed32-4c68-a2e3-ef2e7efcf8e9', 'tp.read', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('ff42fc45-ed48-4251-84bb-51526493b47c', 'tp.update', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('2bed9676-7176-44be-ab82-3a3f9b138400', 'tp.delete', sysdate(), sysdate());

-- Account Authorities
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('51b77880-15fe-4e8b-8b85-d319120410db', 'account.create', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('406b8c86-c239-4a56-86ce-dc1f95e556d0', 'account.read', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('73342fe5-9a6b-45ac-9bf6-c153b2c0366d', 'account.update', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('a45f1d48-6cd7-4290-9f9e-9a15d7c824a0', 'account.delete', sysdate(), sysdate());

-- Transaction Authorities
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('3d5840ce-0d67-4627-ab71-32d3be54d812', 'transaction.create', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('dfdb3aa5-3d39-4cdd-8d15-c152a7599aaf', 'transaction.read', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('1963cd2d-ff17-44a4-b638-303bc75d7176', 'transaction.update', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('1c1f8222-1d09-431d-9990-c143b7e8bd1c', 'transaction.delete', sysdate(), sysdate());

-- Reference Data Authorities
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('a10b4e37-8d3f-4fcb-9eb7-1e256b179bb2', 'ref-data.create', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('3869e7e8-2dfc-4e15-8a02-25d463739fc6', 'ref-data.read', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('694c138b-f369-4860-a1d7-08a5acc8a8c3', 'ref-data.update', sysdate(), sysdate());
INSERT INTO apigatewaydb.authority(authority_id, permission, created_date, updated_date) VALUES ('a2664f2f-7111-4bb5-8984-013027de0106', 'ref-data.delete', sysdate(), sysdate());


-- Roles

INSERT INTO apigatewaydb.role(role_id, role_name, created_date, updated_date) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'ADMIN', sysdate(), sysdate());
INSERT INTO apigatewaydb.role(role_id, role_name, created_date, updated_date) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', 'ENROLLMENT_SPECIALIST', sysdate(), sysdate());
INSERT INTO apigatewaydb.role(role_id, role_name, created_date, updated_date) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', 'ENROLLMENT_MANAGER', sysdate(), sysdate());

-- Role Authority Relationship

-- Admin Role
-- Ref-Data entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'a10b4e37-8d3f-4fcb-9eb7-1e256b179bb2');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '3869e7e8-2dfc-4e15-8a02-25d463739fc6');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '694c138b-f369-4860-a1d7-08a5acc8a8c3');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'a2664f2f-7111-4bb5-8984-013027de0106');


-- Account entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '51b77880-15fe-4e8b-8b85-d319120410db');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '406b8c86-c239-4a56-86ce-dc1f95e556d0');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '73342fe5-9a6b-45ac-9bf6-c153b2c0366d');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'a45f1d48-6cd7-4290-9f9e-9a15d7c824a0');

-- Transaction entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '3d5840ce-0d67-4627-ab71-32d3be54d812');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'dfdb3aa5-3d39-4cdd-8d15-c152a7599aaf');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '1963cd2d-ff17-44a4-b638-303bc75d7176');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '1c1f8222-1d09-431d-9990-c143b7e8bd1c');

-- Trading Partner entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '8658fc2f-8c7c-4a81-90e2-2a58b649ebaf');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '5203ff1b-ed32-4c68-a2e3-ef2e7efcf8e9');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'ff42fc45-ed48-4251-84bb-51526493b47c');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '2bed9676-7176-44be-ab82-3a3f9b138400');

-- Authority Entity
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '277875d5-84cd-47b6-b398-13426f01ad8a');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '037e658d-f476-4834-9b75-a94f43bc73f9');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '1bf80f70-c92f-4387-83cf-0e8308447193');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '02dc6b02-5724-4960-a646-bcadf9a69d5b');

-- Role Entity
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'e339e0a9-93ae-4e42-8e2d-a29ede5aef8a');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '0dbe2c1a-8842-4206-8d73-c15d243af21b');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '77974238-6180-43db-a965-98290f593a7b');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '12b0b0e1-a31d-4219-ae18-a8f1a49ad6e0');

-- User Entity
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'c45e56f5-4f24-47e6-a7d4-a6ce06a132dd');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'ac8f8dad-c53e-4bc6-968f-a8ae9e886372');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', '00362283-464d-4db8-9455-ca9cf461453d');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('0f3656ed-c209-45d1-a16f-bd95aed77c18', 'f9e0cd03-9528-4bf5-aff4-345b0e327126');

-- Enrollment Manager Role

-- Ref-Data entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', 'a10b4e37-8d3f-4fcb-9eb7-1e256b179bb2');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '3869e7e8-2dfc-4e15-8a02-25d463739fc6');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '694c138b-f369-4860-a1d7-08a5acc8a8c3');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', 'a2664f2f-7111-4bb5-8984-013027de0106');

-- Trading Partner Entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '8658fc2f-8c7c-4a81-90e2-2a58b649ebaf');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '5203ff1b-ed32-4c68-a2e3-ef2e7efcf8e9');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', 'ff42fc45-ed48-4251-84bb-51526493b47c');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '2bed9676-7176-44be-ab82-3a3f9b138400');

-- Account entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '51b77880-15fe-4e8b-8b85-d319120410db');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '406b8c86-c239-4a56-86ce-dc1f95e556d0');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '73342fe5-9a6b-45ac-9bf6-c153b2c0366d');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', 'a45f1d48-6cd7-4290-9f9e-9a15d7c824a0');

-- Transaction entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '3d5840ce-0d67-4627-ab71-32d3be54d812');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', 'dfdb3aa5-3d39-4cdd-8d15-c152a7599aaf');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '1963cd2d-ff17-44a4-b638-303bc75d7176');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('6fb3532d-ce13-476b-b3cc-b49db336c22c', '1c1f8222-1d09-431d-9990-c143b7e8bd1c');

-- Enrollment Specialist Role

-- Ref-Data entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', '3869e7e8-2dfc-4e15-8a02-25d463739fc6');

-- Trading Partner Entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', '5203ff1b-ed32-4c68-a2e3-ef2e7efcf8e9');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', 'ff42fc45-ed48-4251-84bb-51526493b47c');

-- Account entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', '406b8c86-c239-4a56-86ce-dc1f95e556d0');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', '73342fe5-9a6b-45ac-9bf6-c153b2c0366d');

-- Transaction entity

INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', 'dfdb3aa5-3d39-4cdd-8d15-c152a7599aaf');
INSERT INTO apigatewaydb.role_authority(role_id, authority_id) VALUES ('69c07827-6870-4347-b962-9da733f6e8a8', '1963cd2d-ff17-44a4-b638-303bc75d7176');



-- Users

INSERT INTO apigatewaydb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', 'john', '$2a$10$q15whOtuMFuQIWqRNzzLzezI7.DURBkOL773py64tMdf6DN.x8IgG', true, true, true, true, sysdate(), sysdate());
INSERT INTO apigatewaydb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', 'mary', '$2a$10$vTh8UGPShOV1CTungBEC4.vC16cyxyxIXcmGAg4xqDGuyQAbkSiby', true, true, true, true, sysdate(), sysdate());
INSERT INTO apigatewaydb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled,created_date, updated_date) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', 'cindy', '$2a$10$pF7dup.ytKQNRksptR2ehessjqG7e3jp8eiuzyT1dOzUtKIUCkDiq', true, true, true, true, sysdate(), sysdate());

-- User Role Relationship

INSERT INTO apigatewaydb.user_role(user_id, role_id) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', '0f3656ed-c209-45d1-a16f-bd95aed77c18');
INSERT INTO apigatewaydb.user_role(user_id, role_id) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', '69c07827-6870-4347-b962-9da733f6e8a8');
INSERT INTO apigatewaydb.user_role(user_id, role_id) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', '6fb3532d-ce13-476b-b3cc-b49db336c22c');
