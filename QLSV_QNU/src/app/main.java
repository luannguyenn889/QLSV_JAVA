package app;
import SV.*;
import java.sql.*;
import java.util.*;
public class main {
	public static void main(String[] args) {
		DSSV ds = new DSSV(null);
		SinhVien sv = new SinhVien();
		    SinhVien sv1  = new SinhVien("4651050091", "Nguyễn Văn An",    "15/03/2004", "CNTT", 8.5, "22CNTT1");
	        SinhVien sv2  = new SinhVien("4651050092", "Trần Thị Bích",    "22/07/2003", "KTPM", 7.8, "22KTPM1");
	        SinhVien sv3  = new SinhVien("4651090093", "Lê Hoàng Cường",   "10/11/2004", "CNTT", 6.2, "22CNTT2");
	        SinhVien sv4  = new SinhVien("4651050094", "Phạm Minh Đức",    "05/01/2003", "KTPM", 9.1, "22KTPM2");
	        SinhVien sv5  = new SinhVien("4651090095", "Hoàng Thị Em",     "28/09/2004", "CNTT", 5.5, "22CNTT1");
	        SinhVien sv6  = new SinhVien("4651050096", "Võ Quốc Phong",    "14/06/2003", "KTPM", 7.0, "22KTPM1");
	        SinhVien sv7  = new SinhVien("4651090097", "Đặng Thùy Giang",  "30/12/2004", "CNTT", 8.9, "22CNTT2");
	        SinhVien sv8  = new SinhVien("4651050098", "Bùi Thanh Hải",    "18/04/2003", "KTPM", 6.7, "22KTPM2");
	        SinhVien sv9  = new SinhVien("4651090099", "Ngô Khánh Inh",    "07/08/2004", "CNTT", 9.5, "22CNTT1");
	        SinhVien sv10 = new SinhVien("4651050019", "Lý Thị Kim",       "25/02/2003", "KTPM", 7.3, "22KTPM1");
	        ds.themSV(sv1);
	        ds.themSV(sv2);
	        ds.themSV(sv3);
	        ds.themSV(sv4);
	        ds.themSV(sv5);
	        ds.themSV(sv6);
	        ds.themSV(sv7);
	        ds.themSV(sv8);
	        ds.themSV(sv9);
	        ds.themSV(sv10);

		Scanner sc = new Scanner(System.in);
		int choice;

        do {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên trong SQL");
            System.out.println("3. Xóa sinh viên trong SQL");
            System.out.println("4. Xem danh sách tất cả sinh viên trong CSDL");
            System.out.println("5. Xem sinh viên cùng lớp sinh hoạt ");
            System.out.println("6. Xem danh sách sinh viên  ");
            System.out.println("7. Xem danh sách sinh viên theo ngành");
            System.out.println("8. Sắp xếp  danh sách sinh viên theo DTB");
            System.out.println("9. Danh sách sinh viên sinh vào 1 tháng nào đó");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: sv.nhapSV_New(); sv.addToSql(sv); break;
                case 2: updateStudent(); break;
                case 3: deleteStudent(); break;
                case 4: printStudentAtSQL(); break;
                case 5: ds.displayListStudentSameClass("22CNTT1"); break;
                case 6: ds.HienThi(); break;
                case 7: ds.displayListStudentSameMajor("CNTT"); break;
                case 8: ds.sapxepSVTheoDTB(); ds.HienThi(); break;
                case 9: ds.displayListStudentMonthofBirth(7); break;
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
  private static void deleteStudent() {
	  Scanner sc = new Scanner(System.in);
	  System.out.print("Nhập mã sinh viên cần xóa: ");
	  String id = sc.nextLine();
	  String sql = "DELETE FROM students WHERE student_id = ?";
	  Connection con = null;
	  PreparedStatement pstmt = null;
  try {
	  con = GetConnection.getconnection();
	  pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id);
      int rows = pstmt.executeUpdate();
      if (rows > 0) System.out.println("Xóa thành công!");
      else System.out.println("Không tìm thấy sinh viên!");
  } catch (SQLException e) {
      System.out.println("Lỗi CSDL: " + e.getMessage());
  }
}
	
}
