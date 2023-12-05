CREATE TABLE public.users
(
    id serial not null primary key,
    name text,
    url text,
    pwd text,
    create_at date NOT NULL DEFAULT NOW()
);

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;