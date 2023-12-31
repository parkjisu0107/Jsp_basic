package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/session/login")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SessionServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("로그아웃 요청이 들어옴!");
		// 특정 세션 데이터를 삭제하는 메서드
		request.getSession().removeAttribute("user_id");
		
		// 모든 세션 데이터를 삭제하는 메서드(세션 객체 자체를 무효화)
		request.getSession().invalidate();
		
		response.sendRedirect("/JspBasic/session/session_login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 문자열 인코딩
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("abc1234") && pw.equals("aaa1111!")) {
			
			/*
            - 세션은 http 통신 데이터를 유지하기 위한 수단으로 사용합니다.
            - 세션에 데이터를 저장할 때는 session 객체의 메서드 setAttribute()
             메서드를 사용합니다.
            - 해당 메서드의 첫번째 매개값으로 세션 데이터의 이름을 정하고, 두 번째 매개값으로
             세션에 저장할 값을 지정합니다.
            */
			
			HttpSession session = request.getSession();
			session.setAttribute("user_id", id);
			
			// 세션의 유효 시간 설정
			// 세션의 수명은 새로운 요청이 서버로 들어오면 초기화됩니다.
			session.setMaxInactiveInterval(60 * 60); // 초 단위의 세션 유효시간 설정.
	
			response.sendRedirect("/JspBasic/session/session_welcome.jsp");
			
		} else {
			response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter w = response.getWriter();
            
            String htmlCode = "";
            htmlCode += "<script>\r\n"
                    + "        alert('로그인에 실패했습니다.');\r\n"
                    + "        history.back();\r\n"
                    + "    </script>\r\n"
                    + "";
            
            w.write(htmlCode);
            w.flush();
            w.close();
		}
	}

}
