package com.xx.demoproject.demofactory.dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by xuxin on 2017/2/3.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
