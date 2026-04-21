-- Enable PostGIS for Map Features
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE museums (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    location TEXT, 
    opening_hours JSONB,             -- Flexible structure for hours/holidays
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE floors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    level INT NOT NULL, -- 1, 2, or 3
    map_image_url VARCHAR(512),
    museum_id UUID REFERENCES museums(id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE rooms (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    floor_id UUID REFERENCES floors(id) ON DELETE CASCADE ON UPDATE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    room_code VARCHAR(6) UNIQUE NOT NULL ,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE collections (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    museum_id UUID REFERENCES museums(id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE artworks (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    room_id UUID REFERENCES rooms(id) ON DELETE CASCADE ON UPDATE CASCADE,
    collection_id UUID REFERENCES collections(id) ON DELETE SET NULL ON UPDATE CASCADE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    description TEXT,
    audio_url VARCHAR(512),
    image_url VARCHAR(512),
    qr_code_token VARCHAR(100) UNIQUE NOT NULL, -- For Feature 1
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(512) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE scan_history (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    artwork_id UUID NOT NULL REFERENCES artworks(id) ON DELETE CASCADE ON UPDATE CASCADE,
    scanned_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE saved_posts (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    artwork_id UUID NOT NULL REFERENCES artworks(id) ON DELETE CASCADE ON UPDATE CASCADE,
    saved_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE portals (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    museum_id UUID REFERENCES museums(id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Indexing for performance
CREATE INDEX idx_artworks_qr ON artworks(qr_code_token);
CREATE INDEX idx_scan_history_user ON scan_history(user_id);
CREATE INDEX idx_scan_history_artwork ON scan_history(artwork_id);
CREATE INDEX idx_saved_posts_user ON saved_posts(user_id);
CREATE INDEX idx_saved_posts_artwork ON saved_posts(artwork_id);
