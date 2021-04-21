DROP SEQUENCE IF EXISTS public.author_sequence;
DROP SEQUENCE IF EXISTS public.book_sequence;
DROP SEQUENCE IF EXISTS public.invoice_sequence;
DROP SEQUENCE IF EXISTS public.order_sequence;
DROP TABLE IF EXISTS public.book_author;
DROP TABLE IF EXISTS public.book;
DROP TABLE IF EXISTS public.invoice;
DROP TABLE IF EXISTS public.orders;
DROP TABLE IF EXISTS public.author;

CREATE SEQUENCE public.author_sequence
    INCREMENT 3
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.author_sequence
    OWNER TO postgres;
	
CREATE SEQUENCE public.book_sequence
    INCREMENT 3
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.book_sequence
    OWNER TO postgres;
	
CREATE SEQUENCE public.invoice_sequence
    INCREMENT 3
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.invoice_sequence
    OWNER TO postgres;
	
CREATE SEQUENCE public.order_sequence
    INCREMENT 3
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.order_sequence
    OWNER TO postgres;
	
CREATE TABLE public.orders
(
    id integer NOT NULL,
    order_date timestamp with time zone NOT NULL,
    completed boolean NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.orders
    OWNER to postgres;

CREATE TABLE public.book
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default",
    order_id integer,
    CONSTRAINT book_pkey PRIMARY KEY (id),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

CREATE TABLE public.author
(
    id integer NOT NULL,
    name character varying COLLATE pg_catalog."default",
    CONSTRAINT author_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.author
    OWNER to postgres;

ALTER TABLE public.book
    OWNER to postgres;

CREATE TABLE public.invoice
(
    id integer NOT NULL,
    price real,
    order_id integer,
    CONSTRAINT invoice_pkey PRIMARY KEY (id),
    CONSTRAINT order_id_fk FOREIGN KEY (order_id)
        REFERENCES public.orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.invoice
    OWNER to postgres;


CREATE TABLE public.book_author
(
    book_id integer,
    author_id integer,
    CONSTRAINT author_fk FOREIGN KEY (author_id)
        REFERENCES public.author (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT book_fk FOREIGN KEY (book_id)
        REFERENCES public.book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.book_author
    OWNER to postgres;

select nextval ('author_sequence');
insert into author(id,name) values(1,'Author1');
insert into author(id,name) values(2,'Author2');
insert into author(id,name) values(3,'Author3');