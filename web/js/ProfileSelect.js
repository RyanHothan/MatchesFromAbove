function readProfile()
{
    var profileId = $("select#profileBox option").filter(":selected").text();
    if(profileId === "No Profile Selected")
    {
      $("#infoTable").hide();
      return;
    }
    $.ajax({
        url:'/MatchesFromAbove/ProfileListHelper',
        type: 'GET',
        dataType: 'JSON',
        data: 'foo='+profileId,
        success: function(data)
        {
            $("#infoTable").show();
            for(i = 0; i < data.length; i++)
            {
                $("#profileId").html(data[i].profileId);
                $("#age").html(data[i].age);
                $("#gender").html(data[i].gender);
                $("#ageRangeStart").html(data[i].ageRangeStart);
                $("#ageRangeEnd").html(data[i].ageRangeEnd);
                $("#geoRange").html(data[i].geoRange);
                $("#hobbies").html(data[i].hobbies);
                $("#height").html(data[i].height);
                $("#weight").html(data[i].weight);
                $("#hairColor").html(data[i].hairColor);
                $("#profileCreationDate").html(data[i].profileCreationDate);
            }
        }
    });
}