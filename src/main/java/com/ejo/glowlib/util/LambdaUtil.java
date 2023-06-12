package com.ejo.glowlib.util;

/**
 * Lambda Util offers a bunch of interfaces with different attributes that can be used in different types
 * of lambda statements depending on the data type chosen. In order to choose a specific variable from the vars
 * array, just type the index of it when using inside the lambda statement.
 */
public class LambdaUtil {

    /**
     * The Void Return functional interface allows for a void function to be executed with a set of parameters, PARAM
     * ::Example Lambda Statement: LambdaUtil.voidRet lambda = (params) -> {};
     */
    @FunctionalInterface
    public interface actionVoid {
        void run(Object... params);
    }

    /**
     * The Custom Return functional interface allows for any specified return value (except void) with a set of parameters, PARAM
     * ::Example Lambda Statement: LambdaUtil.custRet<boolean> lambda = (params) -> {return false};
     * @param <RETURN>
     */
    @FunctionalInterface
    public interface action<RETURN> {
        RETURN run(Object... params);
    }
}
