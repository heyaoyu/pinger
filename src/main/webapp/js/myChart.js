$(document).mousemove(onMouseMove);
function getRes(ref) {
	$.getJSON("/pinger/jsondemo", {
		id : ref
	}, function(ref) {
		draw(ref);
	});

	$("#fade").css("display", "block");
	$("#backButton").css("display", "block");
	$("#tutorial").fadeIn("slow");
	$("#tutorial").css("display", "block");

}
var ctx; // canvas context
var chart_x_color; // chart x-axis color
var chart_y_color; // chart y-axis color
var chart_x_length; // chart x-axis length
var chart_y_length; // chart y-axis length
var chart_x_width; // chart x-axis width
var chart_y_width; // chart y-axis width
var chart_origin_x; // chart origin x-axis value
var chart_origin_y; // chart origin y-axis value
var chart_column; // chart column number
var chart_column_per_length; // length per column
var chart_column_width; // chart column width
var chart_row; // chart row number
var chart_row_width; // chart row width
var chart_row_per_length; // length per row
var chart_fill_color; // chart fill color
var chart_stroke_color; // chart stroke color
var chart_text_width; // chart text width
var chart_text_color; // chart text color
var chart_stroke_width; // chart stroke width
var popup_fill_color; // popup div backfround color
var popup_stroke_color; // popup div stroke color
var dataArray;
var chart_opacity;
var nowAt = -1;
function Data(date, value, height) {
	this.date = date;
	this.value = value;
	this.height = height;
}

// define the default style
function defaultStyle() {
	chart_x_color = "#000000";
	chart_y_color = "#000000";
	chart_x_length = 500;
	chart_y_length = 300;
	chart_x_width = 2;
	chart_y_width = 2;
	chart_origin_x = 80;
	chart_origin_y = 300;
	chart_column = 5;
	chart_column_width = 0.5;
	chart_row = 5;
	chart_row_width = 0.5;
	chart_fill_color = "#0080FF";
	chart_stroke_color = "#0080FF";
	chart_text_width = 0.5;
	chart_text_color = "#000000";
	chart_stroke_width = 1;
	popup_fill_color = "#000000";
	popup_stroke_color = "#0080FF";
	chart_column_per_length = chart_x_length / chart_column;
	chart_row_per_length = chart_y_length / chart_row;
	chart_opacity = 0.5;
}

// get JSON and analysis it
function getJson(ref) {
	dataArray = new Array();
	// $.getJSON("/pinger/jsondemo", null, function(ref) {
	$.each(ref, function(i) {
		var height = ref[i].value / 4000 * chart_y_length;
		var data = new Data(ref[i].date, ref[i].value, height);
		dataArray[i] = data;
	});
}

// initial chart frame
function initChart() {
	// draw x-axis
	drawLine(chart_x_color, chart_x_width, chart_origin_x, chart_origin_y,
			chart_origin_x, chart_origin_y - chart_y_length);
	// draw y-axis
	drawLine(chart_y_color, chart_y_width, chart_origin_x, chart_origin_y,
			chart_origin_x + chart_x_length, chart_origin_y);
	// draw x-axis element
	for ( var i = 0; i < chart_row; i++) {
		drawLine(chart_x_color, chart_row_width, chart_origin_x - 15,
				chart_origin_y - chart_row_per_length * i, chart_origin_x
						+ chart_x_length, chart_origin_y - chart_row_per_length
						* i);
		drawText(chart_text_color, chart_text_width, chart_origin_x - 25,
				chart_origin_y - chart_row_per_length * i - 5, 800 * i);
	}
	// draw y-axis element
	for ( var j = 0; j < chart_column; j++) {
		drawLine(chart_y_color, chart_column_width, chart_origin_x
				+ chart_column_per_length * j, chart_origin_y, chart_origin_x
				+ chart_column_per_length * j, chart_origin_y + 15);
		drawText(chart_text_color, chart_text_width, chart_origin_x
				+ chart_column_per_length * j, chart_origin_y + 15,
				dataArray[j].date);
	}
}

// draw a line
function drawLine(color, width, start_x, start_y, dest_x, dest_y) {
	ctx.beginPath();
	ctx.moveTo(start_x, start_y);
	ctx.lineWidth = width;
	ctx.strokeStyle = color;
	ctx.lineTo(dest_x, dest_y);
	ctx.stroke();
	ctx.closePath();
}

