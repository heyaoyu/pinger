package org.gyf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hyy.mns.daos.CheckDAO;
import org.hyy.mns.daos.RRDAO;
import org.hyy.mns.models.Check;
import org.hyy.mns.models.RequestResult;

import net.sf.json.*;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public class CheckReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RRDAO dao = RRDAO.newInstance();
		String checkId = request.getParameter("id");
		Check check = CheckDAO.newInstance().getCheck(Long.valueOf(checkId));
		List<RequestResult> rrList = dao.getResultBy(check);
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONObject json = new JSONObject();
		int index = 0;
		for (int i = rrList.size() - 1; i >= 0; i--) {
			RequestResult requestResult = rrList.get(i);
			Date date = requestResult.getTs();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
			String time = df.format(date);
			map.put("date", time);
			map.put("value", requestResult.getResponseTime());
			json.put(index, map);
			index++;
		}
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.getWriter().println(json.toString());
		response.getWriter().flush();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
