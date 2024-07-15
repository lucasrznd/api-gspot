INSERT INTO tb_locutor (id, name, phone_number, url_image) VALUES (1, 'Lucas Rezende', '43999999999', '');
INSERT INTO tb_locutor (id, name, phone_number, url_image) VALUES (2, 'John Doe', '43988888888', '');
INSERT INTO tb_locutor (id, name, phone_number, url_image) VALUES (3, 'Bob Green', '43977777777', '');

INSERT INTO tb_empresa (id, name, phone_number, url_image) VALUES (1, 'Molinis Supermercados', '4325255252', '');
INSERT INTO tb_empresa (id, name, phone_number, url_image) VALUES (2, 'Magazine Luiza', '4315155151', '');
INSERT INTO tb_empresa (id, name, phone_number, url_image) VALUES (3, 'Burguer King', '4320200202', '');

INSERT INTO tb_spot (id, title, company_id, announcer_id, spot_date, duration, active_contract, price)
    VALUES (1, 'QUINTA DA CARNE', 1, 1, '2024-07-11', 0.45, 1, 20.00);
INSERT INTO tb_spot (id, title, company_id, announcer_id, spot_date, duration, active_contract, price)
VALUES (2, 'OFERTAS DE VERAO', 2, 1, '2024-07-04', 1.10, 1, 25.00);