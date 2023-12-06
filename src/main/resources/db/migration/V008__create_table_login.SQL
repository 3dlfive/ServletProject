
-- Create the login table
CREATE TABLE public.login
(
    session_id uuid  NOT NULL PRIMARY KEY,
    user_id int NOT NULL,
    login_at date NOT NULL DEFAULT NOW(),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
ALTER TABLE IF EXISTS public.login
    OWNER to postgres;