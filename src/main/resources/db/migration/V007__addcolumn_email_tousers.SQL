ALTER TABLE IF EXISTS public.users
    ADD COLUMN email character varying(25) UNIQUE;