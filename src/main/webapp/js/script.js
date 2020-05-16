$(document).ready(function(){	

   $("#myform").submit(function(){
   	  var search = $("#books").val();
   	  if(search == "")
   	  {
   	  	alert("Please enter something in the field");
   	  }
   	  else{		
   	  var url = "";
   	  var img = "";
      var title = "";
      var author = "";
      var price = "";
      var buyLink = "";

   	  $.get("https://www.googleapis.com/books/v1/volumes?q=" + search,function(response){

   	  	console.log(response);
          $("#results").empty();
          for(i=0;i<response.items.length;i++)
          {
          	url= response.items[i].volumeInfo.imageLinks.smallThumbnail;
          	img = $('<img class="left"><br><a href=' + response.items[i].volumeInfo.infoLink + '><button class="waves-effect waves-light btn down">Read More</button></a>');
          	img.appendTo('#result');
          	img.attr('src', url);
           	title=$('<h2 class="title black-text">Title : <u>' + response.items[i].volumeInfo.title + '</u></h2>');  
           	title.appendTo('#result');
           	author=$('<h3 class="author black-text"> Author:<u>' + response.items[i].volumeInfo.authors + '</u></h3>');
           	author.appendTo('#result');
           	price=$('<h3 class="price red-text">Price : <u>' + response.items[i].saleInfo.retailPrice.amount + '</u></h4>');
           	price.appendTo('#result');
           	buyLink=$('<h4 class="buy black-text">Buy : <a href=>' + response.items[i].saleInfo.buyLink + '</a></h5><br>');
           	buyLink.appendTo('#result');

          }
   	  });
      
      }
      return false;
   });

});