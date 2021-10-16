package alrhal_almky;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;


public class SoundHandler {
   static MediaPlayer player;
static Media pick;

static Status status = Status.STOPPED;

 static HashMap<String, String> soundsHashMap = new HashMap<String, String>();
 static{
     soundsHashMap = new HashMap<>();
     soundsHashMap.put("startPage", "startPage.mp3");
     soundsHashMap.put("instructionInMap", "instructionInMap.mp3");
     soundsHashMap.put("instructionInAlharam", "instructionInAlharam.mp3");
     soundsHashMap.put("lossFirstLevel", "lossFirstLevel.mp3");
     soundsHashMap.put("lossSecondLevel", "lossSecondLevel.mp3");
     soundsHashMap.put("instructionInMap", "instructionInMap.mp3");
     soundsHashMap.put("winFirstLevel", "winFirstLevel.mp3");
     soundsHashMap.put("winSecondLevel", "winSecondLevel.mp3");
     soundsHashMap.put("haramInfo", "haramInfo.mp3");
     soundsHashMap.put("kaaba", "kaaba.mp3");
     soundsHashMap.put("sahn", "sahn.mp3");
     soundsHashMap.put("makam", "makam.mp3");
     soundsHashMap.put("zamzam", "zamzam.mp3");
     soundsHashMap.put("great", "great.mp3");
     soundsHashMap.put("amazing", "amazing.mp3");
     soundsHashMap.put("good", "good.mp3");
     soundsHashMap.put("excellent", "excellent.mp3");
     
     soundsHashMap.put("menaInfo", "menaInfo.mp3");
     soundsHashMap.put("wosta", "wosta.mp3");
     soundsHashMap.put("soghra", "soghra.mp3");
     soundsHashMap.put("kobra", "kobra.mp3");
     soundsHashMap.put("khema", "khema.mp3");
     soundsHashMap.put("hajar", "hajar.mp3");
     soundsHashMap.put("haj", "haj.mp3");
     soundsHashMap.put("congrats", "congrats.mp3");
 
 }

public void playSound(String soundName) {

    switch(status)
    {
        case STOPPED:
            pick = new Media(new File(soundsHashMap.get(soundName)).toURI().toString());
            player = new MediaPlayer(pick);
            player.play();
            status = Status.PLAYING;
            break;
        case PLAYING:
            player.stop();
            pick = new Media(new File(soundsHashMap.get(soundName)).toURI().toString());
            player = new MediaPlayer(pick);
            player.play();
            status = Status.PLAYING;
            break;                
    }
} 
}
