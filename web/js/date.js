function makeDate()
{
    var profileId1 = $("#profileBox option:selected").text();
    var profileId2 = $("#viewProfileTable").attr("name");
    var year = $("#year option:selected").val();
    var month = $("#month option:selected").val();
    var day = $("#day option:selected").val();
    var location = $("#location").val();
    var hour = $("#hour option:selected").val();
    var minute = $("#minute  option:selected").val();
    var comments = $("#comments").val();
    
    
    
    $.ajax({
        url: '/MatchesFromAbove/DateHelper',
        type: 'POST',
        dataType: 'text',
        data: {profileA: profileId1, profileB:profileId2,day: day, month: month, year: year, location: location, hour: hour, minute: minute, comments: comments},
        success: function()
        {
        }
    });
}