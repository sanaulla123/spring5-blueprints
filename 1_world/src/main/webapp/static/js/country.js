$(function(){
	google.charts.setOnLoadCallback(function(){
		getGDP();
	});
});

function getCities(pageNo){
	var params = {"pageNo" : pageNo};
	$.getJSON("/world/api/cities/"+code, params, function(data){
		
	});
}

function getLanguages(pageNo){
	var params = {"pageNo" : pageNo};
	$.getJSON("/world/api/languages/"+code, params, function(data){
		
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

		var chart = new google.visualization.LineChart(document.getElementById('gdp-chart'));

		chart.draw(dataTable, options);
	});
}