package stream18.aescp.view.form.system;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import stream18.aescp.Browser;

public class ShowLogsRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getListCellRendererComponent(JList<?> list,
												Object value,
												int index,
												boolean isSelected,
												boolean cellHasFocus) {
		String logfile = (String) value;
		
		setText(logfile);
		setFont(Browser.FONT);
		
        if(isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
        	setBackground(list.getBackground());
        	setForeground(list.getForeground());
        }
		return this;
	}

}
