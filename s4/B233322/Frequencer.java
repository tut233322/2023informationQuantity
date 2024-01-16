package s4.B233322; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import java.util.Arrays;
import s4.specification.*;

/*
interface FrequencerInterface {  // This interface provides the design for frequency counter.
    void setTarget(byte[] target);  // set the data to search.
    void setSpace(byte[] space);  // set the data to be searched target from.
    int frequency(); // It return -1, when TARGET is not set or TARGET's length is zero
                     // Otherwise, it return 0, when SPACE is not set or Space's length is zero
                     // Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e. target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/


public class Frequencer implements FrequencerInterface {
    // Code to Test, *warning: This code contains intentional problem*
    static boolean debugMode = false;
    byte[] myTarget;
    byte[] mySpace;

    @Override
    public void setTarget(byte[] target) {
        myTarget = target;
    }
    @Override
    public void setSpace(byte[] space) {
        mySpace = space;
    }

    private void showVariables() {
	for(int i=0; i< mySpace.length; i++) { System.out.write(mySpace[i]); }
	System.out.write(' ');
	for(int i=0; i< myTarget.length; i++) { System.out.write(myTarget[i]); }
	System.out.write(' ');
    }

    private int frequencyImpl(int targetStart, int targetEnd) {
        int targetLength = this.myTarget.length;
        int spaceLength = this.mySpace.length;
	
        int count = 0;
	if(debugMode) { showVariables(); }
        for(int j = 0; j + targetLength - 1 < spaceLength; j++) { // Is it OK?
            boolean abort = false;
            for(int i = targetStart; i < targetEnd; i++) {
                if(this.myTarget[i] != this.mySpace[j+(i-targetStart)]) { abort = true; break; }
            }
            if(abort == false) { count++; }
        }
	if(debugMode) { System.out.printf("%10d\n", count); }
	return count;
    }
    
    @Override
    public int frequency() {
	if (this.myTarget == null || this.myTarget.length == 0) { return -1; }
	if (this.mySpace == null || this.mySpace.length == 0) { return 0; }
        return frequencyImpl(0, myTarget.length);
    }

    @Override
    public int subByteFrequency(int start, int end) {
	/*  I don't know how behave when null or length is zero (cause not indicated)
 	    maybe these (like frequency)?
	if (this.myTarget == null || this.myTarget.length == 0) { return -1; }
	if (this.mySpace == null || this.mySpace.length == 0) { return 0; }  */
        return this.frequencyImpl(start, end);
    }

    public static void main(String[] args) {
        Frequencer myObject;
        int freq;
	// White box test, here.
	debugMode = true;
        try {
            myObject = new Frequencer();
            myObject.setSpace("Hi Ho Hi Ho".getBytes());
            myObject.setTarget("H".getBytes());
            freq = myObject.frequency();
        }
        catch(Exception e) {
            System.out.println("Exception occurred: STOP");
        }
    }
}
