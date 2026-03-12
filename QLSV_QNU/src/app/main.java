package app;
import SV.*;

import java.util.*;
import java.sql.*;
public class main {
	public static void main(String[] args) {
		DSSV ds = new DSSV(null);
		SinhVien sv = new SinhVien();
		Scanner sc = new Scanner(System.in);
		int choice;

        do {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Xem danh sách tất cả sinh viên");
            System.out.println("5. Xem sinh viên theo lớp");
            System.out.println("6. Xem sinh viên theo ngành");
            System.out.println("7. Xem danh sách sắp xếp theo điểm trung bình");
            System.out.println("8. Xem sinh viên sinh vào tháng cụ thể");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: sv.NhapSV(); sv.addToSql(sv); break;
                case 2: updateStudent(); break;
                case 3: printStudentAtSQL(); break;
//                case 3: deleteStudent(scanner); break;
//                case 4: printAllStudents(); break;
//                case 5: printStudentsByClass(scanner); break;
//                case 6: printStudentsByMajor(scanner); break;
//                case 7: printStudentsSortedByGpa(); break;
//                case 8: printStudentsByBirthMonth(scanner); break;
                case 0: System.out.println("Thoát chương trình!"); break;
                default: System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
        sc.close();
    }

	
	
	public static void printStudentAtSQL() {
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 String sql = "SELECT * FROM students";
		 try {
			 con = GetConnection.getconnection();
	   	     pstmt = con.prepareStatement(sql);
	   	     ResultSet rs = pstmt.executeQuery();
	   	     System.out.println("maSV" + "|" + "tenSV" + "|" + "ngayThangNamSinh" + "|" + "nganhDaotao"+"|" + "lopSH" + "|" + "dtb");
	   	     while(rs.next()) {
	   	    	String maSV = rs.getString("student_id");
	            String tenSV = rs.getString("full_name");
	            double dtb = rs.getDouble("gpa");
	    	    String ngayThangNamSinh = String.valueOf(rs.getDate("dob"));
	    	    String nganhDaotao = rs.getString("major");
	    	    String lopSH = rs.getString("class_name");
	    	    
	            System.out.println(maSV + "|" + tenSV + "|" + ngayThangNamSinh + "|" + nganhDaotao+"|" + lopSH + "|" + dtb);
	           
	   	     }
		 }catch(SQLException e) {
			 System.out.println("Lỗi CSDL: " + e.getMessage());
		 }
	}

//	public static void addStudent() {
//		// TODO Auto-generated method stub
//		SinhVien sv = new SinhVien();
//		Connection con = null;
//		PreparedStatement pssv = null;
//		sv.NhapSV();
//		sv.addToSql(sv);
//
//		
//	}
	
	public static void updateStudent() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ma sinh vien can sua");
		String id = sc.nextLine();
		
		  Connection con = null;
		  PreparedStatement pssv = null;
		  System.out.print("Nhập điểm trung bình mới: ");
         double newGpa = Double.parseDouble(sc.nextLine());

          String sql = "UPDATE students SET gpa = ? WHERE student_id = ?";
          try {
        	  con = GetConnection.getconnection();
        	  pssv = con.prepareStatement(sql);
        	  pssv.setDouble(1, newGpa);
        	  pssv.setString(2, id);
        	  
        	  int rows = pssv.executeUpdate();
        	  if (rows > 0) {
        		  System.out.println("Cập nhật thành công!");
        	  }else {
        		  System.out.println("Không tìm thấy sinh viên!");
        	  }
          }catch(SQLException e) {
        	  System.out.println("Lỗi CSDL: " + e.getMessage());
          }

	}
  private static void deleteStudent(Scanner scanner) {
	  System.out.print("Nhập mã sinh viên cần xóa: ");
	  String id = scanner.nextLine();
	  String sql = "DELETE FROM students WHERE student_id = ?";
	  Connection con = null;
	  PreparedStatement pstmt = null;
  try {
	  con = GetConnection.getconnection();
      pstmt.setString(1, id);
      int rows = pstmt.executeUpdate();
      if (rows > 0) System.out.println("Xóa thành công!");
      else System.out.println("Không tìm thấy sinh viên!");
  } catch (SQLException e) {
      System.out.println("Lỗi CSDL: " + e.getMessage());
  }
}
	// --- CÁC THAO TÁC CRUD & QUERIES ---
//
//    private static void addStudent(Scanner scanner) {
//        try {
//            System.out.print("Nhập ngành đào tạo (CNTT / KTPM): ");
//            String major = scanner.nextLine().toUpperCase();
//            if (!major.equals("CNTT") && !major.equals("KTPM")) {
//                System.out.println("Ngành không hợp lệ!"); return;
//            }
//
//            System.out.print("Nhập mã sinh viên (10 số): ");
//            String id = scanner.nextLine();
//            if (!isValidId(id, major)) {
//                System.out.println("Mã sinh viên không hợp lệ hoặc không khớp với ngành!"); return;
//            }
//
//            System.out.print("Nhập họ tên: ");
//            String name = normalizeName(scanner.nextLine());
//
//            System.out.print("Nhập ngày sinh (dd/MM/yyyy): ");
//            String dobStr = scanner.nextLine();
//            LocalDate dob = parseAndValidateDob(dobStr);
//            if (dob == null) return;
//
//            System.out.print("Nhập điểm trung bình [0.0 - 10.0]: ");
//            double gpa = Double.parseDouble(scanner.nextLine());
//            if (gpa < 0.0 || gpa > 10.0) {
//                System.out.println("Điểm không hợp lệ!"); return;
//            }
//
//            System.out.print("Nhập lớp sinh hoạt: ");
//            String className = scanner.nextLine();
//
//            String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?)";
//            try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//                pstmt.setString(1, id);
//                pstmt.setString(2, name);
//                pstmt.setDate(3, java.sql.Date.valueOf(dob));
//                pstmt.setString(4, major);
//                pstmt.setDouble(5, gpa);
//                pstmt.setString(6, className);
//                pstmt.executeUpdate();
//                System.out.println("Thêm sinh viên thành công!");
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi CSDL: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Lỗi nhập liệu: " + e.getMessage());
//        }
//    }
//
//    private static void updateStudent(Scanner scanner) {
//        System.out.print("Nhập mã sinh viên cần sửa: ");
//        String id = scanner.nextLine();
//        
//        System.out.print("Nhập điểm trung bình mới: ");
//        double newGpa = Double.parseDouble(scanner.nextLine());
//
//        String sql = "UPDATE students SET gpa = ? WHERE student_id = ?";
//        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setDouble(1, newGpa);
//            pstmt.setString(2, id);
//            int rows = pstmt.executeUpdate();
//            if (rows > 0) System.out.println("Cập nhật thành công!");
//            else System.out.println("Không tìm thấy sinh viên!");
//        } catch (SQLException e) {
//            System.out.println("Lỗi CSDL: " + e.getMessage());
//        }
//    }
//
//    private static void deleteStudent(Scanner scanner) {
//        System.out.print("Nhập mã sinh viên cần xóa: ");
//        String id = scanner.nextLine();
//        String sql = "DELETE FROM students WHERE student_id = ?";
//        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, id);
//            int rows = pstmt.executeUpdate();
//            if (rows > 0) System.out.println("Xóa thành công!");
//            else System.out.println("Không tìm thấy sinh viên!");
//        } catch (SQLException e) {
//            System.out.println("Lỗi CSDL: " + e.getMessage());
//        }
//    }
//
//    private static void executeQueryAndPrint(String sql, Object... params) {
//        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            for (int i = 0; i < params.length; i++) {
//                pstmt.setObject(i + 1, params[i]);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            boolean hasData = false;
//            while (rs.next()) {
//                hasData = true;
//                Student s = new Student(
//                    rs.getString("student_id"), rs.getString("full_name"),
//                    rs.getDate("dob").toLocalDate(), rs.getString("major"),
//                    rs.getDouble("gpa"), rs.getString("class_name")
//                );
//                System.out.println(s);
//            }
//            if (!hasData) System.out.println("Không có dữ liệu.");
//        } catch (SQLException e) {
//            System.out.println("Lỗi CSDL: " + e.getMessage());
//        }
//    }
//
//    private static void printAllStudents() {
//        System.out.println("--- DANH SÁCH TẤT CẢ SINH VIÊN ---");
//        executeQueryAndPrint("SELECT * FROM students");
//    }
//
//    private static void printStudentsByClass(Scanner scanner) {
//        System.out.print("Nhập tên lớp: ");
//        String className = scanner.nextLine();
//        System.out.println("--- DANH SÁCH SINH VIÊN LỚP " + className + " ---");
//        executeQueryAndPrint("SELECT * FROM students WHERE class_name = ?", className);
//    }
//
//    private static void printStudentsByMajor(Scanner scanner) {
//        System.out.print("Nhập tên ngành (CNTT/KTPM): ");
//        String major = scanner.nextLine().toUpperCase();
//        System.out.println("--- DANH SÁCH SINH VIÊN NGÀNH " + major + " ---");
//        executeQueryAndPrint("SELECT * FROM students WHERE major = ?", major);
//    }
//
//    private static void printStudentsSortedByGpa() {
//        System.out.println("--- DANH SÁCH SINH VIÊN SẮP XẾP THEO ĐIỂM ---");
//        executeQueryAndPrint("SELECT * FROM students ORDER BY gpa DESC");
//    }
//
//    private static void printStudentsByBirthMonth(Scanner scanner) {
//        System.out.print("Nhập tháng sinh (1-12): ");
//        int month = Integer.parseInt(scanner.nextLine());
//        System.out.println("--- DANH SÁCH SINH VIÊN SINH THÁNG " + month + " ---");
//        executeQueryAndPrint("SELECT * FROM students WHERE MONTH(dob) = ?", month);
//    }
//
//    // --- CÁC HÀM VALIDATION VÀ CHUẨN HÓA ---
//
//    private static boolean isValidId(String id, String major) {
//        if (!id.matches("\\d{10}")) return false;
//        if (major.equals("CNTT") && !id.startsWith("455105")) return false;
//        if (major.equals("KTPM") && !id.startsWith("455109")) return false;
//        return true;
//    }
//
//    private static String normalizeName(String name) {
//        name = name.trim().replaceAll("\\s+", " ");
//        String[] words = name.split(" ");
//        StringBuilder sb = new StringBuilder();
//        for (String w : words) {
//            if (!w.isEmpty()) {
//                sb.append(Character.toUpperCase(w.charAt(0)));
//                sb.append(w.substring(1).toLowerCase()).append(" ");
//            }
//        }
//        return sb.toString().trim();
//    }
//
//    private static LocalDate parseAndValidateDob(String dobStr) {
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate dob = LocalDate.parse(dobStr, formatter);
//            int age = Period.between(dob, LocalDate.now()).getYears();
//            if (age >= 15 && age <= 110) {
//                return dob;
//            } else {
//                System.out.println("Tuổi sinh viên phải từ 15 đến 110 tuổi (Hiện tại là " + age + " tuổi).");
//                return null;
//            }
//        } catch (DateTimeParseException e) {
//            System.out.println("Ngày sinh không hợp lệ! Định dạng chuẩn là dd/MM/yyyy.");
//            return null;
//        }
//    }
//	   
}
