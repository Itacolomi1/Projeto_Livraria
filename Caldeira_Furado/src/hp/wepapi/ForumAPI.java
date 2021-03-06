package hp.wepapi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import hp.beans.Forum;
import hp.beans.Usuario;
import hp.beans.Venda;
import hp.model.ForumDao;
import hp.model.UsuarioDao;

/**
 * Servlet implementation class ForumAPI
 */
@WebServlet("/ForumAPI")
public class ForumAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumAPI() {
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
		// TODO Auto-generated method stub		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action= request.getParameter("action");
		String id = request.getParameter("idTopico");
		Gson gson = new Gson();
		
		
		if(action.equals("topics")) {
			
			Forum forum = new Forum();			
		
			ArrayList<Forum> topic = new ArrayList<Forum>();
			ForumDao dao = new ForumDao();
			topic = dao.findAll(forum);
			
			if(topic != null) {
				response.getWriter().print(gson.toJson(topic));
				response.getWriter().flush();
			}
		}
		else {
			Forum forum2 = new Forum();		
			forum2.setCod_Pai(Integer.parseInt(id));	
			
			
			ArrayList<Forum> coment = new ArrayList<Forum>();
			ForumDao dao2 = new ForumDao();
			coment = dao2.findComents(forum2);
			
			if(coment != null) {
				response.getWriter().print(gson.toJson(coment));
				response.getWriter().flush();
			
			}		
	
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		String action= request.getParameter("action");
		String idTopico = request.getParameter("idTopico");
        HttpSession session = request.getSession(false);
		
		int idLogado = (int)session.getAttribute("usuario_logado");  //setando c�digo usu�rio temporariamente
			
		if(action.equals("topico"))
		{
			Forum forum = new Forum();				
					
			forum.setCod_Filho(0);
			forum.setDescricao(request.getParameter("desctopico"));
			forum.setCod_Pai(0);
			forum.setCod_Usuario(idLogado);	//pegar usuário	
			
			ForumDao dao = new ForumDao();
			dao.insertTopico(forum);
		}
		else if(action.equals("coment"))
		{
			Forum forum = new Forum();				
			
			forum.setCod_Filho(0);
			forum.setDescricao(request.getParameter("desctopico"));
			forum.setCod_Pai(Integer.parseInt(idTopico));
			forum.setCod_Usuario(idLogado);		
			
			ForumDao dao = new ForumDao();
			dao.insert(forum);
		}
		
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
		
		Forum fr = new Forum();	
		HttpSession session = request.getSession(false);
		
		int idLogado = (int)session.getAttribute("usuario_logado");  //setando c�digo usu�rio temporariamente
		
		fr.setCod_Filho(Integer.parseInt(request.getParameter("id")));
		fr.setCod_Usuario(Integer.parseInt(request.getParameter("userId")));		
		fr.setCod_Pai(Integer.parseInt(request.getParameter("IdPai")));
		
		if(fr.getCod_Usuario()== idLogado)
		{
			ForumDao dao = new ForumDao();
			if(fr.getCod_Pai() != 0)
			{
				dao.delete(fr);
			}
			else 
			{
				dao.deleteComentario(fr);
				dao.delete(fr);
			}
			
		}
	}


}
