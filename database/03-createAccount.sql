-- Administradores
insert into usuario values
(1, 3, 13, 'Renan', 'renan@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13994521678', '64831584356', 'ADMIN', '', ''),
(2, 3, 13, 'Vassão', 'vassao@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13995876143', '43694832578', 'ADMIN', '', '');

-- Usuários pessoas
insert into usuario values
(3, 1, 13, 'Marcelo', 'marcelinhosarinho2008@hotmail.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13996485426', '61549873621', 'USER', '', ''),
(4, 1, 13, 'Vivian', 'contanovadoarmais@outlook.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13999483671', '61593286247', 'USER', '', ''),
(5, 1, 13, 'Daniel', 'daniel@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13993649258', '63695895847', 'USER', '', ''),
(6, 1, 13, 'Douglas', 'douglas@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13998564872', '12145486295', 'USER', '', '');

-- Usuários ONGs
insert into usuario values
(7, 2, 13, 'Casa do Zezinho', 'casadozezinho@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13996584781', '74566035000129', 'USER', '', ''),
(8, 2, 13, 'Amigos do bem', 'amigosdobem@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13995846358', '05108918000172', 'USER', '', ''),
(9, 2, 13, 'Ação da Cidadania', 'acaocidadania@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13995847685', '00346076000173', 'USER', '', ''),
(10, 2, 13, 'Instituição Ampara Animal', 'amparaanimal@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13991352647', '12791298000265', 'USER', '', '');

-- Contas pendentes
insert into usuario values
(11, 1, 12, 'Carla', 'doar.mais@outlook.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13995842635', '48564895714', 'USER', 'comprovante01.jpg', 'comprovantes/b14acfe0-9d1b-4405-8418-b107863cda02'),
(12, 1, 12, 'Manuela', 'manuela@email.com', '$2a$10$vsYALfY0kC30Ver/tyy38OBHaLfzhqQj.Ql.lT0JfoItD5Iv1RROy', '13995632547', '48753264852', 'USER', 'comprovante02.png', 'comprovantes/8563f646-2b7c-4f69-be82-6a8b49cc6c61');