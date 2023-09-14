package com.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.model.BoardRepository;

public class ModifyService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int bId = Integer.parseInt(request.getParameter("bId"));
		BoardRepository.getInstance().getContent(bId);
		request.setAttribute("article", BoardRepository.getInstance().getContent(bId));
		request.setAttribute("boardNo", bId);
	}

}
