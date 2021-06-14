package com.automobile.assistance.util;


import com.automobile.assistance.util.logging.Logger;
import com.automobile.assistance.util.logging.LoggerFactory;

public abstract class Test {

    private Class clazz;
    protected Logger LOG;


    protected Test() {
        clazz = getClass();
        LOG = LoggerFactory.getLogger(clazz);

        test();
    }

    protected abstract Class getClass(Class clazz);

    public void test() {
        LOG.debug("Testing......");
    }
}
