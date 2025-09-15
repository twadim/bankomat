INSERT INTO users (id, name, card, password) VALUES
                                                             (1, 'Vassily Petrov', '1111111111111111', '1234'),
                                                             (2, 'Pjotr Vasechkin', '2222222222222222', '4321')
ON CONFLICT (id) DO NOTHING;

INSERT INTO accounts (id, user_id, balance_byn, balance_usd, balance_eur) VALUES
                                                             (1, 1, 50000.00, 0.00, 0.00),
                                                             (2, 2, 25000.00, 0.00, 0.00)
ON CONFLICT (id) DO NOTHING;

INSERT INTO services (code, name, description, price, active) VALUES
('ELEC001', 'Оплата электроэнергии', 'Оплата за электроэнергию', 25.50, true),
('WATER002', 'Оплата водоснабжения', 'Оплата за водоснабжение', 15.75, true),
('GAS003', 'Оплата газа', 'Оплата за газоснабжение', 30.00, true),
('PHONE004', 'Оплата мобильной связи', 'Пополнение баланса мобильного телефона', 10.00, true),
('INTERNET005', 'Оплата интернета', 'Оплата за интернет-услуги', 20.00, true) ON CONFLICT (code) DO NOTHING;