package SV;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
public class SinhVien {
    // quản lý một danh sách sinh viên, mỗi sinh viên có các thông tin sau: mã sinh viên, họ tên, ngày tháng năm sinh, ngành đào tạo, điểm trung bình, lớp sinh hoạt. 
	//Thao tác quản lý gồm có: thêm, xóa, sửa.
		public String msv;
	    public String hoTen;
	    public String ngayThangNamSinh;
	    public String nganhDaotao;
	    public double dtb;
	    public String lopSH;
		public SinhVien(String msv, String hoTen, String ngayThangNamSinh, String nganhDaotao, double dtb,
				String lopSH) {
			super();
			this.msv = msv;
			this.hoTen = hoTen;
			this.ngayThangNamSinh = ngayThangNamSinh;
			this.nganhDaotao = nganhDaotao;
			this.dtb = dtb;
			this.lopSH = lopSH;
		}
	   public SinhVien() {
		   
	   }
	public String getMsv() {
		return msv;
	}
	public void setMsv(String msv) {
		this.msv = msv;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getNgayThangNamSinh() {
		return ngayThangNamSinh;
	}
	public void setNgayThangNamSinh(String ngayThangNamSinh) {
		this.ngayThangNamSinh = ngayThangNamSinh;
	}
	public String getNganhDaotao() {
		return nganhDaotao;
	}
	public void setNganhDaotao(String nganhDaotao) {
		this.nganhDaotao = nganhDaotao;
	}
	public double getDtb() {
		return dtb;
	}
	public void setDtb(double dtb) {
		this.dtb = dtb;
	}
	public String getLopSH() {
		return lopSH;
	}
	public void setLopSH(String lopSH) {
		this.lopSH = lopSH;
	}
	@Override
	public String toString() {
		return "SinhVien [msv: " + msv + ", hoTen: " + hoTen + ", ngayThangNamSinh: " + ngayThangNamSinh + ", nganhDaotao: "
				+ nganhDaotao + ", dtb: " + dtb + ", lopSH: " + lopSH + "]";
	}
	   
	 public void NhapSVVaoSQL() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Nhap ma sv :");
	        msv= sc.nextLine().toUpperCase();
	     // Kiểm tra xem có đúng 10 chữ số không
	        if (msv.matches("\\d{10}")) {
	            // Kiểm tra tiền tố theo ngành
	            if (!msv.startsWith("455105") || !msv.startsWith("455109") ) {
	            	System.out.println("Mã sinh viên không hợp lệ!"); return;
	            }
	        } else {
	            System.out.println("Lỗi: Mã sinh viên phải bao gồm đúng 10 chữ số!");
	        }
	       
	        System.out.println("Nhập họ tên: ");
	        this.hoTen = sc.nextLine();
	        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        ngayThangNamSinh = sc.nextLine();
	        @SuppressWarnings("unused")
	        LocalDate date = LocalDate.parse(ngayThangNamSinh, formatter);
	        if(date == null) {
	        	System.out.println("Điểm không hợp lệ!"); return;
	        }
	        LocalDate ngayHientai = LocalDate.now();
	        int age = Period.between(date, ngayHientai).getYears();
	        
	        if(age >= 15 || age <= 110) {
	        	System.out.println("Tuổi Sinh viên không hợp lệ!"); return;
	        }
	        
	        
	        System.out.println("Nhap Ngành Đào Tạo : ");
	        nganhDaotao = sc.nextLine();
	        if (!nganhDaotao.equals("CNTT") && !nganhDaotao.equals("KTPM")) {
	            System.out.println("Lỗi: Ngành đào tạo chỉ có thể là CNTT hoặc KTPM!"); return;
	        }
	        System.out.println("Nhập LopSH");
	        lopSH = sc.nextLine();



	        System.out.println("Nhập điểm trung bình [0.0 - 10.0]:: ");
	        this.dtb = sc.nextDouble();
	        if(dtb < 0.0 || dtb > 10.0) {
	        	System.out.println("Điểm không hợp lệ!"); return;
	        }
	        
	        
	        Connection con = null;
			PreparedStatement pssv = null;
			
			System.out.println(toString());
			boolean res = false;
			String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?)";
			try {
				con = GetConnection.getconnection();
				pssv = con.prepareStatement(sql);
				pssv.setString(1, msv);
	            pssv.setString(2, hoTen);
	            pssv.setDate(3, java.sql.Date.valueOf(date));
	            pssv.setString(4, nganhDaotao);
	            pssv.setDouble(5, dtb);
	            pssv.setString(6, lopSH);
	            pssv.executeUpdate();
	            System.out.println("Thêm sinh viên thành công!");
			}catch (SQLException e) {
	            System.out.println("Lỗi CSDL: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("Lỗi nhập liệu: " + e.getMessage());
	        }
	    }
	 
