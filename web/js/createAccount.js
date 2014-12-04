/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createAccount()
{
    var ccns = $("input[name=ccn]").val();
    var ssn = $("input[name=ssn]").val();
    $.ajax({
        url: '/MatchesFromAbove/CreateAccountHelper',
        type: 'GET',
        data: {ccn: ccns, ssn: ssn},
        dataType: 'JSON',
        success: function (data)
        {
            $("#accountBox").append("<option>" + data[0].accountNumber + "</option>");
            $.modal.close();
        }
    });
}

