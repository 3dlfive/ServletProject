CREATE TABLE public.actions
(
    id serial not null primary key,
    user_id int  NOT NULL,
    profile_id int  NOT NULL,
    action text NOT NULL,
    at date NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (profile_id) REFERENCES users(id)
);

ALTER TABLE IF EXISTS public.actions
    OWNER to postgres;