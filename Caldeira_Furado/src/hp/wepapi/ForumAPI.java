package hp.wepapi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hp.beans.Forum;
import hp.beans.Usuario;
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
		response.getWriter().append("teste api forum");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		String action= request.getParameter("action");
			
		if(action.equals("topico"))
		{
			Forum forum = new Forum();	
			
					
			forum.setCod_Filho(0);
			forum.setDescricao(request.getParameter("desctopico"));
			forum.setCod_Pai(0);
			forum.setCod_Usuario(1);		
			
			ForumDao dao = new ForumDao();
			dao.insertTopico(forum);
		}
		/*else
		{
			Forum forum = new Forum();				
			
			forum.setCod_Filho(0);
			forum.setDescricao(request.getParameter("desctopico"));
			forum.setCod_Pai(0);
			forum.setCod_Usuario(1);		
			
			ForumDao dao = new ForumDao();
			dao.insert(forum);
		}
		*/
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
