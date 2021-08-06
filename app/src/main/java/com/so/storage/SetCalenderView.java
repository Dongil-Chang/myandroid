package com.so.storage;

import android.content.Context;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SetCalenderView {
    final String DATE_FORMAT = "yyyy-MM-dd";
    int pink = 0;
    int gray = 1;
    void setEvent(List<String> dateList, int color,MaterialCalendarView calendarView , Context context) {
        List<LocalDate> localDateList = new ArrayList<>();

        for (String string : dateList) {
            LocalDate calendar = getLocalDate(string);
            if (calendar != null) {
                localDateList.add(calendar);
            }
        }


        List<CalendarDay> datesLeft = new ArrayList<>();
        List<CalendarDay> datesCenter = new ArrayList<>();
        List<CalendarDay> datesRight = new ArrayList<>();
        List<CalendarDay> datesIndependent = new ArrayList<>();


        for (LocalDate localDate : localDateList) {

            boolean right = false;
            boolean left = false;

            for (LocalDate day1 : localDateList) {


                if (localDate.isEqual(day1.plusDays(1))) {
                    left = true;
                }
                if (day1.isEqual(localDate.plusDays(1))) {
                    right = true;
                }
            }

            if (left && right) {
                datesCenter.add(CalendarDay.from(localDate));
            } else if (left) {
                datesLeft.add(CalendarDay.from(localDate));
            } else if (right) {
                datesRight.add(CalendarDay.from(localDate));
            } else {
                datesIndependent.add(CalendarDay.from(localDate));
            }
        }

        if (color == pink) {
            setDecor(calendarView ,datesCenter, R.drawable.p_center , context);
            setDecor(calendarView ,datesLeft, R.drawable.p_left , context);
            setDecor(calendarView ,datesRight, R.drawable.p_right , context);
            setDecor(calendarView ,datesIndependent, R.drawable.p_independent , context);
        } else {
            setDecor(calendarView ,datesCenter, R.drawable.g_center , context);
            setDecor(calendarView ,datesLeft, R.drawable.g_left , context);
            setDecor(calendarView ,datesRight, R.drawable.g_right , context);
            setDecor(calendarView ,datesIndependent, R.drawable.g_independent , context);
        }
    }

    void setDecor(MaterialCalendarView calendarView, List<CalendarDay> calendarDayList, int drawable, Context context) {
        calendarView.addDecorators(new EventDecorator(context
                , drawable
                , calendarDayList));
    }

    LocalDate getLocalDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            Date input = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(input);
            return LocalDate.of(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH));


        } catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }
}
