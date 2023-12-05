CREATE TABLE public.messages
(
    m_id serial not null primary key,
    body character varying(500) NOT NULL,
    sender_id integer NOT NULL,
    receiver_id integer NOT NULL,
    created_at timestamp without time zone NOT NULL DEFAULT NOW(),
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

ALTER TABLE IF EXISTS public.messages
    OWNER to postgres;