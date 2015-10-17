package gui;

import data.Grid;
import game.Minesweeper;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Customize extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JPanel modifyPanel; //panel to hold all the labels and TextFields
	private JLabel heightLabel, widthLabel, minesLabel; //labels for the fields
	private JTextField heightField, widthField, minesField; //text fields
	private JButton done; //done button
	
	private Console parentWindow; //window from which the Customize window was initiated
	
	public Customize(Console parentWindow) //constructor
	{
		final int fieldHeight = 30, fieldWidth = 50; //dimensions of a text field
		final int labelWidth = 80; //dimensions of a label
		
		this.parentWindow = parentWindow;
		
		this.setLocation((int)parentWindow.getLocationOnScreen().x + 90, (int)parentWindow.getLocationOnScreen().y + 90); //set the location of this Customize window to in the middle of the parent window
		
		modifyPanel = new JPanel(); //initialize the JPanel
		modifyPanel.setSize(labelWidth + fieldWidth + 20, 3 * fieldHeight + 20); //set the size of the JPanel appropriately
		modifyPanel.setLayout(new FlowLayout()); //set the layout of the jpanel to flow
		
		heightLabel = new JLabel("Height:"); //initialize Height label
		widthLabel = new JLabel("Width:"); //initialize Width label
		minesLabel = new JLabel("Mines:"); //initialize Mines label
		heightField = new JTextField(String.valueOf(Grid.height)); //initialize Height text field with text as current Console height
		widthField = new JTextField(String.valueOf(Grid.width)); //initialize Height text field with text as current Console width
		minesField = new JTextField(String.valueOf(Grid.mines)); //initialize Height text field with text as current number of mines

		done= new JButton("Done"); //initialize done button
		
		modifyPanel.add(heightLabel); //add the height label to the modifyPanel
		heightLabel.setPreferredSize(new Dimension(labelWidth, fieldHeight)); //set the size of the height label
		modifyPanel.add(heightField); //add the height field to the modifyPanel
		heightField.setPreferredSize(new Dimension(fieldWidth, fieldHeight)); //set the size of the height field
		
		modifyPanel.add(widthLabel); //add the width label to the modifyPanel
		widthLabel.setPreferredSize(new Dimension(labelWidth, fieldHeight)); //set the size of the width label
		modifyPanel.add(widthField); //add the width field to the modifyPanel //set the size of the width field
		widthField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		
		modifyPanel.add(minesLabel); //add the mines label to the modifyPanel
		minesLabel.setPreferredSize(new Dimension(labelWidth, fieldHeight)); //set the size of the mines label
		modifyPanel.add(minesField); //add the mines field to the modifyPanel
		minesField.setPreferredSize(new Dimension(fieldWidth, fieldHeight)); //set the size of the mines field
		
		add(modifyPanel); //add the JPanel to the JFrame
		add(done); //add the Done button to JFrame
		done.setBounds(modifyPanel.getWidth() / 2 - labelWidth /2, modifyPanel.getHeight() + 10, labelWidth, fieldHeight); //set the position of the 'Done' button
		
		add(new JLabel()); //add a void JLabel to not mess up the position of the 'Done' button
		
		done.addActionListener(this); //make the Done button clickable
		
		setTitle("Edit"); //set the title as "Edit"
		setSize(modifyPanel.getWidth(), modifyPanel.getHeight() + 50); //set the size of the console
		setVisible(true); //make the console visible
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Grid.width = Integer.valueOf(widthField.getText()); //set the width of the grid to whatever was entered in the textField
		Grid.height = Integer.valueOf(heightField.getText()); //set the height of the grid to whatever was entered in the textField
		Grid.mines = Integer.valueOf(minesField.getText()); //set the number of mines in the grid to whatever was entered in the textField
		parentWindow.dispose(); //close the game with previous specs
		this.dispose(); //close the customize window
		new Minesweeper(); //start a new game with the new specifications
	}
}