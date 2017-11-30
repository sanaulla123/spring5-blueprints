var pageSize = 10;
$(function(){
	getGDP();
	
	
	$("#new-city").on('click', function(){
		
		loadNewCityModal();
	});
	
	$("#new-language").on('click', function(){
		loadNewLanguageModal();
	});
	
	getCities(1);
	getLanguages(1);
});

function loadNewCityModal(){
	loadModal("city-form-template", {});
	/*var html = Mustache.to_html($("#city-form-template").html(), {});
	$("#worldModal").html(html);*/
	setupForm('city-form', function(){
		success("City Added Successfully");
	});
}

function loadNewLanguageModal(){
	loadModal("language-form-template", {});
	/*var html = Mustache.to_html($("#language-form-template").html(), {});
	$("#worldModal").html(html);*/
	setupForm('language-form', function(){
		success("Language Added Successfully");
	});
	$("#isOfficial").on('change', function(){
		if ( $(this).is(":checked")) $(this).val(1);
		if ( !$(this).is(":checked")) $(this).val(0);
	});
}

function getCities(pageNo){
	var params = {"pageNo" : pageNo};
	$.getJSON("/world/api/cities/"+code, params, function(data){
		var md = {};
		md['list'] = data;
		if ( data.length == pageSize){
			md['more'] = 1;
		}else{
			md['more'] = 0;
		}
		md['pageNo'] = pageNo + 1;
		var html = Mustache.to_html($("#cities-list-template").html(), md);
		if ( pageNo == 1) {
			$("#cities").html(html);
		}else{
			$("#cities button").remove();
			$("#cities").append(html);
		}
	});
}

function getLanguages(pageNo){
	var params = {"pageNo" : pageNo};
	$.getJSON("/world/api/languages/"+code, params, function(data){
		var md = {};
		md['list'] = data;
		if ( data.length == pageSize){
			md['more'] = 1;
		}else{
			md['more'] = 0;
		}
		md['pageNo'] = pageNo + 1;
		md['percentage_fmt'] = function(){
			if ( this['percentage']){
				return this['percentage'].toFixed(2);
			}
		}
		md['isOfficial_Bool'] = function(){
			return ( this['isOfficial'] == "TRUE");
		}
		
		var html = Mustache.to_html($("#languages-list-template").html(), md);
		if ( pageNo == 1) {
			$("#languages").html(html);
		}else{
			$("#languages button").remove();
			$("#languages").append(html);
		}
	});
}

function getGDP(){
	$.getJSON("/world/api/countries/"+code+"/gdp", function(data){
	
		var dataTable = new google.visualization.DataTable();
		dataTable.addColumn('number', 'Years');
		dataTable.addColumn('number', 'GDP (current US$)');
		var rows = [];
		//console.log(numeral(646438380568.715).format('($0.00 a)'));
		_.each(data, function(item){
			rows.push([item.year, item.value])
		});
		dataTable.addRows(rows);
		
		
		var options = {
			hAxis: {
				title: 'Year'
			},
			vAxis: {
				title: 'GDP (current US$)'
			}
	      };

		
		$("#gdp-chart").html('');
		google.charts.setOnLoadCallback(function(){
			var chart = new google.visualization.LineChart(document.getElementById('gdp-chart'));
			chart.draw(dataTable, options);
		});
		
	});
}

function setupForm(formId, successCallback){
	
	$("select").each(function(){
		$(this).val($(this).attr("value"));
	});
	$('#'+formId).validate({
		ignore: [],
		errorClass:'text-danger help-inline',
		validClass:'text-success',
		errorElement: 'span',
		highlight: function (element, errorClass, validClass) { 
		    $(element).parent().addClass('has-error').removeClass('has-success'); 
		}, 
		unhighlight: function (element, errorClass, validClass) { 
			$(element).parent().removeClass('has-error').addClass('has-success'); 
		}
	});

	$("#"+formId).submit(function(){
		var url = $(this).attr("action");
		$(this).ajaxSubmit({ 
			url: url,
			target:        	'#warning',
			beforeSubmit: function(){
				return $('#'+formId).valid();
			},
			success: function(data){
				successCallback(data);
			},
			error: function(response){
				error(response.responseText);
			},
			type:      		'POST',
			dataType:  		'text'
		});
		return false;
	});
	
}