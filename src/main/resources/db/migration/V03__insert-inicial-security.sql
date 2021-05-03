INSERT INTO tb_role		values(1, 'ROLE_PESSOA_FIND');
INSERT INTO tb_role		values(2, 'ROLE_PESSOA_SAVE');

INSERT INTO tb_profile 	values(1, 'ADMIN', 'ADMIN');

INSERT INTO tb_profile_role values(1, 1);
INSERT INTO tb_profile_role values(1, 2);

	
INSERT INTO tb_user(
	ds_email, ds_password, nm_user, id_profile)
	VALUES (
	'admin@admin.com',
	'$2a$10$1MxXxcJc9DG40ToCQMPh3.AHerddtnyeVDkOptMPqK08Xo1RCGHNi',
	'Administrador',
	1
	);

