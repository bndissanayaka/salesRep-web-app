package com.casrilanka.test.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.casrilanka.test.dao.SalesRepDAO;
import com.casrilanka.test.model.SalesRep;

import jakarta.servlet.annotation.WebServlet;


public class salesRepServlet {
	@WebServlet("/")
	public class UserServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private SalesRepDAO salesRepDAO;
		
		public void init() {
			salesRepDAO = new SalesRepDAO();
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request, response);
		}

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action = request.getServletPath();

			try {
				switch (action) {
				case "/new":
					showAddForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				default:
					listUser(request, response);
					break;
				}
			} catch (SQLException ex) {
				throw new ServletException(ex);
			}
		}

		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<SalesRep> listUser = salesRepDAO.selectAllReps();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
			dispatcher.forward(request, response);
		}

		private void showAddForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);
		}

		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			SalesRep existingUser = salesRepDAO.selectRep(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			request.setAttribute("user", existingUser);
			dispatcher.forward(request, response);

		}

		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String contactNumber = request.getParameter("contactNumber");
			String joinedDate = request.getParameter("joinedDate");
			String cuurentRoute = request.getParameter("cuurentRoute");
			String comment = request.getParameter("comment");
			
			SalesRep newUser = new SalesRep(0, name, email, contactNumber, joinedDate, cuurentRoute, comment);
			salesRepDAO.insertRep(newUser);
			response.sendRedirect("list");
		}

		private void updateUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String contactNumber = request.getParameter("contactNumber");
			String joinedDate = request.getParameter("joinedDate");
			String cuurentRoute = request.getParameter("cuurentRoute");
			String comment = request.getParameter("comment");

			SalesRep updatedRaw = new SalesRep(id, name, email, contactNumber, joinedDate, cuurentRoute, comment);
			salesRepDAO.updateUser(updatedRaw);
			response.sendRedirect("list");
		}

		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			salesRepDAO.deleteRep(id);
			response.sendRedirect("list");

		}

	}
}
