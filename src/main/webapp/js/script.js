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
    //   var price = "";
	//   var buyLink = "";
	//   var isbn = "";
	  var compare = "";
	  var section ="";
	  

   	  $.get("https://www.googleapis.com/books/v1/volumes?q=" + search,function(response){

   	  	console.log(response);
          $("#result").empty();
          for(i=0;i<5/*response.items.length*/;i++)
          {
			
          	url= response.items[i].volumeInfo.imageLinks.smallThumbnail;
          	img = $('<img class="left"><br><a href=' + response.items[i].volumeInfo.infoLink + '><button class="waves-effect waves-light btn down">Read More</button></a>');
          	img.appendTo('#result');
          	img.attr('src', url);
           	title=$('<h2 class="title black-text">Title : <u>' + response.items[i].volumeInfo.title + '</u></h2>');  
           	title.appendTo('#result');
           	author=$('<h3 class="author black-text"> Author:<u>' + response.items[i].volumeInfo.authors + '</u></h3>');
           	author.appendTo('#result');
           	// price=$('<h3 class="price red-text">Price : <u>' + response.items[i].saleInfo.retailPrice.amount + '</u></h4>');
           	// price.appendTo('#result');
           	// buyLink=$('<h4 class="buy black-text">Buy : <a href=>' + response.items[i].saleInfo.buyLink + '</a></h5><br>');
			// // buyLink.appendTo('#result');
			// isbn=$('<h4 class="buy black-text">ISBN : <u>' + response.items[i].volumeInfo.industryIdentifiers[1].identifier + '</u></h4><br>');
			// isbn.appendTo('#result');
			//Added By Shivani
			compare=$('<button id="myBtn">Open Modal</button>');
			compare.appendTo('#result');
		
			//Closed By Shivani

          }
   	  });
      
      }
      return false;
   });


});

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function() {
  modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

//Added By Shivani 


//Closed By Shivani