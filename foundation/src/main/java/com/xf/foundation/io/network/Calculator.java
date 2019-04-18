package com.xf.foundation.io.network;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @Description: 一个用于计算的工具类
 * @Author: xiefu
 * @Date: 2019/4/16 20:02
 */
public class Calculator {
    private final static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    public static Object cal(String expression) throws ScriptException {
        return jse.eval(expression);
    }
}
