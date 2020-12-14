package com.greedystar.generator.invoker.base;

/**
 * @author GreedyStar
 * @since 2018/9/5
 */
public abstract class AbstractBuilder {
    protected AbstractInvoker invoker;

    public Invoker build() {
        if (!isParamtersValid()) {
            return null;
        }
        return invoker;
    }

    private boolean isParamtersValid() {
        try {
            checkBeforeBuild();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 在创建invoker对象前进行一些检查，由子类去实现
     *
     * @throws Exception 检查失败则抛出异常
     */
    protected abstract void checkBeforeBuild() throws Exception;

}
