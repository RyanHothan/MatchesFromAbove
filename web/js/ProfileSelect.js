function readProfile()
{
    var profileId = $("#profileBox option:selected").text();
    if(profileId === "No Profile Selected")
    {
        $("#infoTable").hide();
        $("#deleteProfileButton").hide();
        return;
    }
    $.ajax({
        url: '/MatchesFromAbove/ProfileListHelper',
        type: 'GET',
        dataType: 'JSON',
        data: 'foo=' + profileId,
        success: function (data)
        {
            $("#infoTable").show();
            $("#deleteProfileButton").show();
            for (i = 0; i < data.length; i++)
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

    $("#infoTable").on('click', 'td', function (event) {
        if ($(this).children().length === 0)
        {
            if ($(this).attr('id') !== 'profileCreationDate' && $(this).attr('value') !== 'unchangeable')
            { 
                var innerHTML = $(this).text();
                $(this).html("");
                $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit' onclick=changeProfileValue($(this))");
                //change focus to input box
                $("#changing").focus();
                $("#changing").on("keyup", function (e)
                {
                    
                    var dataType = $(this).parent().parent().attr('value');
                    var dataToEdit = $(this).attr('value');
                    var profileId = $('#profileBox option:selected').attr('value');
                    if (e.keyCode === 13)
                    {
                        $.ajax({
                            url: '/MatchesFromAbove/EditProfileHelper',
                            type: 'POST',
                            data: {typeOfData: dataType, dataToEdit: dataToEdit, profileId: profileId},
                            dataType: 'text'
                        });
                        var newData = $(this).val();
                        var tdCell = $(this).parent();
                        if($(this).parent().parent().attr('value') === 'profileId')
                        {
                            $("#profileBox option:selected").html(newData)
                            $("#profileBox option:selected").attr('value', newData);
                        }
                        $(this).remove();
                        tdCell.html(newData);
                    }
                });
                $("#changing").on("focusout", function(e){
                    
                     var dataType = $(this).parent().parent().attr('value');
                    var dataToEdit = $(this).attr('value');
                    var profileId = $('#profileBox option:selected').attr('value');
                    $.ajax({
                            url: '/MatchesFromAbove/EditProfileHelper',
                            type: 'POST',
                            data: {typeOfData: dataType, dataToEdit: dataToEdit, profileId: profileId},
                            dataType: 'text'
                        });
                        var newData = $(this).val();
                        var tdCell = $(this).parent();
                        if($(this).parent().parent().attr('value') === 'profileId')
                        {
                            $("#profileBox option:selected").html(newData);
                            $("#profileBox option:selected").attr('value', newData);
                        }
                        $(this).remove();
                        tdCell.html(newData);
                });
            }
        }

    })


}

function changeProfileValue()
{

}
