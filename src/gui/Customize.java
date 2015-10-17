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
	
	private JPanel modifyPanel;
	private JLabel heightLabel, widthLabel, minesLabel;
	private JTextField heightField, widthField, minesField;
	private JButton done;
	
	private Console parentWindow;
	
	public Customize(Console parentWindow)
	{
		final int fieldHeight = 30, fieldWidth = 50;
		final int labelWidth = 80;
		
		this.parentWindow = parentWindow;
		
		this.setLocation((int)parentWindow.getLocationOnScreen().x + 90, (int)parentWindow.getLocationOnScreen().y + 90);
		
		modifyPanel = new JPanel();
		modifyPanel.setSize(labelWidth + fieldWidth + 20, 3 * fieldHeight + 20);
		modifyPanel.setLayout(new FlowLayout());
		
		heightLabel = new JLabel("Height:");
		widthLabel = new JLabel("Width:");
		minesLabel = new JLabel("Mines:");
		heightField = new JTextField(String.valueOf(Grid.height));
		widthField = new JTextField(String.valueOf(Grid.width));
		minesField = new JTextField(String.valueOf(Grid.mines));

		done= new JButton("Done");
		
		modifyPanel.add(heightLabel);
		heightLabel.setPreferredSize(new Dimension(labelWidth, fieldHeight));
		modifyPanel.add(heightField);
		heightField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		
		modifyPanel.add(widthLabel);
		widthLabel.setPreferredSize(new Dimension(labelWidth, fieldHeight));
		modifyPanel.add(widthField);
		widthField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		
		modifyPanel.add(minesLabel);
		minesLabel.setPreferredSize(new Dimension(labelWidth, fieldHeight));
		modifyPanel.add(minesField);
		minesField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		
		add(modifyPanel);
		add(done);
		done.setBounds(modifyPanel.getWidth() / 2 - labelWidth /2, modifyPanel.getHeight() + 10, labelWidth, fieldHeight);
		
		add(new JLabel());
		
		done.addActionListener(this);
		
		setTitle("Edit");
		setSize(modifyPanel.getWidth(), modifyPanel.getHeight() + 50);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Grid.width = Integer.valueOf(widthField.getText());
		Grid.height = Integer.valueOf(heightField.getText());
		Grid.mines = Integer.valueOf(minesField.getText());
		parentWindow.dispose();
		this.dispose();
		new Minesweeper();
	}
}