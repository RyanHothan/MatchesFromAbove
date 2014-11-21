/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function populateCustomersTable()
{
    var displayValue = $("#customersTable").css('display');
    if (displayValue !== 'none')
    {
        $('#customersTable').hide();
        $('#customersTable > tbody').html("");
    }
    else
    {
        $.ajax({
            url: '/MatchesFromAbove/getAllCustomers',
            type: 'GET',
            dataType: 'JSON',
            success: function (data) {
                $("#customersTable").show();
                for (i = 0; i < data.length; i++)
                {
                    var newRow = $("#customersTable > tbody").append("<tr value=" + data[i].ssn + " id=" + data[i].ssn + "></tr>");
                    $("#"+data[i].ssn).append("<td value='ssn'>" + data[i].ssn + "</td>");
                    $("#"+data[i].ssn).append("<td value='ppp'>" + data[i].ppp + "</td>");
                    $("#"+data[i].ssn).append("<td value='rating'>" + data[i].rating + "</td>");
                    $("#"+data[i].ssn).append("<td>" + data[i].lastActiveDate + "</td>");
                    $("#"+data[i].ssn).append("<td><input type='submit' onclick='deleteCustomer(\"" + data[i].ssn + "\")' value='Delete Customer' /></td>");
                }
            }
        });
        $("#customersTable").on('click', 'td', function (event) {
            if ($(this).children().length === 0)
            {
                var innerHTML = $(this).text();
                $(this).html("");
                $(this).append("<input type='text' value='" + innerHTML + "' /> <input type='submit' onclick =changeValue($(this))");


            }
        });
    }
}
;

function deleteCustomer(ssn)
{
    $.ajax({
        url: '/MatchesFromAbove/deleteCustomer',
        type: 'POST',
        data: 'ssn=' + ssn,
        dataType: 'text',
        success: function (data) {
            
        }
    });
    $("tr[value=" + ssn + "]").empty();

    $("tr[value=" + ssn+ "]").remove();
}

