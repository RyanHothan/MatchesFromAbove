function makeLike()
{
    var profileId2 = $("#viewProfileTable").attr("name");
    var profileId1 = $("#profileBox option:selected").text();
    
    $.ajax({
        url: '/MatchesFromAbove/LikeHelper',
        type: 'POST',
        dataType: 'text',
        data: {likee: profileId2, liker:profileId1},
        success: function()
        {
            alert("You liked " + profileId2 + "!");
        }
    });
}


