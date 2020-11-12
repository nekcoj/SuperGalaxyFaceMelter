package com.company.utils;
import java.util.function.Consumer;

public class MenuChoice {
    private char key;
    private String sTitle;
    private Consumer functionToCall;
    private Object parameter;

    public MenuChoice(String sTitle, char key, Consumer functionToCall) {
        this(sTitle,key,functionToCall,null);
    } // MenuChoice

    public MenuChoice(String sTitle, char key, Consumer functionToCall, Object parameter) {
        this.key = key;
        this.sTitle = sTitle;
        this.functionToCall = functionToCall;
        this.parameter = parameter;
    } // MenuChoice

    public Consumer getFunctionToCall() {
        return functionToCall;
    } // getFunctionToCall

    public char getKey() {
        return key;
    } // getKey

    protected String getTitle() {
        return sTitle;
    } // getTitle

    protected String getFullTitle() {
        return getKey() + ". " + getTitle();
    } // getFullTitle

    public Object getParameter() { return parameter; }
} // class MenuChoice
