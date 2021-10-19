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
     soundsHashMap.put("startPage", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/startPage.mp3");
     soundsHashMap.put("instructionInMap", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/instructionInMap.mp3");
     soundsHashMap.put("instructionInAlharam", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/instructionInAlharam.mp3");
     soundsHashMap.put("lossFirstLevel", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/lossFirstLevel.mp3");
     soundsHashMap.put("lossSecondLevel", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/lossSecondLevel.mp3");
     soundsHashMap.put("instructionInMap", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/instructionInMap.mp3");
     soundsHashMap.put("winFirstLevel", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/winFirstLevel.mp3");
     soundsHashMap.put("winSecondLevel", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/winSecondLevel.mp3");
     soundsHashMap.put("haramInfo", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/haramInfo.mp3");
     soundsHashMap.put("kaaba", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/kaaba.mp3");
     soundsHashMap.put("sahn", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/sahn.mp3");
     soundsHashMap.put("makam", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/makam.mp3");
     soundsHashMap.put("zamzam", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/zamzam.mp3");
     soundsHashMap.put("great", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/great.mp3");
     soundsHashMap.put("amazing", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/amazing.mp3");
     soundsHashMap.put("good", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/good.mp3");
     soundsHashMap.put("excellent", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/excellent.mp3");
     soundsHashMap.put("menaInfo", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/menaInfo.mp3");
     soundsHashMap.put("wosta", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/wosta.mp3");
     soundsHashMap.put("soghra", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/soghra.mp3");
     soundsHashMap.put("kobra", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/kobra.mp3");
     soundsHashMap.put("khema", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/khema.mp3");
     soundsHashMap.put("hajar", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/hajar.mp3");
     soundsHashMap.put("haj", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/haj.mp3");
     soundsHashMap.put("congrats", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/congrats.mp3");
     soundsHashMap.put("silent", "https://safaaqutub18.github.io/Al-rahal_Al-maky-game/silent.mp3");
     
 }

public void playSound(String soundName) {

    switch(status)
    {
        case STOPPED:
            //pick = new Media(new File(soundsHashMap.get(soundName)).toURI().toString());
            pick = new Media(soundsHashMap.get(soundName));
            player = new MediaPlayer(pick);
            player.play();
            status = Status.PLAYING;
            break;
        case PLAYING:
            player.stop();
            //pick = new Media(new File(soundsHashMap.get(soundName)).toURI().toString());
            pick = new Media(soundsHashMap.get(soundName));
            player = new MediaPlayer(pick);
            player.play();
            status = Status.PLAYING;
            break;                
    }
} 
}
