package kg.fx.lim.login.view;

import java.sql.*;
import java.util.ArrayList;
import kg.fx.lim.model.User;

/**
 * ---------------------------------------------------------------------------
 * Nintendo POS ver 1.0 DatabaseController
 * 
 * @author 임성현
 *         ---------------------------------------------------------------------------
 */

public class DatabaseController {

	// --------------------------------------------------멤버필드

	// JDBC 드라이버
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	// DB 사용자명
	private final String USER_NAME = "root";
	// DB 사용자 비밀번호
	private final String PASSWORD = "limhyn1130";
	// 접속할 DB의 주소
	private final String DB_URL = "jdbc:mysql://localhost:3306/pos_db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";

	Connection conn = null;
	PreparedStatement pstm = null;

	// ---------------------------------------------------생성자

	/**
	 * ---------------------------------------------------DB 연동
	 */
	public DatabaseController() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("SQLException : " + e.getMessage());
			System.out.println("SQLState : " + e.getSQLState());
			System.out.println("VendorError : " + e.getErrorCode());
		}
	}

	// 메소드
	/**
	 * ----------------------------------------DB에서 사용자가 입력한 id에 맞는 password 반환
	 */
	public String loadPassword(String id) {

		ResultSet rs = null;

		String password = "";

		try {
			String sql = "SELECT user_password FROM user WHERE user_id=" + "'" + id + "'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				password = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception ex) {}
			try {if (pstm != null) pstm.close();} catch(Exception ex) {}
			try {if (conn != null) conn.close();} catch(Exception ex) {}
		}
		return password;
	}

	/**
	 * ----------------------------------------DB에서 사용자가 입력한 id에 맞는 userName 반환
	 */
	public String loadUserName(String id) {
		ResultSet rs = null;

		String userName = "";

		try {
			String sql = "SELECT user_name FROM user WHERE user_id=" + "'" + id + "';";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				userName = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception ex) {}
			try {if (pstm != null) pstm.close();} catch(Exception ex) {}
			try {if (conn != null) conn.close();} catch(Exception ex) {}
		}
		return userName;
	}

	/**
	 * ----------------------------------------DB에서 admin 이외의 userName 모두 반환
	 */
	public ArrayList<String> loadAllUserName() {
		ResultSet rs = null;

		ArrayList<String> userNames = new ArrayList<>();

		try {
			String sql = "SELECT user_name FROM user WHERE NOT user_id='admin';";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				userNames.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception ex) {}
			try {if (pstm != null) pstm.close();} catch(Exception ex) {}
			try {if (conn != null) conn.close();} catch(Exception ex) {}
		}
		return userNames;
	}
	
	/**
	 * ----------------------------------------DB에 사용자 데이터 저장하기
	 */
	public int saveUserData(User user) {
		int result = 0;
		try {
			String sql = "INSERT INTO user VALUES(?,?,?,?,?,?);";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,user.getCode());
			pstm.setString(2,user.getName());
			pstm.setString(3,user.getId());
			pstm.setString(4,user.getPasswd());
			pstm.setString(5,user.getTel());
			pstm.setString(6,user.getAddress());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (pstm != null) pstm.close();} catch(Exception e) {}
			try {if (conn != null) pstm.close();} catch(Exception e) {}
		}
		return result;
	}
	
	/**
	 * ----------------------------------------DB에 사용자 데이터 저장하기
	 */
	public int deleteUserData(User user) {
		int result = 0;
		try {
			String sql = "DELETE FROM user WHERE user_code = ?;";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,user.getCode());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (pstm != null) pstm.close();} catch(Exception e) {}
			try {if (conn != null) pstm.close();} catch(Exception e) {}
		}
		return result;
	}
	
	/**
	 * ---------------------------------------- DB에 사용자 데이터 업데이트
	 */
	public int updateUserData(User user) {
		int result = 0;
		try {
			String sql = "UPDATE user SET user_name = ?, user_id = ?, user_password = ?,"
					+ "user_tel = ?, user_address = ? WHERE user_code = ?;";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,user.getName());
			pstm.setString(2,user.getId());
			pstm.setString(3,user.getPasswd());
			pstm.setString(4,user.getTel());
			pstm.setString(5,user.getAddress());
			pstm.setInt(6,user.getCode());
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {if (pstm != null) pstm.close();} catch(Exception e) {}
			try {if (conn != null) pstm.close();} catch(Exception e) {}
		}
		return result;
	}
	
	/**
	 * ----------------------------------------DB에 사용자 리스트 가져오기
	 */
	public ArrayList<User> loadAllUserList() {
		ResultSet rs = null;
		ArrayList<User> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM user WHERE NOT user_id='admin';";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setCode(rs.getInt("user_code"));
				user.setName(rs.getString("user_name"));
				user.setId(rs.getString("user_id"));
				user.setPasswd(rs.getString("user_password"));
				user.setTel(rs.getString("user_tel"));
				user.setAddress(rs.getString("user_address"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception ex) {}
			try {if (pstm != null) pstm.close();} catch(Exception ex) {}
			try {if (conn != null) conn.close();} catch(Exception ex) {}
		}
		return list;
	}
	
	/**
	 * ---------------------------------------- id 중복 체크
	 */
	public boolean duplicateIdCheck(String id) {
		ResultSet rs = null;
		try {
			String sql = "SELECT user_id FROM user;";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("user_id").equals(id)) {
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception ex) {}
			try {if (pstm != null) pstm.close();} catch(Exception ex) {}
		}
		return false;
	}
	
	/**
	 * ---------------------------------------- update id 중복 체크
	 */
	public boolean duplicateIdCheckForUpdate(String inputId, String temp) {
		ResultSet rs = null;
		try {
			String sql = "SELECT user_id FROM user WHERE NOT user_id = ?;";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, temp);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("user_id").equals(inputId)) {
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception ex) {}
			try {if (pstm != null) pstm.close();} catch(Exception ex) {}
		}
		return false;
	}
	
	/**
	 * ---------------------------------------- code 중복 체크
	 */
	public boolean duplicateCodeCheck(int code) {
		ResultSet rs = null;
		try {
			String sql = "SELECT user_code FROM user;";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(rs.getInt("user_code") == code) {
					return true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null) rs.close();} catch(Exception ex) {}
			try {if (pstm != null) pstm.close();} catch(Exception ex) {}
		}
		return false;
	}
}