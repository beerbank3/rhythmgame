package rhythm_game_1;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

public class setvolume {

	public static float volume = (float) 0.05;

	public static void setvolume(float volume) throws Exception{
		Mixer.Info[] infos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : infos) {
			Mixer mixer = AudioSystem.getMixer(info);
			if (mixer.isLineSupported(Port.Info.SPEAKER)) {
				Port port = (Port)mixer.getLine(Port.Info.SPEAKER);
				port.open();
				if (port.isControlSupported(FloatControl.Type.VOLUME)) {
					FloatControl volCtrl  = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
					//System.out.println(info);
					//System.out.println("- " + Port.Info.SPEAKER);
					volCtrl.setValue(volume);
				}
				port.close();
			}
		}
	}

	public static void main(String[] args) throws Exception{
		Mixer.Info[] infos = AudioSystem.getMixerInfo();
		for (Mixer.Info info : infos) {
			Mixer mixer = AudioSystem.getMixer(info);
			if (mixer.isLineSupported(Port.Info.SPEAKER)) {
				Port port = (Port)mixer.getLine(Port.Info.SPEAKER);
				port.open();
				if (port.isControlSupported(FloatControl.Type.VOLUME)) {
					FloatControl volume = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
					//System.out.println(info);
					//System.out.println("- " + Port.Info.SPEAKER);
					volume.setValue((float)0.01);
					System.out.println("  - " + volume);
				}
				port.close();
			}
		}
	}
}
