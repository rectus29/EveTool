package com.rectuscorp.evetool.web.component.BootStrapFeedbackPanel;


/*-----------------------------------------------------*/
/* User: Rectus for          Date: 04/12/12 14:52 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class BootStrapFeedbackPanel extends FeedbackPanel{

    public BootStrapFeedbackPanel(String id) {
        super(id);
    }

    public BootStrapFeedbackPanel(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
    }



    @Override
    protected String getCSSClass(FeedbackMessage message) {
        switch (message.getLevel()){
            case 100:
                return "alert alert-info";
            case 200:
                return "alert alert-info";
            case 250:
                return "alert alert-success";
            case 300:
                return "alert alert-warning";
            case 400:
                return "alert alert-danger";
            default:
                return "alert alert-danger";
        }
    }
}
