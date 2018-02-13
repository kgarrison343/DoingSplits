package models;

/**
 * Created by kgarrison on 2/13/2018.
 */

public class Game {
    private String _name;
    private String _console;
    private Time _personalBest;

    public Game(){
        _personalBest = new Time();
    }

    public Game(String name, String console){
        _name = name;
        _console = console;
        _personalBest = new Time();
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_console() {
        return _console;
    }

    public void set_console(String _console) {
        this._console = _console;
    }

    public Time get_personalBest() {
        return _personalBest;
    }

    public void set_personalBest(Time _personalBest) {
        this._personalBest = _personalBest;
    }

    public String toString(){
        return _name + ": " + _console + " PB:" + _personalBest.toString();
    }
}
