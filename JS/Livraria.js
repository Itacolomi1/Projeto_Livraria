// Keys
var api_key = 'AIzaSyDcvOZV7sFO5cTjqq2oUW6M4zTq97aYkQc';
var api_hp_key = '$2a$10$4Lup07NIucJ3F01MYN37y.h2YHGAPqmMxfE97RgfT7omp9VrSQxHS';
var api_news_Key = '2iMweAEJf8vdocUAi7VUYKZa22C8vOXy';
// Base_Urls
var url_base_books = 'https://www.googleapis.com/books/v1/volumes?q=intitle:';
var url_base_hp = "https://www.potterapi.com/v1/";
var url_base_news = `https://api.nytimes.com/svc/search/v2/articlesearch.json?q=harryPotter&field-name:("Harry Potter")&api-key=`;
var bookslist =[]
var newslist =[]

var livraria = function () {


    //Ids dos elementos da tela
    var controles = function () {
        return {
            search_books: "search_books",
            booksList: "booksList",
            adress: "adress",
            search_adress: "search_adress",            
            news: "news"
        };
    }

    var search_books = function(){
        var content = document.getElementById(controles().search_books).value;
        var request = url_base_books + content + '&filter=partial&projection=lite&key=' + api_key; 

        console.log(request);

        fetch(request)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) { 
            clearList(controles().booksList);                     
            bookslist = data.items;          
            bookslist.forEach(list_books);            
        }).catch(function(error){
            console.log('Request failed',error);            
        });

    }

    var list_books = function(book,i){
        var ul = document.getElementById(controles().booksList);
        var li = document.createElement('li');
        li.innerHTML = book.volumeInfo.title
        ul.appendChild(li);  
    }

    var list_news = function(news,i){
        var ul = document.getElementById(controles().news);
        var li = document.createElement('li');
        li.innerHTML = news.abstract;
        ul.appendChild(li);  
    }

    var clearList = function(id_lista){
        var ul = document.getElementById(id_lista);
         ul.innerHTML = '';
    }
    
    var search_adress = function(){

        var paragraph = document.getElementById(controles().adress);
        var cep = document.getElementById(controles().search_adress).value;
        var url_base_cep = `https://viacep.com.br/ws/${cep}/json/`;

        fetch(url_base_cep)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) { 
            paragraph.innerHTML = data.logradouro;           
        }).catch(function(error){
            console.log('Request failed',error);            
        });


    }

    var harry_potter_news = function(){     
        var request = url_base_news + api_news_Key; 

        console.log(request);

        fetch(request)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) { 
            clearList(controles().news);                     
            debugger;
            newslist = data.response.docs;          
            newslist.forEach(list_news);            
        }).catch(function(error){
            console.log('Request failed',error);            
        });

    }

    return {
        search_books: search_books,
        search_adress: search_adress,
        harry_potter_news: harry_potter_news
    };
}();