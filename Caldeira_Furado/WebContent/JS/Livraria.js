var api_key = 'AIzaSyDcvOZV7sFO5cTjqq2oUW6M4zTq97aYkQc';
var api_news_Key = '2iMweAEJf8vdocUAi7VUYKZa22C8vOXy';
var url_base_news = `https://api.nytimes.com/svc/search/v2/articlesearch.json?q=harryPotter&field-name:("Harry Potter")&api-key=`;
var hp_key = '$2a$10$4Lup07NIucJ3F01MYN37y.h2YHGAPqmMxfE97RgfT7omp9VrSQxHS';
var url_base = 'https://www.googleapis.com/books/v1/volumes?q=intitle:';
var url_base_hp = 'https://www.potterapi.com/v1';

var bookslist = []
var newslist = []
var characterList = []

function manutencao() { alert("Ainda estamos trabalhando nessa função"); }

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
            complemento: "complemento"
        };
    }

    var search_books = function() {
        var content = document.getElementById(controles().search_books).value;
        var request = url_base + content + '&filter=partial&projection=lite&key=' + api_key;

        console.log(request);

        fetch(request)
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                clearList(controles().booksList);
                bookslist = data.items;
                // debugger;
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
        var img = document.createElement('img');
        img.src = book.volumeInfo.imageLinks.thumbnail
        bloco.appendChild(img);
        var h1 = document.createElement('h1');
        h1.innerHTML = book.volumeInfo.title
        bloco.appendChild(h1);
        var p = document.createElement('p');
        p.innerHTML = book.volumeInfo.description
        bloco.appendChild(p);
        var preco = document.createElement('span');
        preco.innerHTML = "R$ " + (Math.floor(Math.random() * (100 - 20)) + 20) + ",00";
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
        c.addEventListener('click', function() { alert("Ainda estamos trabalhando nessa função"); });
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

    return {
        search_books: search_books,
        harry_potter_head: harry_potter_head,
        harry_potter_news: harry_potter_news,
        harry_potter_character: harry_potter_character,
        search_adress: search_adress
    };

}();