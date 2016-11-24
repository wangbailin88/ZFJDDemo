<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<html>  
    <head>  
        <title>Highcharts Example</title>  
        <script language="javascript" type="text/javascript" src="jquery.min.js"></script>  
        <script language="javascript" type="text/javascript" src="highcharts.js"></script>  
        <script language="javascript" type="text/javascript" src="exporting.js"></script>  
<script type="text/javascript">  
$(document).ready(function() {  
    Highcharts.setOptions({  
        global: {  
            useUTC: false  
        }  
    });  
    var chart;  
    chart = new Highcharts.Chart({  
        chart: {  
            renderTo: 'container',  
            type: 'spline',  
            marginRight: 10,  
            events: {  
                load: function() {  
                    // set up the updating of the chart each second  
                    var series = this.series[0];  
                    setInterval(function() {  
                        var x = (new Date()).getTime(), // current time  
                            y = Math.random();  
                        series.addPoint([x, y], true, true);  
                    }, 1000);  
                }  
            }  
        },  
        title: {  
            text: '<b>Java小强制作</b>'  
        },  
        xAxis: {  
            type: 'datetime',  
            tickPixelInterval: 150  
        },  
        yAxis: {  
            title: {  
                text: '单位：M'  
            },  
            plotLines: [{  
                value: 0,  
                width: 1,  
                color: '#808080'  
            }]  
        },  
        tooltip: {  
            formatter: function() {  
                    return '<b>'+ this.series.name +'</b><br/>'+  
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+  
                    Highcharts.numberFormat(this.y, 2);  
            }  
        },  
        legend: {  
            enabled: false  
        },  
        exporting: {  
            enabled: false  
        },  
        series: [{  
            name: 'Random data',  
            data: (function() {  
                // generate an array of random data  
                var data = [],  
                    time = (new Date()).getTime(),  
                    i;  
  
                for (i = -19; i <= 0; i++) {  
                    data.push({  
                        x: time + i * 1000,  
                        y: Math.random()  
                    });  
                }  
                return data;  
            })()  
        }]  
    });  
});  
</script>  
    </head>  
    <body>  
    <div id="container" style="width: 800px;height: 400px"></div>  
    </body>  
</html> 