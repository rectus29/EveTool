package com.rectuscorp.evetool.session;

import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.apache.shiro.SecurityUtils;
import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/*-----------------------------------------------------*/
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


public class EveToolSession extends WebSession {

    private static final Logger log = LogManager.getLogger(EveToolSession.class);


    public static EveToolSession get() {
        return (EveToolSession) Session.get();
    }

    public EveToolSession(Request request) {
        super(request);
        Injector.get().inject(this);
    }

    public boolean logout() {
        SecurityUtils.getSubject().logout();
        return true;
    }

    public boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

}
