package com.rectuscorp.evetool.web.page.admin.users.panels.edit;

import com.rectuscorp.evetool.entities.core.Role;
import com.rectuscorp.evetool.entities.core.User;
import com.rectuscorp.evetool.enums.UserAuthentificationType;
import com.rectuscorp.evetool.service.IserviceHistory;
import com.rectuscorp.evetool.service.IserviceRole;
import com.rectuscorp.evetool.service.IserviceUser;
import com.rectuscorp.evetool.web.component.BootStrapFeedbackPanel.BootStrapFeedbackPanel;
import org.apache.logging.log4j.Logger; import org.apache.logging.log4j.LogManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: rectus_29
 * Date: 20 janv. 2010
 * Time: 09:12:37
 */
public class UserEditPanel extends Panel {

	private static final Logger log = LogManager.getLogger(UserEditPanel.class);

	public Role role = null;

	@SpringBean(name = "serviceUser")
	private IserviceUser serviceUser;

	@SpringBean(name = "serviceRole")
	private IserviceRole serviceRole;

	@SpringBean(name = "serviceHistory")
	private IserviceHistory serviceHistory;

	private User user;

	private Form form;

	private BootStrapFeedbackPanel feed;

	private String password;

	public UserEditPanel(String s) {
		super(s);
		this.user = new User();
	}

	public UserEditPanel(String id, IModel<User> model) {
		super(id, model);
		this.user = model.getObject();
		this.role = user.getRole();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add((form = new Form("form") {
			@Override
			protected void onInitialize() {
				super.onInitialize();
				add(new TextField<String>("username", new PropertyModel<String>(user, "userName")).setRequired(true));
				add(new TextField<String>("firstname", new PropertyModel<String>(user, "firstName")).setRequired(true));
				add(new TextField<String>("lastname", new PropertyModel<String>(user, "lastName")).setRequired(true));
				add(new PasswordTextField("password", new PropertyModel<String>(UserEditPanel.this, "password")).setRequired(user.getPassword() == null));
				add(new TextField<String>("mail", new PropertyModel<String>(user, "email")).add(EmailAddressValidator.getInstance()).setRequired(true));
				add(new DropDownChoice<Role>("role", new PropertyModel<Role>(user, "role"), serviceRole.getAuthorizedRole(serviceUser.getCurrentUser()), new ChoiceRenderer<Role>("name")).setRequired(true));
				add(new DropDownChoice<UserAuthentificationType>("auth", new PropertyModel<UserAuthentificationType>(user, "userAuthentificationType"), Arrays.asList(UserAuthentificationType.values()), new EnumChoiceRenderer<UserAuthentificationType>()).setRequired(true));
				add(new AjaxSubmitLink("submit") {
					@Override
					protected void onSubmit(AjaxRequestTarget target, Form form) {
						if (password != null) {
							//Salt
							RandomNumberGenerator rng = new SecureRandomNumberGenerator();
							ByteSource salt = rng.nextBytes();
							user.setSalt(salt.toBase64());
							user.setPassword(new Sha256Hash(password, new SimpleByteSource(Base64.decode(user.getSalt())), 1024).toBase64());
						}
						user = serviceUser.save(user);
						user = new User();
						success(new ResourceModel("success").getObject());
						target.add(form);
						UserEditPanel.this.onSubmit(target);
					}

					@Override
					public void onError(AjaxRequestTarget target, Form<?> form) {
						target.add(feed);
					}

				});
				add(new AjaxLink("cancel") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						onCancel(target);
					}
				});
				add((feed = new BootStrapFeedbackPanel("smf")).setOutputMarkupId(true));
			}
		}).setOutputMarkupId(true));
	}

	public void onSubmit(AjaxRequestTarget target) {

	}

	public void onCancel(AjaxRequestTarget target) {

	}
}
