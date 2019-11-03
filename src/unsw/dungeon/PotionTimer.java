package unsw.dungeon;


import java.util.TimerTask;




class PotionTimer extends TimerTask {

    int countdown = 5;

    public void run() {
        countdown = countdown - 1;
        //System.out.println(countdown);
        //label.setText(countdown +"second's left");
    }

}