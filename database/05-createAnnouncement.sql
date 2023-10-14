set time_zone='America/Sao_Paulo';

insert into anuncio values
(1, 1, 2, 21, now(), 'Roupa e comida', now(), date_add(now(),interval 1 day), '11533040', 'SP', 'Cubatão', 'Jardim Casqueiro', 'Rua Estados Unidos', 530, '', 'Final da rua do Kalabalis', 0),
(2, 1, 2, 21, now(), 'Roupa', now(), date_add(now(),interval 2 day), '11533040', 'SP', 'Cubatão', 'Jardim Casqueiro', 'Rua Estados Unidos', 530, '', 'Final da rua do Kalabalis', 0),
(3, 1, 2, 21, now(), 'Notebook', now(), date_add(now(),interval 3 day), '08551040', 'SP', 'Poá', 'Centro', 'Praça da Independência', '10', '', '', 0),
(4, 2, 2, 21, now(), 'Roupa', now(), date_add(now(),interval 2 day), '11533040', 'SP', 'Cubatão', 'Jardim Casqueiro', 'Rua Estados Unidos', 530, '', 'Final da rua do Kalabalis', 0),
(5, 2, 2, 21, now(), 'Notebook', now(), date_add(now(),interval 3 day), '08551040', 'SP', 'Cubatão', 'Centro', 'Praça da Independência', '10', '', '', 0),
(6, 3, 2, 21, now(), 'Roupa', now(), date_add(now(),interval 2 day), '11533040', 'SP', 'Cubatão', 'Jardim Casqueiro', 'Rua Estados Unidos', 530, '', 'Final da rua do Kalabalis', 0),
(7, 3, 2, 21, now(), 'Notebook', now(), date_add(now(),interval 3 day), '08551040', 'SP', 'Cubatão', 'Centro', 'Praça da Independência', '10', '', '', 0);

insert into item_anuncio values
(1, 1, 4, 'Blusa preta', 7, 'Blusa branca tamanho G'),
(2, 1, 1, 'Macarrão', 7, 'Macarrão de 700g'),
(3, 2, 4, 'Blusa branca', 7, 'Blusa branca tamanho M'),
(4, 3, 3, 'Notebook Acer', 1, 'Melhor que o da Positivo'),
(5, 4, 4, 'Blusa branca', 7, 'Blusa branca tamanho M'),
(6, 5, 3, 'Notebook Acer', 1, 'Melhor que o da Positivo'),
(7, 6, 4, 'Blusa branca', 7, 'Blusa branca tamanho M'),
(8, 7, 3, 'Notebook Acer', 1, 'Melhor que o da Positivo');
