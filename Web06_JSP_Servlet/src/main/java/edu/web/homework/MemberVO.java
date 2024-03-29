package edu.web.homework;

import java.io.Serializable;

public class MemberVO implements Serializable{
	// useBean을 위한 VO 생성시, parameter name과 변수명이 같아야함
	private String userid;
	private String password;
	private String email;
	private String emailAgree;
	private String[] interest;
	private String phone;
	private String introduce;
	public MemberVO() {
		System.out.println("MemberVO() 생성자");
	}
	
	public MemberVO(String userid, String password, String email, String emailAgree, String[] interest, String phone,
			String introduce) {
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.emailAgree = emailAgree;
		this.interest = interest;
		this.phone = phone;
		this.introduce = introduce;
	}

	public String[] getInterest() {
		return interest;
	}

	public void setInterest(String[] interest) {
		this.interest = interest;
	}

	public String getUserid() {
		return userid == null ? "입력된 ID가 없습니다." : userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password == null ? "입력된 비밀번호가 없습니다." : password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email == null ? "입력된 이메일이 없습니다." : email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAgree() {
		return emailAgree == null ? "수신여부를 선택하지 않았습니다." : emailAgree;
	}

	public void setEmailAgree(String emailAgree) {
		this.emailAgree = emailAgree;
	}

	public String getPhone() {
		return phone == null ? "입력된 전화번호가 없습니다." : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIntroduce() {
		return introduce == null ? "입력된 자기소개가 없습니다." : introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getInterestToString() {
		return interest == null ? "입력된 관심사항이 없습니다." : String.join(", ", interest);
	}
}
