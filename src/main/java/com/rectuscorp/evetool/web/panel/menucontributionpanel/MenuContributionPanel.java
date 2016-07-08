package com.rectuscorp.evetool.web.panel.menucontributionpanel;

import com.rectuscorp.evetool.web.page.IMenuContributor;
import org.apache.wicket.markup.html.panel.Panel;

import javax.swing.*;
import java.util.List;
/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 30/06/2016 11:59                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public class MenuContributionPanel extends Panel {

	public MenuContributionPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		if(getPage() instanceof IMenuContributor){
			List<MenuElement> menuElements = ((IMenuContributor) getPage()).getMenuContribution();
			for(MenuElement el : menuElements){

			}

		}
	}
}
