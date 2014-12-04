function showDates()
{
    var empSSN = $("input[name=ssn]").val();
    $("#empDatesTable").empty();
    if ($("#profileBox").length > 0 || $("#profileBox option:selected").val() === 'No Profile Selected')
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
                
                if (profileId === $("#profile1Id" + i).html())
                {
                    $("#user1Rating" + i).on('click', function ()
                    {
                        if($(this).children().length !== 0)
                        {
                            return;
                        }
                        var innerHTML = $(this).text();
                        $(this).html("");
                        $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit'");
                        
                        $("#changing").on('keyup', function (e) {
                        var newRating = $(this).attr('value');
                        var profileOne = $(this).parent().prev().prev().prev().prev().prev().prev().html();
                        var profileTwo = $(this).parent().prev().prev().prev().prev().prev().html();
                        var dateTime = $(this).parent().prev().prev().prev().prev().html();
                        var tdCell = $(this).parent();
                        if (e.keyCode === 13)
                        {
                            $.ajax({
                                url: '/MatchesFromAbove/EditUserDateRating',
                                type: 'POST',
                                data: {profileOne: profileOne, profileTwo: profileTwo, dateTime: dateTime, ratingToChange : 'User1Rating', newRating : newRating},
                                dataType: "text"
                            });
                            $(this).remove();
                            tdCell.html(newRating);
                        }
                    });
                    });
                    
                }
                else
                {
                    $("#user2Rating" + i).on('click', function ()
                    {
                        if($(this).children().length !== 0)
                        {
                            return;
                        }
                        var innerHTML = $(this).text();
                        $(this).html("");
                        $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit'");
                        
                        $("#changing").on('keyup', function (e) {
                        var newRating = $(this).attr('value');
                        var profileOne = $(this).parent().prev().prev().prev().prev().prev().prev().html();
                        var profileTwo = $(this).parent().prev().prev().prev().prev().prev().html();
                        var dateTime = $(this).parent().prev().prev().prev().prev().html();
                        var tdCell = $(this).parent();
                        if (e.keyCode === 13)
                        {
                            $.ajax({
                                url: '/MatchesFromAbove/EditUserDateRating',
                                type: 'POST',
                                data: {profileOne: profileOne, profileTwo: profileTwo, dateTime: dateTime, ratingToChange : 'User2Rating', newRating : newRating},
                                dataType: "text"
                            });
                            $(this).remove();
                            tdCell.html(newRating);
                        }
                    });
                    });
                    
                }

            }
            return data;
        }
    });
}


