package com.leonhart.benchmarketing.concurrency.casvslock;

public enum State
{
    ASSOCIATE_PENDING
            {
                @Override
                public State transition()
                {
                    return ASSOCIATED;
                }
            },
    ASSOCIATED,
    DISASSOCIATE_PENDING
            {
                @Override
                public State transition()
                {
                    return DISASSOCIATED;
                }
            },
    DISASSOCIATED;

    public State transition()
    {
        return null;
    }

    public State onAssociated( final State currentState )
    {
        return currentState;
    }


    public State onDisassociated( final State currentState )
    {
        return currentState;
    }
}
