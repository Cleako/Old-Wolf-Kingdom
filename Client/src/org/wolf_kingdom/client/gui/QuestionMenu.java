package org.wolf_kingdom.client.gui;

import org.wolf_kingdom.client.mudclient;
import org.wolf_kingdom.client.interfaces.InterfaceGUI;
import org.wolf_kingdom.client.interfaces.models.Callback;
import org.wolf_kingdom.client.interfaces.models.ILabel;
import org.wolf_kingdom.client.interfaces.models.OnMisClickCallBack;
import org.wolf_kingdom.client.interfaces.models.RSGuiModels;

public class QuestionMenu {
	
	public InterfaceGUI i;
	
	public mudclient mc;
	
	public QuestionMenu(final mudclient mc) {
	
		this.mc = mc;
		
		OnMisClickCallBack onMisClick = new OnMisClickCallBack() {

			@Override
			public void run() {
				//mc.sendSmithingClose();
			}
			
		};
		Callback onclose = new Callback() {

			@Override
			public void run(InterfaceGUI gui, RSGuiModels id) {
				//mc.sendSmithingClose();
				gui.isVisible = false;
			}
			
		};
		/**
		 * Greates a new interface with a 450x250 panel
		 */
		i = new InterfaceGUI(370,180,mc);
		i.titlebar.label = "Please select an option!";
		i.titlebar.x = 60;
		i.xbutton.callback = onclose;
		i.outsideclick = onMisClick;
		/**
		 * Adds the interface to a list
		 */
		mudclient.getInterfaceHandler().guis.add(i);
		
	}
	public void parseQuestions(String[] questionMenuAnswer, int newQuestionMenuCount) {
		i.isVisible = true;
		i.setHeight(newQuestionMenuCount*20+5);
		i.clearLabels();
		
		for (int i = 0; i < newQuestionMenuCount; i++) {
			final int clickId = i;
			Callback onclose = new Callback() {

				@Override
				public void run(InterfaceGUI gui, RSGuiModels id) {
					mc.handleQuestionMenuAnswer(clickId);
				}
				
			};
			ILabel x = this.i.addLabel(questionMenuAnswer[i], 10, 5+ 20*i, onclose, 1);
			
		}
	}
	public void close() {
		this.i.isVisible = false;
		
	}
}
