function makeDate()
{
    var profileId1 = $("#profileBox option:selected").text();
    var profileId2 = $("#viewProfileTable").attr("name");
    
    $.ajax({
        url: '/MatchesFromAbove/DateHelper',
        type: 'POST',
        dataType: 'text',
        data: {profileA: profileId1, profileB:profileId2},
        success: function()
        {
            alert("You liked " + profileId2 + "!");
        }
    });
}