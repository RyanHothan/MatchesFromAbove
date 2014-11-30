/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function getRev()
{
    var monthToGet = $("#salesReport option:selected").val();
    var yearToGet = $("#salesReportYear").val();
    
    $("#revPlace > tbody").empty();
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getRev", month: monthToGet, year: yearToGet},
            dataType: 'JSON',
            success: function(data) {
                for (i = 0; i < data.length; i++)
                {   
                    $("#revPlace > tbody").append("<tr id = monthRev"+i+"></tr>");
                    $("#monthRev"+i).append("<td value='fee'>" + data[i].fee + "</td>");
                    $("#monthRev"+i).append("<td value='time'>" + data[i].time + "</td>");
                }
            }
        });
}
;

function getRevByDate()
{
    var date = $("#datepicker").val();
    $("#dateGenRev > tbody").empty();
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getRevByDate", date: date},
            dataType: 'JSON',
            success: function(data) {
                 for (i = 0; i < data.length-1; i++)
                {   
                    $("#dateGenRev > tbody").append("<tr id = dateGenRev"+i+"></tr>");
                    $("#dateGenRev"+i).append("<td value='fee'>" + data[i].fee + "</td>");
                    $("#dateGenRev"+i).append("<td value='time'>" + data[i].time + "</td>");
                }          
                i = (data.length-1);
                
                $("#dateGenRev > tbody").append("<tr id = dateGenRev"+i+"></tr>");
                    $("#dateGenRev"+i).append("<td value='fee'>-----</td>");
                    $("#dateGenRev"+i).append("<td value='time'>Total: </td>");
                    $("#dateGenRev"+i).append("<td value='total'>" + data[i].total+ "</td>");
            }
        });
}
;

function getRevBySSN()
{
    var ssn = $("#custName").val();
    $("#nameGenRev > tbody").empty();
    alert(ssn)
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getRevBySSN", SSN: ssn},
            dataType: 'JSON',
            success: function(data) {
                alert("jacksauce")
                 for (i = 0; i < data.length-1; i++)
                {   
                    $("#nameGenRev > tbody").append("<tr id = nameGenRev"+i+"></tr>");
                    $("#nameGenRev"+i).append("<td value='fee'>" + data[i].fee + "</td>");
                    $("#nameGenRev"+i).append("<td value='time'>" + data[i].time + "</td>");
                }          
                i = (data.length-1);
                
                $("#nameGenRev > tbody").append("<tr id = nameGenRev"+i+"></tr>");
                    $("#nameGenRev"+i).append("<td value='fee'>-----</td>");
                    $("#nameGenRev"+i).append("<td value='time'>Total: </td>");
                    $("#nameGenRev"+i).append("<td value='total'>" + data[i].total+ "</td>");
            }
        });
}
;

function getBestRep(){
    var text = $("#bestRep").text();
   
    alert(text);
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getBestRep"},
            dataType: 'text',
            success: function(data) {
                 $("#bestRep").empty();
                 $("#bestRep").html(text +" "+ data)
            }
        });
};