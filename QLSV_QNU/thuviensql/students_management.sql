-- 1. Kiểm tra và tạo database nếu chưa tồn tại
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'student_management')
BEGIN
    CREATE DATABASE student_management;
END
GO

-- 2. Sử dụng database
USE student_management;
GO

-- 3. Kiểm tra và tạo bảng students (Xóa bảng cũ nếu đã tồn tại để tránh lỗi khi chạy lại)
IF OBJECT_ID('students', 'U') IS NOT NULL
    DROP TABLE students;
GO

CREATE TABLE students (
    student_id VARCHAR(10) PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL, -- Dùng NVARCHAR để hỗ trợ tiếng Việt có dấu
    dob DATE NOT NULL,
    major VARCHAR(10) NOT NULL,
    gpa FLOAT NOT NULL,               -- SQL Server dùng FLOAT hoặc REAL thay cho DOUBLE
    class_name VARCHAR(50) NOT NULL
);
GO

-- 4. Thêm 10 sinh viên mẫu
-- Sử dụng tiền tố N trước chuỗi để định dạng chuẩn Unicode (phòng trường hợp bạn nhập tiếng Việt có dấu)
INSERT INTO students (student_id, full_name, dob, major, gpa, class_name) 
VALUES 
('4551050001', N'Nguyen Van A', '2005-05-12', 'CNTT', 8.5, 'IT1'),
('4551050002', N'Tran Thi B', '2004-10-20', 'CNTT', 7.2, 'IT1'),
('4551050003', N'Le Van C', '2006-01-15', 'CNTT', 9.0, 'IT1'),
('4551050004', N'Pham Thi D', '2005-08-08', 'CNTT', 6.5, 'IT2'),
('4551050005', N'Hoang Van E', '2004-12-01', 'CNTT', 8.0, 'IT2'),
('4551090001', N'Vu Thi F', '2005-03-25', 'KTPM', 7.8, 'SE1'),
('4551090002', N'Bui Van G', '2006-07-19', 'KTPM', 8.9, 'SE1'),
('4551090003', N'Do Thi H', '2005-11-11', 'KTPM', 9.5, 'SE1'),
('4551090004', N'Ngo Van I', '2004-02-28', 'KTPM', 6.0, 'SE1'),
('4551090005', N'Dinh Thi K', '2005-09-09', 'KTPM', 8.2, 'IT2');
GO

select * from students