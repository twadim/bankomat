INSERT INTO users (id, name, card, password) VALUES
                                                             (1, 'Vassily Petrov', '1111111111111111', '1234'),
                                                             (2, 'Pjotr Vasechkin', '2222222222222222', '4321')
ON CONFLICT (id) DO NOTHING;