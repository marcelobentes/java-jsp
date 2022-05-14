package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;

/**
 * Servlet Filter implementation class FilterAutenticacao
 */
@WebFilter(urlPatterns = {"/principal/*"})//intercepetas todas as requisi��es que vierem do projeto ou mapeamento
public class FilterAutenticacao extends HttpFilter implements Filter {
	
	private static Connection connection;
       
    
	private static final long serialVersionUID = 1L;


	public FilterAutenticacao() {
        super();
    }

	//encerra o processo quando o servidor � parado
	public void destroy() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	/*Interceptas as requisi��es e as respostas no sistema*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath();/* Url que esta sendo acessada*/
		
		/*Validar se esta logado, sen�o redireciona para a tela de login*/
		if (usuarioLogado == null  && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor efetuar o login");
			redireciona.forward(request, response);
			return; /*Para a execu��o e redireciona para o login*/
		}else {
		
		chain.doFilter(request, response);
		
		}
		
		connection.commit();/*salva as altera��o no banco*/
		
		}catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnectionBanco.getConnection();
		
	}

}
