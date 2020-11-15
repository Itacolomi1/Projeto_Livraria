


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
            descricaoTopico: "#descricaoTopico"
    
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
                alert("Tópico inserido com sucesso");
            })
            .fail(function(jqXHR) {
                console.log('Erro');
            });

    }

    var buscaTopico = function() {
       
       // var email = $(controles().emailuser).val();
        request= "http://localhost:8080/Caldeira_Furado/UsuarioApi";      

        $.ajax({
            type:'get',
            url: request,            
            dataType:'json',
        })
            .done(function(returned) {                
                console.log(returned);
                $(controles().cod_usuario).val(returned.Cod_Usuario)
                $(controles().nome_altera).val(returned.Nome);
                $(controles().password_altera).val(returned.Senha);
            })
            .fail(function(jqXHR) {
                console.log('Erro');
            });

    }



    var DeletaUsuario = function(){
        var cod_usuario = $(controles().cod_usuario).val();              
        
        request= "http://localhost:8080/Caldeira_Furado/UsuarioApi?id=" + cod_usuario;
        

        $.ajax({
            type:'delete',
            url: request,                        
        })
            .done(function(returned) {                
                console.log("Usuario deletado");                 
            })
            .fail(function(jqXHR) {
                console.log('Erro');
            });

    }  


    return {     
        inserirTopico: inserirTopico         
    };

}();