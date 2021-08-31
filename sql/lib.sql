--
-- PostgreSQL database dump
--

-- Dumped from database version 10.18
-- Dumped by pg_dump version 10.18

-- Started on 2021-08-31 16:34:44

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
-- TOC entry 2834 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16396)
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
    author_id integer NOT NULL,
    first_name character varying(40) NOT NULL,
    last_name character varying(40) NOT NULL,
    patronymic character varying(40),
    biography text,
    "year_of_birth " integer
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16394)
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
-- TOC entry 2835 (class 0 OID 0)
-- Dependencies: 196
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.author_id_seq OWNED BY public.authors.author_id;


--
-- TOC entry 199 (class 1259 OID 16407)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    book_id integer NOT NULL,
    title character varying(40) NOT NULL,
    year_of_publishing integer,
    genre integer NOT NULL
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16417)
-- Name: books_author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books_author (
    book_id integer,
    author_id integer
);


ALTER TABLE public.books_author OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16405)
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
-- TOC entry 2836 (class 0 OID 0)
-- Dependencies: 198
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.books_id_seq OWNED BY public.books.book_id;


--
-- TOC entry 201 (class 1259 OID 16448)
-- Name: genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genres (
    genre_id integer,
    title character varying(50) DEFAULT NULL::character varying NOT NULL,
    description text NOT NULL
);


ALTER TABLE public.genres OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16465)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id integer NOT NULL,
    first_name character varying(40) NOT NULL,
    last_name character varying(40) NOT NULL,
    patronymic character varying(40),
    year_of_birth integer NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16463)
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
-- TOC entry 2837 (class 0 OID 0)
-- Dependencies: 202
-- Name: user_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;


--
-- TOC entry 2692 (class 2604 OID 16399)
-- Name: authors author_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors ALTER COLUMN author_id SET DEFAULT nextval('public.author_id_seq'::regclass);


--
-- TOC entry 2693 (class 2604 OID 16410)
-- Name: books book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_id_seq'::regclass);


--
-- TOC entry 2695 (class 2604 OID 16468)
-- Name: user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);


--
-- TOC entry 2697 (class 2606 OID 16401)
-- Name: authors author_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT author_pk PRIMARY KEY (author_id);


--
-- TOC entry 2700 (class 2606 OID 16412)
-- Name: books books_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pk PRIMARY KEY (book_id);


--
-- TOC entry 2702 (class 2606 OID 16470)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (user_id);


--
-- TOC entry 2698 (class 1259 OID 16447)
-- Name: books_genre_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX books_genre_uindex ON public.books USING btree (genre);


--
-- TOC entry 2703 (class 2606 OID 16430)
-- Name: books_author author_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_author
    ADD CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES public.authors(author_id) ON DELETE CASCADE;


--
-- TOC entry 2704 (class 2606 OID 16425)
-- Name: books_author book_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books_author
    ADD CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES public.books(book_id) ON DELETE CASCADE;


--
-- TOC entry 2705 (class 2606 OID 16451)
-- Name: genres genre_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genre_id FOREIGN KEY (genre_id) REFERENCES public.books(genre);


-- Completed on 2021-08-31 16:34:45

--
-- PostgreSQL database dump complete
--

