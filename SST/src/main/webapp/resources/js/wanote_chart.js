
am4core.ready(function() {
	// Themes begin
	am4core.useTheme(am4themes_animated);
	// Themes end

	// Create chart instance
	var chart = am4core.create("chartdiv", am4charts.PieChart);

	// Add and configure Series
	var pieSeries = chart.series.push(new am4charts.PieSeries());
	pieSeries.dataFields.value = "COUNT";
	pieSeries.dataFields.category = "TG_NAME";

	// Let's cut a hole in our Pie chart the size of 30% the radius
	chart.innerRadius = am4core.percent(30);

	// Put a thick white border around each Slice
	pieSeries.slices.template.stroke = am4core.color("#fff");
	pieSeries.slices.template.strokeWidth = 2;
	pieSeries.slices.template.strokeOpacity = 1;
	pieSeries.slices.template
	  // change the cursor on hover to make it apparent the object can be interacted with
	  .cursorOverStyle = [
	    {
	      "property": "cursor",
	      "value": "pointer"
	    }
	  ];

	pieSeries.alignLabels = false;
	pieSeries.labels.template.bent = false;
	pieSeries.labels.template.radius = 3;
	pieSeries.labels.template.padding(0,0,0,0);

	pieSeries.ticks.template.disabled = true;

	// Create a base filter effect (as if it's not there) for the hover to return to
	var shadow = pieSeries.slices.template.filters.push(new am4core.DropShadowFilter);
	shadow.opacity = 0;

	// Create hover state
	var hoverState = pieSeries.slices.template.states.getKey("hover"); // normally we have to create the hover state, in this case it already exists

	// Slightly shift the shadow and make it more prominent on hover
	var hoverShadow = hoverState.filters.push(new am4core.DropShadowFilter);
	hoverShadow.opacity = 0.7;
	hoverShadow.blur = 5;

	// Add a legend
	chart.legend = new am4charts.Legend();
	var m_id=$('#m_id').val();
	var result="";
	$.ajax({
		url : '/wanote/countTagChart/' + m_id,
		type : 'GET',
		dataType : 'json',
		async:false,
		success : function(data) {
			result=data;
		}
	});
	chart.data = result;

	}); // end am4core.ready()


am4core.ready(function() {

	// Themes begin
	am4core.useTheme(am4themes_animated);
	// Themes end

	var iconPath = "M53.5,476c0,14,6.833,21,20.5,21s20.5-7,20.5-21V287h21v189c0,14,6.834,21,20.5,21 c13.667,0,20.5-7,20.5-21V154h10v116c0,7.334,2.5,12.667,7.5,16s10.167,3.333,15.5,0s8-8.667,8-16V145c0-13.334-4.5-23.667-13.5-31 s-21.5-11-37.5-11h-82c-15.333,0-27.833,3.333-37.5,10s-14.5,17-14.5,31v133c0,6,2.667,10.333,8,13s10.5,2.667,15.5,0s7.5-7,7.5-13 V154h10V476 M61.5,42.5c0,11.667,4.167,21.667,12.5,30S92.333,85,104,85s21.667-4.167,30-12.5S146.5,54,146.5,42 c0-11.335-4.167-21.168-12.5-29.5C125.667,4.167,115.667,0,104,0S82.333,4.167,74,12.5S61.5,30.833,61.5,42.5z"



	var chart = am4core.create("chartdiv2", am4charts.SlicedChart);
	chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect
	var m_id=$('#m_id').val();
	var result2="";
	$.ajax({
		url : '/wanote/countReasonChart/' + m_id,
		type : 'GET',
		dataType : 'json',
		async:false,
		success : function(data) {
			result2=data;
		}
	});
	console.log(result2);
	chart.data = result2;

	var series = chart.series.push(new am4charts.PictorialStackedSeries());
	series.dataFields.value = "COUNT";
	series.dataFields.category = "W_REASON";
	series.alignLabels = true;

	series.maskSprite.path = iconPath;
	series.ticks.template.locationX = 1;
	series.ticks.template.locationY = 0.5;

	series.labelsContainer.width = 250;

	}); // end am4core.ready()



$(document).ready(function(){
	var m_id = $('#m_id').val();
	$.ajax({
		url : '/wanote/recommendStudyGroup/' + m_id,
		type : 'GET',
		dataType : 'json',
		async : false,
		success : function(data) {
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

	})



	


})



