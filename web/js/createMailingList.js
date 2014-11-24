function createMailingList()
{
    var displayValue = $("#mailingList").css('display');
    if (displayValue !== 'none')
    {
        $('#mailingList').hide();
        $('#mailingList > tbody').html("");
    }
    else
    {
        $.ajax({
            url: '/MatchesFromAbove/CreateMailingList',
            type: 'GET',
            dataType: 'JSON',
            success: function (data) {
                $("#mailingList").show();
                for (i = 0; i < data.length; i++)
                {
                    $("#mailingList > tbody").append("<tr id='mailingList" + data[i].ssn + "'></tr>");
                    $("#mailingList" + data[i].ssn).append("<td id='firstName" + i + "'>" + data[i].firstName + "</td>");
                    $("#mailingList" + data[i].ssn).append("<td id='lastName" + i + "'>" + data[i].lastName + "</td>");
                    $("#mailingList" + data[i].ssn).append("<td id='email" + i + "'>" + data[i].email + "</td>");
                }
            }
        });
    }
}
