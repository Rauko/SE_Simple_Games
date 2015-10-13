package arcada.race;


import javax.swing.*;

public class SimpleRace {

	public static void main(String[] args) {
		window();
	}
	
	public static void window(){
		JFrame frame = new JFrame("F22. Air race on JAVA");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		
		frame.add(new Sky());
		
		
		frame.setVisible(true);
	}
}
