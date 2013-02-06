package pl.itcrowd.tutorials.richfaces.view;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * User: Rafal Gielczowski
 * Date: 2/6/13 Time: 10:39 AM
 */
@ManagedBean
@ApplicationScoped
public class RequestCounter {

    private int requestCount = 0;

    public RequestCounter()
    {
    }

    public int getRequestCount()
    {
        return ++requestCount;
    }

    public void setRequestCount(int requestCount)
    {
        this.requestCount = requestCount;
    }
}
