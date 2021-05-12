var result = "";
$.ajax({
	url : '/SST/ReportCard/averageChartRCAction.do',
	type : 'GET',
	dataType : 'text',
	async : false,
	data : {
		"rc_num" : $('#rc-num').val()
	},
	success : function(data) {
		result = data;
	},
	interpolateNulls : true
});
$("#chart-change").change(
		
		function() {
			if ($("#chart-change").val() == "전체과목") {
				$('#curve_chart').empty();
				google.charts.load('current', {
					'packages' : [ 'corechart' ]
				});
				google.charts.setOnLoadCallback(drawVisualization);

				function drawVisualization() {
					// Some raw data (not necessarily accurate)
					var data = new google.visualization.DataTable(result);

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
		})
