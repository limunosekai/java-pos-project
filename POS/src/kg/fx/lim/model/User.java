package kg.fx.lim.model;

public class User {
	// 멤버필드
	private int code;
	private String name;
	private String id;
	private String passwd;
	private String tel;
	private String address;
	
	// 생성자
	public User() {
	}

	public User(int code, String name, String id, String passwd, String tel, String address) {
		super();
		this.code = code;
		this.name = name;
		this.id = id;
		this.passwd = passwd;
		this.tel = tel;
		this.address = address;
	}

	// 메소드
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
