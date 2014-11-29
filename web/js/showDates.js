function showDates()
{
    var empSSN = $("input[name=ssn]").val();
    
    $.ajax({
        url: '/MatchesFromAbove/DateGetting',
        type: 'GET',
        dataType: 'JSON',
        data: {SSN: empSSN},
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
            }
        }
        });
        alert("omfg");
}

