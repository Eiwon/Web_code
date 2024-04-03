package edu.web.member;

import java.util.Arrays;

public class MemberVO {
	private String userId;
	private String password;
	private String email;
	private String emailAgree;
	private String[] interest;
	private String phone;
	private String introduce;
	
	public MemberVO() {
		System.out.println("MemberVO() 생성자");
	}
	
	public MemberVO(String userId, String password, String email, String emailAgree, String[] interest, String phone,
			String introduce) {
		this.userId = userId;
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
	
	public String getInterestJoin() {
		return (interest == null) ? "없음" : String.join(",", interest);
	}

	public String getUserId() {
		return userId == null ? "없음" : userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password == null ? "없음" : password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email == null ? "없음" : email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailAgree() {
		return emailAgree == null ? "없음" : emailAgree;
	}

	public void setEmailAgree(String emailAgree) {
		this.emailAgree = emailAgree;
	}

	public String getPhone() {
		return phone == null ? "없음" : phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIntroduce() {
		return introduce == null ? "없음" : introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", password=" + password + ", email=" + email + ", emailAgree="
				+ emailAgree + ", interest=" + Arrays.toString(interest) + ", phone=" + phone + ", introduce="
				+ introduce + "]";
	}
	
}
