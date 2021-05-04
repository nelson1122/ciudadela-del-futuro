--
-- PostgreSQL database dump
--

-- Dumped from database version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.6 (Ubuntu 12.6-0ubuntu0.20.04.1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: compra_material; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compra_material (
    id integer NOT NULL,
    fecha_ingreso timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    cantidad_ingresada double precision,
    id_material integer NOT NULL
);


ALTER TABLE public.compra_material OWNER TO postgres;

--
-- Name: compra_material_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compra_material_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.compra_material_id_seq OWNER TO postgres;

--
-- Name: compra_material_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compra_material_id_seq OWNED BY public.compra_material.id;


--
-- Name: tipo_construccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_construccion (
    id integer NOT NULL,
    nombre character varying(50),
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    cemento double precision DEFAULT 0,
    grava double precision DEFAULT 0,
    arena double precision DEFAULT 0,
    madera double precision DEFAULT 0,
    adobe double precision DEFAULT 0,
    dias integer
);


ALTER TABLE public.tipo_construccion OWNER TO postgres;

--
-- Name: construccion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.construccion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.construccion_id_seq OWNER TO postgres;

--
-- Name: construccion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.construccion_id_seq OWNED BY public.tipo_construccion.id;


--
-- Name: estado_orden_construccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estado_orden_construccion (
    id integer NOT NULL,
    nombre character varying(100)
);


ALTER TABLE public.estado_orden_construccion OWNER TO postgres;

--
-- Name: estado_orden_construccion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.estado_orden_construccion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.estado_orden_construccion_id_seq OWNER TO postgres;

--
-- Name: estado_orden_construccion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.estado_orden_construccion_id_seq OWNED BY public.estado_orden_construccion.id;


--
-- Name: material; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.material (
    id integer NOT NULL,
    nombre character varying(50) NOT NULL,
    cantidad_disponible double precision DEFAULT 0,
    fecha_registro timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.material OWNER TO postgres;

--
-- Name: material_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.material_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.material_id_seq OWNER TO postgres;

--
-- Name: material_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.material_id_seq OWNED BY public.material.id;


--
-- Name: orden_construccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orden_construccion (
    id integer NOT NULL,
    fecha_estado timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    id_solicitud integer NOT NULL,
    id_estado integer NOT NULL,
    motivo character varying(200)
);


ALTER TABLE public.orden_construccion OWNER TO postgres;

--
-- Name: COLUMN orden_construccion.fecha_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.orden_construccion.fecha_estado IS 'Fecha en que se registra el estado de la solicitud';


--
-- Name: COLUMN orden_construccion.motivo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.orden_construccion.motivo IS 'Motivo asociado al estado asociado';


--
-- Name: orden_construccion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orden_construccion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orden_construccion_id_seq OWNER TO postgres;

--
-- Name: orden_construccion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orden_construccion_id_seq OWNED BY public.orden_construccion.id;


--
-- Name: solicitud; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.solicitud (
    id integer NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    fecha_fin timestamp without time zone NOT NULL,
    x double precision DEFAULT 0,
    y double precision DEFAULT 0,
    id_tipo_construccion integer NOT NULL
);


ALTER TABLE public.solicitud OWNER TO postgres;

--
-- Name: solicitud_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.solicitud_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.solicitud_id_seq OWNER TO postgres;

--
-- Name: solicitud_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.solicitud_id_seq OWNED BY public.solicitud.id;


--
-- Name: compra_material id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra_material ALTER COLUMN id SET DEFAULT nextval('public.compra_material_id_seq'::regclass);


--
-- Name: estado_orden_construccion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_orden_construccion ALTER COLUMN id SET DEFAULT nextval('public.estado_orden_construccion_id_seq'::regclass);


--
-- Name: material id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.material ALTER COLUMN id SET DEFAULT nextval('public.material_id_seq'::regclass);


--
-- Name: orden_construccion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_construccion ALTER COLUMN id SET DEFAULT nextval('public.orden_construccion_id_seq'::regclass);


--
-- Name: solicitud id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud ALTER COLUMN id SET DEFAULT nextval('public.solicitud_id_seq'::regclass);


--
-- Name: tipo_construccion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_construccion ALTER COLUMN id SET DEFAULT nextval('public.construccion_id_seq'::regclass);


--
-- Data for Name: compra_material; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.compra_material (id, fecha_ingreso, cantidad_ingresada, id_material) FROM stdin;
3	2021-05-03 07:30:11.347	20000	1
4	2021-05-03 07:30:54.338	20000	1
5	2021-05-03 07:31:51.3	20000	1
7	2021-05-03 07:46:12.933	20000	1
8	2021-05-03 07:46:18.429	20000	1
9	2021-05-03 07:46:19.745	20000	1
10	2021-05-03 07:46:21.57	20000	1
11	2021-05-03 07:46:22.808	20000	1
12	2021-05-03 07:46:27.8	20000	1
13	2021-05-03 07:46:58.814	20000	2
14	2021-05-03 07:47:00.312	20000	2
15	2021-05-03 07:48:25.911	20000	2
16	2021-05-03 07:48:50.871	20000	2
17	2021-05-03 07:49:38.908	20000	3
18	2021-05-03 07:49:39.599	20000	3
19	2021-05-03 07:49:40.341	20000	3
20	2021-05-03 07:49:40.848	20000	3
21	2021-05-03 07:49:41.591	20000	3
22	2021-05-03 07:49:42.277	20000	3
23	2021-05-03 07:50:04.625	20000	4
24	2021-05-03 07:50:04.946	20000	4
25	2021-05-03 07:50:05.445	20000	4
26	2021-05-03 07:50:05.756	20000	4
27	2021-05-03 07:50:07.725	20000	4
28	2021-05-03 07:50:08.494	20000	4
29	2021-05-03 07:50:09.291	20000	4
30	2021-05-03 07:50:09.863	20000	4
31	2021-05-03 07:50:10.162	20000	4
32	2021-05-03 07:50:16.557	20000	5
33	2021-05-03 07:50:17.31	20000	5
34	2021-05-03 07:50:17.856	20000	5
35	2021-05-03 07:50:19.941	20000	5
36	2021-05-03 07:50:20.798	20000	5
37	2021-05-03 07:50:21.551	20000	5
38	2021-05-03 07:50:23.952	20000	5
39	2021-05-03 07:50:24.699	20000	5
40	2021-05-03 07:50:25.818	20000	5
41	2021-05-03 07:54:03.448	20000	5
\.


--
-- Data for Name: estado_orden_construccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estado_orden_construccion (id, nombre) FROM stdin;
3	PROGRAMADA
4	EN PROGRESO
5	FINALIZADA
2	RECHAZADA
1	PENDIENTE
\.


--
-- Data for Name: material; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.material (id, nombre, cantidad_disponible, fecha_registro) FROM stdin;
1	CEMENTO	120000	2021-05-03 10:54:13.826106
2	GRAVA	80000	2021-05-03 10:54:13.83967
3	ARENA	120000	2021-05-03 10:54:13.853814
4	MADERA	180000	2021-05-03 10:54:13.865899
5	ADOBE	200000	2021-05-03 10:54:13.879065
\.


--
-- Data for Name: orden_construccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orden_construccion (id, fecha_estado, id_solicitud, id_estado, motivo) FROM stdin;
\.


--
-- Data for Name: solicitud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.solicitud (id, fecha_inicio, fecha_fin, x, y, id_tipo_construccion) FROM stdin;
\.


--
-- Data for Name: tipo_construccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_construccion (id, nombre, fecha_registro, cemento, grava, arena, madera, adobe, dias) FROM stdin;
1	CASA	2021-05-03 10:54:22.500887	100	50	90	20	100	3
2	LAGO	2021-05-03 10:54:22.51356	50	60	80	10	20	2
3	CANCHA	2021-05-03 10:54:22.529038	20	20	20	20	20	1
4	EDIFICIO	2021-05-03 10:54:22.542641	200	100	180	40	200	3
5	GIMNASIO	2021-05-03 10:54:22.556247	50	25	45	10	50	3
\.


--
-- Name: compra_material_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compra_material_id_seq', 41, true);


--
-- Name: construccion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.construccion_id_seq', 5, true);


--
-- Name: estado_orden_construccion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.estado_orden_construccion_id_seq', 5, true);


--
-- Name: material_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.material_id_seq', 5, true);


--
-- Name: orden_construccion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orden_construccion_id_seq', 1, false);


--
-- Name: solicitud_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.solicitud_id_seq', 1, false);


--
-- Name: compra_material compra_material_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra_material
    ADD CONSTRAINT compra_material_pkey PRIMARY KEY (id);


--
-- Name: tipo_construccion construccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_construccion
    ADD CONSTRAINT construccion_pkey PRIMARY KEY (id);


--
-- Name: estado_orden_construccion estado_orden_construccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estado_orden_construccion
    ADD CONSTRAINT estado_orden_construccion_pkey PRIMARY KEY (id);


--
-- Name: material material_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.material
    ADD CONSTRAINT material_pkey PRIMARY KEY (id);


--
-- Name: orden_construccion orden_construccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_construccion
    ADD CONSTRAINT orden_construccion_pkey PRIMARY KEY (id);


--
-- Name: solicitud solicitud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_pkey PRIMARY KEY (id);


--
-- Name: compra_material compra_material_id_material_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra_material
    ADD CONSTRAINT compra_material_id_material_fkey FOREIGN KEY (id_material) REFERENCES public.material(id);


--
-- Name: orden_construccion orden_construccion_id_estado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_construccion
    ADD CONSTRAINT orden_construccion_id_estado_fkey FOREIGN KEY (id_estado) REFERENCES public.estado_orden_construccion(id);


--
-- Name: orden_construccion orden_construccion_id_solicitud_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orden_construccion
    ADD CONSTRAINT orden_construccion_id_solicitud_fkey FOREIGN KEY (id_solicitud) REFERENCES public.solicitud(id);


--
-- Name: solicitud solicitud_id_construccion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.solicitud
    ADD CONSTRAINT solicitud_id_construccion_fkey FOREIGN KEY (id_tipo_construccion) REFERENCES public.tipo_construccion(id);


--
-- PostgreSQL database dump complete
--

