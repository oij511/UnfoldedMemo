package linkdb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.MemoDAO;
import myutil.HttpUtil;

public class ThrowStarList implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MainView");
		
		//로그인 체크(세션에 없을 시 로그인페이지로 리다이렉트)
		String user_idx = myutil.HttpUtil.checkLogin(request, response);
		if(user_idx == null) return;

		int page = Integer.parseInt(request.getParameter("page"));
		
		response.setContentType("text/xml;charset=utf-8"); // 한글이 깨질 경우 처리.
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(HttpUtil.sendJson(true, MemoDAO.getInstance().getStarJson(user_idx, page)));

	}

}
