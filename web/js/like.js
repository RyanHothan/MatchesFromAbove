function makeLike()
{
    var profileId2 = $("#viewProfileTable").attr("name");
    var profileId1 = $("#profileBox option:selected").text();
    
    $.ajax({
        url: '/MatchesFromAbove/LikeHelper',
        type: 'POST',
        dataType: 'text',
        data: {likee: profileId1, liker:profileId2},
        success: function ()
        {
            
        }
    });
}


