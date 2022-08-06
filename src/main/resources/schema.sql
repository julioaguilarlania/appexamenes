CREATE TABLE alumno (
    id_alumno integer DEFAULT nextval('alumno_id_alumno_seq') NOT NULL,
    nombres character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    id_experiencia_educativa integer,
    id_usuario integer NOT NULL
);


CREATE SEQUENCE alumno_id_alumno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE examen (
    id_examen integer DEFAULT nextval('examen_id_examen_seq') NOT NULL,
    nombre character varying(100) NOT NULL,
    id_experiencia_educativa integer NOT NULL,
    momento_apertura timestamp without time zone,
    momento_cierre timestamp without time zone,
    cerrado boolean DEFAULT false NOT NULL
);


CREATE TABLE examen_alumno (
    id_examen_alumno integer DEFAULT nextval('examen_alumno_id_examen_alumno_seq') NOT NULL,
    id_alumno integer NOT NULL,
    id_examen integer NOT NULL,
    momento_inicio timestamp without time zone,
    momento_fin timestamp without time zone,
    calificacion numeric(4,2)
);


CREATE SEQUENCE examen_alumno_id_examen_alumno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE examen_id_examen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE examen_reactivo (
    id_examen_reactivo integer DEFAULT nextval('examen_reactivo_id_examen_reactivo_seq') NOT NULL,
    id_examen integer NOT NULL,
    id_reactivo integer NOT NULL,
    posicion integer
);


CREATE SEQUENCE examen_reactivo_id_examen_reactivo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE experiencia_educativa (
    id_experiencia_educativa integer DEFAULT nextval('experiencia_educativa_id_experiencia_educativa_seq') NOT NULL,
    nombre character varying(200) NOT NULL,
    nrc integer NOT NULL,
    id_maestro integer
);


CREATE SEQUENCE experiencia_educativa_id_experiencia_educativa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE maestro (
    id_maestro integer DEFAULT nextval('maestro_id_maestro_seq') NOT NULL,
    nombre_completo character varying(100) NOT NULL,
    id_usuario integer NOT NULL
);


CREATE SEQUENCE maestro_id_maestro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE reactivo (
    id_reactivo integer DEFAULT nextval('reactivo_id_reactivo_seq') NOT NULL,
    id_experiencia_educativa integer,
    texto character varying(200) NOT NULL,
    opcion_1 character varying(100) NOT NULL,
    opcion_2 character varying(100) NOT NULL,
    opcion_3 character varying(100),
    opcion_4 character varying(100),
    opcion_correcta integer NOT NULL
);


CREATE SEQUENCE reactivo_id_reactivo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE respuesta_reactivo (
    id_respuesta_reactivo integer DEFAULT nextval('respuesta_reactivo_id_respuesta_reactivo_seq') NOT NULL,
    id_examen_alumno integer NOT NULL,
    opcion integer NOT NULL,
    correcta boolean,
    id_reactivo integer
);


CREATE SEQUENCE respuesta_reactivo_id_examen_alumno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE respuesta_reactivo_id_respuesta_reactivo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE rol (
    id_rol integer DEFAULT nextval('rol_id_rol_seq') NOT NULL,
    nombre character varying(50) NOT NULL
);


CREATE SEQUENCE rol_id_rol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE usuario (
    id_usuario integer DEFAULT nextval('usuario_id_usuario_seq') NOT NULL,
    login character varying NOT NULL,
    password character varying(255),
    id_rol integer NOT NULL,
    activo boolean NOT NULL
);


CREATE SEQUENCE usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



