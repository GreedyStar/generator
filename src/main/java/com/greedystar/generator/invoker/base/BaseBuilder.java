package com.greedystar.generator.invoker.base;

import com.greedystar.generator.utils.StringUtil;

/**
 * Author GreedyStar
 * Date   2018/9/5
 */
public abstract class BaseBuilder {

    public abstract BaseInvoker build() throws Exception;

    public boolean isParamtersValid(BaseInvoker invoker) throws Exception {
        if (StringUtil.isBlank(invoker.getDatabase())) {
            throw new Exception("Database can not be null.");
        }
        if (StringUtil.isBlank(invoker.getUsername())) {
            throw new Exception("Username can not be null.");
        }
        checkBeforeBuild();
        return true;
    }

    public abstract void checkBeforeBuild() throws Exception;

}
