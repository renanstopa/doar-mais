insert into tipo_usuario values
(1, 'Pessoa'),
(2, 'ONG'),
(3, 'Administrador');

insert into tipo_denuncia values
(1, 'Melhoria'),
(2, 'Bug'),
(3, 'Denunciar usuário');

insert into situacao values
(1, 'Token não utilizado'),
(2, 'Token expirado'),
(3, 'Token utilizado'),
(11, 'Conta sem email verificado'),
(12, 'Conta sem aprovação do admnistrador'),
(13, 'Conta apta para uso'),
(14, 'Conta suspensa'),
(15, 'Conta encerrada'),
(21, 'Anúncio ativo'),
(22, 'Anúncio sem itens'),
(23, 'Anúncio cancelado'),
(24, 'Anúncio finalizado'),
(31, 'Proposta enviada'),
(32, 'Proposta confirmada'),
(33, 'Proposta recusada'),
(34, 'Proposta cancelada'),
(35, 'Encontro realizado'),
(36, 'Encontro não realizado'),
(41, 'Denúncia criada'),
(42, 'Denúncia gerenciada');

insert into tipo_anuncio values
(1, 'Doação'),
(2, 'Pedido'),
(3, 'Doações rápida');

insert into categoria_item values
(1, 'Alimento'),
(2, 'Eletrônico'),
(3, 'Eletrodoméstico'),
(4, 'Roupa'),
(5, 'Móvel'),
(6, 'Entretenimento'),
(7, 'Pet'),
(8, 'Outro');