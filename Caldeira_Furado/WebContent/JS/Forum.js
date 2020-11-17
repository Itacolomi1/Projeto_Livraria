
var itensList =[]

function manutencao() { alert("Ainda estamos trabalhando nessa função"); }

function abrircadastrar() {
    var principal = document.getElementById("cadastro");
    if (principal.classList.contains('busca')) {
        $('#cadastro').removeClass('busca');
        $('#cadastro').addClass('cadastra');
    } else {
        $('#cadastro').removeClass('primeiro');
        $('#cadastro').addClass('cadastra');
    }

}

function abrirbusca() {
    var principal = document.getElementById("cadastro");
    if (principal.classList.contains('cadastra')) {
        $('#cadastro').removeClass('cadastra');
        $('#cadastro').addClass('busca');
    } else {
        $('#cadastro').removeClass('primeiro');
        $('#cadastro').addClass('busca');
    }

}

function abririncial() {
    var principal = document.getElementById("cadastro");
    if (principal.classList.contains('cadastra')) {
        $('#cadastro').removeClass('cadastra');
        $('#cadastro').addClass('primeiro');
    } else {
        $('#cadastro').removeClass('busca');
        $('#cadastro').addClass('primeiro');
    }

}

function compraRealizada() {
    alert("Compra realizada com sucesso");
}
var forum = function() {

    //Ids dos elementos da tela
    var controles = function() {
        return {
            search_books: "search_books",
            booksList: "booksList",
            resultado: "resultado",
            news: "news",
            resultadoPersonagem: "resultadoPersonagem",
            rua: "rua",
            ceplabel: "ceplabel",
            bairro: "bairro",
            cidade: "cidade",
            numerocasa: "numerocasa",
            complemento: "complemento",
            emailuser: "#emailbusca",
            nome_altera: "#nome_altera",
            password_altera: "#password_altera",
            cod_usuario: "#cod_usuario",
            table_carrinho: "#table_body",
            descricaoComentario: "#descricaoComentario",
            lista_Topicos: "#listatopicos",
            lista_Comentarios:"#listacomentariosmaster"
    
        };
    }

    var inserirTopico = function() {  

        var descricao= $("#descricaoTopico").val();    
        
        request= "http://localhost:8080/Caldeira_Furado/ForumAPI?action=topico&desctopico="+ descricao;      

        $.ajax({
            type:'post',
            url: request
        })
            .done(function(returned) {                
                alert("Tópico inserido com sucesso. Recarregue a página");
            })
            .fail(function(jqXHR) {
                console.log('Erro');
            });

    }

    var inserirComentario = function(codigo) {  

        var descricao= $(controles().descricaoComentario).val();
    
        
        request= "http://localhost:8080/Caldeira_Furado/ForumAPI?action=coment&desctopico="+ descricao +"&idTopico="+ codigo;      

        $.ajax({
            type:'post',
            url: request
        })
            .done(function(returned) {                
                alert("Comentário inserido com sucesso");
            })
            .fail(function(jqXHR) {
                console.log('Erro');
            });

    }

    var carregaTopico = function() {       
       
        request= "http://localhost:8080/Caldeira_Furado/ForumAPI?action=topics";      

        $.ajax({
            type:'get',
            url: request,
            dataType:'json'    
            
        })
            .done(function(returned) {
                debugger;                
                itensList = returned;
                $(controles().lista_Topicos).html("");
                itensList.forEach(lista_topic);

            })
            .fail(function(jqXHR) {
                alert("Erro ao carregar tópico")
            });
    }

    var carregaComentario = function(codigo) {       
       
        request= "http://localhost:8080/Caldeira_Furado/ForumAPI?action=list"+"&idTopico="+ codigo;      

        $.ajax({
            type:'get',
            url: request,
            dataType:'json'               
            
        })
            .done(function(returned) {
                debugger;                
                itensList = returned;
                $(controles().lista_Comentarios).html("");
                itensList.forEach(lista_coment);

            })
            .fail(function(jqXHR) {
                debugger;
                alert("Erro ao carregar comentário")
            });
    }

    var lista_topic = function(topico,i){        
               
        $(controles().lista_Topicos)
        .append( 

            "<div onclick='forum.carregaComentario("+ topico.Cod_Filho + ")'" + "class='topico'>"+
            "<div style='display:none'>" + topico.Cod_Filho+"</div>"+
            "<p>"+topico.Descricao + "</p>"+
            "<div id='listacomentariosmaster' class='respostas' >"+
             //LINHAS COMENTÁRIO                 
            "</div>"+
           "<div class='perguntar'>"+
                "<input id='descricaoComentario'" + "class='form-control mr-sm-2' type='search' placeholder='escreva um comentário' aria-label='comentario'>"+
                "<button onclick='forum.inserirComentario("+ topico.Cod_Filho + ")'" + " class='btn btn-outline-success my-2 my-sm-0' type='button'>Publicar</button>"+
            "</div>"+
            "</div>"
        );        
            

    }  

    var lista_coment = function(topico,i){        
                
        $(controles().lista_Comentarios)
        .append(     
            "<div class='respostas' >"+
            "<p>"+topico.Descricao + "</p>"+//LINHA COMENTÁRIO
            "</div>"                            
        
        );  
    }

    var DeletaTopico = function(codigo){
        
        request= "http://localhost:8080/Caldeira_Furado/ForumAPI?id=" + codigo;
        

        $.ajax({
            type:'delete',
            url: request,                        
        })
            .done(function(returned) {                
                console.log("Tópico deletado com sucesso");                 
            })
            .fail(function(jqXHR) {
                console.log('Erro');
            });

    }  


    return {     
        inserirTopico: inserirTopico,
        carregaTopico:carregaTopico,
        inserirComentario:inserirComentario,
        carregaComentario:carregaComentario,
        DeletaTopico:DeletaTopico
    };

}();