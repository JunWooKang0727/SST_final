var chart = (function(){
	var rc_num = $('#rc-num').val();
	var result1 ="";
	var result2 ="";
	
	$.ajax({
		url : '/sst/scoreanalysis/schoolallscore/' + rc_num,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			result1=data;
		}
	});
	
	$.ajax({
		url : '/sst/scoreanalysis/schoolaverage/' + rc_num,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			result2=data;
		}
	});
	
	
	function drawAllScore() {
		$('#curve_chart').empty();
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
		google.charts.setOnLoadCallback(drawVisualization);

		function drawVisualization() {
			// Some raw data (not necessarily accurate)
			var data = new google.visualization.DataTable(result1);
			var options = {
				title : '전체 과목 성적',
				vAxis : {
					title : '점수'
				},
				hAxis : {
					title : '시험'
				},
				seriesType : 'bars',

			// isStacked:true
			};
			var chart = new google.visualization.ComboChart(document
					.getElementById('curve_chart'));
			chart.draw(data, options);
		}
	}

	function drawAverage() {
		$('#curve_chart').empty();
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
		google.charts.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = new google.visualization.DataTable(result2);
			var options = {
				title : '시험 평균',
				curveType : 'function',
				legend : {
					position : 'bottom'
				}
			};
			var chart = new google.visualization.LineChart(document
					.getElementById('curve_chart'));
			chart.draw(data, options);
		} 
	}
return{
	drawAllScore:drawAllScore,
	drawAverage:drawAverage
}
})();