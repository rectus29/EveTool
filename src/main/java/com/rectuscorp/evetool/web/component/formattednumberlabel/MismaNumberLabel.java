package com.rectuscorp.evetool.web.component.formattednumberlabel;


/*-----------------------------------------------------*/
/* User: Rectus for          Date: 03/04/13 10:57 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import com.rectuscorp.evetool.web.Config;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

public class MismaNumberLabel<T extends Number> extends Label {

    public MismaNumberLabel(String id) {
        super(id);
    }

    public MismaNumberLabel(String id, String label) {
        super(id, label);
    }

    public MismaNumberLabel(String id, Serializable label) {
        super(id, label);
    }

    public MismaNumberLabel(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        Number n = (Number) getDefaultModelObject();
        if (n == null) {
            replaceComponentTagBody(markupStream, openTag, "-");
        } else {
            replaceComponentTagBody(markupStream, openTag, Config.get().format(n.doubleValue()));
        }
    }
}
