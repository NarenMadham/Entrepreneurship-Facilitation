package comparator;

import java.util.Comparator;

import beans.Message;

public class SortMessagesByDate implements Comparator<Message>{

	@Override
	public int compare(Message o1, Message o2) {
		return o1.getTime().compareTo(o2.getTime());
	} 
}
