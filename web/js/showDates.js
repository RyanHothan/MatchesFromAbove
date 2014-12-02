function showDates()
{
    var empSSN = $("input[name=ssn]").val();
    if ($("#profileBox").length !== 0)
    {
        var profileId = $("#profileBox option:selected").val();
    }
    else
    {
        var profileId = "";
    }
    $.ajax({
        url: '/MatchesFromAbove/DateGetter',
        type: 'GET',
        dataType: 'JSON',
        data: {SSN: empSSN, profileId: profileId},
        success: function (data)
        {
            for (i = 0; i < data.length; i++)
            {
                $("#empDatesTable").append("<tr id = 'date" + i + "'></tr>");
                $("#date" + i).append("<td id = 'profile1Id" + i + "'></td>");
                $("#date" + i).append("<td id = 'profile2Id" + i + "'></td>");
                $("#date" + i).append("<td id = 'dateTime" + i + "'></td>");
                $("#date" + i).append("<td id = 'location" + i + "'></td>");
                $("#date" + i).append("<td id = 'fee" + i + "'></td>");
                $("#date" + i).append("<td id = 'comments" + i + "'></td>");
                $("#date" + i).append("<td id = 'user1Rating" + i + "'></td>");
                $("#date" + i).append("<td id = 'user2Rating" + i + "'></td>");

                $("#profile1Id" + i).html(data[i].profile1Id);
                $("#profile2Id" + i).html(data[i].profile2Id);
                $("#dateTime" + i).html(data[i].dateTime);
                $("#location" + i).html(data[i].location);
                $("#fee" + i).html(data[i].fee);
                $("#comments" + i).html(data[i].comments);
                $("#user1Rating" + i).html(data[i].user1Rating);
                $("#user2Rating" + i).html(data[i].user2Rating);

                if (profileId === $("profile1Id" + i).html())
                {
                    $("#user1Rating" + i).on('click', function ()
                    {
                        var innerHTML = $(this).text();
                        $(this).html("");
                        $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit'");
                    });
                    $("#changing").on('keyup', function() {
                        var someData = $(this).attr('value');
                       var  
                    });
                }
                else
                {
                    $("#user2Rating" + i).on('click', function ()
                    {
                        var innerHTML = $(this).text();
                        $(this).html("");
                        $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit'");
                    });
                }

            }
            return data;
        }
    });
}


