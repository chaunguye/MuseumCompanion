ALTER TABLE portals ADD COLUMN room_id UUID REFERENCES rooms(id) ON DELETE SET NULL ON UPDATE CASCADE;

CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE artwork_categories (
    artwork_id UUID REFERENCES artworks(id) ON DELETE CASCADE ON UPDATE CASCADE,
    category_id UUID REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (artwork_id, category_id)
);