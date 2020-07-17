
import java.util.Date;
import java.util.Scanner;

public class UserInfo {
	private String SNo;
	private String SName;
	private String SSex;
	private int Sage;
	private int Scredits;
	
	public String getSSex() {
		return SSex;
	}
	public void setSSex(String sSex) {
		SSex = sSex;
	}
	public String getSNo() {
		return SNo;
	}
	public void setSNo(String sNo) {
		SNo = sNo;
	}
	public String getSName() {
		return SName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	public int getSage() {
		return Sage;
	}
	public void setSage(int sage) {
		Sage = sage;
	}
	public int getScredits() {
		return Scredits;
	}
	public void setScredits(int scredits) {
		Scredits = scredits;
	}
	@Override
	public String toString() {
		return "UserInfo [SNo=" + SNo + ", SName=" + SName + ", SSex=" + SSex
				+ ", Sage=" + Sage + "]";
	}

}