package com.rectuscorp.evetool.web.component.andilmodal;


/*-----------------------------------------------------*/

/* User: Rectus_29         Date: 24/04/13 17:45 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.CoreLibrariesContributor;

public class EveModal extends ModalWindow {
    private static final long serialVersionUID = 1L;
    private static ResourceReference JAVASCRIPT = new JavaScriptResourceReference(EveModal.class, "EveModal.js");
    private static ResourceReference CSS = new PackageResourceReference(EveModal.class, "EveModal.css");

    public EveModal(String id) {
        super(id);
        setCloseButtonCallback(new CloseButtonCallback() {
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                return true;
            }
        });
        this.setResizable(false);
    }

    public EveModal(String id, IModel<?> model) {
        super(id, model);
        setCloseButtonCallback(new CloseButtonCallback() {
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                return true;
            }
        });
        this.setResizable(false);
    }

    public EveModal(String id, ModalFormat format) {
        super(id);
        setSize(format);
    }

    public void setSize(ModalFormat format) {
        if (format == ModalFormat.LARGE) {
            this.setInitialWidth(90);
            this.setHeightUnit("auto");
            this.setWidthUnit("%");
        } else if (format == ModalFormat.MEDIUM) {
            this.setHeightUnit("auto");
            this.setInitialWidth(50);
            this.setWidthUnit("%");
        } else if (format == ModalFormat.SMALL) {
            this.setHeightUnit("auto");
            this.setInitialWidth(30);
            this.setWidthUnit("%");
        } else if (format == ModalFormat.TINY) {
            this.setHeightUnit("auto");
            this.setInitialWidth(25);
            this.setWidthUnit("%");
        } else if (format == ModalFormat.AUTO) {
            this.setHeightUnit("auto");
            this.setWidthUnit("auto");
        }
        this.setResizable(false);
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        CoreLibrariesContributor.contributeAjax(getApplication(), response);
        response.render(JavaScriptHeaderItem.forReference(JAVASCRIPT));

        ResourceReference cssResource = newCssResource();
        if (cssResource != null) {
            response.render(CssHeaderItem.forReference(cssResource));
        }
    }

    @Override
    public boolean showUnloadConfirmation() {
        return false;
    }

    @Override
    protected ResourceReference newCssResource() {
        return CSS;
    }

    public void show(AjaxRequestTarget target, ModalFormat format) {
        setSize(format);
        super.show(target);
    }


    public static enum ModalFormat {
        TINY, SMALL, MEDIUM, LARGE, AUTO
    }


}
