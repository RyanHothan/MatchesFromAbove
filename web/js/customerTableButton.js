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
            success: function(data) {
                //pop up the table
                $("#customersTable").show();
                //populate the data in the table
                for (i = 0; i < data.length; i++)
                {
                    $("#customersTable > tbody").append("<tr value=" + data[i].ssn + " id=" + data[i].ssn + "></tr>");
                    $("#" + data[i].ssn).append("<td value='ssn'>" + data[i].ssn + "</td>");
                    $("#" + data[i].ssn).append("<td value='ppp'>" + data[i].ppp + "</td>");
                    $("#" + data[i].ssn).append("<td value='rating'>" + data[i].rating + "</td>");
                    $("#" + data[i].ssn).append("<td value='lastActiveDate'>" + data[i].lastActiveDate + "</td>");
                    $("#" + data[i].ssn).append("<td><input type='submit' onclick='deleteCustomer(\"" + data[i].ssn + "\")' value='Delete Customer' /></td>");
                }
            }
        });
        //bind on click function for editing purposes
  $("#customersTable").on('click', 'td', function() {
            //if children length is not 0 that means this table cell has been clicked before
            if ($(this).children().length === 0)
            {
                if ($(this).attr('value') !== '')
                {
                    var innerHTML = $(this).text();
                    $(this).html("");
                    $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit'");
                    //change focus to the input box
                    $("#changing").focus();
                    //check to see if user has clicked 'Enter'
                    $("#changing").on('keyup', function(e) {
                        var someData = $(this).attr('value');
                        //this is the value of the table cell
                        var infoType = $(this).parent().attr('value');
                        var customerToChange = $(this).parent().parent().attr('value');
                        var tr = $(this).parent().parent();
                        var newData = $(this).val();
                        var tdCell = $(this).parent();
                        //keycode 13 is for ENTER. if someone clicks enter then we make a servlet call
                        if (e.keyCode === 13){
                            alert("lala");
                        $.ajax({
                            url: '/MatchesFromAbove/EditCustomer',
                            type: 'POST',
                            data: {typeOfData: infoType, thingToEdit: newData, customer: customerToChange},
                            dataType: 'text',
                            success: function(e) {

                                if (!(e === "F")) {
                                    $(this).remove();
                                    tdCell.html(newData);
                                

                                if (infoType === "ssn")
                                {
                                    tr.attr('value', newData);
                                    $(this).attr('value', newData);
                                }
                            }else {
                                 $(this).remove();
                                    tdCell.html(someData);
                                
                            }
                            }
                        });

                        }

                    });
                    $("#changing").on("focusout", function() {
                        var someData = $(this).attr('value');
                        //this is the value of the table cell
                        var infoType = $(this).parent().attr('value');
                        var customerToChange = $(this).parent().parent().attr('value');
                        var tr = $(this).parent().parent();
                        var newData = $(this).val();
                        var tdCell = $(this).parent();
                        //keycode 13 is for ENTER. if someone clicks enter then we make a servlet call
                        
                        $.ajax({
                            url: '/MatchesFromAbove/EditCustomer',
                            type: 'POST',
                            data: {typeOfData: infoType, thingToEdit: newData, customer: customerToChange},
                            dataType: 'text',
                            success: function(e) {

                                if (!(e === "F")) {
                                    $(this).remove();
                                    tdCell.html(newData);
                                

                                if (infoType === "ssn")
                                {
                                    tr.attr('value', newData);
                                    $(this).attr('value', newData);
                                }
                            }else {
                                 $(this).remove();
                                    tdCell.html(someData);
                                
                            }
                            }
                        });
                        
                    });
                }
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
        dataType: 'text'
    });
    $("tr[value=" + ssn + "]").empty();
    $("tr[value=" + ssn + "]").remove();
}

