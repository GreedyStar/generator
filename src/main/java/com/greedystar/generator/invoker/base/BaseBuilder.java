package com.greedystar.generator.invoker.base;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public abstract class BaseBuilder {

    public abstract BaseInvoker build() throws Exception;

    public boolean isParamtersValid() throws Exception {
        checkBeforeBuild();
        return true;
    }

    public abstract void checkBeforeBuild() throws Exception;

}
