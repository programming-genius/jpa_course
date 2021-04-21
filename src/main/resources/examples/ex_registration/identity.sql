DROP TABLE IF EXISTS public.registration;
DROP SEQUENCE IF EXISTS public.registration_id_seq;
DROP SEQUENCE IF EXISTS public.registration_sequence;

CREATE SEQUENCE public.registration_id_seq
    INCREMENT 3
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
CREATE TABLE public.registration
(
    id integer NOT NULL DEFAULT nextval('registration_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default",
    lastname character varying COLLATE pg_catalog."default",
    CONSTRAINT registration_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.registration
    OWNER to postgres;