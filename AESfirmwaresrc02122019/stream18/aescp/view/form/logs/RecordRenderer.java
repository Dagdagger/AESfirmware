package stream18.aescp.view.form.logs;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import stream18.aescp.Browser;
import stream18.aescp.model.LogRecordBean;

public class RecordRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getListCellRendererComponent(JList<?> list,
												Object value,
												int index,
												boolean isSelected,
												boolean cellHasFocus) {
		LogRecordBean logRecord = (LogRecordBean) value;
		
		setText(String.format("%-10s| %s" , logRecord.getTimestamp(), logRecord.getAction()));
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
