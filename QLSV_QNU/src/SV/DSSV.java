package SV;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class DSSV {
  public ArrayList<SinhVien> DSSV;
  public int soSV;
	public DSSV(ArrayList<SinhVien> dSSV) {
		
		DSSV = new ArrayList<>();
	}
	 // them sinh vien 
	public void themSV(SinhVien sv) {
		DSSV.add(sv);
	}
	public void HienThi() {
		for(SinhVien sv : DSSV) {
			System.out.println(sv.toString());
		}
	}
	
	public void xoaSV(String ma) {
        for(SinhVien sv : DSSV) {
            if( !DSSV.isEmpty() && sv.getMsv().equals(ma)) {
                DSSV.remove(sv);
                soSV--;
            }
        }
    }
	
	
	
    // cho phép in ra số sinh viên cùng lớp sinh hoạt
	public void displayListStudentSameClass(String lopSH) {
		for(SinhVien sv : DSSV) {
			if(!DSSV.isEmpty() && sv.getLopSH().equals(lopSH)) {
				System.out.println(sv.toString());
			}
		}
	}
	  // cho phép in ra số sinh viên cùng ngành
		public void displayListStudentSameMajor(String Major) {
			for(SinhVien sv : DSSV) {
				if(!DSSV.isEmpty() && sv.getNganhDaotao().equals(Major)) {
					System.out.println(sv.toString());
				}
			}
		}
		
		public void sapxepSVTheoDTB() {
			DSSV.sort(Comparator.comparingDouble(SinhVien::getDtb));
		}
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        ngayThangNamSinh = sc.nextLine();
//        @SuppressWarnings("unused")
//        LocalDate date = LocalDate.parse(ngayThangNamSinh, formatter);
		public void displayListStudentMonthofBirth(int thangsinh) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			for(SinhVien sv : DSSV) {
				LocalDate date = LocalDate.parse(sv.getNgayThangNamSinh(),formatter);
				int thang = date.getMonthValue();
				if(!DSSV.isEmpty() && thang == thangsinh) {
					System.out.println(sv.toString());
				}
			}
		}
}
