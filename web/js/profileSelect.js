jQuery(function($){
$('title').html('User Home');
});

function readProfile()
{
    var profileId = $("#profileBox option:selected").text();
    if(profileId === "No Profile Selected")
    {
        $("#profileInfoTable").hide();
        $("#deleteProfileButton").hide();
        return;
    }
    $("#recProfileTable").children().remove();
    $.ajax({
        url: '/MatchesFromAbove/ProfileListHelper',
        type: 'GET',
        dataType: 'JSON',
        data: 'profileId=' + profileId,
        success: function (data)
        {
            $("#profileInfoTable").show();
            $("#deleteProfileButton").show();
            $("#profileId").html(data[0].profileId);
            $("#age").html(data[0].age);
            $("#gender").html(data[0].gender);
            $("#ageRangeStart").html(data[0].ageRangeStart);
            $("#ageRangeEnd").html(data[0].ageRangeEnd);
            $("#geoRange").html(data[0].geoRange);
            $("#hobbies").html(data[0].hobbies);
            $("#height").html(data[0].height);
            $("#weight").html(data[0].weight);
            $("#hairColor").html(data[0].hairColor);
            $("#profileCreationDate").html(data[0].profileCreationDate);

            for(i = 1; i < data.length ;i++)
            {
                $("#recProfileTable").append("<tr id = 'recProfile" + i + "'></tr>");
                $("#recProfile"+i).append("<td class = 'viewProfile' id = 'profileId" + i +"'></td>");
                $("#recProfile"+i).append("<td id = 'age" + i +"'></td>");
                $("#recProfile"+i).append("<td id = 'gender" + i +"'></td>");
                $("#recProfile"+i).append("<td id = 'hobbies" + i +"'></td>");
                $("#recProfile"+i).append("<td id = 'height" + i +"'></td>");
                $("#recProfile"+i).append("<td id = 'weight" + i +"'></td>");
                $("#recProfile"+i).append("<td id = 'hairColor" + i +"'></td>");
                $("#profileId"+i).html(data[i].profileId);
                $("#age"+i).html(data[i].age);
                $("#gender"+i).html(data[i].gender);
                $("#hobbies"+i).html(data[i].hobbies);
                $("#height"+i).html(data[i].height);
                $("#weight"+i).html(data[i].weight);
                $("#hairColor"+i).html(data[i].hairColor);
            }
            
        }
    });

    

    $("#profileInfoTable").on('click', 'td', function () {
        if ($(this).children().length === 0)
        {
            if ($(this).attr('id') !== 'profileCreationDate' && $(this).attr('value') !== 'unchangeable')
            { 
                var innerHTML = $(this).text();
                $(this).html("");
                $(this).append("<input type='text' value='" + innerHTML + "' id='changing'/> <input type='submit'");
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
                            $("#profileBox option:selected").html(newData);
                            $("#profileBox option:selected").attr('value', newData);
                        }
                        $(this).remove();
                        tdCell.html(newData);
                    }
                });
                $("#changing").on("focusout", function(){
                    
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

    });

    
}