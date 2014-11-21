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
                //pop up the table
                $("#customersTable").show();
                //populate the data in the table
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
        //bind on click function for editing purposes
        $("#customersTable").on('click', 'td', function (event) {
            //if children length is not 0 that means this table cell has been clicked before
            if ($(this).children().length === 0)
            {
                var innerHTML = $(this).text();
                var tdValue = $(this).attr('value');
                
                $(this).html("");
                $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit' onclick =changeValue($(this))");
                $("#changing").on('keyup', function(e){
                    var someData = $(this).attr('value');
                    var infoType = $(this).parent().attr('value');
                    var customerToChange = $(this).parent().parent().attr('value');
                    //keycode 13 is for ENTER. if someone clicks enter then we make a servlet call
                    if(e.keyCode === 13)
                    {
                        $.ajax({
                            url: '/MatchesFromAbove/EditCustomer',
                            type: 'POST',
                            data: {typeOfData: infoType, thingToEdit : someData, customer: customerToChange},
                            dataType: 'text',
                            success: function(data){}
                        });
                    }
                });
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
        success: function(data){
            
        }
    });
    $("tr[value=" + ssn + "]").empty();

    $("tr[value=" + ssn+ "]").remove();
}

