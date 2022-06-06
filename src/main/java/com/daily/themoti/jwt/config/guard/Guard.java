package com.daily.themoti.jwt.config.guard;

public abstract class Guard {

    public final boolean check(Long id) {
        return isResourceOwner(id);
    }

    abstract protected boolean isResourceOwner(Long id);
}
