-- franchise-api - schema for Supabase (Postgres)
--
-- Run this in the Supabase SQL editor (or via `psql`) to provision the tables manually.
-- It is optional: the application also creates/updates these same tables automatically on
-- startup via Hibernate (spring.jpa.hibernate.ddl-auto=update). Use this script when you
-- want the schema to exist before the app's first run, or want stricter constraints
-- (CHECK, indexes) than what Hibernate generates on its own.
--
-- Entity relationship: one franchise has many branches; one branch has many products.

-- Supabase enables pgcrypto by default; this is just a safety net for gen_random_uuid().
create extension if not exists "pgcrypto";

create table if not exists franchises (
    id   uuid primary key default gen_random_uuid(),
    name varchar(180) not null
);

create table if not exists branches (
    id           uuid primary key default gen_random_uuid(),
    name         varchar(180) not null,
    franchise_id uuid not null references franchises (id) on delete cascade
);

create index if not exists idx_branches_franchise_id on branches (franchise_id);

create table if not exists products (
    id        uuid primary key default gen_random_uuid(),
    name      varchar(180) not null,
    stock     integer not null default 0 check (stock >= 0),
    branch_id uuid not null references branches (id) on delete cascade
);

create index if not exists idx_products_branch_id on products (branch_id);

-- Speeds up "top-stock product per branch" (GET /api/franchises/{id}/top-stock-products):
-- lets Postgres resolve MAX(stock) per branch_id via an index-only scan.
create index if not exists idx_products_branch_id_stock on products (branch_id, stock desc);
