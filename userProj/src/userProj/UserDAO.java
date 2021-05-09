package userProj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public List<UserVO> getUserList() {
		conn = DBCon.getConnect();
		List<UserVO> list = new ArrayList<>();
		try {
			psmt = conn.prepareStatement("select * from user_temp order by user_id");
			rs = psmt.executeQuery();
			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setId(rs.getString("user_id"));
				vo.setName(rs.getString("user_name"));
				vo.setPhone(rs.getString("user_phone"));
				vo.setGender(rs.getString("user_gender"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public UserVO insertUser(UserVO vo) {
		conn = DBCon.getConnect();
		String sql = "insert into user_temp values(?,?,?,?,?)";
		UserVO user = new UserVO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getPass());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getGender());

			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨.");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return user;

	}
	
	public boolean updateUser(UserVO vo) {
		conn = DBCon.getConnect();
		int modifyCnt = 0;
		String sql = "UPDATE user_temp SET user_phone = ? WHERE user_id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPhone());
			psmt.setString(2, vo.getId());
			modifyCnt = psmt.executeUpdate();
			System.out.println(modifyCnt + "건 수정됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return modifyCnt == 0? false : true; 
	}	

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