	 public void NhapSV() {
		  Scanner sc = new Scanner(System.in);
	        System.out.println("Nhap ma sv :");
	        msv= sc.nextLine().toUpperCase();
	     // Kiểm tra xem có đúng 10 chữ số không
	        if (msv.matches("\\d{10}")) {
	            // Kiểm tra tiền tố theo ngành
//	            if (!msv.startsWith("455105") || !msv.startsWith("455109") ) {
//	            	System.out.println("Mã sinh viên không hợp lệ!"); return;
//	            }
	        } 
	        System.out.println("Nhập họ tên: ");
	        this.hoTen = sc.nextLine();
	        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        ngayThangNamSinh = sc.nextLine();
	        @SuppressWarnings("unused")
	        LocalDate date = LocalDate.parse(ngayThangNamSinh, formatter);
//	        if(date == null) {
//	        	System.out.println("Điểm không hợp lệ!"); return;
//	        }
//	        LocalDate ngayHientai = LocalDate.now();
//	        int age = Period.between(date, ngayHientai).getYears();
//	        
//	        if(age >= 15 || age <= 110) {
//	        	System.out.println("Tuổi Sinh viên không hợp lệ!"); return;
//	        }
	        
	        
	        System.out.println("Nhap Ngành Đào Tạo : ");
	        nganhDaotao = sc.nextLine();
//	        if (!nganhDaotao.equals("CNTT") && !nganhDaotao.equals("KTPM")) {
//	            System.out.println("Lỗi: Ngành đào tạo chỉ có thể là CNTT hoặc KTPM!"); return;
//	        }
	        System.out.println("Nhập LopSH");
	        lopSH = sc.nextLine();



	        System.out.println("Nhập điểm trung bình [0.0 - 10.0]:: ");
	        this.dtb = sc.nextDouble();
//	        if(dtb < 0.0 || dtb > 10.0) {
//	        	System.out.println("Điểm không hợp lệ!"); return;
//	        }
	        
	}
	 
	 public void addToSql(SinhVien sv) {

	        Connection con = null;
			PreparedStatement pssv = null;
			
			System.out.println(toString());
			boolean res = false;
			String sql = "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?)";
			try {
				con = GetConnection.getconnection();
				pssv = con.prepareStatement(sql);
				pssv.setString(1, sv.msv);
	            pssv.setString(2, sv.hoTen);
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        @SuppressWarnings("unused")
		        LocalDate date = LocalDate.parse(sv.ngayThangNamSinh, formatter);
	            pssv.setDate(3, java.sql.Date.valueOf(date));
	            pssv.setString(4, sv.nganhDaotao);
	            pssv.setDouble(5, sv.dtb);
	            pssv.setString(6, sv.lopSH);
	            pssv.executeUpdate();
	            System.out.println("Thêm sinh viên thành công!");
			}catch (SQLException e) {
	            System.out.println("Lỗi CSDL: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("Lỗi nhập liệu: " + e.getMessage());
	        }
	 }
	
}
