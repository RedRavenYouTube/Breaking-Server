package com.mite.breakingeverything.asm;

/**
 * Return condition for hooks.
 */
public enum ReturnCondition {
    NEVER(false),
    ALWAYS(false),
    ON_TRUE(true),
    ON_NULL(true),
    ON_NOT_NULL(true);

    public final boolean requiresCondition;

    ReturnCondition(boolean requiresCondition) {
        this.requiresCondition = requiresCondition;
    }
}