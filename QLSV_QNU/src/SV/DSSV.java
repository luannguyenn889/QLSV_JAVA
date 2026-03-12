package SV;
import java.io.*;
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
				System.out.print(sv.toString());
			}
		}
	}
}
