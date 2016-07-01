import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechTimestamp;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;

import java.io.File;

public class Main {
  public static void main(String[] args) {
    SpeechToText service = new SpeechToText();
    service.setUsernameAndPassword(Credentials.USERNAME, Credentials.PASSWORD);

    RecognizeOptions.Builder builder = new RecognizeOptions.Builder();
    builder.interimResults(true);
    builder.contentType(HttpMediaType.AUDIO_WAV);
    builder.model("pt-BR_BroadbandModel");
    builder.timestamps(true);
    RecognizeOptions options = builder.build();
    File audio = new File("sample1.wav");
    SpeechResults transcript = service.recognize(audio, options).execute();
    String traducao = null;
    for(Transcript tsc : transcript.getResults()){
      for(SpeechAlternative alt : tsc.getAlternatives()){
        for(SpeechTimestamp time : alt.getTimestamps()){
          System.out.println("ini:"+time.getStartTime() + ", fim:"+time.getEndTime()+", texto:"+time.getWord());
        }
        traducao = alt.getTranscript();
      }
    }
    System.out.println(traducao);
  }
}
