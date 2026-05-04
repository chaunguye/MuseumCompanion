-- Insert Sample Museum
INSERT INTO museums (id, name, description, location, opening_hours)
VALUES (
    '550e8400-e29b-41d4-a716-446655440000',
    'The Grand History Museum',
    'A world-renowned museum featuring artifacts from various civilizations and eras.',
    '123 Culture Way, Metropolitan City',
    '{"monday": "09:00-18:00", "tuesday": "09:00-18:00", "wednesday": "09:00-18:00", "thursday": "09:00-21:00", "friday": "09:00-18:00", "saturday": "10:00-20:00", "sunday": "10:00-17:00"}'::jsonb
);

-- Insert Sample Floors
INSERT INTO floors (id, level, map_image_url, museum_id)
VALUES 
('d11e8400-e29b-41d4-a716-446655440001', 1, 'https://example.com/maps/floor1.png', '550e8400-e29b-41d4-a716-446655440000'),
('d11e8400-e29b-41d4-a716-446655440002', 2, 'https://example.com/maps/floor2.png', '550e8400-e29b-41d4-a716-446655440000');

-- Insert Sample Rooms
INSERT INTO rooms (id, floor_id, name, description, room_code)
VALUES 
('a22e8400-e29b-41d4-a716-446655440001', 'd11e8400-e29b-41d4-a716-446655440001', 'Renaissance Hall', 'Features masterpieces from the 14th to 17th centuries.', 'R101'),
('a22e8400-e29b-41d4-a716-446655440002', 'd11e8400-e29b-41d4-a716-446655440001', 'Ancient Egypt Gallery', 'Explore the wonders of the Pharaohs.', 'E102'),
('a22e8400-e29b-41d4-a716-446655440003', 'd11e8400-e29b-41d4-a716-446655440002', 'Modern Art Wing', 'Contemporary works from global artists.', 'M201');

-- Insert Sample Collections
INSERT INTO collections (id, name, description, museum_id)
VALUES 
('c33e8400-e29b-41d4-a716-446655440001', 'The Italian Renaissance', 'A curated selection of paintings and sculptures from Italy.', '550e8400-e29b-41d4-a716-446655440000'),
('c33e8400-e29b-41d4-a716-446655440002', 'Artifacts of the Nile', 'Daily life and spiritual items from ancient Egypt.', '550e8400-e29b-41d4-a716-446655440000');

-- Insert Sample Categories
INSERT INTO categories (id, name, description)
VALUES 
('f44e8400-e29b-41d4-a716-446655440001', 'Painting', 'Two-dimensional visual art.'),
('f44e8400-e29b-41d4-a716-446655440002', 'Sculpture', 'Three-dimensional artistic forms.'),
('f44e8400-e29b-41d4-a716-446655440003', 'Ancient Artifact', 'Historical items from antiquity.');

-- Insert Sample Artworks
INSERT INTO artworks (id, room_id, collection_id, title, author, description, audio_url, image_url, qr_code_token)
VALUES 
(
    'b55e8400-e29b-41d4-a716-446655440001', 
    'a22e8400-e29b-41d4-a716-446655440001', 
    'c33e8400-e29b-41d4-a716-446655440001', 
    'Mystic Smile', 
    'Leonardo Di Vinci', 
    'A portrait of a woman with an enigmatic expression.', 
    'https://example.com/audio/mystic_smile.mp3', 
    'https://example.com/images/mystic_smile.jpg', 
    'QR_MYSTIC_001'
),
(
    'b55e8400-e29b-41d4-a716-446655440002', 
    'a22e8400-e29b-41d4-a716-446655440002', 
    'c33e8400-e29b-41d4-a716-446655440002', 
    'Golden Mask of Tut', 
    'Unknown', 
    'The iconic death mask of the boy king.', 
    'https://example.com/audio/golden_mask.mp3', 
    'https://example.com/images/golden_mask.jpg', 
    'QR_TUT_002'
);

-- Map Artworks to Categories
INSERT INTO artwork_categories (artwork_id, category_id)
VALUES 
('b55e8400-e29b-41d4-a716-446655440001', 'f44e8400-e29b-41d4-a716-446655440001'), -- Mystic Smile -> Painting
('b55e8400-e29b-41d4-a716-446655440002', 'f44e8400-e29b-41d4-a716-446655440003'); -- Golden Mask -> Ancient Artifact

-- Insert Sample Portal
INSERT INTO portals (id, name, description, museum_id, room_id)
VALUES (
    'e66e8400-e29b-41d4-a716-446655440001',
    'Renaissance Entrance Portal',
    'Welcome to the Renaissance collection.',
    '550e8400-e29b-41d4-a716-446655440000',
    'a22e8400-e29b-41d4-a716-446655440001'
);

-- Insert Sample User
INSERT INTO users (id, username, email, password_hash)
VALUES (
    '111e8400-e29b-41d4-a716-446655440001',
    'museum_goer_1',
    'goer1@example.com',
    '$2a$10$8.UnVuG9HHgffUDAlk8UrOuR5fLCLjDK51.Vl7M0.WfU78H0hXJ.S' -- bcrypt hash for 'password123'
);
