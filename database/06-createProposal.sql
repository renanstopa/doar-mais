set time_zone='America/Sao_Paulo';

-- Propostas em espera
insert into proposta values
(1, 4, 2, 31, date_add(now(),interval 1 day)),
(2, 5, 3, 31, date_add(now(),interval 1 day)),
(3, 6, 8, 31, date_add(now(),interval 1 day));

insert into item_anuncio_proposta values
(1, 1, 3, 4),
(2, 2, 4, 1),
(3, 3, 12, 1);

-- Propostas agendadas
insert into proposta values
(4, 3, 4, 32, date_add(now(),interval 1 day)),
(5, 4, 1, 32, date_add(now(),interval 1 day));

insert into item_anuncio_proposta values
(4, 4, 5, 1),
(5, 4, 6, 1),
(6, 5, 1, 2),
(7, 5, 2, 3);

-- Propostas histórico
insert into proposta values
(6, 4, 1, 36, now()),
(7, 5, 2, 34, now()),
(8, 6, 3, 33, now());

insert into item_anuncio_proposta values
(8, 6, 1, 1),
(9, 6, 2, 2),
(10, 7, 2, 2),
(11, 8, 4, 1);