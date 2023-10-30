set time_zone='America/Sao_Paulo';

-- Doações
insert into anuncio values
(1, 1, 3, 21, now(), 'Roupa e comida', now(), date_add(now(),interval 1 day), '11533040', 'SP', 'Cubatão', 'Jardim Casqueiro', 'Rua Estados Unidos', 530, '', 'Final da rua do Kalabalis', 2),
(2, 1, 3, 21, now(), 'Roupa', now(), date_add(now(),interval 2 day), '11533040', 'SP', 'Cubatão', 'Jardim Casqueiro', 'Rua Estados Unidos', 530, '', 'Final da rua do Kalabalis', 2),
(3, 1, 3, 21, now(), 'Notebook', now(), date_add(now(),interval 3 day), '11050050', 'SP', 'Santos', 'Boqueirão', 'Rua Alberto Baccarat', 10, '', '', 2),
(4, 1, 4, 21, now(), 'Jogos Xbox One', now(), date_add(now(),interval 10 day), '11025050', 'SP', 'Santos', 'Boqueirão', 'Rua Barão de Cotegipe', 64, 'Casa 2', 'Poste colorido', 1),
(5, 1, 10, 21, now(), 'Ração cachorro', now(), date_add(now(),interval 5 day), '11075390', 'SP', 'Santos', 'Campo Grande', 'Rua Espírito Santo', 54, '', '', 0),
(6, 1, 5, 21, now(), 'Itens pet', now(), date_add(now(),interval 5 day), '11065120', 'SP', 'Santos', 'Gonzaga', 'Rua Alagoas', 1056, '', 'Casa abandonada ao lado', 0);

insert into item_anuncio values
(1, 1, 4, 'Blusa preta', 7, 'Blusa branca tamanho G'),
(2, 1, 1, 'Macarrão', 7, 'Macarrão de 700g'),
(3, 2, 4, 'Blusa branca', 7, 'Blusa branca tamanho M'),
(4, 3, 3, 'Notebook Acer', 1, 'Melhor que o da Positivo'),
(5, 4, 6, 'Mortal Kombat 11', 1, 'Jogo original'),
(6, 4, 6, 'GTA V', 1, 'Jogo pirata'),
(7, 5, 7, 'Golden Special', 3, 'Pacote de 5kg de ração para cachorros de porte médio'),
(8, 6, 7, 'Casinha média', 1, 'Aproximadamente 1 metro de altura e 1,5 metros de largura'),
(9, 6, 7, 'Bolinhas', 14, 'Bolinhas de diversos tipos para seu pet morder, na hora do encontro você pode escolher!'),
(10, 6, 7, 'Roupas', 1, 'Roupas para animais de porte pequeno');

-- Pedidos
insert into anuncio values
(7, 2, 3, 21, now(), 'Roupa', now(), date_add(now(),interval 2 day), '11020000', 'SP', 'Santos', 'Boqueirão', 'Avenida Affonso Penna', 12, 'Segunda casa', '', 0),
(8, 2, 3, 21, now(), 'Notebook', now(), date_add(now(),interval 3 day), '11020000', 'SP', 'Santos', 'Boqueirão', 'Avenida Affonso Penna', 12, 'Segunda casa', '', 1),
(9, 2, 9, 21, now(), 'Cesta básica', now(), date_add(now(),interval 3 day), '20220690', 'RJ', 'Rio de Janeiro', 'Gamboa', 'Rua Barão de Gamboa', 98, 'Com numeração uplementar pela Rua da Gamboa 227', '', 0),
(10, 2, 7, 21, now(), 'Brinquedos', now(), date_add(now(),interval 2 day), '05854020', 'SP', 'São Paulo', 'Parque Maria Helena', 'Rua Analia Dolacio Albino', 333, '', '', 0),
(11, 2, 6, 21, now(), 'Vestidos', now(), date_add(now(),interval 20 day), '11065401', 'SP', 'Santos', 'Gonzaga', 'Avenida General Francisco Glicério', 100, 'Casa dos fundos', '', 0),
(12, 2, 8, 21, now(), 'Periféricos', now(), date_add(now(),interval 5 day), '03350005', 'SP', 'São Paulo', 'Vial Invernada', 'Rua Doutor Gabriel De Resende', 122, '', '', 0);

insert into item_anuncio values
(11, 7, 4, 'Blusa branca', 3, 'Blusa branca tamanho M sem estampa'),
(12, 8, 3, 'Macbook', 1, 'Se possível, um da última geração seria perfeito'),
(13, 9, 1, 'Cesta básica padrão', 20, 'Cestas básicas para realizar doações para quem precisa'),
(14, 10,6, 'Max Steel', 3, 'Se possível, de roupas diferentes'),
(15, 10, 6, 'Barbie', 5, 'Se possível,  de profissões diferentes'),
(16, 10, 6, 'Jogos de tabuleiro', 2, 'Jogos para crianças'),
(17, 11, 4, 'Vestido longo preto', 1, 'Tamanho G'),
(18, 11, 4, 'Vestido curto vermelho', 1, 'Tamanho P'),
(19, 12, 2, 'Mouse', 6, 'Mouse com no máximo 2000 de DPI'),
(20, 12, 2, 'Teclado', 6, 'Teclado mecânico com switch azul'),
(21, 12, 2, 'Headset', 6, 'Headset som 8D'),
(22, 12, 2, 'Monitor', 6, 'Monitor 144Hz'),
(23, 12, 2, 'Webcam', 6, 'Webcam full HD 4K');

-- Doações rápidas
insert into anuncio values
(13, 3, 5, 21, now(), 'Roupa', now(), date_add(now(),interval 2 day), '11533040', 'SP', 'Cubatão', 'Jardim Casqueiro', 'Rua Estados Unidos', 530, '', 'Final da rua do Kalabalis', 0),
(14, 3, 6, 21, now(), 'Notebook', now(), date_add(now(),interval 3 day), '08551040', 'SP', 'Cubatão', 'Centro', 'Praça da Independência', '10', '', '', 0),
(15, 3, 6, 21, now(), 'Guarda roupa', now(), date_add(now(),interval 3 day), '08551040', 'SP', 'Cubatão', 'Centro', 'Praça da Independência', '10', '', '', 0);

insert into item_anuncio values
(24, 13, 4, 'Blusa branca', 7, 'Blusa branca tamanho M'),
(25, 14, 3, 'Notebook Acer', 1, 'Melhor que o da Positivo'),
(26, 15, 5, 'Guarda roupa de madeira de carvalho', 1, '3 divisórias bem espaçosas');
