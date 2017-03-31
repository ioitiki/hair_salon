--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE clients (
    id integer NOT NULL,
    clientname character varying,
    stylistid integer,
    clientdescription text,
    clientage integer,
    clientgender character varying
);


ALTER TABLE clients OWNER TO t1k1;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO t1k1;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: t1k1
--

CREATE TABLE stylists (
    id integer NOT NULL,
    stylistname character varying,
    stylistdescription text
);


ALTER TABLE stylists OWNER TO t1k1;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: t1k1
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO t1k1;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: t1k1
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY clients (id, clientname, stylistid, clientdescription, clientage, clientgender) FROM stdin;
1	Susan Jay	1	the best	22	Female
2	Drew Makin	1	needs extra care around his ears.	35	Male
3	Tom Besti	2	little kid needs to be comforted 	12	Male
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('clients_id_seq', 3, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: t1k1
--

COPY stylists (id, stylistname, stylistdescription) FROM stdin;
1	Barb Smith	great with color and extensions!
2	Jen Akin	all around great!
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: t1k1
--

SELECT pg_catalog.setval('stylists_id_seq', 2, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: t1k1
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

