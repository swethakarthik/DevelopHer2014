
$(document).ready(function() {

    var days = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"];
    var months = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];
    var years = ["2014", "2015", "2016"];
    var hours = ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"];
    var minutes = ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"];
    var seconds = ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"];

    var daysDropDown = "<select id='days'><option>Day</option>";
    for (i = 0; i < days.length; i++) {
        daysDropDown += "<option value='" + days[i] + "'>" + days[i] + "</option>";
    }
    daysDropDown += "</select>";

    var monthsDropDown = "Date: <select id='months'><option>Month</option>";
    for (i = 0; i < months.length; i++) {
        monthsDropDown += "<option value='" + months[i] + "'>" + months[i] + "</option>";
    }
    monthsDropDown += "</select>";

    var yearsDropDown = "<select id='years'><option>Year</option>";
    for (i = 0; i < years.length; i++) {
        yearsDropDown += "<option value='" + years[i] + "'>" + years[i] + "</option>";
    }
    yearsDropDown += "</select>";


    var hoursDropDown = "Time: <select id='hours'><option>Hours</option>";
    for (i = 0; i < hours.length; i++) {
        hoursDropDown += "<option value='" + hours[i] + "'>" + hours[i] + "</option>";
    }
    hoursDropDown += "</select>";


    var minutesDropDown = "<select id='minutes'><option>Minutes</option>";
    for (i = 0; i < minutes.length; i++) {
        minutesDropDown += "<option value='" + minutes[i] + "'>" + minutes[i] + "</option>";
    }
    minutesDropDown += "</select>";


    var secondsDropDown = "<select id='seconds'><option>Seconds</option>";
    for (i = 0; i < seconds.length; i++) {
        secondsDropDown += "<option value='" + seconds[i] + "'>" + seconds[i] + "</option>";
    }
    secondsDropDown += "</select>";

    var modal_body = monthsDropDown + " " + daysDropDown + " " + yearsDropDown
        + "<br/><br/>"
        + " " + hoursDropDown + " " + minutesDropDown + " " + secondsDropDown;

    var candidates = [
        {"managerId":"44645743",
            "candidateId":"24306816",
            "isContacted":true,
            "isInterested":true,
            "contactDate":"Nov 15, 2014 10:20:22 AM",
            "email":"mail2geetu85@gmail.com",
            "candidateName":"Geetanjali Gupta",
            "visa" : "H4"},
        {"managerId":"44645743",
            "candidateId":"44645743",
            "isContacted":false,
            "contactDate":"",
            "email":"yonachali@yahoo.com",
            "candidateName":"Jonida Cali"
        }
    ];

    for (var i = 0; i < candidates.length; i++) {
        var tr;
        var interested;
        if (candidates[i].isInterested == null) {
            interested = "";
        } else if (candidates[i].isInterested == true) {
            interested = "Yes";
        } else {
            interested = "No";
        }

        var isContacted;
        if (candidates[i].isContacted == true) {
            isContacted = "Yes";
        } else {
            isContacted = "No";
        }

        var button = "<button type='button' class='btn btn-primary btn-" + candidates[i].candidateId + "' data-toggle='modal' data-target='#myModal_" + i + "'>"
            + "Schedule"
            + "</button>"
            + "<div class='modal fade' id='myModal_" + i + "' tabindex='-1' role='dialog' aria-labelledby='myModalLabel' aria-hidden='true' style='top:50%;outline: none;'>"
            + "<div class='modal-dialog'>"
            + "<div class='modal-content'>"
            + "<div class='modal-header'>"
            + "<button type='button' class='close' data-dismiss='modal'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>"
            + "<h4 class='modal-title' id='myModalLabel'>Send Invite</h4>"
            + "</div>"
            + "<div class='modal-body'>"
            + modal_body
            + "<input type='hidden' value='" + candidates[i].candidateId + "' name='candidateId' id='candidateId'>"
            + "<br/><br/><p style='text-align:center;'><button class='btn-primary' id='sendInvite'>Send</button></p>"
            + "</div>"
            + "</div>"
            + "</div>"
            + "</div>";

        var isEligible;
        if (candidates[i].visa == null) {
            isEligible = "";
        } else {
            isEligible = candidates[i].visa;
        }

        tr = "<tr>"
        + "<td><a href='http://linkedin.com/profile/view?id=" + candidates[i].candidateId + "'>" + candidates[i].candidateName + "</a></td>"
        + "<td style='text-align:center;vertical-align:middle'>" + isContacted + "</td>"
        + "<td style='text-align:center;vertical-align:middle'>" + interested + "</td>"
        + "<td style='text-align:center;vertical-align:middle'>" + isEligible + "</td>"
        + "<td style='text-align:center;vertical-align:middle'>"
        + button
        + "</td>"
        + "<td style='text-align:center;vertical-align:middle'>"
        + "<script src='https://apis.google.com/js/platform.js' async defer></script>"
        + "<g:hangout render='createhangout'"
        + "invites='[{ id : '" + candidates[i].email + "', invite_type : 'EMAIL' }]' widget_size='72'"
        + ">"
        + "</g:hangout>"
        + "</td>"
        + "<td style='text-align:center;vertical-align:middle'><a href='http://collabedit.com/new'>Start</a></td>"
        + "<td style='text-align:center;vertical-align:middle'><a href=''>Send</a></td>"
        + "</tr>";
        $(".tableBody").append(tr);

        YEvent.on(window, 'load', function () {
            $("#myModal_" + i).modal('show');
        });
    }

    $("button#sendInvite").click(function () {
        var candidateId = $(this).parent().siblings("#candidateId");
        var month = candidateId.siblings("select#months").val();
        var day = candidateId.siblings("select#days").val();
        var year = candidateId.siblings("select#years").val();
        var hour = candidateId.siblings("select#hours").val();
        var minute = candidateId.siblings("select#minutes").val();
        var seconds = candidateId.siblings("select#seconds").val();
        candidateId = candidateId.val();
        var date = year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + seconds + "UTC";

        //TODO: calendar event $ajax

        $(".close").click();
        $(".btn-" + candidateId).parent().html(month + "/" + day + "/" + year + " at " + hour + ":" + minute);
    });

    //TODO: get candidates list


});
