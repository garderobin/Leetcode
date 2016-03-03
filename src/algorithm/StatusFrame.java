package algorithm;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class StatusFrame extends JFrame {
	
	JPanel myPanel;

		   public StatusFrame() {

		       // init frame

		       setVisible(true);

		   }     

		   public void showHalfWayDoneProgress() {

		       myPanel.add(new JLabel("50%"));

		   }

		}

		 

		



class ApplicationThread extends Thread {

	   public void run () {

	       StatusFrame frame = new StatusFrame();      

	       // do some CPU intensive computations

//	       frame.showHalfWayDoneProgress();      

	       // do more CPU intensive computations
//	       SwingUtilities.invokeLater(new Runnable() { 
//	    	    public void run() { 
//	    	        frame.showHalfWayDoneProgress(); 
//	    	    } 
//	    	});
	       

	   }

	} 
