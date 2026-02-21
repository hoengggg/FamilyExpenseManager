CREATE DATABASE QLCT;
GO

USE QLCT;
GO

CREATE TABLE DangNhap (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    phanQuyen VARCHAR(50) NOT NULL,       -- 'Parents' ho?c 'Childs'
    tenDangNhap VARCHAR(100) UNIQUE NOT NULL,
    matKhau VARCHAR(255) NOT NULL
);

CREATE TABLE GiaoDichVaTongQuan (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ngayThang DATE NOT NULL,
    loai NVARCHAR(50) NOT NULL,            -- 'Thu vào' ho?c 'Chi ra'
    danhMuc NVARCHAR(100) NOT NULL,
    moTa NVARCHAR(MAX),
    soTien FLOAT NOT NULL,
    createdById BIGINT NOT NULL,
    FOREIGN KEY (createdById) REFERENCES DangNhap(id)
);

CREATE TABLE MucTieu (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(200) NOT NULL,
    tienHienTai FLOAT NOT NULL,
    thoiHan DATE NOT NULL,
    tienMucTieu FLOAT NOT NULL,
    createdById BIGINT NOT NULL,
    FOREIGN KEY (createdById) REFERENCES DangNhap(id)
);

CREATE TABLE ViCaNhan (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
    ngayThang DATE NOT NULL,
    loai NVARCHAR(50) NOT NULL,            -- 'Thu vào' ho?c 'Chi ra'
    danhMuc NVARCHAR(100) NOT NULL,
    moTa NVARCHAR(MAX),
    soTien FLOAT NOT NULL,
    createdById BIGINT NOT NULL,
    FOREIGN KEY (createdById) REFERENCES DangNhap(id)
);

-- Ngu?i dùng
INSERT INTO DangNhap (phanQuyen, tenDangNhap, matKhau)
VALUES ('Parents', 'Bo', '123'),
       ('Parents', 'Me', '123'),
       ('Childs', 'Toi', '123'),
       ('Childs', 'Em', '123');

-- Giao d?ch
INSERT INTO GiaoDichVaTongQuan (ngayThang, loai, danhMuc, moTa, soTien, createdById)
VALUES 
('2025-09-08', N'Thu vào', N'Lương', N'Tiền lương hàng tháng', 8000000, 1),
('2025-11-19', N'Chi ra', N'Y tế', N'Khám sức khỏe định kỳ cho gia đình', 2000000, 3),
('2025-12-25', N'Thu vào', N'Thu nhập thêm', N'Thưởng cuối năm', 50000000, 2),
('2025-09-15', N'Chi ra', N'Thực phẩm', N'Mua đồ ăn và nhu yếu phẩm', 55000, 1),
('2025-10-05', N'Thu vào', N'Tiết kiệm gia đình', N'Bán đồ cũ trong nhà', 750000, 3),
('2025-10-28', N'Chi ra', N'Học tập', N'Đóng học phí cho con', 120000, 4),
('2025-11-12', N'Chi ra', N'Điện nước', N'Thanh toán tiền điện và nước', 90000, 4),
('2025-12-18', N'Thu vào', N'Thu nhập thêm', N'Tiền dạy kèm cuối tuần', 500000, 3);

-- M?c tiêu
INSERT INTO MucTieu (ten, tienHienTai, thoiHan, tienMucTieu, createdById)
VALUES 
(N'Mua xe PKL', 175000000, '2026-09-08', 250000000, 3),
(N'Đi du lịch cùng gia đình', 50000000, '2026-01-01', 100000000, 1),
(N'Học đại học', 81000000, '2026-07-15', 90000000, 3),
(N'Mua tai nghe mới', 500000, '2025-09-30', 1500000, 2),
(N'Tiết kiệm Trung thu', 300000, '2025-10-05', 1000000, 4),
(N'Mua áo khoác mùa đông', 800000, '2025-11-20', 2000000, 1),
(N'Chuẩn bị Noel', 1200000, '2025-12-24', 3000000, 2),
(N'Tổng kết năm mới', 5000000, '2025-12-31', 10000000, 4);

INSERT INTO ViCaNhan (ngayThang, loai, danhMuc, moTa, soTien, createdById) VALUES  
('2025-01-10', N'Thu vào', N'Lương', N'Thưởng cuối năm từ công ty', 15000000, 1), 
('2025-02-14', N'Chi ra', N'Thực phẩm', N'Mua thực phẩm cho gia đình', 1200000, 3), 
('2025-03-03', N'Thu vào', N'Thu nhập thêm', N'Lợi nhuận bán hàng online tháng 2', 3200000, 2), 
('2025-03-20', N'Chi ra', N'Đi lại', N'Bảo dưỡng xe phục vụ đi làm và đưa đón con', 850000, 1), 
('2025-04-05', N'Thu vào', N'Thu nhập thêm', N'Tiền làm thiết kế logo', 2500000, 3), 
('2025-04-18', N'Chi ra', N'Tiền nhà', N'Đóng tiền thuê nhà', 3500000, 4), 
('2025-05-09', N'Chi ra', N'Học tập', N'Mua sách và dụng cụ học tập cho con', 450000, 4), 
('2025-06-01', N'Thu vào', N'Tiết kiệm gia đình', N'Hoàn tiền từ đơn hàng bị hủy', 600000, 3);
SELECT * FROM DangNhap;

SELECT * FROM GiaoDichVaTongQuan;

SELECT * FROM MucTieu;

SELECT * FROM ViCaNhan;