package pl.itcrowd.tutorials.richfaces.utils;

import pl.itcrowd.tutorials.richfaces.view.RequestCounter;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import java.util.logging.Logger;

/**
 * User: Rafal Gielczowski
 * Date: 2/6/13 Time: 10:14 AM
 */
public class PhaseListener implements javax.faces.event.PhaseListener{

    private static final Logger LOGGER = Logger.getLogger(PhaseListener.class.getCanonicalName());

    private RequestCounter requestCounter;

    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.ANY_PHASE;
    }

    @Override
    public void afterPhase(PhaseEvent phaseEvent)
    {
        System.out.println("BEFORE:" + phaseEvent.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent)
    {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        ExpressionFactory expressionFactory =
            FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
        ValueExpression valueExpression = expressionFactory.createValueExpression(elContext, "#{requestCounter}", RequestCounter.class);
        this.requestCounter = (RequestCounter)valueExpression.getValue(elContext);

        LOGGER.info("Request count:"+requestCounter.getRequestCount());
        System.out.println("AFTER:" + phaseEvent.getPhaseId());
    }
}
