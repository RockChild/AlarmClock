<html>
    <head>
        <title>
        Alarm settings
        </title>
    </head>
    <body>
        <table id="alarmTable" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>
                    <td>
                         Simple Alarm Clock
                    </td>
                </tr>
                <tr>
                    <td>
                        Time is: ${currentTime}
                    </td>
                </tr>
                <tr>
                    <td>
                        <form name="setAlarm" action="setAlarm" method="get">
                            <input type="text" id="alarmTime" name="alarmTime" autofocus value="${alarmTime}" />
                            <input type="submit" id="btnSubmit" value="Set alarm [hh:mm]"/>
                        </form>
                    </td>
                </tr>
        </table>
<script>
if ('${alarmTime}' != '') {
    //If alarm is already set retriger controller every 29 seconds to check if time is up
        setInterval(function() {
            document.getElementById('alarmTime').value='${alarmTime}';
            document.setAlarm.submit();
        }, 29*1000);
    }
</script>
    </body>
</html>