package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelLogin;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(urlPatterns = {"/principal/ServletLogin","/ServletLogin"})/*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //recebe os dados pela url em parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//recebe os dados enviados por um formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = (request.getParameter("login"));
		String senha = (request.getParameter("senha"));
		String url = request.getParameter("url");
		
		if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
			

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if (modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {//simulação de login
				
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if(url == null || url.equals("null")) {
					url = "principal/principal.jsp";
					
				}
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);//redicionando caso não for informado login e senha.
				redirecionar.forward(request, response);
				
				
			}else {
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");//redicionando caso não for informado login e senha.
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
			
			
			
		}else {
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");//redicionando caso não for informado login e senha.
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}
		
		
	}

}
