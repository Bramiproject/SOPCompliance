package com.forestry.sopcompliance.data.model;

/**
 * Created by mmahyudd on 4/28/2017.
 */

import android.view.View;
/**
 * POJO for storing the data associated with a row in the attributes list
 */
public class AttributeItem  {


    private Object value;

    private View view;

    public View getView() {

        return view;
    }

    public void setView(View view) {

        this.view = view;
    }

    public Object getValue() {

        return value;
    }

    public void setValue(Object value) {

        this.value = value;
    }

}
