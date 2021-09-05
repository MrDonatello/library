--
-- PostgreSQL database dump
--

-- Dumped from database version 10.18
-- Dumped by pg_dump version 10.18

-- Started on 2021-09-05 22:25:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE librarydata;
--
-- TOC entry 2845 (class 1262 OID 16527)
-- Name: librarydata; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE librarydata WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE librarydata OWNER TO postgres;

\connect librarydata

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2848 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16589)
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
    author_id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    patronymic character varying(50),
    biography text,
    year_of_birth integer NOT NULL
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16595)
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.author_id_seq OWNER TO postgres;

--
-- TOC entry 2849 (class 0 OID 0)
-- Dependencies: 197
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.author_id_seq OWNED BY public.authors.author_id;


--
-- TOC entry 198 (class 1259 OID 16597)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    book_id integer NOT NULL,
    title character varying(50) NOT NULL,
    year_of_publishing integer NOT NULL,
    genre character varying(50),
    isbn character varying(17) NOT NULL
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16600)
-- Name: books_authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books_authors (
    book_id integer NOT NULL,
    author_id integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.books_authors OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16702)
-- Name: books_authors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.books_authors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.books_authors_id_seq OWNER TO postgres;

--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 205
-- Name: books_authors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.books_authors_id_seq OWNED BY public.books_authors.id;


--
-- TOC entry 200 (class 1259 OID 16603)
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.books_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.books_id_seq OWNER TO postgres;

--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 200
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.books_id_seq OWNED BY public.books.book_id;


--
-- TOC entry 201 (class 1259 OID 16605)
-- Name: genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genres (
    genre_id integer NOT NULL,
    title character varying(50) DEFAULT NULL::character varying NOT NULL,
    description text
);


ALTER TABLE public.genres OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16612)
-- Name: genres_genre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.genres_genre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genres_genre_id_seq OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 202
-- Name: genres_genre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.genres_genre_id_seq OWNED BY public.genres.genre_id;


--
-- TOC entry 203 (class 1259 OID 16614)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    patronymic character varying(50),
    year_of_birth integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16617)
-- Name: user_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_user_id_seq OWNER TO postgres;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 204
-- Name: user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 2696 (class 2604 OID 16619)
-- Name: authors author_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors ALTER COLUMN author_id SET DEFAULT nextval('public.author_id_seq'::regclass);


--
-- TOC entry 2697 (class 2604 OID 16620)
-- Name: books book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_id_seq'::regclass);


--
-- TOC entry 2698 (class 2604 OID 16704)
-- Name: books_authors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_authors ALTER COLUMN id SET DEFAULT nextval('public.books_authors_id_seq'::regclass);


--
-- TOC entry 2700 (class 2604 OID 16621)
-- Name: genres genre_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genres ALTER COLUMN genre_id SET DEFAULT nextval('public.genres_genre_id_seq'::regclass);


--
-- TOC entry 2701 (class 2604 OID 16622)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);


--
-- TOC entry 2703 (class 2606 OID 16624)
-- Name: authors author_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT author_pk PRIMARY KEY (author_id);


--
-- TOC entry 2710 (class 2606 OID 16710)
-- Name: books_authors books_authors_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_authors
    ADD CONSTRAINT books_authors_pk PRIMARY KEY (id);


--
-- TOC entry 2707 (class 2606 OID 16628)
-- Name: books books_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pk PRIMARY KEY (book_id);


--
-- TOC entry 2712 (class 2606 OID 16630)
-- Name: genres genres_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_pk PRIMARY KEY (genre_id);


--
-- TOC entry 2715 (class 2606 OID 16632)
-- Name: users user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT user_pk PRIMARY KEY (user_id);


--
-- TOC entry 2704 (class 1259 OID 16689)
-- Name: authors_first_name_last_name_year_of_birth_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX authors_first_name_last_name_year_of_birth_uindex ON public.authors USING btree (first_name, last_name, year_of_birth);


--
-- TOC entry 2708 (class 1259 OID 16714)
-- Name: books_authors_book_id_author_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX books_authors_book_id_author_id_uindex ON public.books_authors USING btree (book_id, author_id);


--
-- TOC entry 2705 (class 1259 OID 16633)
-- Name: books_isbn_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX books_isbn_uindex ON public.books USING btree (isbn);


--
-- TOC entry 2713 (class 1259 OID 16634)
-- Name: genres_title_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX genres_title_uindex ON public.genres USING btree (title);


--
-- TOC entry 2717 (class 2606 OID 16635)
-- Name: books_authors author_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_authors
    ADD CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES public.authors(author_id) ON DELETE CASCADE;


--
-- TOC entry 2718 (class 2606 OID 16640)
-- Name: books_authors book_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_authors
    ADD CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES public.books(book_id) ON DELETE CASCADE;


--
-- TOC entry 2716 (class 2606 OID 16697)
-- Name: books genre; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT genre FOREIGN KEY (genre) REFERENCES public.genres(title) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2847 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2021-09-05 22:25:29

--
-- PostgreSQL database dump complete
--

