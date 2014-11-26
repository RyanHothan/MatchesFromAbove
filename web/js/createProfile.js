function checkProfile()
{
    var age = $("input[name=age]").val();
    var profilePicture = $("input[name=profilePicture]").val();
    var ageRangeStart = $("input[name=ageRangeStart]").val();
    var ageRangeEnd = $("input[name=ageRangeEnd]").val();
    var geoRange = $("input[name=geoRange]").val();
    var height = $("input[name=height]").val();
    var weight = $("input[name=weight]").val();
    var profileId = $("input[name=profileId]").val();
    var gender = $("input[name=gender]").val();
    var hobbies = $("input[name=hobbies]").val();
    var email = $("input[name=email]").val();
    var ssn = $("input[name=ssn]").val();
    var hairColor = $("input[name=hairColor]").val();
    var password = $("input[name=password]").val();

    if (age === "" || ageRangeStart === "" || ageRangeEnd === "" || ageRangeEnd === ""
            || geoRange === "" || height === "" || weight === "")
    {
        alert("Profile Creation Failed");
    }
    else
    {
        $.ajax({
            url: '/MatchesFromAbove/CreateProfileHelp',
            type: 'GET',
            data: {profilePicture : profilePicture, profileId: profileId, age: age, ageRangeStart: ageRangeStart,
                ageRangeEnd: ageRangeEnd, geoRange: geoRange, height: height, weight: weight,
                gender: gender, hobbies: hobbies, email: email, ssn: ssn, hairColor: hairColor,
                password: password},
            dataType: 'text',
            success: function (data)
            {
                if (data === "False")
                {
                    alert("Profile Creation Failed");
                }
                else if (data === "True")
                {
                    $("#profileBox").append("<option value = '" + profileId + "'>" + profileId + "</option>");
                    $.modal.close();
                }
            }
        });
    }
}

