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
-- Name: category; Type: TABLE; Schema: public; Owner: medmanager
--

CREATE TABLE category (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE category OWNER TO medmanager;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: medmanager
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO medmanager;

--
-- Name: invoice; Type: TABLE; Schema: public; Owner: medmanager
--

CREATE TABLE invoice (
    id integer NOT NULL,
    amount double precision,
    date character varying(255) NOT NULL,
    patient_name character varying(255) NOT NULL
);


ALTER TABLE invoice OWNER TO medmanager;

--
-- Name: invoice_medicine; Type: TABLE; Schema: public; Owner: medmanager
--

CREATE TABLE invoice_medicine (
    invoice_id integer NOT NULL,
    medicine_id integer NOT NULL
);


ALTER TABLE invoice_medicine OWNER TO medmanager;

--
-- Name: medicine; Type: TABLE; Schema: public; Owner: medmanager
--

CREATE TABLE medicine (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    price double precision NOT NULL,
    category_id integer
);


ALTER TABLE medicine OWNER TO medmanager;

--
-- Name: role; Type: TABLE; Schema: public; Owner: medmanager
--

CREATE TABLE role (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE role OWNER TO medmanager;

--
-- Name: users; Type: TABLE; Schema: public; Owner: medmanager
--

CREATE TABLE users (
    id integer NOT NULL,
    confirm_password character varying(255),
    password character varying(255),
    username character varying(255),
    role_id integer
);


ALTER TABLE users OWNER TO medmanager;

--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: medmanager
--

COPY category (id, name) FROM stdin;
2	Tablet
4	TestCat
12	cat1
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: medmanager
--

SELECT pg_catalog.setval('hibernate_sequence', 12, true);


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: medmanager
--

COPY invoice (id, amount, date, patient_name) FROM stdin;
6	\N	2018-06-24	Hiren
8	\N	2018-09-30	Haresh Patel
9	\N	2018-11-13	Haresh Patel
11	\N	2019-02-23	Test
\.


--
-- Data for Name: invoice_medicine; Type: TABLE DATA; Schema: public; Owner: medmanager
--

COPY invoice_medicine (invoice_id, medicine_id) FROM stdin;
6	5
6	7
8	3
9	3
11	3
\.


--
-- Data for Name: medicine; Type: TABLE DATA; Schema: public; Owner: medmanager
--

COPY medicine (id, name, price, category_id) FROM stdin;
3	tab1	123	2
5	tab1	123	2
7	cat1	1	4
10	tab2	123	2
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: medmanager
--

COPY role (id, name) FROM stdin;
1	Admin
2	Employee
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: medmanager
--

COPY users (id, confirm_password, password, username, role_id) FROM stdin;
1	12345678	$2a$10$2ib9iq562QyEPHyRrN3KQecQDugpjocQtM./1ZiYxPthMMrEo0UPu	hiren.vaghasiya	1
\.


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: invoice_medicine invoice_medicine_pkey; Type: CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY invoice_medicine
    ADD CONSTRAINT invoice_medicine_pkey PRIMARY KEY (invoice_id, medicine_id);


--
-- Name: invoice invoice_pkey; Type: CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY invoice
    ADD CONSTRAINT invoice_pkey PRIMARY KEY (id);


--
-- Name: medicine medicine_pkey; Type: CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY medicine
    ADD CONSTRAINT medicine_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users fk4qu1gr772nnf6ve5af002rwya; Type: FK CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fk4qu1gr772nnf6ve5af002rwya FOREIGN KEY (role_id) REFERENCES role(id);


--
-- Name: medicine fk5gtqvwcdroncgiftlhwbs3dog; Type: FK CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY medicine
    ADD CONSTRAINT fk5gtqvwcdroncgiftlhwbs3dog FOREIGN KEY (category_id) REFERENCES category(id);


--
-- Name: invoice_medicine fk61tbi5fnw5ryqtkekjkdjrh5b; Type: FK CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY invoice_medicine
    ADD CONSTRAINT fk61tbi5fnw5ryqtkekjkdjrh5b FOREIGN KEY (medicine_id) REFERENCES medicine(id);


--
-- Name: invoice_medicine fkscutlxw6fafwemp8og7iciwps; Type: FK CONSTRAINT; Schema: public; Owner: medmanager
--

ALTER TABLE ONLY invoice_medicine
    ADD CONSTRAINT fkscutlxw6fafwemp8og7iciwps FOREIGN KEY (invoice_id) REFERENCES invoice(id);


--
-- PostgreSQL database dump complete
--

