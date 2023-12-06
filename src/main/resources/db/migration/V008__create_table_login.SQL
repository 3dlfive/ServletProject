-- Ensure that the uuid-ossp extension is available
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the login table
CREATE TABLE public.login
(
    session_id uuid DEFAULT uuid_generate_v1() NOT NULL,
    user_id int NOT NULL PRIMARY KEY,
    login_at date NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
ALTER TABLE IF EXISTS public.login
    OWNER to postgres;