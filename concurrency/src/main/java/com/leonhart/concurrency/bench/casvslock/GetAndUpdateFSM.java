/************************************************************************
 ** PROJECT:   XVP
 ** LANGUAGE:  Java JDK 1.8
 **
 ** COPYRIGHT: FREQUENTIS AG
 **            Innovationsstrasse 1
 **            A-1100 VIENNA
 **            AUSTRIA
 **            tel +43 1 811 50-0
 **
 ** The copyright to the computer program(s) herein
 ** is the property of Frequentis AG, Austria.
 ** The program(s) shall not be used and/or copied without
 ** the written permission of Frequentis AG.
 **
 ************************************************************************/

package com.leonhart.concurrency.bench.casvslock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class GetAndUpdateFSM
{
   private final AtomicReference<State> sessionState = new AtomicReference<>( State.DISASSOCIATED );

   public void associate()
   {
      this.sessionState.set( State.ASSOCIATE_PENDING );
   }

   public boolean onAssociated()
   {
      return transition( State.ASSOCIATED );
   }

   public void disassociate()
   {
      this.sessionState.set( State.DISASSOCIATE_PENDING );
   }

   public boolean onDisassociated()
   {
      return transition( State.DISASSOCIATED );
   }

   public boolean isAssociated()
   {
      return this.sessionState.get() == State.ASSOCIATED;
   }

   private boolean transition( final State targetState )
   {
      final AtomicBoolean isValid = new AtomicBoolean( true );
      this.sessionState.getAndUpdate( oldState ->
      {
         if ( oldState.transition() != targetState )
         {
            isValid.set( false );
            return oldState;
         }
         return oldState.transition();
      } );

      return isValid.get();
   }
}
