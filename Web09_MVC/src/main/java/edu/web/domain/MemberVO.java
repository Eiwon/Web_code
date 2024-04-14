package edu.web.domain;

public class MemberVO {
	private String memberId;
	private String pw;
	private String name;
	private String email;
	private String phone;
	
	public MemberVO() {}
	
	public MemberVO(String memberId, String pw, String name, String email, String phone) {
		this.memberId = memberId;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", pw=" + pw + ", name=" + name + ", email=" + email + ", phone="
				+ phone + "]";
	}
	
}
