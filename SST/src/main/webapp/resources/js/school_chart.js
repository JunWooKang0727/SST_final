var rc_num = $('#rc-num').val();
var rc_type = $('#rc-type').val();
var rc_subtype = $('#rc-subtype').val();

$(document).ready(function(){
	var reportcard={
			rc_type:rc_type,
			rc_subtype:rc_subtype,
			rc_num:rc_num
	};
	$.ajax({
		type : 'get',
		url : '/reportcard/recommendSchoolTest',
		data : reportcard,
		contentType : "application/json; charset=utf-8",
		dataType : 'json',
		success : function(data) {
			console.log(data);
			$.each(data, function(index, item) {
				var html="";				
				html+='<div class="col-xl-3 col-md-6 mb-4"> <div class="card border-left-info shadow h-100 py-2"> ';
				html+='<div class="card-body">';
				html+='<div class="text-xs font-weight-bold text-success text-uppercase mb-1"> 스터디 그룹 추천 '+(index+1)+'</div>';
				html+='<div ><a class="h5 mb-0 font-weight-bold text-gray-700" href="/group/selectdetail?g_num='+data[index].g_num+'">';
				html+=data[index].g_name;
				html+='</a></div>';
				html+='카테고리: '+data[index].g_category+'<br>';
				html+='</div></div></div>';
				$('#recommend').append(html);
			})
			
		}
	});
	
	
})
var chart = (function(){
	var result1 ="";
	var result2 ="";
	
	$.ajax({
		url : '/scoreanalysis/schoolallscore/' + rc_num,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			result1=data;
		}
	});
	
	$.ajax({
		url : '/scoreanalysis/schoolaverage/' + rc_num,
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