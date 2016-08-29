
import com.alarm.utils.TimeUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Created by pavlo.shtefanesku on 8/29/2016.
 */

public class AlarmTest {
    private final String alarmInputPath = "#alarmTime";
    private final String submitId = "#btnSubmit";
    private final String tableRowPath = "#alarmTable tr";
    private final String timeString = "Time is: %s";

    @Before
    public void openPage() {
        open("/Alarm");
    }

    @Test
    public void initialCheckElements() {
        //checking correct values and places on page
        $$(tableRowPath).shouldHave(size(3));
        $$(tableRowPath).get(0).shouldHave(text("Simple Alarm Clock"));

        $$(tableRowPath).get(1).shouldHave(text(String.format(timeString, TimeUtils.getCurrentTime())));
        $$(tableRowPath).get(2).$(alarmInputPath).shouldBe(visible);
        $$(tableRowPath).get(2).$(submitId).shouldBe(visible).shouldHave(value("Set alarm [hh:mm]"));
    }

    @Test
    public void testAlarm() {
        //entering current time value as alarm time
        enterAlarmTime(TimeUtils.getCurrentTime());

        //checking disappearance of 'old' elements
        $(alarmInputPath).$(submitId).shouldNotBe(visible);

        //checking appearance of 'Alarm!' sign, unfortunately we can't check if alarm sound is going off
        $("#alarmNotification").shouldBe(visible).shouldHave(text("ALARM!"));
    }

    private void enterAlarmTime(String time) {
        $(alarmInputPath).setValue(time);
        $(submitId).submit();
    }

    @Test
    public void testDelayedAlarm() {
        enterAlarmTime(TimeUtils.getDifferentTime(1));
        $(submitId).waitUntil(disappears, 60000);
        $("#alarmNotification").shouldBe(visible).shouldHave(text("ALARM!"));
    }

    @Test
    public void testInvalidValues() {
        for (String invalidTime : Arrays.asList("invalidTime", TimeUtils.getDifferentTime(-1))) {
            enterAlarmTime(invalidTime);
            $$(tableRowPath).get(1).waitUntil(text(String.format(timeString, TimeUtils.getDifferentTime(1))), 60000);
            $(submitId).shouldBe(visible);
            $(alarmInputPath).shouldBe(visible);
            $("#alarmNotification").shouldNotBe(visible);
        }
    }
}
