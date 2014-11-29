/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getRev()
{
    var monthToGet = $("#salesReport option:selected").val();
    var yearToGet = $("#salesReportYear option:selected").text();
    $("#revPlace").show();
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getRev", month: monthToGet, year: yearToGet},
            dataType: 'JSON',
            success: function(data) {
                
                for (i = 0; i < data.length; i++)
                {   $("#revPlace > tbody").html();
                    $("#revPlace > tbody").append("<tr id = monthRev"+i+"></tr>");
                    $("#monthRev"+i).append("<td value='fee'>" + data[i].fee + "</td>");
                    $("#monthRev"+i).append("<td value='time'>" + data[i].time + "</td>");
                }
            }
        });
}
;