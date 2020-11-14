package hp.wepapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import hp.beans.Produto;
import hp.beans.Usuario;
import hp.beans.Venda;
import hp.model.UsuarioDao;
import hp.model.VendaDao;

/**
 * Servlet implementation class LivrariaApi
 */
@WebServlet("/LivrariaApi")
public class LivrariaApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Produto> produtos = new ArrayList<Produto>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LivrariaApi() {
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
		String action = request.getParameter("action");
		
		if(action.equals("buy")) {					
			doGet_buy(request,response);			
		}else if(action.equals("list")) {
			doGet_list(request,response);			
		}else {				
			doGet_remove(request,response);			
		}			


	}
	
	protected void doGet_buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	
		Produto produto = new Produto();
		produto.setCod_Livro(request.getParameter("id_livro"));
		produto.setDescricao(request.getParameter("titulo"));
		produto.setValor(Float.parseFloat(request.getParameter("valor")));			
		produtos.add(produto);
		response.getWriter().append("foi adicionado");
	
		
		
		
	}
	
	protected void doGet_remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8");
		//response.setContentType("application/json");
		
		//Gson gson = new Gson();
		//response.getWriter().print(gson.toJson(produtos));
		//response.getWriter().flush();
		
	}
	
	
	protected void doGet_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		
		Gson gson = new Gson();	
		response.getWriter().print(gson.toJson(produtos));
		response.getWriter().flush();	
		
	}
	
	private int isExisting(String id, List<Produto> produtos) {
		
		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getCod_Livro().equalsIgnoreCase(id)) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//inserir primeiro a venda
		VendaDao vendaDao = new VendaDao();		
		//setando código usuário temporariamente
		Venda venda = new Venda();
		venda.setCod_Usuario(1);
		venda.setTotal(MaximoVenda(produtos));
		vendaDao.insert(venda);
		
		
		
		
		
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
	
	private float MaximoVenda(List<Produto> produtos) {
		float retorno = 0;
		
		for (Produto item : produtos) {			
		    retorno+= item.getValor();		    
		}
		
		return retorno;
	}

}
