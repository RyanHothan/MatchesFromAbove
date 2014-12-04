function readAccount()
{
    var accountNumber = $("#accountBox option:selected").text();
    if (accountNumber === "No Account Selected")
    {
        $("#accountInfoTable").hide();
        $("#deleteAccountButton").hide();
        return;
    }
    $.ajax({
        url: '/MatchesFromAbove/AccountListHelper',
        type: 'GET',
        dataType: 'JSON',
        data: 'accountNumber=' + accountNumber,
        success: function (data)
        {
            $("#accountInfoTable").show();
            $("#deleteAccountButton").show();
            for (i = 0; i < data.length; i++)
            {
                $("#accountNumber").html(data[i].accountNumber);
                $("#creditCardNumber").html(data[i].creditCardNumber);
                $("#accountCreationDate").html(data[i].accountCreationDate);
            }
        }
    });
        $("#accountInfoTable").on('click', 'td', function () {
        if ($(this).children().length === 0)
        {
            if ($(this).attr('id') === 'creditCardNumber')
            { 
                var innerHTML = $(this).text();
                $(this).html("");
                $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit'");
                //change focus to input box
                $("#changing").focus();
                $("#changing").on("keyup", function (e)
                {  
                    var dataToEdit = $(this).attr('value');
                    var accountNumber = $('#accountBox option:selected').attr('value');
                    if (e.keyCode === 13)
                    {
                        $.ajax({
                            url: '/MatchesFromAbove/EditAccountHelper',
                            type: 'POST',
                            data: {dataToEdit: dataToEdit, accountNumber: accountNumber},
                            dataType: 'text'
                        });
                        var newData = $(this).val();
                        var tdCell = $(this).parent();
                        if($(this).parent().parent().attr('value') === 'accountNumber')
                        {
                            $("#accountBox option:selected").html(newData);
                            $("#accountBox option:selected").attr('value', newData);
                        }
                        $(this).remove();
                        tdCell.html(newData);
                    }
                });
                $("#changing").on("focusout", function(){
                    
                    var dataToEdit = $(this).attr('value');
                    var accountNumber = $('#accountBox option:selected').attr('value');
                    $.ajax({
                            url: '/MatchesFromAbove/EditAccountHelper',
                            type: 'POST',
                            data: {dataToEdit: dataToEdit, accountNumber: accountNumber},
                            dataType: 'text'
                        });
                        var newData = $(this).val();
                        var tdCell = $(this).parent();
                        if($(this).parent().parent().attr('value') === 'accountNumber')
                        {
                            $("#accountBox option:selected").html(newData);
                            $("#accountBox option:selected").attr('value', newData);
                        }
                        $(this).remove();
                        tdCell.html(newData);
                });
            }
        }

    })
}
