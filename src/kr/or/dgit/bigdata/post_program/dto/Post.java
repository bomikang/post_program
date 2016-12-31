package kr.or.dgit.bigdata.post_program.dto;

public class Post {
	private String zipcode;
	private String sido;
	private String sigungu;
	private String doro;
	private int building1;
	private int building2;
	
	public Post() {}
	
	public Post(String sido) {
		this.sido = sido;
	}
	
	public Post(String sido, String doro) {
		this.sido = sido;
		this.doro = doro;
	}

	public Post(String zipcode, String sido, String sigungu, String doro, int building1, int building2) {
		this.zipcode = zipcode;
		this.sido = sido;
		this.sigungu = sigungu;
		this.doro = doro;
		this.building1 = building1;
		this.building2 = building2;
	}

	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getDoro() {
		return doro;
	}
	public void setDoro(String doro) {
		this.doro = doro;
	}
	public int getBuilding1() {
		return building1;
	}
	public void setBuilding1(int building1) {
		this.building1 = building1;
	}
	public int getBuilding2() {
		return building2;
	}
	public void setBuilding2(int building2) {
		this.building2 = building2;
	}

	

	@Override
	public String toString() {
		return String.format("%s", sido);
	}

	public String[] toArray(){
		String addr = String.format("%s %s %s %s%s", sido, sigungu, doro, building1, building2==0?"":"-"+building2);
		return new String[]{zipcode, addr};
	}
}
