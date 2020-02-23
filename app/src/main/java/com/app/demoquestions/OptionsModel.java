package com.app.demoquestions;

public class OptionsModel
{
    String str_opt_id,str_opt_name;

    public OptionsModel(String str_opt_id, String str_opt_name)
    {
        this.str_opt_id = str_opt_id;
        this.str_opt_name = str_opt_name;
    }

    public String getStr_opt_id() {
        return str_opt_id;
    }

    public void setStr_opt_id(String str_opt_id) {
        this.str_opt_id = str_opt_id;
    }

    public String getStr_opt_name() {
        return str_opt_name;
    }

    public void setStr_opt_name(String str_opt_name) {
        this.str_opt_name = str_opt_name;
    }
}
