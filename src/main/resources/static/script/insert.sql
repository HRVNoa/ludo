INSERT INTO public.clients OVERRIDING SYSTEM VALUE VALUES (6, 'MARGERITTE', 'Allan', 'allan@gmail.com', '0123456789', '5 rue de la chose', '50000', 'Saint-lô');
INSERT INTO public.clients OVERRIDING SYSTEM VALUE VALUES (4, 'HERVIEU', 'Noä', 'noa.hervieu2024@campus-eni.fr', '0123456789', '6 rue Mathurin Brissoneau', '44100', 'Nantes');
INSERT INTO public.clients OVERRIDING SYSTEM VALUE VALUES (9, 'Etchebest', 'Philippe', 'Etchebest@gmail.com', '', '8 route de la kitchen', '33000', 'Bordeaux');
INSERT INTO public.clients OVERRIDING SYSTEM VALUE VALUES (5, 'BASTOS', 'André', 'bastos@campus-eni.fr', '0123456789', '58 bis rue Aristide Briand', '44400', 'Rezé');

INSERT INTO public.genres OVERRIDING SYSTEM VALUE VALUES (1, 'Action');
INSERT INTO public.genres OVERRIDING SYSTEM VALUE VALUES (2, 'Carte');
INSERT INTO public.genres OVERRIDING SYSTEM VALUE VALUES (3, 'Ambiance');
INSERT INTO public.genres OVERRIDING SYSTEM VALUE VALUES (4, 'Stratégie');
INSERT INTO public.genres OVERRIDING SYSTEM VALUE VALUES (5, 'Puzzle');

INSERT INTO public.jeux OVERRIDING SYSTEM VALUE VALUES (5, 'Dekal', '458632971235', 'Jeu d''ambiance - Jeu de société à la mécanique du taquin revisitée - Un Jeu addictif et pour Tous Niveaux!', 2, 6, 15);
INSERT INTO public.jeux OVERRIDING SYSTEM VALUE VALUES (15, 'Trio', '46542', 'Dans le jeu de cartes Trio, dévoilez 2 cartes Numéro soit sur la table, soit dans la main d’un joueur (les cartes y sont classées du plus petit au plus grand)', 2, 6, 10);
INSERT INTO public.jeux OVERRIDING SYSTEM VALUE VALUES (6, 'Traitres à Bord', '458632971235', 'Traitres à Bord est un jeu d''ambiance à identité secrète qui allie bluff, stratégie, alliances et trahisons. Parfait pour passer vos meilleurs soirées entre amis ou en famille !', 2, 10, 10);

INSERT INTO public.exemplaires OVERRIDING SYSTEM VALUE VALUES (5, '1564', true, 6);
INSERT INTO public.exemplaires OVERRIDING SYSTEM VALUE VALUES (10, '123456789564', true, 6);
INSERT INTO public.exemplaires OVERRIDING SYSTEM VALUE VALUES (19, '1234567892547', false, 15);
INSERT INTO public.exemplaires OVERRIDING SYSTEM VALUE VALUES (17, '1564984984', false, 5);
INSERT INTO public.exemplaires OVERRIDING SYSTEM VALUE VALUES (11, '156447984156', true, 5);

INSERT INTO public.jeux_genres VALUES (15, 2);
INSERT INTO public.jeux_genres VALUES (6, 2);
INSERT INTO public.jeux_genres VALUES (5, 1);
INSERT INTO public.jeux_genres VALUES (5, 2);
INSERT INTO public.jeux_genres VALUES (5, 3);
INSERT INTO public.jeux_genres VALUES (5, 4);

INSERT INTO public.users OVERRIDING SYSTEM VALUE VALUES (1, 'noix', '$2a$10$SnQG2DeZmB6n5vE1RSKsx.d4ubLfVo6PcdccJzpC8J6W7Rg9QOgAG', '["EMPLOYER"]');
