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
            dataType: 'text',
            success: function(data) {
                alert(data);
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
    var ssn = $("#custName").val().trim();
    $("#nameGenRev > tbody").empty();
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getRevBySSN", SSN: ssn},
            dataType: 'JSON',
            success: function(data) {
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
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getBestRep"},
            dataType: 'text',
            success: function(data) {
                 $("#bestRep").empty();
                 $("#bestRep").html(data)
            }
        });
};

function getBestCust(){
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "getBestCust"},
            dataType: 'text',
            success: function(data) {
                 $("#bestCust").empty();
                 $("#bestCust").html(data)
            }
        });
};


function bestRatedCust(){
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "bestRatedCust"},
            dataType: 'text',
            success: function(data) {
                 $("#bestRatedCust").empty();
                 $("#bestRatedCust").html(data)
            }
        });
};

function bestDateDays(){
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "bestDateDays"},
            dataType: 'text',
            success: function(data) {
                 $("#bestDateDays").empty();
                 $("#bestDateDays").html(data)
            }
        });
};

function actCust(){
    $.ajax({
            url: '/MatchesFromAbove/managerFunctions',
            type: 'GET',
            data: {func: "mostActCust"},
            dataType: 'JSON',
           success: function(data) {
               $("#mostActCustTable > tbody").html("");
                 for (i = 0; i < data.length; i++)
                {   
                    $("#mostActCustTable > tbody").append("<tr id = mostActCustTable"+i+"></tr>");
                    $("#mostActCustTable"+i).append("<td value='name'>" + data[i].name  + "</td>");
                    $("#mostActCustTable"+i).append("<td value='level'>" + data[i].level + "</td>");
                }          
            }
        });
    
    
};