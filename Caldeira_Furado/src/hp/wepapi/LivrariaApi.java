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

import hp.beans.Historico_Compra_Usuario;
import hp.beans.Produto;
import hp.beans.Produto_Venda;
import hp.beans.Usuario;
import hp.beans.Venda;
import hp.model.ProdutoDao;
import hp.model.Produto_VendaDao;
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
		String id_livro = request.getParameter("id_livro");
		Gson gson = new Gson();	
	
		
		if(action.equals("buy")) {
			int exist = isExisting(id_livro,produtos);
			if(exist != -1) {
				response.getWriter().print(gson.toJson("Livro já foi adicionado"));
				response.getWriter().flush();				
			}else {				
				doGet_buy(request,response);
				response.getWriter().print(gson.toJson("Livro adicionado com sucesso!"));
				response.getWriter().flush();	
			}
						
		}else if(action.equals("list")) {
			doGet_list(request,response);			
		}else if(action.equals("list_historico")) {
			doGet_list_historico(request,response);
		}else {				
			doGet_remove(request,response);
			response.getWriter().print(gson.toJson("Livro removido com sucesso. Recarregue a página"));
			response.getWriter().flush();	
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
		
	}
	
	protected void doGet_remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id_livro = request.getParameter("id_livro");
		Produto livro = produtos.stream().filter(x->x.getCod_Livro().equals(id_livro)).findFirst().get();		
		produtos.remove(livro);
		
	}
	
	
	protected void doGet_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		
		Gson gson = new Gson();	
		response.getWriter().print(gson.toJson(produtos));
		response.getWriter().flush();	
		
	}
	
	protected void doGet_list_historico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		
		Usuario usuario = new Usuario();
		usuario.setCod_Usuario(1);
		
		Produto_VendaDao pv_dao = new Produto_VendaDao();
		ArrayList<Historico_Compra_Usuario> retorno = pv_dao.comprarUsuario(usuario);
		
		Gson gson = new Gson();	
		response.getWriter().print(gson.toJson(retorno));
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
		ProdutoDao produtoDao = new ProdutoDao();		
		VendaDao vendaDao = new VendaDao();	
		Produto_VendaDao pv_dao = new Produto_VendaDao();
		HttpSession session = request.getSession();
		//inserir primeiro a venda
		
		Venda venda = new Venda();
		venda.setCod_Usuario((int)session.getAttribute("usuario_logado"));  //setando código usuário temporariamente
		venda.setTotal(MaximoVenda(produtos));
		int id_venda = vendaDao.insert_id(venda);
		int id_produto =0;
		
		//inserir produtos e produto_venda
		for (Produto item : produtos) {
				
			 id_produto = produtoDao.insert_id(item);
			 item.setQuantidade(1);
			 
			 Produto_Venda produto_venda = new Produto_Venda();
			 produto_venda.setCod_Produto(id_produto);
			 produto_venda.setCod_Venda(id_venda);
			 produto_venda.setQuantidade(item.getQuantidade());
			 
			 pv_dao.insert(produto_venda);		 
		 		    
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
