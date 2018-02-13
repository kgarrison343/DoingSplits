package models;

/**
 * Created by kgarrison on 2/13/2018.
 */

public class Time {
    private int _hours;
    private int _minutes;
    private int _seconds;
    private int _thousandthsOfSeconds;

    @Override
    public String toString() {
        String ret = "";

        if(_hours != 0){
            ret = _hours + ":";
        }
        ret += _minutes + ":" + _seconds + "." + _thousandthsOfSeconds;

        return ret;
    }
}
