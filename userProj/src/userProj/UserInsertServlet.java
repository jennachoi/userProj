package userProj;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInsertServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	response.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String pass = request.getParameter("pass");
	String phone = request.getParameter("phone");
	String gender = request.getParameter("gender");
	
	UserDAO dao = new UserDAO();
	UserVO vo = new UserVO();
	
	vo.setId(id);
	vo.setName(name);
	vo.setPass(pass);
	vo.setPhone(phone);
	vo.setGender(gender);
	
	dao.insertUser(vo);
	
	}

}
