USE WebProgrammingAPI
GO

INSERT INTO Categories (category_name, icon) VALUES (N'iPhone', NULL);
INSERT INTO Categories (category_name, icon) VALUES (N'Samsung Galaxy', NULL);
INSERT INTO Categories (category_name, icon) VALUES (N'Xiaomi', NULL);
INSERT INTO Categories (category_name, icon) VALUES (N'OPPO', NULL);
INSERT INTO Categories (category_name, icon) VALUES (N'Vivo', NULL);
INSERT INTO Categories (category_name, icon) VALUES (N'Realme', NULL);
INSERT INTO Categories (category_name, icon) VALUES (N'Asus ROG Phone', NULL);


-- iPhone
INSERT INTO Products (product_name, quantity, unit_price, images, description, discount, create_date, status, category_id)
VALUES 
(N'iPhone 15 Pro Max 256GB', 20, 32990000, N'iphone-15-pro-max.jpg', N'Chip A17 Pro, camera 48MP, khung Titan', 5, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'iPhone')),
(N'iPhone 15 128GB', 35, 21990000, N'iphone-15.jpg', N'Chip A16 Bionic, cổng USB-C, Dynamic Island', 3, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'iPhone')),
(N'iPhone 14 128GB', 40, 17990000, N'iphone-14.jpg', N'Chip A15 Bionic, camera kép 12MP', 10, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'iPhone')),
(N'iPhone 13 128GB', 25, 13990000, N'iphone-13.jpg', N'Chip A15 Bionic, màn hình Super Retina XDR', 15, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'iPhone'));

-- Samsung Galaxy
INSERT INTO Products (product_name, quantity, unit_price, images, description, discount, create_date, status, category_id)
VALUES 
(N'Samsung Galaxy S24 Ultra 512GB', 15, 29990000, N'samsung-s24-ultra.jpg', N'Snapdragon 8 Gen 3, bút S Pen, camera 200MP', 8, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Samsung Galaxy')),
(N'Samsung Galaxy S24 256GB', 30, 20990000, N'samsung-s24.jpg', N'Snapdragon 8 Gen 3, màn hình Dynamic AMOLED 2X', 6, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Samsung Galaxy')),
(N'Samsung Galaxy Z Fold 5', 10, 40990000, N'samsung-zfold5.jpg', N'Màn hình gập, đa nhiệm mạnh mẽ', 5, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Samsung Galaxy')),
(N'Samsung Galaxy A55 128GB', 50, 9990000, N'samsung-a55.jpg', N'Phân khúc tầm trung, pin 5000mAh', 12, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Samsung Galaxy'));

-- Xiaomi
INSERT INTO Products (product_name, quantity, unit_price, images, description, discount, create_date, status, category_id)
VALUES 
(N'Xiaomi 14 Pro 256GB', 25, 18990000, N'xiaomi-14-pro.jpg', N'Snapdragon 8 Gen 3, camera Leica', 10, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Xiaomi')),
(N'Xiaomi 13T 256GB', 30, 12990000, N'xiaomi-13t.jpg', N'Sạc nhanh 120W, camera Leica', 8, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Xiaomi')),
(N'Redmi Note 13 Pro 128GB', 60, 6990000, N'redmi-note13-pro.jpg', N'Camera 200MP, giá tốt trong tầm giá', 15, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Xiaomi'));

-- OPPO
INSERT INTO Products (product_name, quantity, unit_price, images, description, discount, create_date, status, category_id)
VALUES 
(N'OPPO Find X7 256GB', 18, 16990000, N'oppo-find-x7.jpg', N'Camera Hasselblad, chip Dimensity 9300', 7, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'OPPO')),
(N'OPPO Reno 11 256GB', 35, 10990000, N'oppo-reno11.jpg', N'Camera chân dung AI, thiết kế mỏng nhẹ', 10, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'OPPO'));

-- Vivo
INSERT INTO Products (product_name, quantity, unit_price, images, description, discount, create_date, status, category_id)
VALUES 
(N'Vivo V30 128GB', 30, 9990000, N'vivo-v30.jpg', N'Camera chân dung Zeiss, màn hình AMOLED 120Hz', 12, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Vivo')),
(N'Vivo X100 Pro 256GB', 12, 22990000, N'vivo-x100-pro.jpg', N'Camera Zeiss APO, chip Dimensity 9300', 6, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Vivo'));

-- Realme
INSERT INTO Products (product_name, quantity, unit_price, images, description, discount, create_date, status, category_id)
VALUES 
(N'Realme 12 Pro+ 256GB', 25, 8990000, N'realme-12-pro-plus.jpg', N'Camera tele periscope 3x, thiết kế da', 10, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Realme')),
(N'Realme GT 6 256GB', 20, 13990000, N'realme-gt6.jpg', N'Snapdragon 8s Gen 3, sạc nhanh 120W', 8, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Realme'));

-- Asus ROG Phone
INSERT INTO Products (product_name, quantity, unit_price, images, description, discount, create_date, status, category_id)
VALUES 
(N'Asus ROG Phone 8 Pro 512GB', 8, 27990000, N'rog-phone-8-pro.jpg', N'Snapdragon 8 Gen 3, tản nhiệt AeroActive, dành cho gaming', 5, GETDATE(), 1, (SELECT category_id FROM Categories WHERE category_name = N'Asus ROG Phone'));
