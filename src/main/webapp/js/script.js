	$(document).ready(function () {
		function showAlert(text) {
			console.error(text);
			let innerHtml = `<div class="alert alert-warning" role="alert">${text}</div>`;
			$('#alerts').html(innerHtml);
		}

		function resetAll() {
			$('#results').empty();
			$('#alerts').empty();
		}

		$('.close').click(function () {
			$('#modal').modal().hide();
		});

		$('#reset').click(function (e) {
			e.preventDefault();
			$('#txtBooks').val('');
			resetAll();
		});

		function getVendors(isbn10) {
			const VENDOR_API = `http://localhost:8055/Books_war/webapi/bookLinks?isbn10=${isbn10}`;
			return fetch(VENDOR_API)
				.then(function (response) {
					if (response.ok) return response.json();
					console.error(`response failed.`);
				})
				.catch(function (error) {
					console.error(`Error occured ` + error);
				});
		}

		$('#myform').submit(function () {
			// reset all ids
			resetAll();
			// get search value check with regex to allow only numbers and strings
			const searchText = $('#txtBooks').val();
			if (!searchText.match(/^[a-z0-9\s]{0,255}$/i) || searchText == '') {
				showAlert('search value is invalid.');
			} else {
				const API_URL = 'https://www.googleapis.com/books/v1/volumes?q=';
				let innerHtml = '';
				$.get(API_URL + searchText, function (response) {
					console.log(response);
				})
					.done(function (response) {
						if (response.totalItems > 0) {
							let generateHtml = response.items.reduce((html, item, index) => {
								const authors = item.volumeInfo.authors
									? item.volumeInfo.authors
									: [];
								let isbnObj = item.volumeInfo.industryIdentifiers
									? item.volumeInfo.industryIdentifiers.find(
										(item) => item['type'] == 'ISBN_10'
									)
									: {};
								let isSalable =
									item.saleInfo &&
									Object.keys(item.saleInfo).length > 0 &&
									item.saleInfo.saleability == 'FOR_SALE'
										? true
										: false;
								let price = '';
								let buyLink = '';
								if (isSalable) {
									buyLink = item.saleInfo.buyLink;
									price = item.saleInfo.retailPrice
										? `${item.saleInfo.retailPrice.amount} ${item.saleInfo.retailPrice.currencyCode}`
										: `${item.saleInfo.listPrice.amount} ${item.saleInfo.retailPrice.currencyCode}`;
								} else {
									price = 'Not for sale';
								}
								const isbn10 = isbnObj ? isbnObj.identifier : null;
								return (
									html +
									(isbn10 && item.volumeInfo
										? `<li key=${index} class='list-group-item m-1'>
									<div class='row'>
										<div class='col-4 col-md-3 col-lg-2'><img src =${
											item.volumeInfo.imageLinks
												? item.volumeInfo.imageLinks.smallThumbnail
												: ''
										} alt=${item.volumeInfo.title}/></div>
										<div class='col-8 col-md-9 col-lg-10'>
											<button class='btn btn-link generateModal pl-0 text-left' data-isbn10='${isbn10}' data-price='${price}'
											data-buylink='${buyLink}'
											data-thumbnail ='${
											item.volumeInfo.imageLinks
												? item.volumeInfo.imageLinks.smallThumbnail
												: ''
										}'
											data-title = ${item.volumeInfo.title.replace(/\ /g, '%20')}
											><i class='fa fa-book'></i> ${item.volumeInfo.title}</button>
											<div class='text-secondary'>${
											authors.length > 0
												? `<i class='fa fa-user'></i> ` + authors.join(', ')
												: ''
										}</div>
											<p class='pt-1'>${
											item.volumeInfo.description &&
											item.volumeInfo.description.length > 300
												? item.volumeInfo.description.substr(0, 300) + '....'
												: item.volumeInfo.description
										}</p>
										</div>
									</div>
								</li>`
										: '')
								);
							}, `<ul class='list-group'>`);
							$('#results').append(generateHtml + '</ul>');
							$('.generateModal').click(function (event) {
								console.info(event.target.dataset);
								$('.modal-body').empty();
								let ds = event.target.dataset;
								const title = ds.title.replace(/\%20/g, ' ');
								// make java api call to get other data
								/*
								  [
									{
									  vendor: 'Amazon.in',
									  path:
										'https://www.amazon.in//Eragon-Inheritance-Cycle-Christopher-Paolini/dp/0966621336/ref\u003dsr_1_1?dchild\u003d1\u0026keywords\u003d9780966621334\u0026qid\u003d1590160176\u0026sr\u003d8-1#customerReviews',
									  price: 183.06,
									  format: 'Kindle Edition',
									},
									{
									  vendor: 'Amazon.in',
									  path:
										'https://www.amazon.in//Eragon-Inheritance-Cycle-Christopher-Paolini/dp/0966621336/ref\u003dsr_1_1?dchild\u003d1\u0026keywords\u003d9780966621334\u0026qid\u003d1590160176\u0026sr\u003d8-1#customerReviews',
									  price: 190.0,
									  format: 'Paperback',
									},
								  ];
								  */
								getVendors(ds.isbn10).then(function (vendors) {
									let generateHtml = `
					<div class='container'>
					 <div class='row p-2 border-bottom'>
					   <div class='col-4'>
						 <img src =${ds.thumbnail} alt=${title}/>
					   </div>
					   <div class='col-8'>
						 <div class='dispaly-4'>${title}</div>
					   </div>
					 </div>
					 <div class='vendors d-flex flex-column'>
					   <div class='p-2'>
						 ${
										ds.price != 'Not for sale'
											? `<a type='button' class='btn btn-outline-secondary col-12'
							 href=${ds.buylink} target='_blank' ><i class='fa fa-google'></i> Books - ${ds.price} </a>`
											: ''
									}
					   </div>
					   ${vendors.reduce(
										(html, item, index) =>
											html +
											`<div class='p-2' key=${index}>
							 <a type='button' class='btn btn-outline-secondary col-12'
							 href=${item.path} target='_blank' >${item.vendor} - ${item.price} </a>
						 </div>`,
										''
									)}
					 </>
				   </div>`;
									$('.modal-body').append(generateHtml);
									$('#modal').modal('show');
								});
							});
						} else {
							showAlert('No books found. Please try with different keywords.');
						}
					})
					.fail(function (error) {
						console.error(`Error occured accessing API -` + error.responseText);
					});
			}
			return false;
		});
	});
