var vueJs;
$(function(){
	$('#linksModal').on('shown.bs.modal', function (event) {
		vueJs.newLink.title = "";
		vueJs.newLink.url = "";
		vueJs.newLink.description = "";
		vueJs.newLink.category = "";
		
	});
	$("#new-link-form").validate({
		errorClass : "is-invalid",
		validClass : "is-valid"
	});
	vueJs = new Vue({
		"el" : "#page_content",
		"data": {
			newLink: {
				title: "",
				url: "",
				description: "",
				category: ""
			}
		}, 
		"methods":{
			"saveLink": function(){
				var formValid = $("#new-link-form").valid();
				console.log(formValid);
				console.log(this.newLink);
			}
		}
	});
});