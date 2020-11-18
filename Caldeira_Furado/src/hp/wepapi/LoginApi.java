package hp.wepapi;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import hp.beans.Usuario;
import hp.model.UsuarioDao;

/**
 * Servlet implementation class LoginApi
 */
@WebServlet("/LoginApi")
public class LoginApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    String action = request.getParameter("action");
		
		if(action.equals("logar")) {
		
			UsuarioDao dao = new UsuarioDao();
			Gson gson = new Gson();
		   	
			Usuario usuario_sent = new Usuario();		
			usuario_sent.setEmail(request.getParameter("email"));		
			usuario_sent.setSenha(request.getParameter("senha"));
			
			Usuario usuario_received = dao.find(usuario_sent);
			
			if(usuario_received != null) {
				HttpSession session = request.getSession();
				session.setAttribute("usuario_logado", usuario_received.getCod_Usuario());
				response.getWriter().print(gson.toJson("Login realizado com sucesso!"));
				response.getWriter().flush();
				
			}else{
				response.getWriter().print(gson.toJson("As credenciais informadas não correspondem a um usuário logado"));
				response.getWriter().flush();
				
			}
		} else{
			doGet_verifica_usuario_logado(request,response);
		}
		
		
	}
	
	protected void doGet_verifica_usuario_logado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		
		if(session != null) {
			
			if(session.getAttribute("usuario_logado") != null) {				
				response.getWriter().print(gson.toJson("true"));
				response.getWriter().flush();				
			}else {
				
				response.getWriter().print(gson.toJson("false"));
				response.getWriter().flush();	
			}		
						
		}else {
			response.getWriter().print(gson.toJson("false"));
			response.getWriter().flush();			
		}
		
	
	}	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
