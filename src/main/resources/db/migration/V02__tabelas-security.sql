CREATE TABLE tb_profile (id_profile int4 NOT NULL, nm_profile varchar(255) NOT NULL, ds_profile varchar(500), PRIMARY KEY (id_profile));
CREATE TABLE tb_profile_role (id_profile int4 NOT NULL, id_role int4 NOT NULL, PRIMARY KEY (id_profile, id_role));
CREATE TABLE tb_role (id_role int4 NOT NULL, nm_role varchar(255) NOT NULL UNIQUE, PRIMARY KEY (id_role));
CREATE TABLE tb_user (id_user int4 NOT NULL AUTO_INCREMENT, ds_email varchar(255) NOT NULL UNIQUE, ds_password varchar(500), nm_user varchar(255) NOT NULL, id_profile int4 NOT NULL, PRIMARY KEY (id_user));
ALTER TABLE tb_user ADD CONSTRAINT FKtb_user747850 FOREIGN KEY (id_profile) REFERENCES tb_profile (id_profile);
ALTER TABLE tb_profile_role ADD CONSTRAINT FKtb_profile238820 FOREIGN KEY (id_role) REFERENCES tb_role (id_role);
ALTER TABLE tb_profile_role ADD CONSTRAINT FKtb_profile589574 FOREIGN KEY (id_profile) REFERENCES tb_profile (id_profile);
