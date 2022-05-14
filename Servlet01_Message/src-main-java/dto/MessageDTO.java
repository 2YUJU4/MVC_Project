public class MessageDTO {
    private String nickname;
	private String massage;
	
	public MessageDTO() {}
	
	public MessageDTO(String nickname, String massage) {
		super();
		this.nickname = nickname;
		this.massage = massage;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	@Override
	public String toString() {
		return nickname + " : " +  massage;
	}
}
