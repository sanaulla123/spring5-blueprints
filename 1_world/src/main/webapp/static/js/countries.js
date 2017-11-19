var pageSize = 20;
$(function(){
	var count = $("#pagination").attr("count");
	$('#pagination').bootpag({				
	    total: Math.ceil(count/pageSize),          // total pages
	    page: 1,            // default page
	    maxVisible: 5,     // visible pagination
	    leaps: true         // next/prev leaps through maxVisible
	}).on("page", function(event, num){
	    //list(num); 
		console.log(queryString);
		console.log( $.param( queryString ));
	});
	
	
	$(".filter").on('change', function(e){
		var key = $(e.target).attr("name");
		console.log($(e.target))
		var value = $(e.target).val();
		console.log(e);
		handleSearch(key, value);
	});
	
});

function handleSearch(key, value){
	console.log(key + ", " + value);
	queryString[key] = value;
	location.href = location.pathname + "?" + $.param(queryString);
}

