function makeReferral()
{
    var profileA = $('#referralProfileA').val();
    var profileB = $('#referralProfileB').val();
    var profileC = $('#profileBox option:selected').text();
    $.ajax({
        url: '/MatchesFromAbove/makeReferral',
        type: 'POST',
        data: {profileA: profileA, profileB: profileB, profileC : profileC},
        dataType: 'text',
        success: function (e) {
            alert("You made a referral for " + profileA + " to date " + profileB);
        }
    })
}