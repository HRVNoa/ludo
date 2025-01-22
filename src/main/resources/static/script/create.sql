CREATE TABLE public.clients (
    no_client integer NOT NULL,
    nom character varying(50) NOT NULL,
    prenom character varying(30) NOT NULL,
    email character varying(100) NOT NULL,
    no_tel character varying(10),
    rue character varying(50) NOT NULL,
    code_postal character varying(5) NOT NULL,
    ville character varying(30) NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

ALTER TABLE public.clients ALTER COLUMN no_client ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.client_no_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.exemplaires (
    no_exemplaire integer NOT NULL,
    codebarre character varying(13) NOT NULL,
    louable boolean NOT NULL,
    no_jeu integer NOT NULL
);


ALTER TABLE public.exemplaires OWNER TO postgres;


ALTER TABLE public.exemplaires ALTER COLUMN no_exemplaire ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.exemplaire_no_exemplaire_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


CREATE TABLE public.genres (
    no_genre integer NOT NULL,
    libelle character varying(30) NOT NULL
);


ALTER TABLE public.genres OWNER TO postgres;


ALTER TABLE public.genres ALTER COLUMN no_genre ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.genre_no_genre_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.jeux (
    no_jeu integer NOT NULL,
    titre character varying(30) NOT NULL,
    reference character varying(13) NOT NULL,
    description character varying(200),
    tarif_journee integer NOT NULL,
    age_min integer NOT NULL,
    duree integer
);


ALTER TABLE public.jeux OWNER TO postgres;

ALTER TABLE public.jeux ALTER COLUMN no_jeu ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.jeu_no_jeu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.jeux_genres (
    no_jeu integer NOT NULL,
    no_genre integer NOT NULL
);

ALTER TABLE public.jeux_genres OWNER TO postgres;

CREATE TABLE public.users (
    no_user integer NOT NULL,
    username character varying(30) NOT NULL,
    password character varying(200) NOT NULL,
    roles character varying(200)
);


ALTER TABLE public.users OWNER TO postgres;

ALTER TABLE public.users ALTER COLUMN no_user ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_no_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

SELECT pg_catalog.setval('public.client_no_client_seq', 1, true);

SELECT pg_catalog.setval('public.exemplaire_no_exemplaire_seq', 1, true);

SELECT pg_catalog.setval('public.genre_no_genre_seq', 1, true);

SELECT pg_catalog.setval('public.jeu_no_jeu_seq', 1, true);

SELECT pg_catalog.setval('public.users_no_user_seq', 1, true);

ALTER TABLE ONLY public.jeux_genres
    ADD CONSTRAINT "PK_jeu_genre" PRIMARY KEY (no_jeu, no_genre);

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT client_pkey PRIMARY KEY (no_client);

ALTER TABLE ONLY public.exemplaires
    ADD CONSTRAINT exemplaire_pkey PRIMARY KEY (no_exemplaire);

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genre_pkey PRIMARY KEY (no_genre);

ALTER TABLE ONLY public.jeux
    ADD CONSTRAINT jeu_pkey PRIMARY KEY (no_jeu);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (no_user);

ALTER TABLE ONLY public.jeux_genres
    ADD CONSTRAINT "FK_genre" FOREIGN KEY (no_genre) REFERENCES public.genres(no_genre);

ALTER TABLE ONLY public.jeux_genres
    ADD CONSTRAINT "FK_jeu" FOREIGN KEY (no_jeu) REFERENCES public.jeux(no_jeu) ON DELETE CASCADE NOT VALID;

ALTER TABLE ONLY public.exemplaires
    ADD CONSTRAINT "FK_jeu" FOREIGN KEY (no_jeu) REFERENCES public.jeux(no_jeu) NOT VALID;
