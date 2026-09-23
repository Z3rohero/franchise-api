-- franchise-api - schema for Supabase (Postgres)

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


create index if not exists idx_products_branch_id_stock on products (branch_id, stock desc);
