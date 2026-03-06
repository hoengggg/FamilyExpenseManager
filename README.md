# 💰 FamilyExpenseManager

## 📌 Giới thiệu

FamilyExpenseManager là hệ thống Web được xây dựng theo mô hình MVC, giúp người dùng quản lý thu – chi cá nhân và gia đình, mục tiêu một cách hiệu quả.
Hệ thống hỗ trợ thống kê, phân tích và tổng hợp tài chính theo năm, tính toán tiền tiết kiệm và hiển thị dữ liệu bằng biểu đồ trực quan.

Thời gian thực hiện: 11/2025 – 02/2026

Vai trò: FullStack Developer

## 🚀 Chức năng chính
- Đăng nhập và xác thực người dùng
- Tổng quan tài chính theo năm
- Quản lý thu - chi cho gia đình
- Quản lý mục tiêu cho gia đình
- Quản lý ví cá nhân
- Thống kê thu - chi theo tháng bằng biểu đồ
- Lọc dữ liệu theo năm

## 🛠 Công nghệ sử dụng

### Backend
- Java Servlet
- Hibernate
- JSP
- JSTL

### Frontend
- HTML
- CSS
- Bootstrap

### Database
- SQL Server

### Công cụ hỗ trợ
- Chart.js (Hiển thị biểu đồ thống kê)
- Ngrok (Deploy lên Online)

## 🏗 Kiến trúc hệ thống
Dự án được xây dựng theo mô hình MVC **(Model - View - Controller)**;

```
ASM_QLCT
│
├── src
│   └── main
│       ├── java
│       │   ├── Controller
│       │   ├── Entity
│       │   ├── Repository
│       │   ├── helper
│       │   └── mail
│       │
│       ├── resources
│       │
│       └── webapp
│           └── View
│               └── views
```
### 📌 Mô tả các thành phần

- **Controller**: Nhận request từ người dùng và điều hướng xử lý
- **Entity**: Các lớp dữ liệu ánh xạ với bảng trong cơ sở dữ liệu thông qua Hibernate/JPA
- **Repository**: Thực hiện các thao tác với database thông qua Hibernate
- **Helper**: Hỗ trợ kết nối đến cơ sở dữ liệu
- **Mail**: Xử lý chức năng gửi email của hệ thống
- **Resources**: Lưu trữ file cấu hình và hỗ trợ đa ngôn ngữ
- **View**: Chứa các trang giao diện người dùng được xây dựng bằng JSP

### Mô hình MVC giúp:
- Tách biệt giao diện và logic xử lý
- Dễ bảo trì và mở rộng hệ thống
- Code rõ ràng và có cấu trúc

## ⚙️ Demo dự án
Truy cập link sau để chạy dự án: https://interlocular-ji-sorely.ngrok-free.dev/ASM_QLCT/dang_nhap

## 🎯 Kinh nghiệm đạt được
- Xây dựng và phát triển Web theo mô hình MVC
- Kết nối và thực hiện thao tác với SQL Server bằng Hibernate
- Xây dựng cơ chế phân quyền và xác thực người dùng
- Tích hợp I18n cho Web
- Deploy dự án lên Online bằng Ngrok
- Phát triển bộ lọc theo năm và xử lý logic tính tiền tiết kiệm, tổng hợp thu–chi và mục tiêu tài chính

## 👨‍💻 Tác giả

**Lê Huy Hoàng**  
Sinh viên ngành Phát triển phần mềm  
Cao Đẳng FPT Polytechnic
