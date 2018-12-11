package Server;

public class User {

	private int id;
	private String username;
	private String password;
	private String name;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public User(int id, String username, String password, String name, String lastName, String email,
			String phoneNumber) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

}
