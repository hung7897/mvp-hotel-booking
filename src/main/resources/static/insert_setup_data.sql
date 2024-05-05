INSERT INTO address (address_id, street, city, state, postal_code, country, created_on, created_by, updated_on, updated_by)
VALUES
  (1, '123 Main Street', 'Springfield', 'IL', '62701', 'United States', '2024-05-04T10:00:00Z', 1001, '2024-05-04T14:30:00Z', 1002),
  (2, '456 Elm Avenue', 'Oakland', 'CA', '94612', 'United States', '2024-05-04T11:15:00Z', 1003, '2024-05-04T15:45:00Z', 1004);
-- Add more rows as needed

-- Inserting sample data into the hotel table
INSERT INTO hotel (hotel_id, name, address_id, phone, email, status, created_on, created_by, updated_on, updated_by)
VALUES
  (1, 'Grand Hotel', 1, '+1 123-456-7890', 'grand@example.com', 'ACTIVE', '2024-05-04T10:00:00Z', 1001, '2024-05-04T14:30:00Z', 1002),
  (2, 'Ocean View Resort', 2, '+1 987-654-3210', 'oceanview@example.com', 'ACTIVE', '2024-05-04T11:15:00Z', 1003, '2024-05-04T15:45:00Z', 1004);
-- Add more rows as needed

-- Insert sample rooms for hotels
INSERT INTO room (hotel_id, number, type, price, status)
VALUES
    (1, '101', 'Standard', 100.00, 'ACTIVE'),
    (1, '102', 'Standard', 100.00, 'ACTIVE'),
    (2, '201', 'Deluxe', 150.00, 'ACTIVE'),
    (2, '202', 'Deluxe', 150.00, 'ACTIVE');

-- Assume hotel IDs are 1, 2, and 3
-- Room types: 'Standard', 'Deluxe', 'Single'
-- Prices are in USD

-- Insert sample guests
INSERT INTO guest (first_name, last_name, email, phone)
VALUES
    ('John', 'Doe', 'john.doe@example.com', '+1 123-456-7890'),
    ('Alice', 'Smith', 'alice.smith@example.com', '+1 987-654-3210'),
    ('Bob', 'Johnson', 'bob.johnson@example.com', '+1 555-123-4567');

-- Adjust email and phone numbers as needed