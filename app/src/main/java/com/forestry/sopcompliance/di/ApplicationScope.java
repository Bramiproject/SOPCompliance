package com.forestry.sopcompliance.di;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by abrami on 08/09/2017.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}