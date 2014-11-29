function deleteProfile()
{
    var profileId = $("#profileBox option:selected").text();
    
    $.ajax(
    {
        url:'/MatchesFromAbove/ProfileDeleteHelper',
        type: 'POST',
        dataType: 'JSON',
        data: 'profileId='+profileId,
    });
    $("#profileInfoTable").hide();
    $("#deleteProfileButton").hide();
    $("select#profileBox option:selected").remove();
    $("#recProfileTable").empty();
}