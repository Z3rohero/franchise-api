-- franchise-api - sample data for Supabase (Postgres)
--
-- Optional: run after schema.sql to have data ready to test the API and, in particular,
-- GET /api/franchises/{id}/top-stock-products. Uses fixed UUIDs so the relationships below
-- are easy to read; feel free to delete this data afterwards.

insert into franchises (id, name) values
    ('f0000000-0000-0000-0000-000000000001', 'Fast Burger'),
    ('f0000000-0000-0000-0000-000000000002', 'Pizza Town')
on conflict (id) do nothing;

insert into branches (id, name, franchise_id) values
    ('b0000000-0000-0000-0000-000000000001', 'Downtown', 'f0000000-0000-0000-0000-000000000001'),
    ('b0000000-0000-0000-0000-000000000002', 'Uptown',   'f0000000-0000-0000-0000-000000000001'),
    ('b0000000-0000-0000-0000-000000000003', 'Mall',     'f0000000-0000-0000-0000-000000000002')
on conflict (id) do nothing;

insert into products (id, name, stock, branch_id) values
    ('c0000000-0000-0000-0000-000000000001', 'Classic Burger',   40, 'b0000000-0000-0000-0000-000000000001'),
    ('c0000000-0000-0000-0000-000000000002', 'Fries',            15, 'b0000000-0000-0000-0000-000000000001'),
    ('c0000000-0000-0000-0000-000000000003', 'Soda 400ml',       60, 'b0000000-0000-0000-0000-000000000001'), -- top stock in Downtown
    ('c0000000-0000-0000-0000-000000000004', 'Veggie Burger',    25, 'b0000000-0000-0000-0000-000000000002'),
    ('c0000000-0000-0000-0000-000000000005', 'Milkshake',        30, 'b0000000-0000-0000-0000-000000000002'), -- top stock in Uptown
    ('c0000000-0000-0000-0000-000000000006', 'Pepperoni Pizza',  18, 'b0000000-0000-0000-0000-000000000003'),
    ('c0000000-0000-0000-0000-000000000007', 'Garlic Bread',     45, 'b0000000-0000-0000-0000-000000000003')  -- top stock in Mall
on conflict (id) do nothing;
