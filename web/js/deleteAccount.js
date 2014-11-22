function deleteAccount()
{
    var accountNumber = $("#accountBox option:selected").text();
    
    $.ajax(
    {
        url:'/MatchesFromAbove/AccountDeleteHelper',
        type: 'POST',
        dataType: 'JSON',
        data: 'accountNumber='+accountNumber,
    });
    $("#accountInfoTable").hide();
    $("#deleteAccountButton").hide();
    $("select#accountBox option:selected").remove();
}


