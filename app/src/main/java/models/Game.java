package models;

import java.util.List;

/**
 * Created by kgarrison on 2/13/2018.
 */

public class Game {
    private String _name;
    private List<String> _console;
    private Time _personalBest;

    public Game(){
        _personalBest = new Time();
    }

    public Game(String name, List<String> console){
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

    public List<String> get_console() {
        return _console;
    }

    public void set_console(List<String> _console) {
        this._console = _console;
    }

    public Time get_personalBest() {
        return _personalBest;
    }

    public void set_personalBest(Time _personalBest) {
        this._personalBest = _personalBest;
    }

    //TODO: Make to string work correctly
    public String toString(){
        StringBuilder consoles = new StringBuilder();

        for (String console : _console) {
            consoles.append(console).append(", ");
        }
        if (consoles.length() != 0) {
            consoles.delete(consoles.length() - 2, consoles.length() - 1);
        }

        return _name + ": " + consoles.toString() + " PB:" + _personalBest.toString();
    }
}