// draw a text
function drawText(color, width, x, y, text) {
	ctx.beginPath();
	ctx.lineWidth = width;
	ctx.strokeText(text, x, y);
	ctx.closePath();
}

function drawArc(width, x, y) {
	ctx.beginPath();
	ctx.moveTo(x, y);
	ctx.arc(x, y, 3, 0, Math.PI * 2, true);
	ctx.lineWidth = width;
	ctx.strokeStyle = chart_stroke_color;
	ctx.stroke();
	ctx.fillStyle = "white";
	ctx.fill();
	ctx.closePath();
}

function drawPerArea(x, y, dest_x, dest_y, areaColor) {
	ctx.beginPath();
	ctx.moveTo(x, y);
	ctx.lineTo(dest_x, dest_y);
	ctx.lineTo(dest_x, chart_origin_y);
	ctx.lineTo(x, chart_origin_y);
	ctx.lineWidth = chart_stroke_width;
	ctx.strokeStyle = chart_stroke_color;
	ctx.stroke();
	ctx.fillStyle = areaColor;
	ctx.fill();
	ctx.closePath();
}

// fill data area with the given color and opacity
function fillDataArea(color, opacity) {
	ctx.globalAlpha = opacity;
	for ( var i = 0; i < (dataArray.length - 1); i++) {
		drawPerArea(chart_origin_x + chart_column_per_length * i,
				chart_origin_y - dataArray[i].height, chart_origin_x
						+ chart_column_per_length * (i + 1), chart_origin_y
						- dataArray[i + 1].height, color);
		drawArc(4, chart_origin_x + chart_column_per_length * i, chart_origin_y
				- dataArray[i].height);
	}
	for ( var i = 0; i < dataArray.length; i++) {
		drawArc(4, chart_origin_x + chart_column_per_length * i, chart_origin_y
				- dataArray[i].height);
	}
}

// -------------------------------------------------------------------------
function onMouseMove(evt) {
	if ($("#tutorial").css("display") != "none") {
		if (evt.pageX > ($("#tutorial").offset().left + 65)
				&& evt.pageX < ($("#tutorial").offset().left + chart_x_length + 15)
				&& evt.pageY < ($("#tutorial").offset().top + chart_origin_y + 15)
				&& evt.pageY > ($("#tutorial").offset().top + 15)) {
			ctx.beginPath();
			ctx.globalAlpha = 1;
			var index = divide((evt.pageX - $("#tutorial").offset().left),
					chart_column_per_length);
			if (nowAt != index) {
				reset();
				ctx.moveTo(chart_origin_x + chart_column_per_length * index,
						chart_origin_y - dataArray[index].height);
				ctx.arc(chart_origin_x + chart_column_per_length * index,
						chart_origin_y - dataArray[index].height, 3, 0,
						Math.PI * 2, true);
				ctx.fillStyle = chart_fill_color;
				ctx.fill();
				ctx.closePath();
				$("#pop").fadeIn("slow");
				$("#pop").css(
						{
							"display" : "block",
							"left" : $("#tutorial").offset().left
									+ chart_origin_x + index
									* chart_column_per_length + 15,
							"top" : chart_origin_y - dataArray[index].height
									+ $("#tutorial").offset().top - 35,
							"background-color" : popup_fill_color
						})
				$("#pop").html(
						"ResponseTime:<br/>" + dataArray[index].value + "(ms)");
				nowAt = index;
			}
		} else {
			reset();
			$("#pop").css({
				"display" : "none"
			});
		}
	}
}

function divide(exp1, exp2) {
	var rslt = exp1 / exp2;
	if (rslt > 0) {
		rslt = Math.floor(rslt);
	} else {
		rslt = Math.ceil(rslt);
	}
	return rslt;
}

function reset() {
	for ( var i = 0; i < dataArray.length; i++) {
		ctx.beginPath();
		ctx.moveTo(chart_origin_x + chart_column_per_length * i, chart_origin_y
				- dataArray[i].height);
		ctx.fillStyle = "white";
		ctx.arc(chart_origin_x + chart_column_per_length * i, chart_origin_y
				- dataArray[i].height, 3, 0, Math.PI * 2, true);
		ctx.fill();
		ctx.closePath();
	}
}
// -------------------------------------------------------------------------

function draw(ref) {
	ctx = document.getElementById('tutorial').getContext('2d');
	defaultStyle();
	getJson(ref);
	initChart();
	fillDataArea(chart_fill_color, chart_opacity);
}