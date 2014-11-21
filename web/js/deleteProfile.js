function deleteProfile()
{
    var profileId = $("select#profileBox option").filter(":selected").text();
    
    $.ajax(
    {
        url:'/MatchesFromAbove/ProfileDeleteHelper',
        type: 'POST',
        dataType: 'JSON',
        data: 'profileId='+profileId,
        success: function()
        {
            $("#infoTable").hide();
            $("#deleteProfileButton").hide();
            $("select#profileBox option:selected").remove();
        }
    });
}