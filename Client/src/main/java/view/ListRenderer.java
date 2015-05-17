package view;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Model;
import model.User;
import model.ViewConstraints;

/**
 * Contains the ListCellRenderer creator for the UserBoards JList 
 */
public class ListRenderer {
	
	/**
	 * Returns the created listCellRenderer based on the given data model
	 * @param model
	 * @return The label for the respective list item
	 */
	public static ListCellRenderer<User> createListRenderer(final Model model) {
		return new ListCellRenderer<User>() {			

			@Override
			public Component getListCellRendererComponent(
					JList<? extends User> list, User value, int index,
					boolean isSelected, boolean cellHasFocus) {

				// Creating the label
				JLabel label = new JLabel();							
				label.setText("  "+value.getUserName());
				
				// Set basic style
				label.setBorder(BorderFactory.createCompoundBorder(ViewConstraints.USER_BORDER,ViewConstraints.PADDING_BORDER));				
				label.setFont(ViewConstraints.USER_NAME_LABLE_FONT);
				label.setOpaque(true);
				
				if (model.getOnlineUsers().contains(value)||value.getUserName().equals(ViewConstraints.PUBIC_USER_NAME)) {
					
					// THE User is ONLINE
					label.setText("  "+value.getUserName()+ "    [online]");
					label.setBackground(ViewConstraints.USER_ONLINE_COLOR);
					
				}else{
					
					//THE User is OFFLINE
					label.setText("  "+value.getUserName()+ "    [offline]");
					label.setBackground(ViewConstraints.USER_OFFLINE_COLOR);
					
				}
				if (model.getAuthUser().getUserName()!= null && model.getAuthUser().getUserName().equals(value.getUserName())){
					
					//THE PUBLIC CHAT is always ONLINE
					label.setText("  "+value.getUserName()+ "  [You are online]");
					label.setBackground(ViewConstraints.USER_AUTH_COLOR);
					
				}
				if (isSelected) {			
					
					//THE User is SELECTED					
					label.setText("  "+value.getUserName()+ "    [selected]");
					label.setBackground(ViewConstraints.USER_SELECTED_COLOR);
					
				}
				
				return label;				
			}
		};
	}
}
