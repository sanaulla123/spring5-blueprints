var vueJs;
$(function(){
	/*$('#linksModal').on('shown.bs.modal', function (event) {
		console.log(event);
	});*/
	
	vueJs = new Vue({
		"el" : "#page_content",
		"data": {
			newLink: {
				title: "",
				url: "",
				description: "",
				category: ""
			}
		}
	});
});