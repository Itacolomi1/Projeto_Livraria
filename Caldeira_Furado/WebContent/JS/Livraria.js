var api_key = 'AIzaSyDcvOZV7sFO5cTjqq2oUW6M4zTq97aYkQc';
var api_news_Key = '2iMweAEJf8vdocUAi7VUYKZa22C8vOXy';
var url_base_news = `https://api.nytimes.com/svc/search/v2/articlesearch.json?q=harryPotter&field-name:("Harry Potter")&api-key=`;
var hp_key = '$2a$10$4Lup07NIucJ3F01MYN37y.h2YHGAPqmMxfE97RgfT7omp9VrSQxHS';
var url_base = 'https://www.googleapis.com/books/v1/volumes?q=intitle:';
var url_base_hp = 'https://www.potterapi.com/v1';

var bookslist = []
var newslist = []
var characterList = []
var itensList =[]
var lista_grafico =[]
var valor_Janeiro = 0;
var venda_Janeiro = 0;
var valor_Fevereiro = 0;
var venda_Fevereiro = 0;
var valor_Marco = 0;
var venda_Marco = 0;
var valor_Abril = 0;
var venda_Abril = 0;
var valor_Maio = 0;
var venda_Maio = 0;
var valor_Junho = 0;
var venda_Junho = 0;
var valor_Julho = 0;
var venda_Julho = 0;
var valor_Agosto = 0;
var venda_Agosto = 0;
var valor_Setembro = 0;
var venda_Setembro = 0;
var valor_Outubro = 0;
var venda_Outubro = 0;
var valor_Novembro = 0;
var venda_Novembro = 0;
var valor_Dezembro = 0;
var venda_Dezembro = 0;

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
var livraria = function() {

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
            descricaoTopico: "#descricaoTopico",
            email_login: "#email_login",
            senha_login: "#senha_login"          
    
        };
    }

    var buscaUsuario = function() {
       
        var email = $(controles().emailuser).val();
        request= "http://localhost:8080/Caldeira_Furado/UsuarioApi?email="+ email;      

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

    var AlteraUsuario = function(){      
        var cod_usuario = $(controles().cod_usuario).val(); 
        var email = $(controles().emailuser).val();
        var nome = $(controles().nome_altera).val(); 
        var password = $(controles().password_altera).val();          
     
        
        request= "http://localhost:8080/Caldeira_Furado/UsuarioApi?id=" + cod_usuario +        
                                        "&nome=" + nome + "&email=" + email + "&senha=" + password;
        

        $.ajax({
            type:'put',
            url: request,                    
        })
            .done(function(returned) {                
             console.log("Usuario alterado")
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

    var LogarUsuario = function(){

        var email = $(controles().email_login).val();
        var senha = $(controles().senha_login).val();
        request= "http://localhost:8080/Caldeira_Furado/LoginApi?action=logar&email="+ email + "&senha=" + senha;      

        $.ajax({
            type:'get',
            url: request,            
            dataType:'json',
        })
            .done(function(returned) {                
               alert(returned);
            })
            .fail(function(jqXHR) {
                alert("erro ao realizar o login");
            });

    }

    var ValidaUsuarioForum = function(){


        request= "http://localhost:8080/Caldeira_Furado/LoginApi?action=verifica";      

        $.ajax({
            type:'get',
            url: request,            
            dataType:'json',
        })
            .done(function(returned) {    
                debugger;
                if(returned == "true"){
                    window.location.href = "forum.html";
                }else{
                    alert("Precisa estar logado no sistema para acessar o Fórum");
                }
               
            })
            .fail(function(jqXHR) {
                alert("erro ao validar o login");
            });
        
    }

    var ValidaUsuarioCarrinho = function(){


        request= "http://localhost:8080/Caldeira_Furado/LoginApi?action=verifica";      

        $.ajax({
            type:'get',
            url: request,            
            dataType:'json',
        })
            .done(function(returned) {    
                debugger;
                if(returned == "true"){
                    window.location.href = "carrinho.html";
                }else{
                    alert("Precisa estar logado no sistema para acessar o Fórum");
                }
               
            })
            .fail(function(jqXHR) {
                alert("erro ao validar o login");
            });
        
    }
    var search_books = function() {
        var content = document.getElementById(controles().search_books).value;
        var request = url_base + content + '&filter=partial&projection=lite&key=' + api_key;     

        fetch(request)
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                clearList(controles().booksList);
                bookslist = data.items;           
                bookslist.forEach(list_books);
            }).catch(function(error) {
                console.log('Request failed', error);
            });

    }

    var list_books = function(book, i) {
        var div = document.getElementById(controles().booksList);
        var bloco = document.createElement('div');
        bloco.className = "ocultar";
        div.appendChild(bloco);
        var divId = document.createElement('div');        
        divId.innerHTML = book.id;
        divId.style.display = 'none';
        divId.id = 'id_livro' + i;
        bloco.appendChild(divId);        
        var img = document.createElement('img');
        img.src = book.volumeInfo.imageLinks.thumbnail
        bloco.appendChild(img);
        var h1 = document.createElement('h1');
        h1.innerHTML = book.volumeInfo.title
        h1.id = "id_titulo" + i;
        bloco.appendChild(h1);
        var p = document.createElement('p');
        p.innerHTML = book.volumeInfo.description
        bloco.appendChild(p);
        var preco = document.createElement('span');
        preco.innerHTML = "R$ " + (Math.floor(Math.random() * (100 - 20)) + 20) + ".00";
        preco.id = "id_valor"+ i;
        bloco.appendChild(preco);
        var d = document.createElement('button');
        d.className = "btn-menos";
        d.innerHTML = "Saiba menos -"
        d.addEventListener('click', function() { $(this).parent('div').addClass('ocultar'); });
        bloco.appendChild(d);
        var b = document.createElement('button');
        b.className = "btn-saiba";
        b.innerHTML = "Saiba mais +"
        b.addEventListener('click', function() { $(this).parent('div .ocultar').removeClass('ocultar'); });
        bloco.appendChild(b);
        var c = document.createElement('button');
        c.className = "btn-comprar";
        c.addEventListener('click', function(){livraria.adiciona_carrinho(i)});
        c.innerHTML = "COMPRAR"
        bloco.appendChild(c);


    }
    var list_news = function(news, i) {
        var d = document.getElementById(controles().news);

        var a = document.createElement('div');
        if (i == 0) {
            a.className = "carousel-item active";
        } else {
            a.className = "carousel-item ";
        }
        d.appendChild(a);
        var p = document.createElement('p');
        p.innerHTML = news.abstract;
        p.className = "titulo-noticia";
        a.appendChild(p);
        var l = document.createElement('a');
        l.className = "";
        l.innerHTML = "Saiba mais +"
        a.appendChild(l);
        l.href = news.web_url;
        l.target = "_blank";

    }
    var harry_potter_head = function() {
        var request = url_base_hp + '/sortingHat';
        console.log(request);

        fetch(request)
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                document.getElementById(controles().resultado).innerHTML = data;


            }).catch(function(error) {
                console.log('Request failed', error);
            });
    }

    var harry_potter_news = function() {
        var request = url_base_news + api_news_Key;

        console.log(request);

        fetch(request)
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                clearList(controles().news);

                newslist = data.response.docs;
                newslist.forEach(list_news);
            }).catch(function(error) {
                console.log('Request failed', error);
            });

    }

    var harry_potter_character = function() {
        var request = url_base_hp + '/characters?key=' + hp_key;
        console.log(request);

        fetch(request)
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {

                characterList = data;
                var character = characterList[Math.floor((Math.random() * (characterList.length - 1)) + 1)];
                document.getElementById(controles().resultadoPersonagem).innerHTML = character.name;

            }).catch(function(error) {
                console.log('Request failed', error);
            });




    }

    var adiciona_carrinho = function(numero){        
        var livro = {
            "id":"",
            "titulo":"",
            "valor":""
        }
        livro.id = $('#id_livro' + numero).text();
        livro.titulo = $("#id_titulo" + numero).text();
        livro.valor = $("#id_valor" + numero).text();
             
        livro.valor = livro.valor.substring(livro.valor.indexOf("$")+2,livro.valor.length)
        request= "http://localhost:8080/Caldeira_Furado/LivrariaApi?id_livro="+ livro.id +"&titulo=" + livro.titulo + "&valor=" + livro.valor + "&action=buy";      

        $.ajax({
            type:'get',
            url: request    
            
        })
            .done(function(returned) { 
                debugger;               
                alert(returned);
            })
            .fail(function(jqXHR) {
                alert("Erro ao adicionar ao carrinho")
            });
    }

    var load_carrinho = function(){
        request= "http://localhost:8080/Caldeira_Furado/LivrariaApi?action=list"      

        $.ajax({
            type:'get',
            url: request,
            dataType:'json'    
            
        })
            .done(function(returned) {                
                itensList = returned;
                $(controles().table_carrinho).html("");
                itensList.forEach(lista_produtos);
            })
            .fail(function(jqXHR) {
                alert("Erro ao adicionar ao carrinho")
            });

    }

    var remove_carrinho = function(numero){
      
        request= "http://localhost:8080/Caldeira_Furado/LivrariaApi?action=delete&id_livro=" + numero;      

        $.ajax({
            type:'get',
            url: request           
        })
            .done(function(returned) { 
                alert(returned);
            })
            .fail(function(jqXHR) {
                alert("Erro ao adicionar ao carrinho")
            });

    }

    var lista_produtos = function(livro,i){        
            livro.Quantidade = 1;
            livro.Cod_Livro = '"' + livro.Cod_Livro + '"';
            $(controles().table_carrinho)
            .append( 
                "<tr>" + 
                "<td style='display:none'>" + livro.Cod_Livro+"</td>" +
                "<td>" + livro.Descricao+"</td>" +
                "<td>" + livro.Valor+"</td>"+ 
                "<td> <div class='quantidade'>"+
                    "<button onclick='manutencao()'>+</button>"+
                    "<label class='qtd'>" + livro.Quantidade +"</label>" +
                    "<button onclick='manutencao()'>-</button>" +
                    "<button class= 'apagar' onclick='livraria.remove_carrinho("+ livro.Cod_Livro +")'>Excluir</button>" +
                "</div></td>"+
                "</tr>"                      
            );

    }  

    var comprar_produtos = function(){
        request= "http://localhost:8080/Caldeira_Furado/LivrariaApi";      

        $.ajax({
            type:'post',
            url: request            
            
        })
            .done(function(returned) {
                alert("A sua compra foi efetuada com sucesso")

            })
            .fail(function(jqXHR) {
                alert("Erro ao realizar as compras")
            });

    }

    var search_adress = function() {

        var cep = document.getElementById(controles().ceplabel).value;
        //console.log(cep);
        var url_base_cep = `https://viacep.com.br/ws/${cep}/json/`;

        fetch(url_base_cep)
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                // debugger;
                var endereco = data;
                document.getElementById(controles().rua).innerHTML = "<strong> Rua: </strong>" + data.logradouro;
                document.getElementById(controles().bairro).innerHTML = "<strong> Bairro: </strong>" + data.bairro;
                document.getElementById(controles().cidade).innerHTML = "<strong> Localidade: </strong>" + data.localidade;
                document.getElementById(controles().numerocasa).className = "visivel";
                document.getElementById(controles().complemento).className = "visivel";
            }).catch(function(error) {
                console.log('Request failed', error);
            });


    }

    var clearList = function(id_lista) {
        var ul = document.getElementById(id_lista);
        ul.innerHTML = '';
    }
    
    var carrega_vendas = function() {              
       
        request= "http://localhost:8080/Caldeira_Furado/LivrariaApi?action=list_historico";      

        $.ajax({
            type:'get',
            url: request,
            dataType:'json'                           
        })
            .done(function(returned) {                
                grafico(returned);        

            })
            .fail(function(jqXHR) {
                
                alert("Erro ao carregar comentário")
            });
    }

    var dados_grafico = function(item, i) {
        if (item.Data_Venda.includes("jan")) {
            valor_Janeiro += item.Valor;
            venda_Janeiro++;
        }
        if (item.Data_Venda.includes("fev")) {
            valor_Fevereiro += item.Valor;
            venda_Fevereiro++;
        }
        if (item.Data_Venda.includes("marc")) {
            valor_Marco += item.Valor;
            venda_Marco++;
        }
        if (item.Data_Venda.includes("abr")) {
            valor_Abril += item.Valor;
            venda_Abril++;
        }
        if (item.Data_Venda.includes("maio")) {
            valor_Maio += item.Valor;
            venda_Maio++;
        }
        if (item.Data_Venda.includes("jun")) {
            valor_Junho += item.Valor;
            venda_Junho++;
        }
        if (item.Data_Venda.includes("jul")) {
            valor_Julho += item.Valor;
            venda_Julho++;
        }
        if (item.Data_Venda.includes("ago")) {
            valor_Agosto += item.Valor;
            venda_Agosto++;
        }
        if (item.Data_Venda.includes("set")) {
            valor_Setembro += item.Valor;
            venda_Setembro++;
        }
        if (item.Data_Venda.includes("out")) {
            valor_Outubro += item.Valor;
            venda_Outubro++;
        }
        if (item.Data_Venda.includes("nov")) {
            valor_Novembro += item.Valor;
            venda_Novembro++;
        }
        if (item.Data_Venda.includes("dez")) {
            valor_Dezembro += item.Valor;
            venda_Dezembro++;
        }

    }
  
    var grafico = function(lista) {

        lista.forEach(dados_grafico);   
        let chart = new Chart(primeiroGrafico, {
        type: 'line',
        data: {

            labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
            datasets: [{
                label: 'Compras X Mês',
                backgroundColor: '#873436',
                borderColor: 'black',
                data: [venda_Janeiro, venda_Fevereiro, venda_Marco, venda_Abril, venda_Maio, venda_Junho, venda_Julho, venda_Agosto, venda_Setembro, venda_Outubro, venda_Novembro, venda_Dezembro]

            }, {
                label: 'Total Gasto x Mês',
                data: [valor_Janeiro, valor_Fevereiro, valor_Marco, valor_Abril, valor_Maio, valor_Junho, valor_Julho, valor_Agosto, valor_Setembro, valor_Outubro, valor_Novembro, valor_Dezembro],
                backgroundColor: "#fbc12e",
                borderColor: "black"
            }]
        }
    });
    }
   

    return {
        search_books: search_books,
        harry_potter_head: harry_potter_head,
        harry_potter_news: harry_potter_news,
        harry_potter_character: harry_potter_character,
        search_adress: search_adress, 
        buscaUsuario: buscaUsuario,
        AlteraUsuario: AlteraUsuario,
        DeletaUsuario: DeletaUsuario,
        adiciona_carrinho: adiciona_carrinho,
        load_carrinho: load_carrinho,           
        comprar_produtos: comprar_produtos,
        remove_carrinho: remove_carrinho,
        LogarUsuario: LogarUsuario,
        ValidaUsuarioForum: ValidaUsuarioForum,
        ValidaUsuarioCarrinho: ValidaUsuarioCarrinho,
        carrega_vendas: carrega_vendas
            
    };

}();