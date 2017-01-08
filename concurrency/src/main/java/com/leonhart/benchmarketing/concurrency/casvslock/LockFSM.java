package com.leonhart.benchmarketing.concurrency.casvslock;

import java.util.function.Function;

public class LockFSM {
    private State sessionState = State.DISASSOCIATED;

    public synchronized void associate()
    {
        this.sessionState = State.ASSOCIATE_PENDING;
    }

    public synchronized boolean onAssociated()
    {
        return transition( this.sessionState::onAssociated );
    }

    public synchronized void disassociate()
    {
        this.sessionState = State.DISASSOCIATE_PENDING;
    }

    public synchronized boolean onDisassociated()
    {
        return transition( this.sessionState::onDisassociated );
    }

    public synchronized boolean isAssociated()
    {
        return this.sessionState == State.ASSOCIATED;
    }

    private synchronized boolean transition( final Function<State, State> transitionFunction )
    {
        final State lastState = this.sessionState;
        this.sessionState = transitionFunction.apply( this.sessionState );
        return lastState != this.sessionState;
    }

}
